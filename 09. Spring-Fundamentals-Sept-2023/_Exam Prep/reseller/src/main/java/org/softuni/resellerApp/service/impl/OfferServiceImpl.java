package org.softuni.resellerApp.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.softuni.resellerApp.model.dto.OfferDTO;
import org.softuni.resellerApp.model.entity.Offer;
import org.softuni.resellerApp.repository.OfferRepository;
import org.softuni.resellerApp.repository.UserRepository;
import org.softuni.resellerApp.service.OfferService;
import org.softuni.resellerApp.util.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    @Override
    public List<OfferDTO> getAllOtherOffers() {
        return offerRepository.findAllAvailableAndNotByUser(currentUser.getId())
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<OfferDTO> getUserOffers() {
        return userRepository.getUserById(currentUser.getId()).getOffers()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    @Transactional
    public List<OfferDTO> getUserBoughtOffers() {
        return userRepository.getUserById(currentUser.getId()).getBoughtOffers()
                .stream()
                .map(this::map)
                .toList();
    }

    private OfferDTO map(Offer offer) {
        return new OfferDTO(offer.getDescription(),
                offer.getPrice(),
                offer.getCondition(),
                offer.getCreated());
    }
}
