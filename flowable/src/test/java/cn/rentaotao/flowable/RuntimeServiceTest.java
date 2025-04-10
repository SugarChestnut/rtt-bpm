package cn.rentaotao.flowable;

import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class RuntimeServiceTest {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    HistoryService historyService;

    /**
     * 创建流程实例
     */
    @Test
    public void testCreateProcessInstance() {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("process", new HashMap<>());
    }
    /**
     * 删除流程实例
     */
    @Test
    public void testDeleteProcessInstance() {
        String processInstanceId = "";
        ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (instance != null) {
            runtimeService.deleteProcessInstance(instance.getId(), "");
        } else {
            historyService.deleteHistoricProcessInstance(processInstanceId);
        }
    }
}
