package com.dc.esb.servicegov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by lmd on 2016/2/29.
 */
@Entity
@Table(name = "TASK_OPINION")
public class TaskOpinion implements Serializable {
    @Id
    @Column(name = "TASK_ID")
    private long taskId;
    @Column(name = "PROCESS_INSTANCE_ID")
    private long processInstanceId;
    @Column(name = "TASK_TYPE")
    private String taskType;
    @Column(name = "OPINION")
    private String opinion;
    @Column(name = "ROLLBACK_OPINION")
    private String rollbackOpinion;
    @Column(name = "TASK_STATUS")
    private String taskStatus;

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getRollbackOpinion() {
        return rollbackOpinion;
    }

    public void setRollbackOpinion(String rollbackOpinion) {
        this.rollbackOpinion = rollbackOpinion;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(long processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}
