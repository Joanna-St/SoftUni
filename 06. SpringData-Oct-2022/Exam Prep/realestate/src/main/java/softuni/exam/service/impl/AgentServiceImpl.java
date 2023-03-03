package softuni.exam.service.impl;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AgentJsonImportDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.AgentService;
import softuni.exam.util.FilePaths;
import softuni.exam.util.Messages;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;

@Service
@AllArgsConstructor
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;
    private final TownRepository townRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Override
    public boolean areImported() {
        return this.agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return Files.readString(FilePaths.AGENTS_JSON_PATH);
    }

    @Override
    public String importAgents() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        AgentJsonImportDTO[] agents = gson.fromJson(this.readAgentsFromFile(), AgentJsonImportDTO[].class);

        for (AgentJsonImportDTO agent : agents) {
            if (validationUtil.isValid(agent) && isUnique(agent.getFirstName())){
                Agent mappedAgent = mapper.map(agent, Agent.class);
                mappedAgent.setTown(this.townRepository.findTownByTownName(agent.getTown()));

                this.agentRepository.save(mappedAgent);

                stringBuilder.append(String.format(Messages.SUCCESSFULLY_IMPORTED_AGENT
                        ,agent.getFirstName(),agent.getLastName()));
            } else {

                stringBuilder.append(Messages.INVALID_AGENT);
            }

            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    private boolean isUnique(String firstName) {
        return this.agentRepository.findAgentByFirstName(firstName) == null;
    }
}
