package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findCarByPlateNumber(String plateNumber);
    Car findCarById(Long id);
}
