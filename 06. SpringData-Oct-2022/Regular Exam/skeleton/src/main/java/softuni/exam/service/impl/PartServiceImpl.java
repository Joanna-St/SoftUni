package softuni.exam.service.impl;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.importDTOs.json.PartJsonImportDTO;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartRepository;
import softuni.exam.service.PartService;
import softuni.exam.util.FilePaths;
import softuni.exam.util.Messages;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;

@Service
@AllArgsConstructor
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Override
    public boolean areImported() {
        return this.partRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        return Files.readString(FilePaths.PARTS_JSON_PATH);
    }

    @Override
    public String importParts() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        PartJsonImportDTO[] parts = gson.fromJson(this.readPartsFileContent(), PartJsonImportDTO[].class);

        for (PartJsonImportDTO part : parts) {
            if (validationUtil.isValid(part) && isUnique(part.getPartName())){

                Part mappedPart = mapper.map(part, Part.class);

                this.partRepository.save(mappedPart);

                stringBuilder.append(String.format(Messages.SUCCESSFULLY_IMPORTED_PART
                                                ,part.getPartName(),part.getPrice()));
            } else {

                stringBuilder.append(Messages.INVALID_PART);
            }

            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    private boolean isUnique(String partName) {
        return this.partRepository.findPartByPartName(partName) == null;
    }
}
