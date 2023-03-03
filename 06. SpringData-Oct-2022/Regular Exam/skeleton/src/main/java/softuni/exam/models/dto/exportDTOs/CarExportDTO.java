package softuni.exam.models.dto.exportDTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarExportDTO {
    private String carMake;
    public String carModel;
    private Integer kilometers;
    private Double engine;
}
