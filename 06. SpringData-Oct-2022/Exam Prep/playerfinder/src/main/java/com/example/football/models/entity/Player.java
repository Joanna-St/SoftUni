package com.example.football.models.entity;

import com.example.football.models.entity.enums.Position;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "players")
public class Player extends BaseEntity {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(nullable = false)
    private Position position;

    @ManyToOne(targetEntity = Town.class)
    @JoinColumn(name = "town_id")
    private Town town;

    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(targetEntity = Stat.class)
    @JoinColumn(name = "stat_id")
    private Stat stat;
}
