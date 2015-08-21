package com.dc.esb.servicegov.service;

import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.service.support.BaseService;
import com.dc.esb.servicegov.vo.ReleaseListVO;
import com.dc.esb.servicegov.vo.ReleaseVO;
import com.dc.esb.servicegov.vo.ReuseRateVO;

import java.util.List;
import java.util.Map;

/**
 * Created by wang on 2015/8/14.
 */
public interface StatisticsService{
    public List<ReuseRateVO> getReuseRate(Map<String, String[]> values, Page page);
    public long getReuseRateCount(Map<String, String[]> values);
    public List<ReleaseVO> getReleaseVO(Map<String, String[]> values, Page page);
    public long getReleaseVOCount(Map<String, String[]> values);
    public List<ReuseRateVO> getServiceReuseRate(Map<String, String[]> values);
}
