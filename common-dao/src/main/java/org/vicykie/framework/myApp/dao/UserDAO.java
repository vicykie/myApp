package org.vicykie.framework.myApp.dao;


import org.vicykie.framework.myApp.common.entity.authority.User;

/**
 * Created by vicykie on 2016/5/5.
 */
public interface UserDAO {
    int addUser(User user);
    int deleteUser(User user);

    User getUserById(int id);

    User getUserByUsername(String username);
}
