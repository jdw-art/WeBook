package com.jacob.micro.auth;

import com.jacob.micro.auth.domain.dataobject.UserDO;
import com.jacob.micro.auth.domain.mapper.UserDOMapper;
import com.jacob.micro.framework.common.util.JsonUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
class WebookAuthApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private UserDOMapper userDOMapper;

}
