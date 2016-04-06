package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.entity.ProcessContext;
import org.jbpm.task.query.TaskSummary;
import com.dc.esb.servicegov.service.impl.ProcessContextServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lmd on 2016/2/23.
 */
@Service
@Transactional
public class GetAllFlowServiceImpl {
    @Autowired
    private ProcessContextServiceImpl processContextService;
    /**拿到当前的节点*/
    public ProcessContext getNowTask(String optUser,String name,String processId){
        ProcessContext nowProcessContext = new ProcessContext();
        nowProcessContext.setName(name);
        nowProcessContext.setOptUser(optUser);
        nowProcessContext.setProcessId(processId);
        nowProcessContext.setColourFlag("1");
        return nowProcessContext;
    }
    /**拿到当前节点后面的所有节点*/
    public List<ProcessContext> getNextTask(String name,String processId){
        ProcessContext processContextArray[] = new ProcessContext[7];
        for(int i = 0;i<7;i++){
            processContextArray[i] = new ProcessContext();
        }
        List<ProcessContext> processContexts = new ArrayList<ProcessContext>();
        String data = "接口定义服务定义服务审核服务发布服务开发服务测试服务上线";
        if(name.endsWith("创建元数据")){
            processContextArray[0].setName("元数据审核");
            processContextArray[0].setProcessId(processId);
            processContextArray[0].setColourFlag("2");
            processContexts.add(processContextArray[0]);
        }
        if(name.endsWith("接口定义")){
            for(int i=0,j=4;i<6;i++){
                processContextArray[i].setName(data.substring(j,j+=4));
                processContextArray[i].setProcessId(processId);
                processContextArray[i].setColourFlag("2");
                processContexts.add(processContextArray[i]);
            }
        }
        if(name.equals("接口需求上传")){
            for(int i=0,j=0;i<7;i++){
                processContextArray[i].setName(data.substring(j,j+=4));
                processContextArray[i].setProcessId(processId);
                processContextArray[i].setColourFlag("2");
                processContexts.add(processContextArray[i]);
            }
        }
        if(name.endsWith("服务定义")){
            for(int i=0,j=8;i<5;i++){
                processContextArray[i].setName(data.substring(j,j+=4));
                processContextArray[i].setProcessId(processId);
                processContextArray[i].setColourFlag("2");
                processContexts.add(processContextArray[i]);
            }
        }
        if(name.endsWith("服务审核")){
            for(int i=0,j=12;i<4;i++){
                processContextArray[i].setName(data.substring(j,j+=4));
                processContextArray[i].setProcessId(processId);
                processContextArray[i].setColourFlag("2");
                processContexts.add(processContextArray[i]);
            }
        }
        if(name.endsWith("服务发布")){
            for(int i=0,j=16;i<3;i++){
                processContextArray[i].setName(data.substring(j,j+=4));
                processContextArray[i].setProcessId(processId);
                processContextArray[i].setColourFlag("2");
                processContexts.add(processContextArray[i]);
            }
        }
        if(name.endsWith("服务开发")){
            for(int i=0,j=20;i<2;i++){
                processContextArray[i].setName(data.substring(j,j+=4));
                processContextArray[i].setProcessId(processId);
                processContextArray[i].setColourFlag("2");
                processContexts.add(processContextArray[i]);
            }
        }
        if(name.endsWith("服务测试")){
                processContextArray[0].setName("服务上线");
                processContextArray[0].setProcessId(processId);
                processContextArray[0].setColourFlag("2");
                processContexts.add(processContextArray[0]);
        }
        if(name.endsWith("创建公共代码")){
            processContextArray[0].setName("公共代码审核");
            processContextArray[0].setProcessId(processId);
            processContextArray[0].setColourFlag("2");
            processContexts.add(processContextArray[0]);
        }
        return processContexts;
    }
    /**筛选出已完成任务中重复的节点名*/
    public List<ProcessContext> filtration (List<ProcessContext> ProcessContexts){
        List<String> allTaskName = new ArrayList<String>();
        for(ProcessContext task:ProcessContexts){
            String opinion = processContextService.getAllOpinion(task.getName(),task.getProcessId());//获取意见，通过节点名称和流程号获得节点
            task.setOpinion(opinion);
            allTaskName.add(task.getName());
        }
        int flag = 0;
        for(Iterator<ProcessContext> it = ProcessContexts.iterator();it.hasNext();) {
            ProcessContext processContext = it.next();
            if(allTaskName.contains(processContext.getName())){//对比重复
                flag++;
                if(flag>1){
                    it.remove();//移除重复
                    flag = 0;
                }
            }
        }
        return ProcessContexts;
    }
    /**判断流程当前节点是否第一节点*/
    public boolean isPrimalNode(String taskName){
        if(taskName.endsWith("创建元数据")||taskName.endsWith("接口需求上传")||taskName.endsWith("创建公共代码")){
            return true;
        }else{
            return false;
        }
    }

}



























