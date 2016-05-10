package org.vicykie.framework.myApp.common.enums;

/**
 * Created by vicykie on 2016/5/5.
 */
public enum UserStatus {
    ENABLE("启用"),DISABLED("禁用"),DELETED("删除");
    private String description;

    UserStatus(String description) {
        this.description = description;
    }

    public static int enable(){
        return ENABLE.ordinal();
    }
    public static int disabled(){
        return DISABLED.ordinal();
    }
    public static int deleted(){
        return DELETED.ordinal();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
