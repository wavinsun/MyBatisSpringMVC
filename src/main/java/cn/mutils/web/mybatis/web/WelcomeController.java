package cn.mutils.web.mybatis.web;

import cn.mutils.web.mybatis.model.User;
import cn.mutils.web.mybatis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class WelcomeController {

    private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        logger.debug("index() is executed!");

        model.put("msg", userService.getById(1).getName());

        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public User json() {
        User u = new User();
        u.setId(1);
        u.setName("wavinsun");
        u.setPass("wavinsun");
        return u;
    }
}
