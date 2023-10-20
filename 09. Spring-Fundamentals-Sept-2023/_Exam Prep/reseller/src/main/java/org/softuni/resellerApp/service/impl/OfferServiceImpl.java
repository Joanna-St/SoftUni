package org.softuni.resellerApp.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.softuni.resellerApp.model.dto.OfferDTO;
import org.softuni.resellerApp.model.entity.Offer;
import org.softuni.resellerApp.model.enums.ConditionEnum;
import org.softuni.resellerApp.repository.ConditionRepository;
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
    private final ConditionRepository conditionRepository;
    private final CurrentUser currentUser;

    @Override
    public List<Offer> getAllOtherOffers() {
        return offerRepository.findAllAvailableAndNotByUser(currentUser.getId());
    }

    @Override
    public List<Offer> getUserOffers() {
        return userRepository.getUserById(currentUser.getId()).getOffers();
    }

    @Override
    @Transactional
    public List<Offer> getUserBoughtOffers() {
        return userRepository.getUserById(currentUser.getId()).getBoughtOffers();
    }

    @Override
    public void addOffer(OfferDTO offerDTO) {
        offerRepository.save(mapNew(offerDTO));
    }

    @Override
    public void buyOffer(Long id) {
        Offer toBuy = offerRepository.findById(id).get();
        toBuy.setBought(userRepository.getUserById(currentUser.getId()));
        toBuy.setCreated(null);
        offerRepository.save(toBuy);
    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.delete(offerRepository.findById(id).get());
    }

    private OfferDTO mapNew(Offer offer) {
        return new OfferDTO(offer.getDescription(),
                offer.getPrice(),
                offer.getCondition().getConditionName().name(),
                offer.getCreated());
    }

    private Offer mapNew(OfferDTO offerDTO) {
        Offer newOffer = new Offer();
        newOffer.setDescription(offerDTO.description());
        newOffer.setPrice(offerDTO.price());
        newOffer.setCondition(conditionRepository.getConditionByConditionName(ConditionEnum.valueOf(offerDTO.condition())));
        newOffer.setCreated(userRepository.getUserById(currentUser.getId()));
        newOffer.setBought(null);

        return newOffer;
    }
}
