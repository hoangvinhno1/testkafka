//package com.demo;
//
//import vn.vcc.bigdata.news.global.SourceNews;
//
//import java.util.Calendar;
//
///**
// * Created by PC0353 on 11/10/2016.
// */
//public class UpdateHistoryMng implements Runnable{
//    @Override
//    public void run() {
//        Calendar calendar = Calendar.getInstance();
//        if(calendar.get(Calendar.HOUR) == 5 && ServerMonitor.saveDB){
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            SimpleConsumer simpleConsumer = SimpleConsumer.getInstance();
//            for (int sourceId : SourceNews.ALL_TESTING_SOURCE) {
//                simpleConsumer.getMsgFromTopic(sourceId);
//            }
//            ServerMonitor.saveDB = false;
//
//        }
//        if (calendar.get(Calendar.HOUR) == 6){
//            ServerMonitor.saveDB = true;
//        }
//    }
//}
