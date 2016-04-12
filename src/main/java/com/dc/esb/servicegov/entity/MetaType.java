package com.dc.esb.servicegov.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by xhx113 on 2016/3/4.
 */
@Entity
@Table(name = "META_TYPE")
public class MetaType implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    private String id;
    @Column(name = "LABEL",length = 50)
    private String label;//标签
    @Column(name = "NAME", length = 50)
    private String name;//元数据名称
    @Column(name = "CHINESENAME",length = 255)
    private String chineseName;//元数据中文名称
    @Column(name = "CLASSIFY",length = 50)
    private String classify;//一级分类
    @Column(name = "METADATAID",length = 50)
    private String metadataId;
    @Column(name = "ENGLISH_NAME_ESB",length = 255)
    private String enAllName;//ESB标准英文全称
    @Column(name = "STANDARD_CHNAME",length = 255)
    private String standardChName;//标准中文名称
    @Column(name = "STANDARD_ENNAME",length = 255)
    private String standardEnName;//标准英文名称
    @Column(name = "USEGUIDE",length = 1023)
    private String useGuide;//使用指引
    @Column(name = "STANDARD_ALIAS",length = 255)
    private String standardAlias;//标准别名
    @Column(name = "MEANING",length = 1023)
    private String meaning;//业务含义
    @Column(name = "TYPE", length = 50)
    private String type;//数据类型
    @Column(name = "TYPE_LINK",length = 255)
    private String typeLink;
    @Column(name = "FORMAT",length = 50)
    private String format;//数据格式
    @Column(name = "VALUE_RANGE",length = 50)
    private String valueRange;//取值范围
    @Column(name = "MEASUREMENT",length = 50)
    private String measurement;//度量单位
    @Column(name = "SUSCEPTIBILITY",length = 50)
    private String susceptibility;//敏感度
    @Column(name = "RELEVANT_STANDARD",length = 255)
    private String relevantStandard;//相关标准
    @Column(name = "INFORMATION_RELATION",length = 255)
    private String informationRelation;//与相关信息项关系
    @Column(name = "CODE_RULE",length = 255)
    private String codeRule;//代码编码规则
    @Column(name = "INFORMATION_RULE",length = 1023)
    private String informationRule;//信息项业务规则
    @Column(name = "ACCORDINGBY",length = 255)
    private String accordingBy;//制定依据
//    @Column(name = "AUTHORITY_SYSTEM",length = 127)
//    private String authoritySytem;//权威系统/发文
    @Column(name = "STANDARD_SOURCE",length = 127)
    private String standardSource;//标准初始来源
    @Column(name = "APPLYTO",length = 127)
    private String applyTo;//业务应用领域
    @Column(name = "RELEVANCEMOTIF",length = 127)
    private String relevanceMotif;//相关关联主题
//    @Column(name = "MADESTANDARDUSER",length = 50)
//    private String madeStandardUser;//标准定义者
//    @Column(name = "MANAGESTANDARDUSER",length = 50)
//    private String manageStandardUser;//标准管理者
//    @Column(name = "USESTANDARDUSER",length = 50)
//    private String useStandardUser;//标准使用者
    @Column(name = "VERSIONDATE",length = 20)
    private String versionDate;//版本日期
    @Column(name = "REMARK",length=1023)
    private String remark;//备注
    @Column(name = "OPTUSER",length = 50)
    private String optUser;//操作用户
    @Column(name = "OPTDATE",length = 20)
    private String optDate;//操作时间
//    @Column(name = "SCALE", length = 10)
//    private String scale;//精度
//    @Column(name = "LENGTH", length = 50)
//    private String length;//长度


    public String getUseGuide() {
        return useGuide;
    }

    public void setUseGuide(String useGuide) {
        this.useGuide = useGuide;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

//    public String getScale() {
//        return scale;
//    }
//
//    public void setScale(String scale) {
//        this.scale = scale;
//    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public String getLength() {
//        return length;
//    }
//
//    public void setLength(String length) {
//        this.length = length;
//    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getEnAllName() {
        return enAllName;
    }

    public void setEnAllName(String enAllName) {
        this.enAllName = enAllName;
    }

    public String getStandardChName() {
        return standardChName;
    }

    public void setStandardChName(String standardChName) {
        this.standardChName = standardChName;
    }

    public String getStandardEnName() {
        return standardEnName;
    }

    public void setStandardEnName(String standardEnName) {
        this.standardEnName = standardEnName;
    }

    public String getStandardAlias() {
        return standardAlias;
    }

    public void setStandardAlias(String standardAlias) {
        this.standardAlias = standardAlias;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getValueRange() {
        return valueRange;
    }

    public void setValueRange(String valueRange) {
        this.valueRange = valueRange;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(String metadataId) {
        this.metadataId = metadataId;
    }

    public String getSusceptibility() {
        return susceptibility;
    }

    public void setSusceptibility(String susceptibility) {
        this.susceptibility = susceptibility;
    }

    public String getRelevantStandard() {
        return relevantStandard;
    }

    public void setRelevantStandard(String relevantStandard) {
        this.relevantStandard = relevantStandard;
    }

    public String getInformationRelation() {
        return informationRelation;
    }

    public void setInformationRelation(String informationRelation) {
        this.informationRelation = informationRelation;
    }

    public String getCodeRule() {
        return codeRule;
    }

    public void setCodeRule(String codeRule) {
        this.codeRule = codeRule;
    }

    public String getInformationRule() {
        return informationRule;
    }

    public void setInformationRule(String informationRule) {
        this.informationRule = informationRule;
    }

    public String getAccordingBy() {
        return accordingBy;
    }

    public void setAccordingBy(String accordingBy) {
        this.accordingBy = accordingBy;
    }

//    public String getAuthoritySytem() {
//        return authoritySytem;
//    }
//
//    public void setAuthoritySytem(String authoritySytem) {
//        this.authoritySytem = authoritySytem;
//    }

    public String getStandardSource() {
        return standardSource;
    }

    public void setStandardSource(String standardSource) {
        this.standardSource = standardSource;
    }

    public String getApplyTo() {
        return applyTo;
    }

    public void setApplyTo(String applyTo) {
        this.applyTo = applyTo;
    }

    public String getRelevanceMotif() {
        return relevanceMotif;
    }

    public void setRelevanceMotif(String relevanceMotif) {
        this.relevanceMotif = relevanceMotif;
    }

//    public String getMadeStandardUser() {
//        return madeStandardUser;
//    }
//
//    public void setMadeStandardUser(String madeStandardUser) {
//        this.madeStandardUser = madeStandardUser;
//    }
//
//    public String getManageStandardUser() {
//        return manageStandardUser;
//    }
//
//    public void setManageStandardUser(String manageStandardUser) {
//        this.manageStandardUser = manageStandardUser;
//    }
//
//    public String getUseStandardUser() {
//        return useStandardUser;
//    }
//
//    public void setUseStandardUser(String useStandardUser) {
//        this.useStandardUser = useStandardUser;
//    }

    public String getVersionDate() {
        return versionDate;
    }

    public void setVersionDate(String versionDate) {
        this.versionDate = versionDate;
    }

    public String getTypeLink() {
        return typeLink;
    }

    public void setTypeLink(String typeLink) {
        this.typeLink = typeLink;
    }
}
