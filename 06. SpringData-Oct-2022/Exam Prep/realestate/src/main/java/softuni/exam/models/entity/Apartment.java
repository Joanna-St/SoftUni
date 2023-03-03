package softuni.exam.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.models.entity.enums.ApartmentType;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "apartments")
public class Apartment extends BaseEntity{
    @Column(name = "apartment_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ApartmentType apartmentType;

    @Column(nullable = false)
    private Double area;

    @ManyToOne(targetEntity = Town.class)
    @JoinColumn(name = "town_id")
    private Town town;
}
