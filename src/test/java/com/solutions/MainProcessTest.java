package com.solutions;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
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
public class MainProcessTest {

    private static final String SECOND_LEVEL_PROCESS = "processes/bug/secondLevelSubprocess.bpmn20.xml";
    private static final String FIRST_LEVEL_PROCESS = "processes/bug/firstLevelSubprocess.bpmn20.xml";
    private static final String MAIN_PROCESS = "processes/bug/mainProcess.bpmn20.xml";

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
                .addClasspathResource(FIRST_LEVEL_PROCESS)
                .addClasspathResource(MAIN_PROCESS)
                .deploy();

        Map<String, Object> variables = new HashMap<>();
        variables.put("days", "20");
        variables.put("months", "5");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                "mainProcess");

        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        List<Execution> executions = runtimeService.createExecutionQuery().list();

        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId())
                .singleResult();
        Assert.assertEquals("Starting Laptop", task.getName());
        taskService.complete(task.getId(), variables);

        instances = runtimeService.createProcessInstanceQuery().list();
        executions = runtimeService.createExecutionQuery().list();

        task = taskService.createTaskQuery()
                .singleResult();
        Assert.assertEquals("Reading dossier Email", task.getName());
        taskService.complete(task.getId(), variables);

        instances = runtimeService.createProcessInstanceQuery().list();
        executions = runtimeService.createExecutionQuery().list();

        task = taskService.createTaskQuery()
                .singleResult();
        Assert.assertEquals("First examination", task.getName());
        taskService.complete(task.getId(), variables);

        instances = runtimeService.createProcessInstanceQuery().list();
        executions = runtimeService.createExecutionQuery().list();

        task = taskService.createTaskQuery()
                .singleResult();
        Assert.assertEquals("Waiting for user or message", task.getName());
        taskService.complete(task.getId(), variables);

        instances = runtimeService.createProcessInstanceQuery().list();
        executions = runtimeService.createExecutionQuery().list();

        task = taskService.createTaskQuery()
                .singleResult();
        Assert.assertEquals("Final success", task.getName());

    }

    @Test
    public void finishingWithBoundaryEvent() {

        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(SECOND_LEVEL_PROCESS)
                .addClasspathResource(FIRST_LEVEL_PROCESS)
                .addClasspathResource(MAIN_PROCESS)
                .deploy();

        Map<String, Object> variables = new HashMap<>();
        variables.put("days", "5");
        variables.put("months", "1");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                "mainProcess");

        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        List<Execution> executions = runtimeService.createExecutionQuery().list();

        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId())
                .singleResult();
        Assert.assertEquals("Starting Laptop", task.getName());
        taskService.complete(task.getId(), variables);

        instances = runtimeService.createProcessInstanceQuery().list();
        executions = runtimeService.createExecutionQuery().list();

        task = taskService.createTaskQuery()
                .singleResult();
        Assert.assertEquals("Reading dossier Email", task.getName());
        taskService.complete(task.getId(), variables);

        instances = runtimeService.createProcessInstanceQuery().list();
        executions = runtimeService.createExecutionQuery().list();

        task = taskService.createTaskQuery()
                .singleResult();
        Assert.assertEquals("First examination", task.getName());
        taskService.complete(task.getId(), variables);

        instances = runtimeService.createProcessInstanceQuery().list();
        executions = runtimeService.createExecutionQuery().list();

        task = taskService.createTaskQuery()
                .singleResult();
        Assert.assertEquals("Waiting for user or message", task.getName());
        taskService.complete(task.getId(), variables);

        instances = runtimeService.createProcessInstanceQuery().list();
        executions = runtimeService.createExecutionQuery().list();

        task = taskService.createTaskQuery()
                .singleResult();
        Assert.assertEquals("Telephone call", task.getName());
        variables.put("inFirstLevel", "webServices");
        taskService.complete(task.getId(), variables);

        task = taskService.createTaskQuery()
                .singleResult();
        Assert.assertEquals("Urgent Meeting", task.getName());
        taskService.complete(task.getId());

        instances = runtimeService.createProcessInstanceQuery().list();
        executions = runtimeService.createExecutionQuery().list();
        Assert.assertEquals(0, instances.size());
        Assert.assertEquals(0, executions.size());
    }
}
