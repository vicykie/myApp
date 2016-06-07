package org.vicykie.framework.myApp.common.entity.authority;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.vicykie.framework.myApp.common.enums.Status;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by vicykie on 2016/5/5.
 */
@Document(collection = "user")//指定collection的名字

public class User implements Serializable {
    @Id
    @Field(value = "id")
    private int id;
    private String username;
    private String name;
    @Field(value = "create_date")
    private Date createDate = new Date();
    private Date expireDate;
    @JsonSerialize()
    private Status status = Status.ENABLE;
    private int age;
    private int score;
    private Role role;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(String username, String name, Status status, int score, int age) {
        this.username = username;
        this.name = name;
        this.status = status;
        this.score = score;
        this.age = age;
    }

    public User() {
    }

}
