package org.vicykie.framework.myApp.mongo.dao;

import org.vicykie.framework.myApp.common.entity.User;

/**
 * Created by d on 2016/5/5.
 */
public interface UserDAO {
    int addUser(User user);
    int deleteUser(User user);

}
