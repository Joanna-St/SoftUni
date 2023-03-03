package com.example.football.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "stat")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatXmlImportDTO {
    @Positive
    @NotNull
    @XmlElement
    private Float passing;

    @Positive
    @NotNull
    @XmlElement
    private Float shooting;

    @Positive
    @NotNull
    @XmlElement
    private Float endurance;
}
