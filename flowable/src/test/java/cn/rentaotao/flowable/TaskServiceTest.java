package cn.rentaotao.flowable;

import org.flowable.engine.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author rtt
 * @date 2025/4/11 10:07
 */
@SpringBootTest
public class TaskServiceTest {

    @Autowired
    TaskService taskService;

    /**
     * 根据流程实例ID查询任务流
     */
    @Test
    public void testQueryByInstanceId() {

    }
}
