package org.softuni.resellerApp.service;

import org.softuni.resellerApp.model.dto.OfferDTO;
import org.softuni.resellerApp.model.entity.Offer;

import java.util.List;

public interface OfferService {
    List<Offer> getAllOtherOffers();

    List<Offer> getUserOffers();

    List<Offer> getUserBoughtOffers();

    void addOffer(OfferDTO offerDTO);

    void buyOffer(Long id);

    void deleteOffer(Long id);
}