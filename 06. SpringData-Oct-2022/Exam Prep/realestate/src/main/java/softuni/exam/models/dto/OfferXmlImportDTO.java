package softuni.exam.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferXmlImportDTO {
    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private AgentXmlImportDTO agent;

    @NotNull
    private ApartmentIdXmlImportDTO apartment;

    @NotNull
    private String publishedOn;
}
