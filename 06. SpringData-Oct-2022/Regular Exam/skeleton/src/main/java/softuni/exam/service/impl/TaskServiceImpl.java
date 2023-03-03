package softuni.exam.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.exportDTOs.TaskExportDTO;
import softuni.exam.models.dto.importDTOs.xml.MechanicXmlImportDTO;
import softuni.exam.models.dto.importDTOs.xml.TaskXmlImportDTO;
import softuni.exam.models.dto.importDTOs.xml.TaskXmlImportDTOs;
import softuni.exam.models.entity.Task;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.repository.PartRepository;
import softuni.exam.repository.TaskRepository;
import softuni.exam.service.TaskService;
import softuni.exam.util.FilePaths;
import softuni.exam.util.Messages;
import softuni.exam.util.ValidationUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final CarRepository carRepository;
    private final MechanicRepository mechanicRepository;
    private final PartRepository partRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;

    @Override
    public boolean areImported() {
        return this.taskRepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return Files.readString(FilePaths.TASKS_XML_PATH);
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        BufferedReader bufferedReader = Files.newBufferedReader(FilePaths.TASKS_XML_PATH);

        JAXBContext context = JAXBContext.newInstance(TaskXmlImportDTOs.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        TaskXmlImportDTOs tasks = (TaskXmlImportDTOs) unmarshaller.unmarshal(bufferedReader);

        for (TaskXmlImportDTO task : tasks.getTasks()) {
            if (validationUtil.isValid(task) && mechanicExists(task.getMechanic())){

                Task mappedTask = mapper.map(task, Task.class);
                mappedTask.setCar(this.carRepository.findCarById(task.getCar().getId()));
                mappedTask.setMechanic(this.mechanicRepository.findMechanicByFirstName(task.getMechanic().getFirstName()));
                mappedTask.setPart(this.partRepository.findPartById(task.getPart().getId()));
                mappedTask.setDate(LocalDateTime.parse(task.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

                this.taskRepository.save(mappedTask);

                stringBuilder.append(String.format(Messages.SUCCESSFULLY_IMPORTED_TASK
                                                    ,task.getPrice()));
            } else {

                stringBuilder.append(Messages.INVALID_TASK);
            }

            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    private boolean mechanicExists(MechanicXmlImportDTO mechanic) {
        return this.mechanicRepository.findMechanicByFirstName(mechanic.getFirstName()) != null;
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {
        StringBuilder stringBuilder = new StringBuilder();

        List<Task> highestPricedTasks = this.taskRepository.findHighestPricedTasks();

        highestPricedTasks.stream()
                .map(task -> mapper.map(task, TaskExportDTO.class))
                .forEach(taskExportDTO -> stringBuilder.append(taskExportDTO.toString()));

        return stringBuilder.toString();
    }
}
