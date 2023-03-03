package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CitySeedJsonDTO;
import softuni.exam.models.entity.City;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CityService;
import softuni.exam.util.Messages;
import softuni.exam.util.Paths;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final ModelMapper mapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, CountryRepository countryRepository, ModelMapper mapper, Gson gson, ValidationUtil validationUtil) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.mapper = mapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files.readString(Path.of(Paths.CITIES_PATH));
    }

    @Override
    public String importCities() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        CitySeedJsonDTO[] cities = gson.fromJson(readCitiesFileContent(), CitySeedJsonDTO[].class);

        for (CitySeedJsonDTO city : cities) {
            if (validationUtil.isValid(city) && isUnique(city)) {
                City mappedCity = mapper.map(city, City.class);
                mappedCity.setCountry(this.countryRepository.findCountryById(city.getCountry()));

                this.cityRepository.save(mappedCity);

                stringBuilder.append(String.format(Messages.SUCCESSFULLY_IMPORTED_CITY
                        ,city.getCityName(),city.getPopulation()));
            } else {
                stringBuilder.append(Messages.INVALID_CITY);
            }
            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    private boolean isUnique(CitySeedJsonDTO city) {
        return this.cityRepository.findCityByCityName(city.getCityName()) == null;
    }
}

