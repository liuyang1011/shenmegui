package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.InterfaceDAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.dao.support.SearchCondition;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.*;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import com.dc.esb.servicegov.util.TreeNode;
import com.dc.esb.servicegov.vo.InterfaceExVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.System;
import java.util.*;
@Service
@Transactional
public class InterfaceServiceImpl extends AbstractBaseService<Interface, String> implements InterfaceService{

	private static final Log log = LogFactory.getLog(InterfaceServiceImpl.class);

	@Autowired
	private InterfaceDAOImpl interfaceDAOImpl;
	@Autowired
	private InterfaceHeadService interfaceHeadService;
	@Autowired
	private ProtocolService protocolService;
	@Autowired
	private SystemProtocolService systemProtocolService;
	@Autowired
	private FileManagerService fileManagerService;

	/**
	 * TODO 这里为什么要这么做
	 * @return
	 */
	@Override
	public HibernateDAO<Interface, String> getDAO() {
		return interfaceDAOImpl;
	}

	public Interface getInterfaceById(String hql,String interfaceId){

		List<SearchCondition> searchConds = new ArrayList<SearchCondition>();
		SearchCondition search = new SearchCondition();
		search.setField("interfaceId");
		search.setFieldValue(interfaceId);
		searchConds.add(search);
		List<Interface> inters = interfaceDAOImpl.findBy(hql, searchConds);
		System.out.print(inters.size()+"========================================");
		Interface inter = inters.get(0);
		System.out.print(inter);
		return inter;
	}

	public List<Interface> getBySystemId(String systemId){
		String hql = "select distinct i from " + Interface.class.getName() + " as i , "+ ServiceInvoke.class.getName()
				+" as si where i.interfaceId = si.interfaceId and si.systemId = ?";
		List<Interface> list = interfaceDAOImpl.find(hql, systemId);
		return list;
	}
	public List<Interface> findByConditions(String condition){
		condition = "%" + condition + "%";
		StringBuffer hql = new StringBuffer("select t from Interface t where t.interfaceId like ?");
		hql.append(" or t.desc like ?");
		hql.append(" or t.interfaceName like ?");
		hql.append(" or t.ecode like ?");
		List<Interface> list = interfaceDAOImpl.find(hql.toString(), condition, condition, condition,condition);
		return list;
	}

	@Override
	public List<InterfaceExVO> queryByCondition(Map<String, String[]> values, Page page) {
		return new ArrayList<InterfaceExVO>();
	}

	@Override
	public List<TreeNode> getLeftTreeBySystems(List<com.dc.esb.servicegov.entity.System> systems) {
		List<TreeNode> rootList = new ArrayList<TreeNode>();
		for (com.dc.esb.servicegov.entity.System s : systems) {

			TreeNode interfacesNode = new TreeNode();
			interfacesNode.setId(s.getSystemId());
			interfacesNode.setText("接口");
			interfacesNode.setClick("interfaces");

			TreeNode headsNode = new TreeNode();
			headsNode.setId(s.getSystemId());
			headsNode.setText("报文头");
			headsNode.setClick("heads");
			List<InterfaceHead> heads = interfaceHeadService.findBy("systemId", s.getSystemId());
			List<TreeNode> headTreeNodes = new ArrayList<TreeNode>();
			for (InterfaceHead head : heads) {
				TreeNode treeNode = new TreeNode();
				treeNode.setId(head.getHeadId());
				treeNode.setClick("head");
				treeNode.setText(head.getHeadName());
				headTreeNodes.add(treeNode);
			}
			headsNode.setChildren(headTreeNodes);

			TreeNode protocolNode = new TreeNode();
			protocolNode.setId(s.getSystemId());
			protocolNode.setText("协议");
			protocolNode.setClick("protocols");
			List<TreeNode> protocolTreeNodes = new ArrayList<TreeNode>();
			List<SystemProtocol> systemProtocols = systemProtocolService.findBy("systemId", s.getSystemId());
			for(SystemProtocol systemProtocol : systemProtocols){
				String protocolId = systemProtocol.getProtocolId();
				Protocol protocol = protocolService.getById(protocolId);
				TreeNode treeNode = new TreeNode();
				treeNode.setId(protocol.getProtocolId());
				treeNode.setClick("protocol");
				treeNode.setText(protocol.getProtocolName());
				protocolTreeNodes.add(treeNode);
			}
			protocolNode.setChildren(protocolTreeNodes);

			TreeNode fileNode = new TreeNode();
			fileNode.setId(s.getSystemId());
			fileNode.setText("文档");
			fileNode.setClick("files");

			List<FileManager> files = fileManagerService.findBy("systemId", s.getSystemId());
			List<TreeNode> fileTreeNodes = new ArrayList<TreeNode>();
			for(FileManager file : files){
				String fileName = file.getFileName();
				String id = file.getFileId();
				TreeNode treeNode = new TreeNode();
				treeNode.setId(id);
				treeNode.setText(fileName);
				treeNode.setClick("file");
				fileTreeNodes.add(treeNode);
			}
			fileNode.setChildren(fileTreeNodes);

			List<TreeNode> rootChildren = new ArrayList<TreeNode>();
			rootChildren.add(interfacesNode);
			rootChildren.add(headsNode);
			rootChildren.add(protocolNode);
			rootChildren.add(fileNode);

			TreeNode rootinterface = new TreeNode();
			rootinterface.setId(s.getSystemId());
			rootinterface.setText(s.getSystemChineseName());
			rootinterface.setClick("disable");

			rootinterface.setChildren(rootChildren);

			try {
				List<ServiceInvoke> serviceIns = s.getServiceInvokes();
				List<TreeNode> childList = new ArrayList<TreeNode>();
				for (ServiceInvoke si : serviceIns) {
					TreeNode child = new TreeNode();
					if(null == si.getInter()){
						continue;
					}

					child.setId(si.getInter().getInterfaceId());
					child.setText(si.getInter().getInterfaceName() + "(" + si.getInter().getInterfaceId() + ")");
					if (!contains(childList, child)) {
						childList.add(child);
					}
				}
				Collections.sort(childList, new Comparator<TreeNode>() {

					@Override
					public int compare(TreeNode o1, TreeNode o2) {
						return o1.getText().compareToIgnoreCase(o2.getText());
					}

				});
				interfacesNode.setChildren(childList);
			} catch (Exception e) {
				log.error(e,e);
			}
			rootList.add(rootinterface);
		}
		return rootList;
	}

	private boolean contains(List<TreeNode> childList, TreeNode treeNode) {
		for (TreeNode node : childList) {
			if (node.getId().equals(treeNode.getId())) {
				return true;
			}
		}
		return false;
	}


}
