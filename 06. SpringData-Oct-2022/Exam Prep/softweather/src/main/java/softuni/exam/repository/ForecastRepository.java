package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.entity.enums.DayOfWeek;

import java.util.List;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {
    Forecast findForecastByDayOfWeekAndCityId(DayOfWeek dayOfWeek, Long city);

    @Query(value = "SELECT f " +
            "FROM Forecast AS f " +
            "WHERE f.dayOfWeek = 'SUNDAY' " +
            "AND f.city.population < 150000 " +
            "ORDER BY f.maxTemperature DESC , f.id ASC ")
    List<Forecast> findForecastsFromSundayForCitiesLessThan150000CitizensOrderByMaxTemperatureDescIdAsc();
}
