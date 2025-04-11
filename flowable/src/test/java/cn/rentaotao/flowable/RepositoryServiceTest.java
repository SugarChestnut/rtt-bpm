package cn.rentaotao.flowable;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author rtt
 * @date 2025/4/11 10:04
 */
@SpringBootTest
public class RepositoryServiceTest {

    @Autowired
    RepositoryService repositoryService;

    /**
     * 部署流程
     */
    @Test
    public void testDeploy() throws FileNotFoundException {
        Deployment deployment = repositoryService.createDeployment()
                // 从文件系统部署，还可以使用url、字符串、字节数组等
                .addInputStream("", new FileInputStream("xx"))
                .name("xxx")
                .key("xxx")
                .enableDuplicateFiltering() /* 避免重复部署 */
                .deploy();

    }

    /**
     * 查询部署的流程
     */
    @Test
    public void testQuery() {
        // 查询部署信息，部署的记录
        List<Deployment> deployments = repositoryService.createDeploymentQuery().list();

        // 查询流程定义
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().list();
    }

    /**
     * 删除流程
     */
    @Test
    public void testDelete() {
        // 删除部署信息，第二个参数表示是否级联删除，会删除流程定义、流程实例、流程任务以及历史信息
        repositoryService.deleteDeployment("", true);
    }

    /**
     * 挂起/启动流程
     */
    @Test
    public void testSuspend() {

        // 挂起流程，不能创建新的实例
        repositoryService.suspendProcessDefinitionById("processDefinitionId");
        // 挂起流程，第二个参数表示，同时暂停实例，第三个参数表示开始挂起的时间
        repositoryService.suspendProcessDefinitionById("processDefinitionId", true, null);

        // 激活流程
        repositoryService.activateProcessDefinitionById("processDefinitionId");
    }
}
