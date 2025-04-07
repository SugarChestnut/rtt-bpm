package cn.rentaotao.flowable;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FlowableTest {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    TaskService taskService;

    @Autowired
    RuntimeService runtimeService;

    @Test
    public void test() {

        long count1 = repositoryService.createProcessDefinitionQuery().count();

        long count = taskService.createTaskQuery().count();

        runtimeService.startProcessInstanceByKey("xxx");
    }
}
