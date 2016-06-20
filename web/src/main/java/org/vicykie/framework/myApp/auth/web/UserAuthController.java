package org.vicykie.framework.myApp.auth.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vicykie.framework.myApp.auth.UserAuthentication;
import org.vicykie.framework.myApp.common.entity.authority.User;
import org.vicykie.framework.myApp.common.util.UserTokenUtil;
import org.vicykie.framework.myApp.common.vo.ResponseVO;

/**
 * Created by vicykie on 2016/6/15.
 */
@Controller
@RequestMapping("/auth")
public class UserAuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserTokenUtil tokenUtil;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<?> login(@RequestBody User user) {
        Authentication authentication = new UserAuthentication(user);
        authenticationManager.authenticate(authentication);
        User userDetails = (User) userDetailsService.loadUserByUsername(user.getUsername());

        String token = tokenUtil.generateToken(userDetails);
        return ResponseVO.getDataSuccess(token, "token获取成功");
    }
}
