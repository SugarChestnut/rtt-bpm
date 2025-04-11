package cn.rentaotao.flowable.mapper;

import cn.rentaotao.flowable.entity.LeaveEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author rtt
 * @date 2025/4/11 08:55
 */
@SpringBootTest
public class LeaveMapperTest {

    @Autowired
    LeaveMapper leaveMapper;

    @Test
    public void testInsert() {
        LeaveEntity leaveEntity = new LeaveEntity();
        leaveMapper.insert(leaveEntity);
    }
}
