package com.dc.esb.servicegov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/9.
 * 函数参数
 */
@Entity
@Table(name="ATT_FUNC_PARAM")
public class AttFuncParam implements Serializable {
    private static final long serialVersionUID = -1L;
    @Id
    @Column(name="ID")
    private String id;

    @Column(name="NAME")
    private String name;
    @Column(name="VALUE")
    private String value;
    @Column(name="DEFAULT_VALUE")
    private String defaultValue;
    @Column(name="SEQ")
    private String seq;


    @Column(name="FUNC_ID")
    private String funcId;

    @Column(name="DES")
    private String des;//描述
    @Column(name="OPT_USER")
    private String optUser;
    @Column(name="OPT_DATE")
    private String optDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getFuncId() {
        return funcId;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId;
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

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }
}
