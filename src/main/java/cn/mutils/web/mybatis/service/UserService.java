package cn.mutils.web.mybatis.service;

import cn.mutils.web.mybatis.dao.mapper.UserMapper;
import cn.mutils.web.mybatis.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

}
