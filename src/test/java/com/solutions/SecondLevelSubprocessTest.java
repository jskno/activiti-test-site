package com.solutions;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Assert;
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
 * Created by Jose on 2/25/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MyApp.class})
@WebAppConfiguration
@IntegrationTest
public class SecondLevelSubprocessTest {

    private static final String SECOND_LEVEL_PROCESS = "processes/bug/secondLevelSubprocess.bpmn20.xml";

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    TaskService taskService;

    @Test
//    @org.activiti.engine.test.Deployment( resources = { SECOND_LEVEL_PROCESS } )
    public void StartProcess() {

        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(SECOND_LEVEL_PROCESS)
                .deploy();

        Map<String, Object> variables = new HashMap<>();
        variables.put("days", "5");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                "secondLevelSubprocess");

        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        List<Execution> executions = runtimeService.createExecutionQuery().list();

        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId())
                .singleResult();
        Assert.assertEquals("First examination", task.getName());
        taskService.complete(task.getId(), variables);

        instances = runtimeService.createProcessInstanceQuery().list();
        executions = runtimeService.createExecutionQuery().list();

        task = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId())
                .singleResult();
        Assert.assertEquals("Waiting for user or message", task.getName());
//        Assert.assertEquals("5", task.getProcessVariables().get("days"));
//        Assert.assertEquals("5", instances.get(0).getProcessVariables().get("days"));
//        Assert.assertEquals("5", ((ExecutionEntity) executions.get(0)).getVariable("days"));
        taskService.complete(task.getId(), variables);

        instances = runtimeService.createProcessInstanceQuery().list();
        executions = runtimeService.createExecutionQuery().list();
    }

    @Test
    public void finishingWithBoundaryEvent() {
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/bug/secondLevelSubprocess.bpmn20.xml")
                .deploy();

        Map<String, Object> variables = new HashMap<>();
        variables.put("days", "5");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                "secondLevelSubprocess");

        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId())
                .singleResult();
        Assert.assertEquals("First examination", task.getName());
        taskService.complete(task.getId(), variables);

        task = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId())
                .singleResult();
        Assert.assertEquals("Waiting for user or message", task.getName());

        Execution msgExec = runtimeService.createExecutionQuery()
                .processInstanceId(processInstance.getProcessInstanceId())
                .messageEventSubscriptionName("endSecondLevelSubprocess").singleResult();

        runtimeService.messageEventReceived("endSecondLevelSubprocess", msgExec.getId(), null );

        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        List<Execution> executions = runtimeService.createExecutionQuery().list();





    }
}
