package com.example.football.models.dto;

import com.example.football.models.entity.enums.Position;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerXmlImportDTO {
    @Size(min = 2)
    @NotNull
    @XmlElement(name = "first-name")
    private String firstName;

    @Size(min = 2)
    @NotNull
    @XmlElement(name = "last-name")
    private String lastName;

    @Email
    @NotNull
    @XmlElement(name = "email")
    private String email;

    @NotNull
    @XmlElement(name = "birth-date")
    private String birthDate;

    @NotNull
    @XmlElement(name = "position")
    private Position position;

    @NotNull
    @XmlElement(name = "town")
    private TownXmlImportDTO town;

    @NotNull
    @XmlElement(name = "team")
    private TeamXmlImportDTO team;

    @NotNull
    @XmlElement(name = "stat")
    private StatIdXmlImportDTO stat;
}
