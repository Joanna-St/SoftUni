package org.softuni.resellerApp.repository;

import org.softuni.resellerApp.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query(value = "SELECT o FROM " +
            "Offer AS o " +
            "WHERE o.bought IS NULL " +
            "AND o.created.id <> ?1")
    List<Offer> findAllAvailableAndNotByUser(Long id);
}
