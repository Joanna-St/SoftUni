package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CitySeedJsonDTO {
    @NotNull
    @Size(min = 2, max = 60)
    @Expose
    String cityName;
    @Size(min = 2)
    @Expose
    String description;
    @NotNull
    @Min(500)
    @Expose
    Integer population;
    @Expose
    Long country;
}
