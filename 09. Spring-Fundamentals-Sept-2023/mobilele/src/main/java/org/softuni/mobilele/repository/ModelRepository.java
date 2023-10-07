package org.softuni.mobilele.repository;

import org.softuni.mobilele.models.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findModelsByBrandName(String brandName);
    List<Model> findModelsByCategory(String category);
}
