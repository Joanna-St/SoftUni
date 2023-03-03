package softuni.exam.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "offers")
public class Offer extends BaseEntity{
    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "published_on", nullable = false)
    private LocalDate publishedOn;

    @ManyToOne(targetEntity = Apartment.class)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @ManyToOne(targetEntity = Agent.class)
    @JoinColumn(name = "agent_id")
    private Agent agent;
}
