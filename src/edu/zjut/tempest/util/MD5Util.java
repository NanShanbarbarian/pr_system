package edu.zjut.tempest.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	/**
	 * getMD5 通过Java自带的MD5算法对数据进行MD5加密，获得加密后数据
	 * @param data
	 * @return
	 */
	public static String getMD5(String data) {
		
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");   //创建一个MD5算法对象
			
			md5.update(data.getBytes());   //使用指定的字节更新摘要
			byte b[] = md5.digest();
			
			int num;
			StringBuffer buf = new StringBuffer("");
			
			for(int i=0; i<b.length; i++) {
				num = b[i];
				if(num < 0) {
					num += 256;
				}
				if(num < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(num));
			}
			
			return buf.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(MD5Util.getMD5("123456"));
		System.out.println(MD5Util.getMD5("123456").length());
	}
}
