package com.example.football.repository;

import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findPlayerByEmail(String email);

    @Query(value = "SELECT p " +
            "FROM Player AS p " +
            "WHERE p.birthDate BETWEEN '1995-01-02' AND '2002-12-31' " +
            "ORDER BY p.stat.shooting DESC, " +
            "p.stat.passing DESC, " +
            "p.stat.endurance DESC, " +
            "p.lastName")
    List<Player> findBestPlayers();
}
