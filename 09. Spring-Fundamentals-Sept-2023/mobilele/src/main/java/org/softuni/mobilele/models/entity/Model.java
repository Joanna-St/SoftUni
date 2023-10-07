package org.softuni.mobilele.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.mobilele.models.entity.enums.CarCategoryEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "models")
public class Model extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarCategoryEnum category;
    @Column(name = "image_url", length = 512)
    private String imageUrl;
    @Column(nullable = false)
    private Integer startYear;
    @Column
    private Integer endYear;
    @Column(nullable = false)
    private LocalDateTime created;
    @Column
    private LocalDateTime modified;
    @ManyToOne
    private Brand brand;
}
