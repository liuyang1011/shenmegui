package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.IdaDAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.Ida;
import com.dc.esb.servicegov.service.IdaService;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IdaServiceImpl extends AbstractBaseService<Ida, String> implements IdaService{
	@Autowired
	private IdaDAOImpl idaDAOImpl;

	@Override
	public HibernateDAO<Ida, String> getDAO() {
		return idaDAOImpl;
	}

	public void deletes(String [] ids){
		for(String id:ids){
			idaDAOImpl.delete(id);
		}
	}

	@Override
	public void saveOrUpdate(Ida[] idas) {
		for(Ida ida:idas){
			idaDAOImpl.save(ida);
		}
	}

    @Override
    public boolean updateMetadataId(String metadataId, String id) {
        String hql = " update "+ Ida.class.getName() + " set metadataId = ? where id = ?";
        idaDAOImpl.batchExecute(hql, metadataId, id);
        return true;
    }

	public boolean deleteList(List<Ida> list){
		idaDAOImpl.delete(list);
		return true;
	}
}
