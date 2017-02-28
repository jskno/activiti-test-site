package com.solutions;

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
    }

    public void processSuccess(Execution execution) {
        System.out.println("Success !!");
    }
}
