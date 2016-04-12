package com.dc.esb.servicegov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by xhx109 on 2016/4/8.
 */
@Entity
@Table(name="release_version")
public class ReleaseVersion {
    @Id
    @Column(name = "id")
    public String id;

    public String version;

    public String createDate;
    public String createUser;
    public String remark;


}
