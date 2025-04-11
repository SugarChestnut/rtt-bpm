package cn.rentaotao.flowable.listener.leave;

import cn.rentaotao.flowable.entity.LeaveEntity;
import cn.rentaotao.flowable.mapper.LeaveMapper;
import lombok.AllArgsConstructor;
import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * 监听表达式，会调用这个方法，构建一个实体类插入数据库
 *
 * @author rtt
 * @date 2025/4/10 17:28
 */
@Service
@AllArgsConstructor
public class LeaveStartListener {

    private final LeaveMapper leaveMapper;

    public LeaveEntity newLeave(DelegateExecution execution) {
        LeaveEntity leaveEntity = new LeaveEntity();

        leaveEntity.setProcessInstanceId(execution.getProcessInstanceId());
        leaveEntity.setStartTime(execution.getVariable("startTime", LocalDate.class));
        leaveEntity.setEndTime(execution.getVariable("endTime", LocalDate.class));
        leaveEntity.setReason(execution.getVariable("reason", String.class));
        leaveMapper.insert(leaveEntity);

        return leaveEntity;
    }
}
