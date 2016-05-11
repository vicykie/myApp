package org.vicykie.framework.myApp.common.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.vicykie.framework.myApp.common.enums.Status;

/**
 * Created by vicykie on 2016/5/10.
 */
@Document(collection = "auth")
public class Authority {

    @Id
    private int id;
    private String authName;
    private String description;
    private String resourcePaths;
    private Status status = Status.ENABLE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResourcePaths() {
        return resourcePaths;
    }

    public void setResourcePaths(String resourcePaths) {
        this.resourcePaths = resourcePaths;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
