package softuni.exam.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.models.entity.enums.DayOfWeek;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "forecast")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastSeedXmlDTO {
    @NotNull
    @XmlElement(name = "day_of_week")
    private DayOfWeek dayOfWeek;
    @NotNull
    @DecimalMin(value = "-20")
    @DecimalMax(value = "60")
    @XmlElement(name = "max_temperature")
    private Double maxTemperature;
    @NotNull
    @DecimalMin(value = "-50")
    @DecimalMax(value = "40")
    @XmlElement(name = "min_temperature")
    private Double minTemperature;
    @NotNull
    @XmlElement(name = "sunrise")
    private String sunrise;
    @NotNull
    @XmlElement(name = "sunset")
    private String sunset;
    @NotNull
    @XmlElement(name = "city")
    private Long city;
}
