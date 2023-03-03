package softuni.exam.models.dto.importDTOs.xml;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.models.entity.enums.CarType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarXmlImportDTO {
    @NotNull
    @Size(min = 2, max = 30)
    @XmlElement
    private String carMake;

    @NotNull
    @Size(min = 2, max = 30)
    @XmlElement
    private String carModel;

    @NotNull
    @Positive
    @XmlElement
    private Integer year;

    @NotNull
    @Size(min = 2, max = 30)
    @XmlElement
    private String plateNumber;

    @NotNull
    @Positive
    @XmlElement
    private Integer kilometers;

    @NotNull
    @DecimalMin(value = "1")
    @XmlElement
    private Double engine;

    @NotNull
    @XmlElement
    private CarType carType;

}
