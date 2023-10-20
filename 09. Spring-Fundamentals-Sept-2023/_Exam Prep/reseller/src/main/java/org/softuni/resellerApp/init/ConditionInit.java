package org.softuni.resellerApp.init;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.softuni.resellerApp.model.entity.Condition;
import org.softuni.resellerApp.model.enums.ConditionEnum;
import org.softuni.resellerApp.repository.ConditionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
public class ConditionInit implements CommandLineRunner {
    private final ConditionRepository conditionRepository;
    
    @Override
    public void run(String... args) throws Exception {
        if(conditionRepository.count() == 0) {
            for (ConditionEnum conditionEnum : ConditionEnum.values()) {
                Condition condition = new Condition(conditionEnum);
                conditionRepository.save(condition);
            }
        }
    }
}
