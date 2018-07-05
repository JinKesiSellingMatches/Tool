package core.download.twitter;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Spring;

import org.springframework.web.util.HtmlUtils;

import core.download.util.MyX509TrustManager;
import core.utils.JacksonUtil;

public class download {
	
	static int count=0;

	public static void main(String[] args) throws Exception {
		goTwitter("946275959287595008");
	}
	
	public static void goTwitter(String min_position) throws Exception{
		if (count==10) {
			Thread.currentThread().sleep(10000);
			count=0;
		}
		//https://twitter.com/i/profiles/show/picoondayo/media_timeline?include_available_features=1&include_entities=1&max_position=984483037994872832&reset_error_state=false
		count++;
		String href="https://twitter.com";
		String requestUrl="/i/profiles/show/picoondayo/media_timeline?include_available_features=1&include_entities=1&max_position="
				+ min_position
				+ "&reset_error_state=false";
		String requestMethod="GET";
		String path="D:\\twitter\\"
				+ min_position
				+ ".txt";
		Map<String, String> header=new HashMap<String,String>();
//		header.put("authority", "twitter.com");
//		header.put("method", "GET");
//		header.put("path", requestUrl);
//		header.put("scheme", "https");
		header.put("accept", "application/json, text/javascript, */*; q=0.01");
		header.put("cookie", "personalization_id=\"v1_hUj24SefIdwRcFhmdBxrHA==\"; guest_id=v1%3A152739310707887255; _ga=GA1.2.1587517566.1527393109; _gid=GA1.2.801351672.1527393109; dnt=1; ads_prefs=\"HBESAAA=\"; kdt=aoAWRLbGVUT0QFpMaY4iFEwIKf1kRmrelbU4Etbx; remember_checked_on=1; _twitter_sess=BAh7CiIKZmxhc2hJQzonQWN0aW9uQ29udHJvbGxlcjo6Rmxhc2g6OkZsYXNo%250ASGFzaHsABjoKQHVzZWR7ADoPY3JlYXRlZF9hdGwrCBJIuZ9jAToMY3NyZl9p%250AZCIlM2JhNTc1ZDE1YmYzNjVmMTMwYjgwNzMzOTA4NGRlODk6B2lkIiU5OGNh%250AZDdiMWVmNzJiNjkxNWNmZDEyNjUyOTk3OTYwNzoJdXNlcmwrCQbAVbTMkLsN--e8459a33d8aec15b7447bad4436d01558664ff09; twid=\"u=989543752011202566\"; u=66417dde8b1211e977713c25a7012d28; auth_token=95d1bd3cc83555c792e930ac16c4a0f737677d7a; lang=zh-cn; csrf_same_site_set=1; csrf_same_site=1; ct0=6395ef2c289ae13e9eae3faa5706c92f; __utma=43838368.1587517566.1527393109.1527395423.1527395423.1; __utmc=43838368; __utmz=43838368.1527395423.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); external_referer=padhuUp37ziuyzdWM8qJSyzkybKeRAroSPKMxWugwvpal0jCh9Fzl5v3zoimFFvrfNCwVR68O2f1040SMpRyc5BDZIWoLemWrtNwUJI9C8oZC4uzsNMOwZUgaOyY1PER32ueAOdbRIg%3D|0|8e8t2xd8A2w%3D");
		header.put("referer", "https://twitter.com/picoondayo");
		header.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
		header.put("x-requested-with", "XMLHttpRequest");
		header.put("x-twitter-active-user", "yes");
		System.out.println(href+requestUrl);
		String info=MyX509TrustManager.httpsRequest(href+requestUrl, requestMethod,path,header,null,null);
		info=HtmlUtils.htmlUnescape(info);
		System.out.println(info);
		readFile.downloadfile(path);
		
	}
}
