package com.dc.esb.servicegov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/9.
 * SDA, IDA 附加函数
 */
@Entity
@Table(name="ATT_FUNCTION")
public class AttFunction implements Serializable{
    private static final long serialVersionUID = -1L;

    @Id
    @Column(name="ID")
    private String id;

    @Column(name="CATEGORY")
    private String category;//分类：ida，sda
    @Column(name="TARGET_ID")
    private String targetId; //目标id， ida的id或 sdaId
    @Column(name="TYPE")
    private String type;//类型：固定值，表达式
    @Column(name="NAME")
    private String name;//中文名称
    @Column(name="FUNC_NAME")
    private String funcName;//函数名称
    @Column(name="DES")
    private String des;//描述
    @Column(name="OPT_USER")
    private String optUser;
    @Column(name="OPT_DATE")
    private String optDate;

    private String params;//参数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getOptUser() {
        return optUser;
    }

    public void setOptUser(String optUser) {
        this.optUser = optUser;
    }

    public String getOptDate() {
        return optDate;
    }

    public void setOptDate(String optDate) {
        this.optDate = optDate;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
