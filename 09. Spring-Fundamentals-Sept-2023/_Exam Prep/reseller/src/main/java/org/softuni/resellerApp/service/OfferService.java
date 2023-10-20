package org.softuni.resellerApp.service;

import org.softuni.resellerApp.model.dto.OfferDTO;

import java.util.List;

public interface OfferService {
    List<OfferDTO> getAllOtherOffers();

    List<OfferDTO> getUserOffers();

    List<OfferDTO> getUserBoughtOffers();
}