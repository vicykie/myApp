package org.vicykie.framework.myApp.common.vo;

/**
 * Created by vicykie on 2016/5/5.
 */
public class ResponseVO {
    private int code;
    private String rspMsg;
    private Object data;

    public ResponseVO(int code, String rspMsg) {
        this.code = code;
        this.rspMsg = rspMsg;
    }

    public Object getData() {

        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {

        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }


    public ResponseVO(int code, String rspMsg, Object data) {
        this.code = code;
        this.rspMsg = rspMsg;
        this.data = data;
    }

    public static ResponseVO success(String rspMsg) {
        return new ResponseVO(1, rspMsg);
    }

    public static ResponseVO error(String rspMsg) {
        return new ResponseVO(0, rspMsg);
    }

    public static ResponseVO data(Object data, String rspMsg) {
        return new ResponseVO(0, rspMsg, data);
    }
}
