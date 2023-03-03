package softuni.exam.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.importDTOs.xml.CarXmlImportDTO;
import softuni.exam.models.dto.importDTOs.xml.CarXmlImportDTOs;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.FilePaths;
import softuni.exam.util.Messages;
import softuni.exam.util.ValidationUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return Files.readString(FilePaths.CARS_XML_PATH);
    }

    @Override
    public String importCars() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        BufferedReader bufferedReader = Files.newBufferedReader(FilePaths.CARS_XML_PATH);

        JAXBContext context = JAXBContext.newInstance(CarXmlImportDTOs.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        CarXmlImportDTOs cars = (CarXmlImportDTOs) unmarshaller.unmarshal(bufferedReader);

        for (CarXmlImportDTO car : cars.getCars()) {
            if (validationUtil.isValid(car) && isUnique(car.getPlateNumber())){

                Car mappedCar = mapper.map(car, Car.class);

                this.carRepository.save(mappedCar);

                stringBuilder.append(String.format(Messages.SUCCESSFULLY_IMPORTED_CAR
                        ,car.getCarMake(), car.getCarModel()));
            } else {

                stringBuilder.append(Messages.INVALID_CAR);
            }

            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    private boolean isUnique(String plateNumber) {
        return this.carRepository.findCarByPlateNumber(plateNumber) == null;
    }
}
