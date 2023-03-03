package com.example.football.service.impl;

import com.example.football.models.dto.TownJsonImportDTO;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.example.football.util.FilePaths;
import com.example.football.util.Messages;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@AllArgsConstructor
@Service
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
            if (validationUtil.isValid(town) && isUnique(town.getName())) {
                Town mappedTown = mapper.map(town, Town.class);

                this.townRepository.save(mappedTown);

                stringBuilder.append(String.format(Messages.SUCCESSFULLY_IMPORTED_TOWN
                        , town.getName(), town.getPopulation()));

            } else {
                stringBuilder.append(Messages.INVALID_TOWN);
            }

            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    private boolean isUnique(String townName) {
        return this.townRepository.findTownByName(townName) == null;
    }
}
