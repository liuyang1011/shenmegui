package com.dc.esb.servicegov.dao.impl;

import com.dc.esb.servicegov.dao.support.HibernateDAO;
import org.springframework.stereotype.Repository;
import java.util.*;

import com.dc.esb.servicegov.entity.InterfaceHead;

@Repository
public class InterfaceHeadDAOImpl extends HibernateDAO<InterfaceHead, String> {
//    public InterfaceHead getByName(String name){
//        String hql = "from InterfaceHead where headName like ?";
//        List<InterfaceHead> list = find(hql, name);
//        return list.get(0);
//    }
}