package com.solutions.bug;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Jose on 2/25/2017.
 */
@Component
public class DossierService {

    public void validate(DelegateExecution execution) {
        System.out.println("Validating");
        for(Map.Entry<String, Object> variable : execution.getVariables().entrySet()) {
            System.out.println(variable);
        }
        boolean isRecordalNeeded = Integer.valueOf((String) execution.getVariable("days")) > 10;
        execution.setVariable("isRecordalNeeded", isRecordalNeeded);
    }

    public void processSuccess(DelegateExecution execution) {
        System.out.println("Finish First Level !!");
        int total =
                Integer.valueOf((String)execution.getVariable("days")) *
                Integer.valueOf((String) execution.getVariable("months"));
        String inFirstLevel = (total > 50) ? "spring" : "webServices";
        System.out.println(execution.getVariable("secondLevelSubprocessResult"));
        System.out.println(execution.getVariable("firstLevelSubprocessResult"));
        execution.setVariable("inFirstLevel", inFirstLevel);
    }
}
