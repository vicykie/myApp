package org.vicykie.framework.myApp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vicykie.framework.myApp.common.entity.User;
import org.vicykie.framework.myApp.common.vo.ResponseVO;
import org.vicykie.framework.myApp.dao.UserDAO;

/**
 * Created by d on 2016/5/5.
 */
@RequestMapping("/user")
@Controller("userCTL")
public class UserCTL {
    @Autowired
    @Qualifier("mongoUserDAO")      //根据名称
    UserDAO userDAO;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO addUser(@PathVariable("id")int id){
        User user = new User();
        user.setId(id);
        user.setName("vicykie");
        User u = userDAO.getUserById(id);
        return ResponseVO.data(u, "获取成功");
    }

    @RequestMapping("/test")
    public String test(Model model) {
        model.addAttribute("user", userDAO.getUserById(22));
        return "user/info";
    }

}
