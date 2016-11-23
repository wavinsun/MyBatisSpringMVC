package cn.mutils.web.mybatis.dao.mapper;

import cn.mutils.web.mybatis.common.page.Page;
import cn.mutils.web.mybatis.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wenhua.ywh on 2016/11/8.
 */
@Repository
@Transactional
public interface UserMapper {

    User getUser(@Param("id") Integer id);

    List<User> getUsers(Page<User> page);

    void addUser(@Param("id") Integer id, @Param("name") String name, @Param("pass") String pass);

}
