package com.dc.esb.servicegov.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by xhx113 on 2016/3/21.
 *
 */
@Entity
@Table(name = "CODE_ENUM")
public class MetaTypeCodeEnum{
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    private String id;
    @Column(name = "STANDARD_NUM")
    private String standarNum;//标准编号
    @Column(name = "STANDARD_CHNAME",length = 255)
    private String standardChName;//标准中文名称
    @Column(name = "CODE_VALUE")
    private String codeValue;//代码值
    @Column(name = "CODE_MEANING")
    private String codeMeaning;//代码含义
    @Column(name = "ORTHER",length = 511)
    private String orther;//其他
    @Column(name = "SOURCE")
    private String source;//来源
    @Column(name = "LINK")
    private String link;//链接
    @Column(name = "UPDATE_DATE")
    private String date;//更新日期
    @Column(name = "PERSON_IN_CHARGE")
    private String personInCharge;//责任人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStandarNum() {
        return standarNum;
    }

    public void setStandarNum(String standarNum) {
        this.standarNum = standarNum;
    }

    public String getStandardChName() {
        return standardChName;
    }

    public void setStandardChName(String standardChName) {
        this.standardChName = standardChName;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeMeaning() {
        return codeMeaning;
    }

    public void setCodeMeaning(String codeMeaning) {
        this.codeMeaning = codeMeaning;
    }

    public String getOrther() {
        return orther;
    }

    public void setOrther(String orther) {
        this.orther = orther;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }
}
