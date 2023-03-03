package softuni.exam.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ApartmentXmlImportDTO;
import softuni.exam.models.dto.ApartmentXmlImportDTOs;
import softuni.exam.models.entity.Apartment;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.ApartmentService;
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
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final TownRepository townRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;

    @Override
    public boolean areImported() {
        return this.apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return Files.readString(FilePaths.APARTMENTS_XML_PATH);
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        BufferedReader bufferedReader = Files.newBufferedReader(FilePaths.APARTMENTS_XML_PATH);

        JAXBContext context = JAXBContext.newInstance(ApartmentXmlImportDTOs.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ApartmentXmlImportDTOs apartments = (ApartmentXmlImportDTOs) unmarshaller.unmarshal(bufferedReader);

        for (ApartmentXmlImportDTO apartment : apartments.getApartments()) {
            if (validationUtil.isValid(apartment) && isUnique(apartment)) {

                Apartment mappedApartment = mapper.map(apartment, Apartment.class);
                mappedApartment.setTown(this.townRepository.findTownByTownName(apartment.getTown()));

                this.apartmentRepository.save(mappedApartment);

                stringBuilder.append(String.format(Messages.SUCCESSFULLY_IMPORTED_APARTMENT
                        , apartment.getApartmentType(), apartment.getArea()));
            } else {

                stringBuilder.append(Messages.INVALID_APARTMENT);
            }

            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    private boolean isUnique(ApartmentXmlImportDTO apartment) {
        return this.apartmentRepository
                .findApartmentByAreaAndTown_TownName(apartment.getArea(), apartment.getTown()) == null;
    }
}
