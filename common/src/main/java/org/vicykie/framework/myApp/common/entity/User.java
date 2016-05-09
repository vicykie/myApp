package org.vicykie.framework.myApp.common.entity;

import org.springframework.data.annotation.Id;
import org.vicykie.framework.myApp.common.enums.UserStatus;

import java.util.Date;

/**
 * Created by d on 2016/5/5.
 */

public class User {
    @Id
    private int id;
    private String username;
    private String name;
    private Date createDate = new Date();
    private Date expireDate;
    private UserStatus status =UserStatus.ENABLE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
