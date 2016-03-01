package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.entity.ProcessContext;
import com.dc.esb.servicegov.process.impl.JbpmSupport;
import com.dc.esb.servicegov.service.impl.ProcessContextServiceImpl;
import com.dc.esb.servicegov.service.support.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.drools.runtime.StatefulKnowledgeSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jbpm.task.*;
import org.jbpm.task.query.TaskSummary;
import org.jbpm.task.service.ContentData;
import org.jbpm.task.service.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private ProcessContextServiceImpl processContextService;

    @RequestMapping("/")
    public String test() {
        return "index";
    }

    @RequestMapping("{user}/list")
    public
    @ResponseBody
    List<TaskSummary> list(@PathVariable("user") String user, Model model,HttpServletRequest req) {
        log.info(user + " list his tasks");
        String processInstanceId=req.getParameter("processInstanceId");
        String taskId=req.getParameter("taskId");
        TaskService taskService = jbpmSupport.getTaskService();
        List<TaskSummary> tasks = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
        log.info("\n***Task size::" + tasks.size() + "***\n");
        List<TaskSummary> newTasks=new ArrayList<TaskSummary>();
        for(int i=0;i<tasks.size();i++){
            log.info(tasks.get(i).getId() + " :: " + tasks.get(i).getActualOwner());
            String  newPid=String.valueOf(tasks.get(i).getProcessInstanceId());
            String  newTid=String.valueOf(tasks.get(i).getId());
            if(processInstanceId!=null||taskId != null) {
                if(newTid.equals(taskId)){
                    newTasks.add(tasks.get(i));
                }else if(newPid.equals(processInstanceId)){
                    newTasks.add(tasks.get(i));
                }
                return newTasks;

            }
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


    @RequestMapping(value = "{user}/complete/{task}", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean complete(@PathVariable("user") String user, @PathVariable("task") Long taskId, @RequestBody Map<String, Object> params) {
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
        return true;
    }
    @RequestMapping(value = "{user}/obsolete/{taskId}", method = RequestMethod.GET)
    public
    @ResponseBody
    boolean obsolete(@PathVariable("user") String user, @PathVariable("taskId") Long taskId, Model model) {
        processContextService.obsolete(taskId);
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

    @ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
    public String processUnauthorizedException() {
        return "403";
    }
}
