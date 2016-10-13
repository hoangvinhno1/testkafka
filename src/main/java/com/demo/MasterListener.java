//package com.demo;
//
//import vn.vcc.news.dataface.flow.counter.LimitGuidView;
//import vn.vcc.news.dataface.flow.counter.LimitView;
//import vn.vcc.news.dataface.flow.manager.*;
//
//import java.util.concurrent.ScheduledThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//public class MasterListener {
//	private ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(7);
//
//	public void start() {
//		System.out.println("Master update service started...");
//		stpe.scheduleWithFixedDelay(new UpdateHistoryMng(),0,1,TimeUnit.SECONDS);
//		stpe.scheduleWithFixedDelay(new NewsUpdaterMng(), 0, 30, TimeUnit.SECONDS);
//		stpe.scheduleWithFixedDelay(new VerifierMng(), 3, 10, TimeUnit.SECONDS);
//		stpe.scheduleWithFixedDelay(new MonitorMng(), 0, 1, TimeUnit.SECONDS);
//		stpe.scheduleWithFixedDelay(new LimitView(), 0, 1, TimeUnit.HOURS);
//		stpe.scheduleWithFixedDelay(new LimitGuidView(), 0, 5, TimeUnit.MINUTES);
//		stpe.scheduleWithFixedDelay(new StatsUpdateMng(), 0, 1, TimeUnit.MINUTES);
//	}
//
//	public void stop() {
//		stpe.shutdown();
//	}
//
//}
