package cn.mutils.web.mybatis;

import cn.mutils.web.mybatis.common.page.Page;
import cn.mutils.web.mybatis.model.User;
import cn.mutils.web.mybatis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by wenhua.ywh on 2016/11/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-core-config.xml")
public class UserServiceTest {

    private final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Resource
    private UserService userService;

    @Test
    public void testGetUserByPage() {
        Page<User> page = userService.getByPage(1, 10);
        logger.debug("Page size is {}", page.getSize());
    }

}
