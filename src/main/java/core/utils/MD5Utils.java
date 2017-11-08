package core.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MD5 Utils
 * Copyright(c) 2011 China National Petroleum Corporation , http://www.cnpc.com.cn
 * 
 * @author hefei
 * @since 2011-7-8
 */
public final class MD5Utils {

	/**
	 * Logger
	 */
	protected static final Logger LOG = LoggerFactory.getLogger(MD5Utils.class);

	/**
	 * the constant NO;
	 */
	private static final int NO = 0xFF;
	
    /**
     * key
     */
    private static final String KEY = "☆Z@1";

	/**
	 * MD5 加密
	 * 
	 * @param str
	 *            the str
	 * @return md5StrBuff
	 *         the md5StrBuff
	 */
	public static String getMD5Str(String str) {
	    str = KEY + str;
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			LOG.error(e.getMessage());
			return "";
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage());
			return "";
		}

		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(NO & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(Integer.toHexString(NO & byteArray[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(NO & byteArray[i]));
			}
		}
		return md5StrBuff.toString();
	}

	/**
	 * the default constructor
	 */
	private MD5Utils() {
	}

	 public static void main(String[] args) {
	 System.out.println(MD5Utils.getMD5Str("yiside"));
	 }

}
