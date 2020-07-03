package com.fengqwq.box;

import java.security.*;

/**
 * MD5加密工具类
 *
 * Created by 梦雪 on 2019/08/17.
 * 欢迎加入Android开发技术交流②群，群聊号码：758110864
 */
public class MD5 {
	/**
	 md5加密

	 @return 16位密文
	 */
	public static String md5(String sourceStr) {
		//声明StringBuffer对象
		StringBuilder sb = new StringBuilder();
        try {
			MessageDigest md = MessageDigest.getInstance("MD5");//1.初始化MessageDigest信息摘要对象,并指定为MD5不分大小写都可以
			md.update(sourceStr.getBytes());//2.传入需要计算的字符串更新摘要信息，传入的为字节数组byte[],将字符串转换为字节数组使用getBytes()方法完成
			byte b[] = md.digest();//3.计算信息摘要digest()方法,返回值为字节数组
			int i;//定义整型
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];//将首个元素赋值给i
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					sb.append("0");//前面补0
				}
				sb.append(Integer.toHexString(i));//转换成16进制编码
			}
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
        }
        return sb.toString().substring(8, 24);//返回结果
	}
}
