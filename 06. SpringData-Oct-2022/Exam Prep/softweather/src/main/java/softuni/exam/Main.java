package softuni.exam;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.exam.models.entity.Country;
import softuni.exam.util.Paths;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class Main implements CommandLineRunner {
    private final Gson gson;
    private final ModelMapper mapper;

    public Main(Gson gson, ModelMapper mapper) {
        this.gson = gson;
        this.mapper = mapper;
    }

    @Override
    public void run(String... args) throws Exception {



    }
}
