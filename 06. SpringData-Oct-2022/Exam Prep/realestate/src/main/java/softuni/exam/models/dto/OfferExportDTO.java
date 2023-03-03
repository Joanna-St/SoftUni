package softuni.exam.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class OfferExportDTO {
    private Long id;
    private AgentExportDTO agent;
    private ApartmentExportDTO apartment;
    private BigDecimal price;

    @Override
    public String toString() {
        return String.format("Agent %s %s with offer №%d%n" +
                             "  -Apartment area: %.2f%n" +
                             "  --Town: %s%n" +
                             "  ---Price: %.2f$%n"
                             ,this.agent.getFirstName()
                             ,this.agent.getLastName()
                             ,this.id
                             ,this.apartment.getArea()
                             ,this.apartment.getTown().getName()
                             ,this.price);
    }
}
