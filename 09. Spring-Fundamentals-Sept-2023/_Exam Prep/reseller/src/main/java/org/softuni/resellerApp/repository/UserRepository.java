package org.softuni.resellerApp.repository;

import org.softuni.resellerApp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    User getUserById(Long id);

    @Query(value = "SELECT u FROM " +
            "User AS u " +
            "WHERE SIZE(u.offers)> 0 " +
            "AND u.id <> ?1")
    List<User> findAllOtherUsersWithOffers(Long id);
}
