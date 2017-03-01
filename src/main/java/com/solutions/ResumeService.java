package com.solutions;

import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.runtime.Execution;
import org.springframework.stereotype.Component;

/**
 * Created by Jose on 2/25/2017.
 */
@Component
public class ResumeService {

    public void storeResume() {
        System.out.println("Storing resume...");
    }

    public void validate(Execution execution) {
        System.out.println("Validating...");
        ExecutionEntity executionEntity = (ExecutionEntity) execution;
        String weeks;
        if(executionEntity.getVariable("days").equals("14")) {
            weeks = "TwoWeeks";
        } else {
            weeks = "OneWeek";
        }
        executionEntity.setVariable("secondLevelSubprocessResult", weeks);
    }

    public void processSuccess(Execution execution) {
        System.out.println("Success !!");
    }
}
