package org.vicykie.framework.myApp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.vicykie.framework.myApp.common.entity.authority.User;
import org.vicykie.framework.myApp.dao.UserDAO;

/**
 * Created by vicykie on 2016/6/14.
 */
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountStatusUserDetailsChecker checker = new AccountStatusUserDetailsChecker();
    @Autowired
    UserDAO userDAO;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserByUsername(username);
        if (user == null) throw new UsernameNotFoundException("user not found");
        checker.check(user);
        return user;
    }
}
