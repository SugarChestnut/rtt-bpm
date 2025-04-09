package cn.rentaotao.flowable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class LeaveTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    @Test
    public void deploy() {
    }

    @Test
    public void startLeave() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("day", 5);
        variables.put("user", "小明");
        variables.put("submit", "提交");

        ProcessInstance instance = runtimeService.startProcessInstanceByKey("leave", variables);
        Task task = taskService.createTaskQuery().processInstanceId(instance.getId()).singleResult();
        // 发起人完成当前任务
        taskService.complete(task.getId());
    }

    @Test
    public void queryLeaderTask() throws JsonProcessingException {
        // 根据流程的设置候选组，获取需要处理的任务
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("manager").list();
        for (Task task : tasks) {
            // 获取任务的参数
            Map<String, Object> variables = taskService.getVariables(task.getId());
            // {"submit":"提交","permission":"通过","user":"小明","day":5}
            System.out.println(mapper.writeValueAsString(variables));
        }
    }

    @Test
    public void approveLeaderTask() {
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("manager").list();
        for (Task task : tasks) {
            // 添加参数，完成审批
            Map<String, Object> variables = new HashMap<>();
            variables.put("permission", "通过");
            taskService.complete(task.getId(), variables);
        }
    }

    @Test
    public void queryBossTask() throws JsonProcessingException {
        // 根据流程的设置候选组，获取需要处理的任务
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("boss").list();
        for (Task task : tasks) {
            // 获取任务的参数
            Map<String, Object> variables = taskService.getVariables(task.getId());
            // {"submit":"提交","permission":"通过","user":"小明","day":5}
            System.out.println(mapper.writeValueAsString(variables));
        }
    }

    @Test
    public void approveBossTask() {
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("boss").list();
        for (Task task : tasks) {
            // 添加参数，完成审批
            Map<String, Object> variables = new HashMap<>();
            variables.put("permission", "不通过");
            taskService.complete(task.getId(), variables);
        }
    }
}
