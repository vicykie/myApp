package org.vicykie.framework.myApp.dao.mongo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.vicykie.framework.myApp.common.entity.authority.User;
import org.vicykie.framework.myApp.dao.UserDAO;

/**
 * Created by d on 2016/5/5.
 */
@Repository("mongoUserDAO")
public class MongoUserDAOImpl implements UserDAO {
    @Autowired
    MongoTemplate template;

    @Override
    public int addUser(User user) {
//        Set<String> collections = template.getCollectionNames();
//        collections.forEach(System.out::println);
        template.insert(user);
        return 0;
    }

    @Override
    public int deleteUser(User user) {
        template.remove(user);
        return 0;
    }

    @Override
    public User getUserById(int id) {
        Criteria criteria = new Criteria("_id");
        criteria.is(id);
        Query query = new Query(criteria);

        return template.findOne(query, User.class);
    }

    @Override
    public User getUserByUsername(String username) {
        Criteria criteria = new Criteria("username");
        criteria.is(username);
        Query query = new Query(criteria);
//        Query query = new Query(criteria);
        return template.findOne(query, User.class);
    }

}
