package core.download.pixiv;

public class test {
	
	public static void main(String[] args) {
		String teString="\"original\":\"https:\\/\\/i.pximg.net\\/img-original\\/img\\/2018\\/06\\/23\\/00\\/00\\/45\\/69353904_p0.jpg\"},\"tags";
		String[] strings = teString.split("\"original\":\"");	
		System.out.println(strings[1]);
		String url = teString.replaceAll("\\\\", "");
		url=url.substring(0, url.indexOf("\""));
	}

}
