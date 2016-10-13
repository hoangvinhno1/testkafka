//package com.demo;
//
//import org.json.JSONArray;
//import org.rapidoid.http.MediaType;
//import org.rapidoid.http.Req;
//import org.rapidoid.http.ReqHandler;
//import org.rapidoid.http.Resp;
//import org.rapidoid.job.Jobs;
//import vn.vcc.bigdata.news.global.SourceNews;
//import vn.vcc.news.dataface.controler.NewsRating;
//import vn.vcc.news.dataface.model.News;
//import vn.vcc.news.dataface.params.NewsRequest;
//import vn.vcc.news.dataface.resource.Blacklist;
//import vn.vcc.news.dataface.resource.DataResource;
//import vn.vcc.news.dataface.resource.ViewCounter;
//import vn.vcc.news.dataface.ulti.ProssesImage;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
///**
// * get news information for main request
// *
// * @author vungoc
// *
// */
//public class PubBoxNewsG implements ReqHandler {
//
//	/**
//	 *
//	 */
//	private static final long serialVersionUID = -2900039165945846624L;
//
//	@Override
//	public Object execute(Req req) throws Exception {
//		Resp resp = req.response();
//		req.async();
//
//		Jobs.schedule(() -> {
//			// STEP 1. SERVICE MONITOR
//			// set max request per second
//			if (ServerMonitor.NUM_REQEUST > 100000) {
//				resp.code(429);
//				resp.done();
//				return;
//			}
//			// Monitor service
//			ServerMonitor.NUM_REQEUST++;
//			long guid = 0;
//			int boxId = 0;
//			try {
//				guid = Long.parseLong(req.param("u"));
//				boxId = Integer.parseInt(req.param("b"));
//			} catch (Exception e) {
//				resp.code(400);
//				resp.done();
//				return;
//			}
//			if (!ViewCounter.setGuidRequest(guid)) {
//				resp.plain("var __dmtb" + boxId + " = []");
//				resp.done();
//				return;
//			}
//			// TODO HARD CODE. Need to replace
//			int sourceId = SourceNews.SOHA_ID;
//			if (boxId == ConfigNews.BOX_ID_K14) {
//				sourceId = SourceNews.KENH14_ID;
//			}else if(boxId == ConfigNews.BOX_ID_K14_TEST){
//                sourceId = SourceNews.KENH14_ID;
//			}
//			SimpleProducer kafkaProducer = SimpleProducer.getInstance();
//			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//			Calendar calendar = Calendar.getInstance();
//			if (sourceId == SourceNews.KENH14_ID){
//				kafkaProducer.sendMsgtoTopic(ConfigNews.KAFKA_K14_TOPIC,format.format(calendar.getTime()),calendar.get(Calendar.HOUR)+"");
//			}else if(sourceId == SourceNews.SOHA_ID){
//				kafkaProducer.sendMsgtoTopic(ConfigNews.KAFKA_SOHA_TOPIC,format.format(calendar.getTime()),calendar.get(Calendar.HOUR)+"");
//			}
//
//			// STEP 3. CONTENT BUID
//			resp.contentType(MediaType.TEXT_PLAIN_UTF8);
//			List<News> resultNews = new ArrayList<News>();
//			for (long newsid : NewsRating.getSortedNews(sourceId)) {
//				if (resultNews.size() > NewsRequest.NUM_RESULT)
//					break;
//				if (!DataResource.contains(newsid))
//					continue;
//				if (Blacklist.isBlocked(newsid))
//					continue;
//				// check number guid reached
//				if (ViewCounter.setView(newsid, guid)) {
//					News temp = DataResource.get(newsid);
//					temp.setPictureUrl(ProssesImage.cropImageforBox(boxId,temp));
//					resultNews.add(temp);
//				}
//			}
//
//			if (resultNews.size() == 0)
//				ServerMonitor.NUM_EMPTY_ERROR_REQUEST++;
//
//			JSONArray jsArr = new JSONArray(resultNews);
//
//			String result = "var __dmtb" + req.param("b") + " = " + jsArr;
//			resp.plain(result);
//			resp.done();
//			// req.response().json(req.data()).done();
//		}, 0, TimeUnit.NANOSECONDS);
//
//		return req;
//	}
//
//}
