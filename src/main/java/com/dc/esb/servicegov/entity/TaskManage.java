package com.dc.esb.servicegov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by lmd on 2016/1/27.
 */
@Entity
@Table(name = "TASK_MANAGE")
public class TaskManage implements Serializable{
    @Id
    @Column(name = "TASKNUM")
    private String taskNum;
    @Column(name = "WORKFLOW")
    private String workFlow;
    @Column(name = "TASKPOINT")
    private String taskPoint;
    @Column(name = "RESPONSIBILITY")
    private String responsibility;
    @Column(name = "URGENCYDEGREE")
    private String urgencyDegree;
    @Column(name = "TASKDESCRIBE")
    private String taskDescribe;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "STARTDATE")
    private String startDate;
    @Column(name = "ENDDATE")
    private String endDate;
    @Column(name = "AUTHORITY")
    private String authority;

    public String getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum;
    }

    public String getWorkFlow() {
        return workFlow;
    }

    public void setWorkFlow(String workFlow) {
        this.workFlow = workFlow;
    }

    public String getTaskPoint() {
        return taskPoint;
    }

    public void setTaskPoint(String taskPoint) {
        this.taskPoint = taskPoint;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public String getUrgencyDegree() {
        return urgencyDegree;
    }

    public void setUrgencyDegree(String urgencyDegree) {
        this.urgencyDegree = urgencyDegree;
    }

    public String getTaskDescribe() {
        return taskDescribe;
    }

    public void setTaskDescribe(String taskDescribe) {
        this.taskDescribe = taskDescribe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}