package softuni.exam.models.dto.exportDTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TaskExportDTO {
    private Long id;
    private BigDecimal price;
    private CarExportDTO car;
    private MechanicExportDTO mechanic;

    @Override
    public String toString() {
        return String.format("Car %s %s with %dkm%n" +
                "-Mechanic: %s %s - task №%d:%n" +
                "--Engine: %.1f%n" +
                "---Price: %.2f$%n"
                ,this.car.getCarMake(), this.car.getCarModel(), this.car.getKilometers()
                ,this.mechanic.getFirstName(), this.mechanic.getLastName(), this.id
                ,this.car.getEngine()
                ,this.price);
    }
}
