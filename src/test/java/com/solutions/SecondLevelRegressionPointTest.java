package com.solutions;

import com.solutions.mappings.ActivitiMap;
import com.solutions.mappings.MigrationVariable;
import com.solutions.mappings.Variable;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jose on 2/26/2017.
 */
public class SecondLevelRegressionPointTest {

    private static final String PROCESS_KEY = "migrationProcess";
    private static final String SECOND_LEVEL_PROCESS = "processes/bug/secondLevelSubprocess.bpmn20.xml";
    private static final String FIRST_LEVEL_PROCESS = "processes/bug/firstLevelSubprocess.bpmn20.xml";
    private static final String MIGRATION_PROCESS = "processes/MigrationProcess.bpmn";

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    private Map<String, Object> startVars;

    @Before
    private void beforeTest() {
        startVars = new HashMap<>();

    }

    @Test
    public void startUserTaskMigrationProcess() {


        // Initialize particular vars
        startVars.put(MigrationVariable.MIGRATION_TASK_TYPE.getValue(), "userTask");
        startVars.put(Variable.TASK_NAME.getValue(), "Migration By User Task");
        startVars.put(MigrationVariable.MIGRATION_FORM_KEY.getValue(), "interpartes_check_if_recordal_needed");
        startVars.put(MigrationVariable.MIGRATION_POINT_AFTER_COMPLETE.getValue(), "interpartes_migration_point_after_check_if_recordal_needed");
        startVars.put(MigrationVariable.MIGRATION_POINT_TRIGGER.getValue(), "key");
        startVars.put(MigrationVariable.MIGRATION_REGRESSION_STACK.getValue(), "interpartes_migration_point_after_admissibility_check");

        startVars.put("days", 20);

        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(SECOND_LEVEL_PROCESS)
                .addClasspathResource(FIRST_LEVEL_PROCESS)
                .addClasspathResource(MIGRATION_PROCESS)
                .deploy();

        // Arrange
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_KEY, startVars);
    }

    @Test
    public void startCallActivitiMigrationProcess() {


        // Initialize particular vars
        startVars.put(MigrationVariable.MIGRATION_TASK_TYPE.getValue(), "callActivity");
        startVars.put(Variable.TASK_NAME.getValue(), "Migration By Call Activiti");
        startVars.put(MigrationVariable.MIGRATION_FORM_KEY.getValue(), "callActivitiMigrationSubprocess");
        startVars.put(MigrationVariable.MIGRATION_POINT_AFTER_COMPLETE.getValue(), "interpartes_migration_point_after_check_if_recordal_needed");
        startVars.put(MigrationVariable.MIGRATION_POINT_TRIGGER.getValue(), "key");
        startVars.put(MigrationVariable.MIGRATION_REGRESSION_STACK.getValue(), "interpartes_migration_point_after_admissibility_check");

        startVars.put("days", 20);

        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(SECOND_LEVEL_PROCESS)
                .addClasspathResource(FIRST_LEVEL_PROCESS)
                .addClasspathResource(MIGRATION_PROCESS)
                .deploy();

        // Arrange
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_KEY, startVars);
    }

    @Test
    public void startServiceMigrationProcess() {


        // Initialize particular vars
        startVars.put(MigrationVariable.MIGRATION_TASK_TYPE.getValue(), "callActivity");
        startVars.put(Variable.TASK_NAME.getValue(), "Junit test task");
        startVars.put(MigrationVariable.MIGRATION_FORM_KEY.getValue(), "interpartes_check_if_recordal_needed");
        startVars.put(MigrationVariable.MIGRATION_POINT_AFTER_COMPLETE.getValue(), "interpartes_migration_point_after_check_if_recordal_needed");
        startVars.put(MigrationVariable.MIGRATION_POINT_TRIGGER.getValue(), "key");
        startVars.put(MigrationVariable.MIGRATION_REGRESSION_STACK.getValue(), "interpartes_migration_point_after_admissibility_check");

        startVars.put("days", 20);

        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(SECOND_LEVEL_PROCESS)
                .addClasspathResource(FIRST_LEVEL_PROCESS)
                .addClasspathResource(MIGRATION_PROCESS)
                .deploy();

        // Arrange
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_KEY, startVars);
    }

}
