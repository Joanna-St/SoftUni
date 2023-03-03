package com.example.football.models.dto;

import com.example.football.models.entity.enums.Position;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerExportDTO {
    private String firstName;
    private String lastName;
    private Position position;
    private TeamExportDTO team;

    @Override
    public String toString() {
        return String.format("Player - %s %s%n" +
                             "  Position - %s%n" +
                             "  Team - %s%n" +
                             "  Stadium - %s%n",
                this.firstName,this.lastName,this.position,this.team.getName(),this.team.getStadiumName());
    }
}
