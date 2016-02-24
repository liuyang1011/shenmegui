package com.dc.esb.servicegov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/20.
 */
@Entity
@Table(name="ESB_SERVER")
public class EsbServer implements Serializable {
    @Id
    @Column(name = "id")
    private String serverId;
    @Column(name = "server_name")
    private String serverName;
    @Column(name = "server_ip")
    private String serverIp;
    @Column(name = "in_config_path")
    private String inConfigPath;
    @Column(name = "out_config_path")
    private String outConfigPath;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_psw")
    private String userPsw;
    @Column(name = "remark")
    private String remark;
    @Column
    private String state;
    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getInConfigPath() {
        return inConfigPath;
    }

    public void setInConfigPath(String inConfigPath) {
        this.inConfigPath = inConfigPath;
    }

    public String getOutConfigPath() {
        return outConfigPath;
    }

    public void setOutConfigPath(String outConfigPath) {
        this.outConfigPath = outConfigPath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
