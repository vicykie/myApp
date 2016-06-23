package org.vicykie.framework.myApp.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.vicykie.framework.myApp.common.entity.authority.User;
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
    public ResponseVO<User> addUser(@PathVariable("id") int id) {
        User user = new User();
//        user.setId(id);
        user.setName("vicykie");
        userDAO.addUser(user);
        User u = userDAO.getUserById(id);
        return ResponseVO.getDataSuccess(u, "获取成功");
    }

    @RequestMapping("/test")
    public String test(Model model) {
        model.addAttribute("user", userDAO.getUserById(22));
        return "user/info";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", userDAO.getUserById(1));
        return "user/info";
    }

    @RequestMapping(value = "/t", method = RequestMethod.PATCH)
    public String tes(Model model, @RequestBody Object statisticData) {
        System.out.println(statisticData != null);
        model.addAttribute("user", userDAO.getUserById(1));
        return "user/info";
    }

}
