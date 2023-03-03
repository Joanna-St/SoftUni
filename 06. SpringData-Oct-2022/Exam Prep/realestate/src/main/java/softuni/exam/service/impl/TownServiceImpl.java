package softuni.exam.service.impl;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TownJsonImportDTO;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.FilePaths;
import softuni.exam.util.Messages;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;

@Service
@AllArgsConstructor
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final ModelMapper mapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(FilePaths.TOWNS_JSON_PATH);
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        TownJsonImportDTO[] towns = gson.fromJson(this.readTownsFileContent(), TownJsonImportDTO[].class);

        for (TownJsonImportDTO town : towns) {
            if (validationUtil.isValid(town) && isUnique(town.getTownName())){
                Town mappedTown = mapper.map(town, Town.class);

                this.townRepository.save(mappedTown);

                stringBuilder.append(String.format(Messages.SUCCESSFULLY_IMPORTED_TOWN
                        ,town.getTownName(),town.getPopulation()));
            } else {

                stringBuilder.append(Messages.INVALID_TOWN);
            }

            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    private boolean isUnique(String townName) {
        return this.townRepository.findTownByTownName(townName) == null;
    }
}
