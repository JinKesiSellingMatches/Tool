package core.download.pixiv;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.util.HtmlUtils;

import com.alibaba.druid.sql.visitor.functions.Now;

import core.download.util.MyX509TrustManager;

public class download {

	static int count = 0;

	static String userAllWorks = "https://www.pixiv.net/member_illust.php?id=";

	static String userAlllistUrl = "https://www.pixiv.net/bookmark.php?type=user&rest=hide&p=";

	static String requestMethod = "GET";
	
	static String path="E:\\OneDrive\\图片\\pixiv\\up\\";
	
	static SimpleDateFormat sFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	static List<String> allFile=new ArrayList<>();

	// 全部的关注人
	static List<AttentionPOJO> attentionAllUsers = new ArrayList<AttentionPOJO>();
	
	static List<AttentionPOJO> attentionStartUsers = new ArrayList<AttentionPOJO>();

	public static void main(String[] args) throws Exception {
		
		File file = new File(path);
		allFile.addAll(ergodic(file, new ArrayList<>(), "jpg"));
		allFile.addAll(ergodic(file, new ArrayList<>(), "png"));
		
		TestThread testThread = new TestThread();
        testThread.start();
        
		List<AttentionPOJO> downloadlist = new ArrayList<>();
		getAttentionAllPageUsers();
		starUser(null,30);
		System.out.println(sFormat.format(new Date())+"----"+attentionStartUsers.get(0).getAttentionName());
		for (AttentionPOJO pojo : attentionStartUsers) {
			getAttentionAllPageUsersWorks(pojo);
			getAllImgUrl(pojo);
			downloadlist.add(pojo);
			downloadImg(path, downloadlist);
			downloadlist.clear();
		}

	}
	
	private static List<String> ergodic(File file, List<String> resultFileName, String fileType) {
		File[] files = file.listFiles();
		if (files == null)
			return resultFileName;// 判断目录下是不是空的
		for (File f : files) {
			if (f.isDirectory()) {// 判断是否文件夹
				// resultFileName.add(f.getPath());
				ergodic(f, resultFileName, fileType);// 调用自身,查找子目录
			} else if (f.getPath().endsWith(fileType)) {
				resultFileName.add(f.getPath());
			}
		}
		return resultFileName;
	}
	
	

	public static void starUser(String userName,int maxSize){
		
		if (userName==null) {
			if (maxSize!=0) {
				for (int i = 0; i < maxSize; i++) {
					attentionStartUsers.add(attentionAllUsers.get(i));
				}
			}else{
				attentionStartUsers.addAll(attentionAllUsers);
			}
			return;
		}
		int count=0;
		for (AttentionPOJO attentionPOJO : attentionAllUsers) {
			if (userName.equals(attentionPOJO.getAttentionName())) {
				count++;
			}
			if (count==1) {
				attentionStartUsers.add(attentionPOJO);
			}
		}
	}
	public static void downloadImg(String path, List<AttentionPOJO> pojos) {

		for (AttentionPOJO attentionPOJO : pojos) {
			for (int i = 0; i < attentionPOJO.getImgUrl().size(); i++) {
				System.out.println(sFormat.format(new Date())+"----"+i);
				for (int j = 0; j < 1000; j++) {

					String url = attentionPOJO.getImgUrl().get(i);
					url = url.substring(0, url.lastIndexOf(".") - 1) + j
							+ url.substring(url.lastIndexOf("."), url.length());
					String fileName = url.substring(url.lastIndexOf("/") + 1, url.length());
					
					Integer count=0;
					for (String string : allFile) {
						if (string.equals(path + attentionPOJO.getAttentionName() + "\\"+fileName)) {
							System.out.println("存在");
							count++;
							break;
						}
					}
					if (count!=0) {
						break;
					}
					System.out.println(sFormat.format(new Date())+"----"+"下载" + url);
					try {
						MyX509TrustManager.httpsRequest(url, requestMethod, null, getRequestHeaderImg(),
								path + attentionPOJO.getAttentionName() + "\\", fileName);
					} catch (IOException | KeyManagementException | NoSuchAlgorithmException e) {
						System.out.println(sFormat.format(new Date())+"----"+e);
						break;
					}
				}
			}
		}
	}

