package cn.mutils.web.mybatis;

import cn.mutils.web.mybatis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Created by wenhua.ywh on 2016/11/23.
 */
public class StartupActivity {

    private static final Logger logger= LoggerFactory.getLogger(StartupActivity.class);

    public void init() throws Exception {
        logger.debug("Startup==>");
    }

}
