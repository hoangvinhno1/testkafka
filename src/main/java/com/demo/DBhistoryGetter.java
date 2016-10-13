//package com.demo;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import vn.vccorp.bigdata.scher.sql.tools.Closer;
//import vn.vccorp.bigdata.scher.sqlconnection.ScherConnectionPool;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by PC0353 on 06/10/2016.
// */
//public class DBhistoryGetter {
//    static Logger logger = LoggerFactory.getLogger(DBhistoryGetter.class);
//
//
//    /**
//     * luu lai viewhistory box test
//     * @param totalViewBoxTest
//     * @return
//     */
//    public boolean saveHistoryView(TotalViewBoxTest totalViewBoxTest){
//        Map<String,Long> viewHour = totalViewBoxTest.getViewHour();
//        Connection conn = null;
//        PreparedStatement statement = null;
//        try {
//            conn = ScherConnectionPool.borrowConnectionFrom("news");
//            statement = conn.prepareStatement("INSERT INTO `news`." + "history_view_box_test"
//                    + " (`date_log`, `sourceId`, `0`, `1`, `2`, "
//                    + "`3`, `4`, `5`, `6`, `7`, "
//                    + "`8`, `9`, `10`, `11`, `12` , `13` , `14` , `15` , `16` , `17` , `18` , `19` , `20` , `21` , `22` , `23`) "
//                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ");
//
//            statement.setString(1,totalViewBoxTest.getDate());
//            statement.setInt(2,totalViewBoxTest.getSourceId());
//            statement.setLong(3,viewHour.get("0"));
//            statement.setLong(4,viewHour.get("1"));
//            statement.setLong(5,viewHour.get("2"));
//            statement.setLong(6,viewHour.get("3"));
//            statement.setLong(7,viewHour.get("4"));
//            statement.setLong(8,viewHour.get("5"));
//            statement.setLong(9,viewHour.get("6"));
//            statement.setLong(10,viewHour.get("7"));
//            statement.setLong(11,viewHour.get("8"));
//            statement.setLong(12,viewHour.get("9"));
//            statement.setLong(13,viewHour.get("10"));
//            statement.setLong(14,viewHour.get("11"));
//            statement.setLong(15,viewHour.get("12"));
//            statement.setLong(16,viewHour.get("13"));
//            statement.setLong(17,viewHour.get("14"));
//            statement.setLong(18,viewHour.get("15"));
//            statement.setLong(19,viewHour.get("16"));
//            statement.setLong(20,viewHour.get("17"));
//            statement.setLong(21,viewHour.get("18"));
//            statement.setLong(22,viewHour.get("19"));
//            statement.setLong(23,viewHour.get("20"));
//            statement.setLong(24,viewHour.get("21"));
//            statement.setLong(25,viewHour.get("22"));
//            statement.setLong(26,viewHour.get("23"));
//            statement.execute();
//        } catch (Exception e) {
//            logger.error("\t save History view error. ", e);
//            return false;
//        } finally {
//            if (conn != null)
//                ScherConnectionPool.returnConnectionto(conn);
//            Closer.safeClose(statement);
//        }
//        return true;
//    }
//
//
//    /**
//     * lay lich su view box test
//     * @param sourceId
//     * @return
//     */
//    public TotalViewBoxTest getHistoryView(int sourceId, String date){
//        Connection conn = null;
//        PreparedStatement statement = null;
//        ResultSet rs = null;
//        TotalViewBoxTest totalViewBoxTest = new TotalViewBoxTest();
//        try {
//            conn = ScherConnectionPool.borrowConnectionFrom("news");
//            statement = conn.prepareStatement("SELECT * FROM " + "history_view_box_test"
//                    + " A WHERE A.`date_log` = ? AND `sourceId` = ?; ");
//            statement.setString(1, date);
//            statement.setInt(2,sourceId);
//            rs = statement.executeQuery();
//            while (rs.next()) {
//                Map<String,Long> viewHour = new HashMap<>();
//                totalViewBoxTest.setDate(date);
//                totalViewBoxTest.setSourceId(sourceId);
//                viewHour.put("0",rs.getLong("0"));
//                viewHour.put("1",rs.getLong("1"));
//                viewHour.put("2",rs.getLong("2"));
//                viewHour.put("3",rs.getLong("3"));
//                viewHour.put("4",rs.getLong("4"));
//                viewHour.put("5",rs.getLong("5"));
//                viewHour.put("6",rs.getLong("6"));
//                viewHour.put("7",rs.getLong("7"));
//                viewHour.put("8",rs.getLong("8"));
//                viewHour.put("9",rs.getLong("9"));
//                viewHour.put("10",rs.getLong("10"));
//                viewHour.put("11",rs.getLong("11"));
//                viewHour.put("12",rs.getLong("12"));
//                viewHour.put("13",rs.getLong("13"));
//                viewHour.put("14",rs.getLong("14"));
//                viewHour.put("15",rs.getLong("15"));
//                viewHour.put("16",rs.getLong("16"));
//                viewHour.put("17",rs.getLong("17"));
//                viewHour.put("18",rs.getLong("18"));
//                viewHour.put("19",rs.getLong("19"));
//                viewHour.put("20",rs.getLong("20"));
//                viewHour.put("21",rs.getLong("21"));
//                viewHour.put("22",rs.getLong("22"));
//                viewHour.put("23",rs.getLong("23"));
//                totalViewBoxTest.setViewHour(viewHour);
//            }
//
//        } catch (Exception e) {
//            logger.error("\t Get History error. ", e);
//            return null;
//        } finally {
//            if (conn != null)
//                ScherConnectionPool.returnConnectionto(conn);
//            Closer.safeClose(statement);
//            Closer.safeClose(rs);
//        }
//        return totalViewBoxTest;
//    }
//
//    public static void main(String[] args) {
////        try {
////            ScherConnectionPool.initAllConnection(ScherConnectionPool.DEPLOY_TYPE);
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        DBhistoryGetter db = new DBhistoryGetter();
////        TotalViewBoxTest t = db.getHistoryView(1,"2016-10-06");
////        System.out.println(t.getViewHour().get("2"));
//    }
//}
