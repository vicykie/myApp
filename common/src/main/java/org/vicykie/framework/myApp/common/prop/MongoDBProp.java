package org.vicykie.framework.myApp.common.prop;

/**
 * Created by vicykie on 2016/5/4.
 */
public class MongoDBProp {
    private int port;
    private String host;
    private int connectTimeOut;
    private String dbName;


    public MongoDBProp(int port, String host, int connectTimeOut, String dbName) {

        this.port = port;
        this.host = host;
        this.connectTimeOut = connectTimeOut;
        this.dbName = dbName;
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