package com.dc.esb.servicegov.vo;

import com.dc.esb.servicegov.entity.TaskManage;
/**
 * Created by lmd on 2016/1/28.
 */
public class TaskVO {
    private String taskNum;
    private String workFlow;
    private String taskPoint;
    private String responsibility;
    private String urgencyDegree;
    private String taskDescribe;
    private String status;
    private String startDate;
    private String endDate;
    public TaskVO(String taskNum,String workFlow,String taskPoint,String responsibility,
                  String urgencyDegree,String taskDescribe,String status,String startDate,
                  String endDate){
        this.taskNum = taskNum;
        this.workFlow = workFlow;
        this.taskPoint = taskPoint;
        this.urgencyDegree = urgencyDegree;
        this.taskDescribe = taskDescribe;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public TaskVO(TaskManage task){
        this.taskNum = task.getTaskNum();
        this.workFlow = task.getWorkFlow();
        this.taskPoint = task.getTaskPoint();
        this.responsibility = task.getResponsibility();
        this.urgencyDegree = task.getUrgencyDegree();
        this.taskDescribe = task.getTaskDescribe();
        this.status = task.getStatus();
        this.startDate = task.getStartDate();
        this.endDate = task.getEndDate();
    }

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
}
