package com.solutions;

import com.solutions.mappings.ActivitiMap;
import com.solutions.mappings.MigrationVariable;
import com.solutions.mappings.Variable;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jose on 2/26/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MyApp.class})
@WebAppConfiguration
@IntegrationTest
public class SecondLevelRegressionPointTest {

    private static final String PROCESS_KEY = "migrationProcess";
    private static final String SECOND_LEVEL_PROCESS = "processes/bug/secondLevelSubprocess.bpmn20.xml";
    private static final String FIRST_LEVEL_PROCESS = "processes/bug/firstLevelSubprocess.bpmn20.xml";
    private static final String MIGRATION_PROCESS = "processes/MigrationProcess.bpmn";

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    private Map<String, Object> startVars;

    @Before
    public void beforeTest() {
        startVars = new HashMap<>();

    }

    @Test
    public void startUserTaskMigrationProcess() {


        // Initialize particular vars
        startVars.put(MigrationVariable.MIGRATION_TASK_TYPE.getValue(), "userTask");
        startVars.put(Variable.TASK_NAME.getValue(), "Migration By User Task");
        startVars.put(MigrationVariable.MIGRATION_FORM_KEY.getValue(), "interpartes_check_if_recordal_needed");
        startVars.put(MigrationVariable.MIGRATION_POINT_AFTER_COMPLETE.getValue(), "interpartes_migration_point_after_check_if_recordal_needed");
        startVars.put(MigrationVariable.MIGRATION_POINT_TRIGGER_BY.getValue(), "key");
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
        startVars.put(MigrationVariable.MIGRATION_POINT_AFTER_COMPLETE.getValue(), "secondLevelSubprocess");
        startVars.put(MigrationVariable.MIGRATION_POINT_TRIGGER_BY.getValue(), "key");
        startVars.put(MigrationVariable.MIGRATION_REGRESSION_STACK.getValue(),
                "firstLevelRegressionPoint, mainProcessRegressionPoint");

        startVars.put("days", 20);

        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(SECOND_LEVEL_PROCESS)
                .addClasspathResource(FIRST_LEVEL_PROCESS)
                .addClasspathResource(MIGRATION_PROCESS)
                .deploy();

        // Arrange
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_KEY, startVars);

        // SecondLevelSubprocess
        List<Task> tasks = taskService.createTaskQuery().list();
        Assert.assertEquals("User Task 3-1", tasks.get(0).getName());
        taskService.complete(tasks.get(0).getId());

        tasks = taskService.createTaskQuery().list();
        Assert.assertEquals("User Task 3-2", tasks.get(0).getName());
        taskService.complete(tasks.get(0).getId());

        tasks = taskService.createTaskQuery().list();
        Assert.assertEquals("First examination", tasks.get(0).getName());
        taskService.complete(tasks.get(0).getId());

        tasks = taskService.createTaskQuery().list();
        Assert.assertEquals("Waiting for user or message", tasks.get(0).getName());
        Execution msgExec = runtimeService.createExecutionQuery()
                .processInstanceId(processInstance.getProcessInstanceId())
                .messageEventSubscriptionName("endSecondLevelSubprocess").singleResult();
        runtimeService.messageEventReceived(msgExec.getProcessInstanceId(), "endSecondLevelSubprocess");

        // FirstLevelSubprocess
        tasks = taskService.createTaskQuery().list();
        Assert.assertEquals("Telephone call", tasks.get(0).getName());
        taskService.complete(tasks.get(0).getId());

        // MainProcess
        tasks = taskService.createTaskQuery().list();
        Assert.assertEquals(2, tasks.size());
        taskService.complete(tasks.get(0).getId());
        taskService.complete(tasks.get(0).getId());

        List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().list();

        // Verify no task running
        Assert.assertEquals(0, processInstances.size());
        Assert.assertEquals("Unexpected task running after test", 0, taskService.createTaskQuery().count());


    }

    @Test
    public void startServiceMigrationProcess() {


        // Initialize particular vars
        startVars.put(MigrationVariable.MIGRATION_TASK_TYPE.getValue(), "service");
        startVars.put(Variable.TASK_NAME.getValue(), "Junit test task");
        startVars.put(MigrationVariable.MIGRATION_FORM_KEY.getValue(), "interpartes_check_if_recordal_needed");
        startVars.put(MigrationVariable.MIGRATION_POINT_AFTER_COMPLETE.getValue(), "interpartes_migration_point_after_check_if_recordal_needed");
        startVars.put(MigrationVariable.MIGRATION_POINT_TRIGGER_BY.getValue(), "message");
        startVars.put(MigrationVariable.MIGRATION_REGRESSION_STACK.getValue(),
                "firstLevelRegressionPoint, mainProcessRegressionPoint");

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
