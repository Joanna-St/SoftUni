package com.example.football.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class TownJsonImportDTO {
    @Size(min = 2)
    @NotNull
    private String name;

    @Positive
    @NotNull
    private Integer population;

    @Size(min = 10)
    @NotNull
    private String travelGuide;
}
