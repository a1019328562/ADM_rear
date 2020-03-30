package org.zju.adm.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.codec.binary.Base64;
import org.zju.adm.common.constrant.GlobalConstant;
import org.zju.adm.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.security.MessageDigest;

public class CommonUtil {
	/**
	 * 
	 * @Title: MD5Utils.java
	 * @Package com.imooc.utils
	 * @Description: 对字符串进行md5加密
	 */
	public static String getMD5Digest(String strValue) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		String digest = Base64.encodeBase64String(md5.digest(strValue.getBytes()));
		return digest;
	}

}
