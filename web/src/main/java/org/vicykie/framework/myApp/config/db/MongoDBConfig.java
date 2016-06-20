package org.vicykie.framework.myApp.config.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.vicykie.framework.myApp.common.prop.MongoDBConfiguration;

import java.net.UnknownHostException;

/**
 * mongoDB 配置
 *
 * @author vicykie
 */
@Configuration
@EnableMongoRepositories
public class MongoDBConfig {
    private static Logger logger = LogManager.getLogger(MongoDBConfig.class);

    @Bean
    @Autowired
    public MongoClientOptions mongoClientOptions(MongoDBConfiguration mongoDBConfiguration) {

        logger.info("mongodb init...");
        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        builder.connectTimeout(mongoDBConfiguration.getConnectTimeOut());
        builder.description("mongodb description..");
        return builder.build();
    }

    @Bean
    @Autowired
    public MongoClient mongoClient(MongoDBConfiguration mongoDBConfiguration, MongoClientOptions mongoClientOptions) {
        try {
            ServerAddress serverAddress = new ServerAddress(mongoDBConfiguration.getHost(), mongoDBConfiguration.getPort());
            MongoClient mc = new MongoClient(serverAddress, mongoClientOptions);
            return mc;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    @Autowired
    public MongoDbFactory mongoDbFactory(MongoClient mongoClient, MongoDBConfiguration mongoDBConfiguration) {
        return new SimpleMongoDbFactory(mongoClient, mongoDBConfiguration.getDbName());
    }

    @Bean
    @Autowired
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) {
        return new MongoTemplate(mongoDbFactory);
    }

}
