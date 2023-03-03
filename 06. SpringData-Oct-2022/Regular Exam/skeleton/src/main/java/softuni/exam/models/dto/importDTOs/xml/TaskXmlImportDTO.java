package softuni.exam.models.dto.importDTOs.xml;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskXmlImportDTO {
    @NotNull
    @XmlElement
    private String date;

    @NotNull
    @Positive
    @XmlElement
    private BigDecimal price;

    @NotNull
    @XmlElement
    private CarXmlIdImportDTO car;

    @NotNull
    @XmlElement
    private MechanicXmlImportDTO mechanic;

    @NotNull
    @XmlElement
    private PartXmlImportDTO part;
}
