package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.entity.OperationLog;
import com.dc.esb.servicegov.entity.ProcessContext;
import com.dc.esb.servicegov.process.impl.JbpmSupport;
import com.dc.esb.servicegov.service.impl.ProcessContextServiceImpl;
import com.dc.esb.servicegov.service.impl.GetAllFlowServiceImpl;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
import com.dc.esb.servicegov.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.drools.runtime.StatefulKnowledgeSession;
import org.jbpm.task.*;
import org.jbpm.task.query.TaskSummary;
import org.jbpm.task.service.ContentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by vincentfxz on 15/7/5.
 */
@Controller
@RequestMapping("process")
public class ProcessController {

    private static final Log log = LogFactory.getLog(ProcessController.class);

    @Autowired
    private JbpmSupport jbpmSupport;
    @Autowired
    private GetAllFlowServiceImpl getAllFlowService;

    @Autowired
    private ProcessContextServiceImpl processContextService;

    @RequestMapping("/")
    public String test() {
        return "index";
    }

    @RequestMapping("{user}/list")
    public
    @ResponseBody
    List<TaskSummary> list(@PathVariable("user") String user, Model model) {
        log.info(user + " list his tasks");
        TaskService taskService = jbpmSupport.getTaskService();
        List<TaskSummary> tasks = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
        log.info("\n***Task size::" + tasks.size() + "***\n");
        for (TaskSummary taskSummary:tasks) {
            log.info(taskSummary.getId() + " :: " + taskSummary.getActualOwner());
        }
        return tasks;
    }

    @RequestMapping(value = "{user}/delegate/{targetUser}/task/{taskId}", method = RequestMethod.GET)
    public
    @ResponseBody
    boolean delegate(@PathVariable("user") String user, @PathVariable("targetUser") String targetUser, @PathVariable("taskId") Long taskId, Model model) {
        TaskService taskService = jbpmSupport.getTaskService();
        taskService.delegate(taskId, user, targetUser);
        return true;
    }

