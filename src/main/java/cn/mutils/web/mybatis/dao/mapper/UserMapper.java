package cn.mutils.web.mybatis.dao.mapper;

import cn.mutils.web.mybatis.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wenhua.ywh on 2016/11/8.
 */
@Repository
@Transactional
public interface UserMapper {

    User getUser(Integer id);

}
