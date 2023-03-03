package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountrySeedJsonDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.Messages;
import softuni.exam.util.Paths;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final ModelMapper mapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper mapper, Gson gson, ValidationUtil validationUtil) {
        this.countryRepository = countryRepository;
        this.mapper = mapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(Path.of(Paths.COUNTRIES_PATH));
    }

    @Override
    public String importCountries() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        CountrySeedJsonDTO[] countries = gson.fromJson(readCountriesFromFile(), CountrySeedJsonDTO[].class);

        for (CountrySeedJsonDTO country : countries) {
            if (validationUtil.isValid(country) && isUnique(country)) {
                Country mappedCountry = mapper.map(country, Country.class);

                this.countryRepository.save(mappedCountry);

                stringBuilder.append(String.format(Messages.SUCCESSFULLY_IMPORTED_COUNTRY
                        , mappedCountry.getCountryName(), mappedCountry.getCurrency()));

            } else {
                stringBuilder.append(Messages.INVALID_COUNTRY);
            }

            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private boolean isUnique(CountrySeedJsonDTO country) {
        return this.countryRepository.findCountryByCountryName(country.getCountryName()) == null;
    }
}
