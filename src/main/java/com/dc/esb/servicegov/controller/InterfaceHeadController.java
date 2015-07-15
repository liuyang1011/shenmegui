package com.dc.esb.servicegov.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dc.esb.servicegov.entity.Ida;
import com.dc.esb.servicegov.entity.InterfaceHead;
import com.dc.esb.servicegov.service.IdaService;
import com.dc.esb.servicegov.service.InterfaceHeadService;
import com.dc.esb.servicegov.util.TreeNode;

@Controller
@RequestMapping("/interfaceHead")
public class InterfaceHeadController {

	@Autowired
	private InterfaceHeadService interfaceHeadService;
	
	@Autowired
	private IdaService idaService;

	@RequestMapping(method = RequestMethod.GET, value = "/getAll", headers = "Accept=application/json")
	public @ResponseBody
	List<TreeNode> getAll() {
		List<InterfaceHead> heads = interfaceHeadService.getAll();

		List<TreeNode> trees = new ArrayList<TreeNode>();
		TreeNode root = new TreeNode();
		root.setId("root");
		root.setText("报文头管理");
		for (InterfaceHead head : heads) {
			TreeNode tree = new TreeNode();
			tree.setId(head.getHeadId());
			tree.setText(head.getHeadName());
			trees.add(tree);
		}
		root.setChildren(trees);
		List<TreeNode> rootList = new ArrayList<TreeNode>();
		rootList.add(root);
		return rootList;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/add", headers = "Accept=application/json")
	public @ResponseBody
	boolean save(@RequestBody
	InterfaceHead head) {
		
		boolean add = false;
		if(head.getHeadId()==null || "".equals(head.getHeadId())){
			add = true;
		}
		interfaceHeadService.save(head);
		
		//添加报文，自动生成固定报文头<root><request><response>
		if(add){
			//root
			Ida ida = new Ida();
			ida.setHeadId(head.getHeadId());
			ida.set_parentId(null);
			ida.setStructName("root");
			ida.setStructAlias("根节点");

			idaService.save(ida);
			String parentId = ida.getId();
			
			ida = new Ida();
			ida.setHeadId(head.getHeadId());
			ida.set_parentId(parentId);
			ida.setStructName("request");
			ida.setStructAlias("请求头");
			ida.setSeq(0);
			idaService.save(ida);
			
			ida = new Ida();
			ida.setHeadId(head.getHeadId());
			ida.set_parentId(parentId);
			ida.setStructName("response");
			ida.setStructAlias("响应头");
			ida.setSeq(1);
			idaService.save(ida);
		}
		return true;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/edit/{headId}", headers = "Accept=application/json")
	public ModelAndView getInterfaceHead(@PathVariable
	String headId) {

		InterfaceHead head = interfaceHeadService.getById(headId);
		ModelAndView modelAndView = new ModelAndView();  
        modelAndView.addObject("head", head);  
        modelAndView.setViewName("sysadmin/header_edit");  
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/delete/{headId}", headers = "Accept=application/json")
	public @ResponseBody
	boolean delete(@PathVariable
			String headId) {
		interfaceHeadService.deleteById(headId);
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("headId", headId);
//		List<Ida> idas = idaService.findBy(params);
//		for(Ida ida :idas){
//			idaService.deleteById(ida.getId());
//		}
		return true;
	}
}
