package softuni.exam.service.impl;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.importDTOs.json.MechanicJsonImportDTO;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.service.MechanicService;
import softuni.exam.util.FilePaths;
import softuni.exam.util.Messages;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;

@Service
@AllArgsConstructor
public class MechanicServiceImpl implements MechanicService {
    private final MechanicRepository mechanicRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Override
    public boolean areImported() {
        return this.mechanicRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        return Files.readString(FilePaths.MECHANICS_JSON_PATH);
    }

    @Override
    public String importMechanics() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        MechanicJsonImportDTO[] mechanics = gson.fromJson(this.readMechanicsFromFile(), MechanicJsonImportDTO[].class);

        for (MechanicJsonImportDTO mechanic : mechanics) {
            if (validationUtil.isValid(mechanic) && isUnique(mechanic.getEmail())){

                Mechanic mappedMechanic = mapper.map(mechanic, Mechanic.class);

                this.mechanicRepository.save(mappedMechanic);

                stringBuilder.append(String.format(Messages.SUCCESSFULLY_IMPORTED_MECHANIC
                        ,mechanic.getFirstName(),mechanic.getLastName()));
            } else {

                stringBuilder.append(Messages.INVALID_MECHANIC);
            }

            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    private boolean isUnique(String email) {
        return this.mechanicRepository.findMechanicByEmail(email) == null;
    }
}