	public static void getAllImgUrl(AttentionPOJO pojo) {
		

		Integer count=0;
		for (String string : pojo.getImgPageUrl()) {
			System.out.println(sFormat.format(new Date())+"----"+string);
			System.out.println(sFormat.format(new Date())+"----"+count++);
			try {
				String info = MyX509TrustManager.httpsRequest(string, requestMethod, null, getRequestHeaderData(), null, null);
				info = HtmlUtils.htmlUnescape(info);
				String[] strings = info.split("\"original\":\"");
				List<String> imgUrls = pojo.getImgUrl() == null ? new ArrayList<>() : pojo.getImgUrl();
				for (int i = 1; i < strings.length; i++) {
					String url = strings[i].replaceAll("\\\\", "");
					imgUrls.add(url.substring(0, url.indexOf("\"")));
				}
				pojo.setImgUrl(imgUrls);
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * 获取所有路径
	 * 
	 * @param pojo
	 * @throws Exception
	 */
	public static void getAttentionAllPageUsersWorks(AttentionPOJO pojo) throws Exception {

		getAttentionPageALLWorks(pojo, getRequestHeaderData());
	}

	/**
	 * 获取每个用户全部
	 * 
	 * @param pojo
	 * @param requestHeader
	 * @throws Exception
	 */
	public static void getAttentionPageALLWorks(AttentionPOJO pojo, Map<String, String> requestHeader)
			throws Exception {

		System.out.println(sFormat.format(new Date())+"----"+pojo.getAttentionUrl());
		String info = MyX509TrustManager.httpsRequest(pojo.getAttentionUrl(), requestMethod, null, requestHeader, null,
				null);
		info = HtmlUtils.htmlUnescape(info);
		pojo.setPageUrl(getAttentionAllPageUsersWorksPageList(info, pojo.getAttentionUrl()));
		for (String string : pojo.getPageUrl()) {
			getAttentionPageWorks(string, getRequestHeaderData(), pojo);
		}

	}

	/**
	 * 获取分页地址
	 * 
	 * @param info
	 * @param url
	 * @return
	 */
	private static List<String> getAttentionAllPageUsersWorksPageList(String info, String url) {

		String[] strings = info.split("count-badge");
		Integer count = 0;
		for (int i = 1; i < strings.length; i++) {
			if (count==0) {
				count = Integer.valueOf(strings[i].substring(2, strings[i].indexOf("件")));
			}
		}
		int page = count / 4 / 5;
		List<String> result = new ArrayList<>();
		for (int i = 1; i < page + 2; i++) {
			result.add(url + "&type=all&p=" + i);
		}
		return result;
	}

	/**
	 * 
	 * 获取每页全部
	 */
	public static void getAttentionPageWorks(String url, Map<String, String> requestHeader, AttentionPOJO pojo)
			throws Exception {

		System.out.println(sFormat.format(new Date())+"----"+url);
		String info = MyX509TrustManager.httpsRequest(url, requestMethod, null, requestHeader, null, null);
		info = HtmlUtils.htmlUnescape(info);
		List<String> works = pojo.getImgPageUrl() == null ? new ArrayList<>() : pojo.getImgPageUrl();
		works.addAll(getWorks(info));
		pojo.setImgPageUrl(works);
	}

	public static List<String> getWorks(String info) {

		List<String> result = new ArrayList<>();
		String[] strings = info.split("</a><a href=\"/member_illust.php\\?mode=medium&illust_id=");
		for (int i = 1; i < strings.length; i++) {
			result.add("https://www.pixiv.net/member_illust.php?mode=medium&illust_id="
					+ strings[i].substring(0, strings[i].indexOf("\">")));
		}
		return result;
	}

	public static void getAttentionAllPageUsers() throws Exception {
		// 循环
		List<String> attentionUserPageList = getAttentionUserPageList(null);
		for (String string : attentionUserPageList) {
			// 循环页数
			getAttentionPageUsers(string, requestMethod, getRequestHeaderData());
		}
	}

	public static void getAttentionPageUsers(String url, String requestMethod, Map<String, String> requestHeader)
			throws Exception {

		System.out.println(sFormat.format(new Date())+"----"+url);
		String info = MyX509TrustManager.httpsRequest(url, requestMethod, null, requestHeader, null, null);
		info = HtmlUtils.htmlUnescape(info);
		List<AttentionPOJO> attentionUsers = getAttentionUser(info);
		attentionAllUsers.addAll(attentionUsers);
	}

	private static List<AttentionPOJO> getAttentionUser(String info) {

		List<AttentionPOJO> result = new ArrayList<>();
		String[] strings = info.split("type=\"checkbox\" /><a href=\"/member.php\\?id=");
		for (int i = 1; i < strings.length; i++) {
			AttentionPOJO pojo = new AttentionPOJO();
			pojo.setAttentionName(
					strings[i].substring(strings[i].indexOf("data-user_name=\"") + 16, strings[i].indexOf("\">")));
			pojo.setAttentionUrl(userAllWorks + strings[i].substring(0, strings[i].indexOf("\"")));
			result.add(pojo);
		}
		return result;
	}

	private static List<String> getAttentionUserPageList(String info) {

		int page = 183 / 3 / 16;
		List<String> result = new ArrayList<>();
		for (int i = 1; i < page + 2; i++) {
			result.add(userAlllistUrl + i);
		}
		return result;
	}

	public static Map<String, String> getRequestHeaderData() {
		Map<String, String> requestHeader = new HashMap<String, String>();
		requestHeader.put("authority", "www.pixiv.net");
		requestHeader.put("method", "GET");
		requestHeader.put("path", "/member_illust.php?id=260108");
		requestHeader.put("scheme", "https");
		requestHeader.put("accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		requestHeader.put("accept-encoding", "pplication/json, text/javascript, */*; q=0.01");
		requestHeader.put("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
		requestHeader.put("cache-control", "no-cache");
		requestHeader.put("cookie",
				"p_ab_id=8; p_ab_id_2=3; _ga=GA1.2.829985661.1527426547; PHPSESSID=25324942_e89a63a28ea12b636da08b414eeb4e02; device_token=34f9a2c6934e6c2d28c38aa70d6cec04; c_type=24; a_type=0; b_type=1; module_orders_mypage=%5B%7B%22name%22%3A%22sketch_live%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22tag_follow%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22recommended_illusts%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22showcase%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22everyone_new_illusts%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22following_new_illusts%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22mypixiv_new_illusts%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22fanbox%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22featured_tags%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22contests%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22user_events%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22sensei_courses%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22spotlight%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22booth_follow_items%22%2C%22visible%22%3Atrue%7D%5D; yuid=EYOBVnM85; login_ever=yes; __gads=ID=6b4bb420895077d3:T=1527426579:S=ALNI_MbOhjdztW1TJ5AvLylIFh_i1PItLQ; first_visit_datetime_pc=2018-05-28+16%3A57%3A03; GMOSSP_USER=mGzoNyUamv7wlTKJ; __utmz=235335808.1529214331.11.4.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; howto_recent_view_history=38232567; privacy_policy_agreement=1; is_sensei_service_user=1; __utmc=235335808; OX_plg=pm; first_visit_datetime=2018-06-24+15%3A46%3A24; webp_available=1; limited_ads=%7B%22header%22%3A%22%22%2C%22t_header%22%3A%22%22%7D; __utma=235335808.829985661.1527426547.1529818772.1529826373.20; __utmt=1; __utmv=235335808.|2=login%20ever=yes=1^3=plan=premium=1^5=gender=male=1^6=user_id=25324942=1^9=p_ab_id=8=1^10=p_ab_id_2=3=1^11=lang=zh=1^20=webp_available=yes=1; tag_view_ranking=kP7msdIeEU~OT4SuGenFI~ZXFMxANDG_~RTJMXD26Ak~Lt-oEicbBr~BU9SQkS-zU~_pwIgrV8TB~jH0uD88V6F~nQRrj5c6w_~zyKU3Q5L4C~q303ip6Ui5~Oa9b6mEc1T~y8GNntYHsi~KN7uxuR89w~bo5e_8AN4e~JtHr1OyMVc~OvYjFzHBbv~azESOjmQSV~2RBMoxiBY_~TXH0zUwQ7-~3Y00EEf2pf~f4V1aCLsyM~aNqTPYQ7NR~NBK37t_oSE~7cMRrOPRjW~OnPXVFB0OB~nrFOQYIh7z~oCR2Pbz1ly~qiO14cZMBI~UEwVvtxPms~Gx02furCtC~x_jB0UM4fe~Ie2c51_4Sp~tgP8r-gOe_~zuU0Lk5eoo~WcTW9TCOx9~DuCdp8i1kQ~1Xn1rApx2-~HzAWb1jKBD~Ms9Iyj7TRt~eVxus64GZU~TqkJ-zrPT2~ETjPkL0e6r~tOmXaSPNav~h9r9YX0n2U~BtXd1-LPRH~SJK3YcGD-h~E8plmQ7kUK~y68AFldGp7~UX647z2Emo~NQtJdZaf3U~IjWKRFNv1Q~thj0HHIg2f~1F9SMtTyiX~_C3rBVxdV5~I4dSlkNZug~ueeKYaEKwj~R1dhNf-8Dm~ahHegnNVxX~tIWZL2l8dp~plqXT5B4--~1HuE7w0nKg~ZnmOm5LdC3~AjBDLpRc95~LJo91uBPz4~n7YxiukgPF~OJuMINnr06~4Ew9pzGr3u~RKAHEY3QDd~CrFcrMFJzz~ecjTAYlbdY~i83OPEGrYw~mtPjI7b6wl~tAvX8S-RSe~0G1fdsiW-i~MSNRmMUDgC~V9WUAkfln2~0xsDLqCEW6~vFwTRLUjL6~_jm8oBqAts~NpsIVvS-GF~wx98zZNxJM~gooMLQqB9a~8uLJ5_XXR_~G6EmdndVFn~VihadKMfX9~aKAp3RlsBg~wBaT7BbUEi~qvqXJkzT2e~Qa8ggRsDmW~afQT0PrnAv~TCnz1buGzH~y3NlVImyly~HhiSxrqwAi~I5npEODuUW~28gdfFXlY7~z_hZuFJUTa~Cr3jSW1VoH~e1L-7w6uXk~65aiw_5Y72; __utmb=235335808.21.9.1529826450492");
		requestHeader.put("pragma", "no-cache");
		requestHeader.put("referer", "https://www.pixiv.net/member_illust.php?mode=medium&illust_id=69123643");
		requestHeader.put("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
		return requestHeader;
	}

	private static Map<String, String> getRequestHeaderImg() {
		Map<String, String> requestHeader = new HashMap<String, String>();
		requestHeader.put("authority", "i.pximg.net");
		requestHeader.put("method", "GET");
		requestHeader.put("path", "/img-master/img/2018/06/08/01/15/57/69123643_p0_master1200.jpg");
		requestHeader.put("scheme-length", "https");
		requestHeader.put("accept", "image/webp,image/apng,image/*,*/*;q=0.8");
		requestHeader.put("accept-encoding", "gzip, deflate, br");
		requestHeader.put("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
		requestHeader.put("x-content-type-options", "nosniff");
		requestHeader.put("cache-control", "no-cache");
		requestHeader.put("pragma", "no-cache");
		requestHeader.put("referer", "https://www.pixiv.net/member_illust.php?mode=medium&illust_id=69123643");
		requestHeader.put("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
		return requestHeader;
	}
}
