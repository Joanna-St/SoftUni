package softuni.exam.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class TownJsonImportDTO {
    @NotNull
    @Size(min = 2)
    private String townName;

    @NotNull
    @Positive
    private Integer population;
}
