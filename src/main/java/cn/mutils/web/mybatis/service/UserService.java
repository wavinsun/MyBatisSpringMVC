package cn.mutils.web.mybatis.service;

import cn.mutils.web.mybatis.common.page.Page;
import cn.mutils.web.mybatis.dao.mapper.UserMapper;
import cn.mutils.web.mybatis.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by wenhua.ywh on 2016/11/7.
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;

    public User getById(Integer id) {
        logger.debug("getById() is executed! $id : {}", id);
        return userMapper.getUser(id);
    }

    public Page<User> getByPage(int pageNo, int pageSize) {
        logger.debug("getByPage() is executed!");
        Page<User> page = new Page<>(pageNo, pageSize);
        userMapper.getUsers(page);
        return page;
    }

    @Transactional
    public void addUser() {
        userMapper.addUser(2, "name2", "pass2");
        userMapper.addUser(1, "name1", "pass2");
    }

    @Transactional
    public void addUserOne() {
        userMapper.addUser(2, "name2", "pass2");
    }

    @Transactional
    public void addUserNest() {
        addUserOne();
        throw new RuntimeException("I am an exception.");
    }


}
