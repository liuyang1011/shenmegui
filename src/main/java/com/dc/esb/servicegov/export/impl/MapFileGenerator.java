package com.dc.esb.servicegov.export.impl;

import com.dc.esb.servicegov.entity.OperationPK;
import com.dc.esb.servicegov.export.task.ExportMapFileTask;
import com.dc.esb.servicegov.export.task.IExportMapFileTask;
import com.dc.esb.servicegov.vo.OperationPKVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class MapFileGenerator {
	Log log = LogFactory.getLog(MapFileGenerator.class);
	@Autowired
	private MapFileDataFromDB db;
	CountDownLatch countDown = null;

	public static Map<String, Map<String, Object>> headCache = new ConcurrentHashMap<String, Map<String, Object>>();

	public void generate(OperationPKVO pkvo, String path){
		List<OperationPK> pks = pkvo.getPks();
		List<IExportMapFileTask> taskLst = new ArrayList<IExportMapFileTask>();
		countDown = new CountDownLatch(pks.size());
		for(OperationPK pk : pks){
			IExportMapFileTask task = new ExportMapFileTask();
			task.init(pk,path, db, countDown);
			taskLst.add(task);
		}
		ExecutorService executor = Executors.newFixedThreadPool(20);
		for (IExportMapFileTask t : taskLst) {
			executor.execute(t);
		}
		try {
			countDown.await();
//			if(!countDown.await(60 * 10, TimeUnit.SECONDS)){
//				log.error("**************指定时间内未完成全部导出，剩余数量：" + countDown.getCount() + "***************");
//			}
		} catch (InterruptedException e) {
			log.error("MapFileGenerator countDown : " + countDown.getCount());
			e.printStackTrace();
		}
	}
	
}
