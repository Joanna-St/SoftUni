package com.example.football.service.impl;

import com.example.football.models.dto.StatXmlImportDTO;
import com.example.football.models.dto.StatXmlImportDTOs;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
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

@AllArgsConstructor
@Service
public class StatServiceImpl implements StatService {

    private final StatRepository statRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;

    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(FilePaths.STATS_XML_PATH);
    }

    @Override
    public String importStats() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        BufferedReader bufferedReader = Files.newBufferedReader(FilePaths.STATS_XML_PATH);

        JAXBContext statImportContext = JAXBContext.newInstance(StatXmlImportDTOs.class);
        Unmarshaller unmarshaller = statImportContext.createUnmarshaller();

        StatXmlImportDTOs stats = (StatXmlImportDTOs) unmarshaller.unmarshal(bufferedReader);

        for (StatXmlImportDTO stat : stats.getStats()) {
            if (validationUtil.isValid(stat) && isUnique(stat)){
                Stat mappedStat = mapper.map(stat, Stat.class);

                this.statRepository.save(mappedStat);

                stringBuilder.append(String.format(Messages.SUCCESSFULLY_IMPORTED_STAT
                        ,stat.getShooting(),stat.getPassing(),stat.getEndurance()));
            } else {

                stringBuilder.append(Messages.INVALID_STAT);
            }

            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    private boolean isUnique(StatXmlImportDTO stat) {
        return this.statRepository.findStatByPassingAndShootingAndEndurance(
                stat.getPassing(),stat.getShooting(),stat.getEndurance()) == null;
    }
}
