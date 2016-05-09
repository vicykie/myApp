package org.vicykie.framework.myApp.config.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.vicykie.framework.myApp.common.constants.MongoDBKeyConstants;
import org.vicykie.framework.myApp.common.prop.MongoDBProp;

import java.net.UnknownHostException;

/**
 * mongoDB 配置
 * @author vicykie
 */
@Configuration
@EnableMongoRepositories
public class MongoDBConfig implements MongoDBKeyConstants {
    private static Logger logger = LogManager.getLogger(MongoDBConfig.class);

    @Bean(name = {"mongoDBProp", "mp", "mongoProp"})
    @Autowired
    public MongoDBProp mongoDBProp(Environment environment) {
        logger.info("mongodb init..");
        return new MongoDBProp(environment.getProperty(KEY_DB_PORT, Integer.class)
                , environment.getProperty(KEY_DB_HOST), environment.getProperty(KEY_DB_CONNECT_TIMEOUT, Integer.class), environment.getProperty(KEY_DB_NAME));
    }

    @Bean
    @Autowired
    public MongoClientOptions mongoClientOptions(MongoDBProp mongoDBProp) {
        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        //mongodb  config
        builder.connectTimeout(mongoDBProp.getConnectTimeOut());
        builder.description("mongodb description..");
        return builder.build();
    }

    @Bean
    @Autowired
    public MongoClient mongoClient(MongoDBProp mongoDBProp,MongoClientOptions mongoClientOptions) {
        try {
            MongoClient mc = new MongoClient(mongoDBProp.getHost(),mongoClientOptions);
            return mc;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    @Autowired
    public MongoDbFactory mongoDbFactory(MongoClient mongoClient,MongoDBProp mongoDBProp) {
        return new SimpleMongoDbFactory(mongoClient, mongoDBProp.getDbName());
    }

    @Bean
    @Autowired
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory){
        return new MongoTemplate(mongoDbFactory);
    }

}
