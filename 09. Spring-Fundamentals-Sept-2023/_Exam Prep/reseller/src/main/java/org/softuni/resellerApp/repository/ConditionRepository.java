package org.softuni.resellerApp.repository;

import org.softuni.resellerApp.model.entity.Condition;
import org.softuni.resellerApp.model.enums.ConditionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {
    Condition getConditionByConditionName(ConditionEnum condition);
}
