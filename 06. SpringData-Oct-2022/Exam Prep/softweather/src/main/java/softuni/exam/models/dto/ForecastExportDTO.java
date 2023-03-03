package softuni.exam.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.models.entity.enums.DayOfWeek;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class ForecastExportDTO {
    private DayOfWeek dayOfWeek;
    private Double maxTemperature;
    private Double minTemperature;
    private LocalTime sunrise;
    private LocalTime sunset;
    private String cityName;

    @Override
    public String toString() {
        return String.format("City: %s:%n" +
                             "-min temperature: %.2f%n" +
                             "--max temperature: %.2f%n" +
                             "---sunrise: %s%n" +
                             "----sunset: %s%n",
                cityName,minTemperature,maxTemperature,sunrise,sunset);
    }
}
