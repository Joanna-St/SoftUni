package org.softuni.resellerApp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.resellerApp.model.enums.ConditionEnum;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity {
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private ConditionEnum conditionName;

    @NotNull
    @Column(nullable = false)
    private String description;


    public Condition(ConditionEnum condition) {
        this.conditionName = condition;
        this.description = condition.getDescription();
    }
    public void setDescription() {
        this.description = this.conditionName.getDescription();
    }
}
