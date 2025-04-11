package cn.rentaotao.flowable.controller;

import cn.rentaotao.flowable.entity.LeaveEntity;
import lombok.AllArgsConstructor;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author rtt
 * @date 2025/4/11 13:26
 */
@RestController
@RequestMapping("/leave")
@AllArgsConstructor
public class LeaveController {

    private final RuntimeService runtimeService;

    private final TaskService taskService;

    @RequestMapping("/submit")
    public String createLeave(@RequestBody LeaveEntity leave) {
        /*
            使用的是最新版本
         */
        runtimeService.startProcessInstanceByKey("leave", new HashMap<>());
        /*
            通过表单启动
         */
        runtimeService.startProcessInstanceWithForm("", "", new HashMap<>(), "");
        return "ok";
    }
}
