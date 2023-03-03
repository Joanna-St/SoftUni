package softuni.exam.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "countries")
public class Country extends BaseEntity{
    @Column(name = "country_name", unique = true, nullable = false)
    private String countryName;
    @Column(name = "currency",nullable = false)
    private String currency;

    @OneToMany(targetEntity = City.class, mappedBy = "country")
    Set<City> cities;
}
