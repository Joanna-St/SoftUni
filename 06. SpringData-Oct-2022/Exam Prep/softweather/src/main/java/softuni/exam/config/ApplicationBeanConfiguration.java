package softuni.exam.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.exam.models.dto.ForecastsSeedXmlDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Scanner;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public ModelMapper createModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Scanner createScanner() {
        return new Scanner(System.in);
    }

    @Bean
    public Gson createGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public JAXBContext createForecastImportContext() throws JAXBException {
        return JAXBContext.newInstance(ForecastsSeedXmlDTO.class);
    }
}
