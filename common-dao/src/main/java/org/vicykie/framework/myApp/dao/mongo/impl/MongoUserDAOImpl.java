package org.vicykie.framework.myApp.dao.mongo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.vicykie.framework.myApp.common.entity.User;
import org.vicykie.framework.myApp.dao.UserDAO;

import java.util.Set;

/**
 * Created by d on 2016/5/5.
 */
@Repository("mongoUserDAO")
public class MongoUserDAOImpl implements UserDAO{
    @Autowired
    MongoTemplate template;

    @Override
    public int addUser(User user) {
        Set<String> collections = template.getCollectionNames();
        for (String name : collections
                ) {
            System.out.println(name);
        }
        template.insert(user);
        return 0;
    }

    @Override
    public int deleteUser(User user) {
        return 0;
    }
}
