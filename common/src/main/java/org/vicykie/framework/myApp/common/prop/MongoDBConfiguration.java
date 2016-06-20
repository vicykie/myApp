package org.vicykie.framework.myApp.common.prop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by vicykie on 2016/5/4.
 * mongoDb 相关配置
 */
@Component
public class MongoDBConfiguration {


    private int port;
    private String host;
    private int connectTimeOut;
    private String dbName;

    @Autowired
    protected MongoDBConfiguration(Environment environment) {
        this.port = environment.getProperty("mongo.port", Integer.class);
        this.host = environment.getProperty("mongo.host");
        this.connectTimeOut = environment.getProperty("mongo.connect.timeout", Integer.class);
        this.dbName = environment.getProperty("mongo.dbName");
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getConnectTimeOut() {
        return connectTimeOut;
    }

    public void setConnectTimeOut(int connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}