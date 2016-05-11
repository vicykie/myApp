package org.vicykie.framework.myApp.common.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * Created by vicykie on 2016/5/10.
 */
@Document(collection = "goods")
public class Goods {
    @Id
    private int id;
    private String name;
    private String descrition;
    private String model;
    private String price;
    private Set<String> picPaths;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Set<String> getPicPaths() {
        return picPaths;
    }

    public void setPicPaths(Set<String> picPaths) {
        this.picPaths = picPaths;
    }
}
