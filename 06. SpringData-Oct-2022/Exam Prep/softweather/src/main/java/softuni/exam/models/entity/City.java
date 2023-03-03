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
@Table(name = "cities")
public class City extends BaseEntity{
    @Column(name = "city_name", unique = true, nullable = false)
    private String cityName;
    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "population", nullable = false)
    private Integer population;
    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name = "country_id")
    private Country country;
    @OneToMany(targetEntity = Forecast.class, mappedBy = "city")
    private Set<Forecast> forecasts;
}
