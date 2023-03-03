package softuni.exam.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.OfferExportDTO;
import softuni.exam.models.dto.OfferXmlImportDTO;
import softuni.exam.models.dto.OfferXmlImportDTOs;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.FilePaths;
import softuni.exam.util.Messages;
import softuni.exam.util.ValidationUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final AgentRepository agentRepository;
    private final ApartmentRepository apartmentRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(FilePaths.OFFERS_XML_PATH);
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        BufferedReader bufferedReader = Files.newBufferedReader(FilePaths.OFFERS_XML_PATH);

        JAXBContext context = JAXBContext.newInstance(OfferXmlImportDTOs.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        OfferXmlImportDTOs offers = (OfferXmlImportDTOs) unmarshaller.unmarshal(bufferedReader);

        for (OfferXmlImportDTO offer : offers.getOffers()) {
            if (validationUtil.isValid(offer) && agentExists(offer.getAgent().getName())){

                Offer mappedOffer = mapper.map(offer, Offer.class);
                mappedOffer.setAgent(this.agentRepository.findAgentByFirstName(offer.getAgent().getName()));
                mappedOffer.setApartment(this.apartmentRepository.findApartmentById(offer.getApartment().getId()));
                mappedOffer.setPublishedOn(LocalDate.parse(offer.getPublishedOn(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                this.offerRepository.save(mappedOffer);

                stringBuilder.append(String.format(Messages.SUCCESSFULLY_IMPORTED_OFFER
                        ,offer.getPrice()));
            } else {

                stringBuilder.append(Messages.INVALID_OFFER);
            }

            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    private boolean agentExists(String name) {
        return this.agentRepository.findAgentByFirstName(name) != null;
    }

    @Override
    public String exportOffers() {
        StringBuilder stringBuilder = new StringBuilder();

        List<Offer> bestOffers = this.offerRepository.findBestOffers();

        bestOffers.stream()
                .map(offer -> mapper.map(offer, OfferExportDTO.class))
                .forEach(offerExportDTO -> stringBuilder.append(offerExportDTO.toString()));

        return stringBuilder.toString();
    }
}
