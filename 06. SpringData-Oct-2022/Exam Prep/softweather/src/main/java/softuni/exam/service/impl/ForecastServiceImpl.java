package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ForecastExportDTO;
import softuni.exam.models.dto.ForecastSeedXmlDTO;
import softuni.exam.models.dto.ForecastsSeedXmlDTO;
import softuni.exam.models.entity.Forecast;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;
import softuni.exam.util.Messages;
import softuni.exam.util.Paths;
import softuni.exam.util.ValidationUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.List;

@Service
public class ForecastServiceImpl implements ForecastService {
    private final ForecastRepository forecastRepository;
    private final CityRepository cityRepository;
    private final ModelMapper mapper;
    private final JAXBContext forecastImportContext;
    private final ValidationUtil validationUtil;

    @Autowired
    public ForecastServiceImpl(ForecastRepository forecastRepository, CityRepository cityRepository, ModelMapper mapper, JAXBContext forecastImportContext, ValidationUtil validationUtil) {
        this.forecastRepository = forecastRepository;
        this.cityRepository = cityRepository;
        this.mapper = mapper;
        this.forecastImportContext = forecastImportContext;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(Path.of(Paths.FORECASTS_PATH));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        BufferedReader bufferedReader = Files.newBufferedReader(Path.of(Paths.FORECASTS_PATH));
        StringBuilder stringBuilder = new StringBuilder();

        Unmarshaller unmarshaller = forecastImportContext.createUnmarshaller();

        ForecastsSeedXmlDTO forecasts = (ForecastsSeedXmlDTO) unmarshaller.unmarshal(bufferedReader);

        for (ForecastSeedXmlDTO forecast : forecasts.getForecasts()) {
            if (validationUtil.isValid(forecast) && isUnique(forecast)){
                Forecast mappedForecast = mapper.map(forecast, Forecast.class);
                mappedForecast.setCity(this.cityRepository.findCityById(forecast.getCity()));
                mappedForecast.setSunrise(LocalTime.parse(forecast.getSunrise()));
                mappedForecast.setSunset(LocalTime.parse(forecast.getSunset()));

                this.forecastRepository.save(mappedForecast);

                stringBuilder.append(String.format(Messages.SUCCESSFULLY_IMPORTED_FORECAST
                        ,forecast.getDayOfWeek(),forecast.getMaxTemperature()));
            } else {
                stringBuilder.append(Messages.INVALID_FORECAST);
            }

            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    @Override
    public String exportForecasts() {
        StringBuilder stringBuilder = new StringBuilder();
        TypeMap<Forecast, ForecastExportDTO>typeMap = mapper.createTypeMap(Forecast.class, ForecastExportDTO.class);
        typeMap.addMapping(src -> src.getCity().getCityName(), ForecastExportDTO::setCityName);

        List<Forecast> forecasts =
                this.forecastRepository
                .findForecastsFromSundayForCitiesLessThan150000CitizensOrderByMaxTemperatureDescIdAsc();

        for (Forecast forecast : forecasts) {
            ForecastExportDTO mappedForecast = typeMap.map(forecast);

            stringBuilder.append(mappedForecast.toString());
        }

        return stringBuilder.toString();
    }

    private boolean isUnique(ForecastSeedXmlDTO forecast) {
        return this.forecastRepository.findForecastByDayOfWeekAndCityId
                (forecast.getDayOfWeek(),forecast.getCity()) == null;
    }
}
