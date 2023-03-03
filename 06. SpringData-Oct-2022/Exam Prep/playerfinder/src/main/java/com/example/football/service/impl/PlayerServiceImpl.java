package com.example.football.service.impl;

import com.example.football.models.dto.PlayerExportDTO;
import com.example.football.models.dto.PlayerXmlImportDTO;
import com.example.football.models.dto.PlayerXmlImportDTOs;
import com.example.football.models.entity.Player;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import com.example.football.util.FilePaths;
import com.example.football.util.Messages;
import com.example.football.util.ValidationUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TownRepository townRepository;
    private final TeamRepository teamRepository;
    private final StatRepository statRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(FilePaths.PLAYERS_XML_PATH);
    }

    @Override
    public String importPlayers() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        BufferedReader bufferedReader = Files.newBufferedReader(FilePaths.PLAYERS_XML_PATH);

        JAXBContext playerImportContext = JAXBContext.newInstance(PlayerXmlImportDTOs.class);
        Unmarshaller unmarshaller = playerImportContext.createUnmarshaller();

        PlayerXmlImportDTOs players = (PlayerXmlImportDTOs) unmarshaller.unmarshal(bufferedReader);

        for (PlayerXmlImportDTO player : players.getPlayers()) {
            if (validationUtil.isValid(player) && isUnique(player.getEmail())){
                Player mappedPlayer = mapper.map(player, Player.class);

                mappedPlayer.setBirthDate(LocalDate.parse(player.getBirthDate()
                        ,DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                mappedPlayer.setTown(this.townRepository.findTownByName(player.getTown().getName()));
                mappedPlayer.setTeam(this.teamRepository.findTeamByName(player.getTeam().getName()));
                mappedPlayer.setStat(this.statRepository.findStatById(player.getStat().getId()));

                this.playerRepository.save(mappedPlayer);

                stringBuilder.append(String.format(Messages.SUCCESSFULLY_IMPORTED_PLAYER
                        ,player.getFirstName(),player.getLastName(),player.getPosition()));
            } else {

                stringBuilder.append(Messages.INVALID_PLAYER);
            }

            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    private boolean isUnique(String email) {
        return this.playerRepository.findPlayerByEmail(email) == null;
    }

    @Override
    public String exportBestPlayers() {
        StringBuilder stringBuilder = new StringBuilder();

        List<Player> bestPlayers = this.playerRepository.findBestPlayers();

        bestPlayers.stream()
                .map(player -> mapper.map(player, PlayerExportDTO.class))
                .forEach(playerExportDTO -> stringBuilder.append(playerExportDTO.toString()));

        return stringBuilder.toString();
    }
}
