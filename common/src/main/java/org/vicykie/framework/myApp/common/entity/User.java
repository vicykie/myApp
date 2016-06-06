package org.vicykie.framework.myApp.common.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.vicykie.framework.myApp.common.enums.Status;

import java.util.Date;
import java.util.Set;

/**
 * Created by vicykie on 2016/5/5.
 */
@Document(collection = "user")//指定collection的名字
public class User {
    @Id
    private int id;
    private String username;
    private String name;
    private Date createDate = new Date();
    private Date expireDate;
    private Status status = Status.ENABLE;
    private int age;
    private int score;

    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

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
