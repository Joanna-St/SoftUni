package org.softuni.mobilele.repository;

import org.softuni.mobilele.models.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findBrandsByName(String name);
}
