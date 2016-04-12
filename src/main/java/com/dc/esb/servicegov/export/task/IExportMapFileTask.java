package com.dc.esb.servicegov.export.task;

import com.dc.esb.servicegov.entity.OperationPK;
import com.dc.esb.servicegov.export.impl.MapFileDataFromDB;

import java.io.File;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public interface IExportMapFileTask extends Runnable {
	
	public void generate();
	
	public void init(OperationPK pk, String path, MapFileDataFromDB db, CountDownLatch countDown);

}
