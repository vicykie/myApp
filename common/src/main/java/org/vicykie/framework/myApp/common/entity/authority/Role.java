package org.vicykie.framework.myApp.common.entity.authority;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.vicykie.framework.myApp.common.enums.Status;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


/**
 * Created by vicykie on 2016/5/10.
 */
@Document(collection = "role")
public class Role implements Serializable {
    @Id
    @Field(value = "id")
    private int id;
    private String roleName;
    private String description;
    private Status status = Status.ENABLE;
    @Field(value = "create_date")
    private Date createDate = new Date();
    @Transient
    private Set<Authority> authorities;

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
