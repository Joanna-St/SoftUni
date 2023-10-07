package org.softuni.mobilele.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.mobilele.models.entity.enums.UserRoleEnum;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRoleEnum role;
}
