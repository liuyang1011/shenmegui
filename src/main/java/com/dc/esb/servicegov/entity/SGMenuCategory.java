package com.dc.esb.servicegov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by jiangqi on 2015/10/14.
 */
@Entity
@Table(name="SG_MENU_CATEGORY")
public class SGMenuCategory implements Serializable {
    @Id
    @Column(name="ID")
    private String Id;
    @Column(name="TEMP")
    private String temp;
    @Column(name="CHINESE_NAME")
    private String chineseName;
    @Column(name="PARENT_ID")
    private String parentId;

    //构建菜单树时使用的属性
    private String type;
    private String state;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
