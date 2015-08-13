package com.dc.esb.servicegov.service;

import com.dc.esb.servicegov.entity.Ida;
import com.dc.esb.servicegov.service.support.BaseService;

import java.util.List;

public interface IdaService  extends BaseService<Ida, String> {
    public void deletes(String [] ids);

    public void saveOrUpdate(Ida[] idas);

    public boolean updateMetadataId(String metadataId, String id);

    public boolean deleteList(List<Ida> list);
}