    @RequestMapping(value = "{user}/create/{type}", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean doCreate(@PathVariable("user") String user, @PathVariable("type") String type, @RequestBody Map<String, Object> params, Model model) {
        log.info(user + " create a process [" + type + "]");
        StatefulKnowledgeSession ksession = jbpmSupport.getKsession();
        try{
            ksession.startProcess("com.dc.esb.servicegov.process." + type, params);
            ksession.fireAllRules();
        }catch (Exception e){
            log.error(e, e);
        }
        return true;
    }


    @RequestMapping(value = "{user}/complete/{task}/task/{message}", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean complete(@PathVariable("user") String user, @PathVariable("task") Long taskId, @PathVariable("message") String message,@RequestBody Map<String, Object> params) {
        String tMessage = URLDecoder.decode(message);
        log.info(user + " complete work on task " + taskId);
        TaskService taskService = jbpmSupport.getTaskService();
        ContentData contentData = null;
        try {
             if (params != null) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream outs;

                    outs = new ObjectOutputStream(bos);
                    outs.writeObject(params);
                    outs.close();
                    contentData = new ContentData();
                    contentData.setContent(bos.toByteArray());
                    contentData.setAccessType(AccessType.Inline);
                }
            taskService.complete(taskId, user, contentData);
        } catch (IOException e) {
            log.error(e, e);
        } catch (Exception e) {
            log.error(e, e);
        }
        processContextService.checkRollback(taskId);
        processContextService.completeOpinion(taskId, tMessage);
        return true;
    }

    @RequestMapping(value = "{user}/work/{task}", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean doWork(@PathVariable("user") String user, @PathVariable("task") Long taskId,
                   @RequestBody Map<String, String> taskInfo) {
        log.info(user + " complete work on task " + taskId);
        TaskService taskService = jbpmSupport.getTaskService();
        Task task = taskService.getTask(taskId);
        if(task.getTaskData().getStatus().name().equals("InProgress")){
            return true;
        }
        taskService.start(taskId, user);
        return true;
    }

    @RequestMapping(value = "/metadataByTask/process/{processId}/task/{taskId}", method = RequestMethod.GET)
    public ModelAndView createMetadataTask(@PathVariable("processId") Long processId, @PathVariable("taskId") String taskId) {
        ModelAndView modelAndView = new ModelAndView("metadata/task/metadataByTask");
        modelAndView.addObject(processId);
        modelAndView.addObject(taskId);
        return modelAndView;
    }

    @RequestMapping(value = "/metadataAuditByTask/process/{processId}/task/{taskId}", method = RequestMethod.GET)
    public ModelAndView auditMetadataTask(@PathVariable("processId") Long processId, @PathVariable("taskId") String taskId) {
        ModelAndView modelAndView = new ModelAndView("metadata/task/metadataAuditByTask");
        modelAndView.addObject(processId);
        modelAndView.addObject(taskId);
        return modelAndView;
    }

    @RequestMapping(value = "/{jspPath}/{auditTask}/process/{processId}/task/{taskId}", method = RequestMethod.GET)
    public ModelAndView auditTask(@PathVariable("jspPath") String jspPath,@PathVariable("auditTask") String auditTaskName,@PathVariable("processId") Long processId, @PathVariable("taskId") String taskId) {
        ModelAndView modelAndView = new ModelAndView("taskAudit/"+jspPath+"/"+auditTaskName);
        modelAndView.addObject(processId);
        modelAndView.addObject(taskId);
        return modelAndView;
    }

    @RequestMapping(value = "/getContext/{processId}", method = RequestMethod.GET)
    public @ResponseBody List<ProcessContext> getProcessContext(@PathVariable("processId") String processId){
//        List<ProcessContext> processContexts = processContextService.findBy("processId", processId);
        Map<String, String> params = new HashMap<String, String>();
        params.put("processId", processId);
        List<ProcessContext> processContexts = processContextService.findBy(params,"optDate");
        if(null == processContexts){
            processContexts = new ArrayList<ProcessContext>();
        }
        return processContexts;
    }

    @RequestMapping(value = "{actualOwner}/getContext/{processId}/task/{taskName}", method = RequestMethod.GET)
    public @ResponseBody List<ProcessContext> getProcessContext(@PathVariable("processId") String processId,@PathVariable("actualOwner") String optUser,@PathVariable("taskName") String taskName){
//        List<ProcessContext> processContexts = processContextService.findBy("processId", processId);
//        String name = URLDecoder.decode(taskName, "UTF-8");
        String tName = URLDecoder.decode(taskName);
        String tUser = URLDecoder.decode(optUser);
        Map<String, String> params = new HashMap<String, String>();
        params.put("processId", processId);
        //新建一个空的集合用来存放所有的流程
        List<ProcessContext> processContexts = new ArrayList<ProcessContext>();
        //将已完成的任务放入集合中
        processContexts.addAll(getAllFlowService.filtration(processContextService.findBy(params,"optDate")));
        for(ProcessContext task:processContexts){
            task.setColourFlag("0");
        }
        //将要正在做的任务放入集合中
        if(null == processContexts){
            processContexts = new ArrayList<ProcessContext>();
        }
        ProcessContext nowProcessContext = getAllFlowService.getNowTask(tUser, tName, processId);
        processContexts.add(nowProcessContext);
        //将未做的任务放入集合中
        processContexts.addAll(getAllFlowService.getNextTask(tName,processId));
        return processContexts;
    }

    @RequestMapping(value = "{user}/obsolete/{taskId}", method = RequestMethod.GET)
    public
    @ResponseBody
    boolean obsolete(@PathVariable("user") String user, @PathVariable("taskId") long processInstanceId, Model model) {
        String tUser = URLDecoder.decode(user);
        List<String> list = processContextService.getPioneerTaskUser(processInstanceId);
        if(list.get(0).equals(tUser)){
            processContextService.obsolete(processInstanceId);
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping(value = "{name}/task/{taskId}/rollback/{processInstanceId}/{rollbackOpinion}", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean resume(@PathVariable("name") String taskName, @PathVariable("taskId") long taskId, @PathVariable("processInstanceId") long processInstanceId,@PathVariable("rollbackOpinion") String rollbackOpinion) {
        String tName = URLDecoder.decode(taskName);
        String tRollbackOpinion = URLDecoder.decode(rollbackOpinion);
        if(getAllFlowService.isPrimalNode(tName)){
            return false;
        }else{
            processContextService.rollback(processInstanceId, taskId);//回退
            processContextService.rollbackOpinion(taskId,tRollbackOpinion);//插入回退意见
            return true;
        }
    }

    @RequestMapping(value = "{priority}/setPriority/{user}/task/{task}", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean setPriority(@PathVariable("task") long taskId,@PathVariable("priority") int priority,@PathVariable("user") String user) {
        TaskService taskService = jbpmSupport.getTaskService();
        if(priority==0){
            taskService.setPriority(taskId, user, priority);
            return true;
        }else if(priority==1){
            taskService.setPriority(taskId,user,priority);
            return true;
        }else if(priority==2){
            taskService.setPriority(taskId,user,priority);
            return true;
        }else{
            return false;
        }
    }
    @ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
    public String processUnauthorizedException() {
        return "403";
    }
}
