package com.example.football.service.impl;

import com.example.football.models.dto.TeamJsonImportDTO;
import com.example.football.models.entity.Team;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
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
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TownRepository townRepository;
    private final ModelMapper mapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files.readString(FilePaths.TEAMS_JSON_PATH);
    }

    @Override
    public String importTeams() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        TeamJsonImportDTO[] teams = gson.fromJson(this.readTeamsFileContent(), TeamJsonImportDTO[].class);

        for (TeamJsonImportDTO team : teams) {
            if (validationUtil.isValid(team) && isUnique(team.getName())){
                Team mappedTeam = mapper.map(team, Team.class);
                mappedTeam.setTown(this.townRepository.findTownByName(team.getTownName()));

                this.teamRepository.save(mappedTeam);

                stringBuilder.append(String.format(Messages.SUCCESSFULLY_IMPORTED_TEAM
                        ,team.getName(),team.getFanBase()));
            } else {

                stringBuilder.append(Messages.INVALID_TEAM);
            }

            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    private boolean isUnique(String teamName) {
        return this.teamRepository.findTeamByName(teamName) == null;
    }
}
