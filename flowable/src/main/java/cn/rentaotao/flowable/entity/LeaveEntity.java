package cn.rentaotao.flowable.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

/**
 * 通过自定义表，有利于查询优化
 *
 * @author rtt
 * @date 2025/4/10 17:01
 */
@Data
@TableName("leave_t")
public class LeaveEntity {

    @TableId("id")
    private Integer id;
    /**
     * flowable流程ID
     */
    private String processInstanceId;

    private LocalDate startTime;

    private LocalDate endTime;

    private String reason;

    private Boolean approved;
}
