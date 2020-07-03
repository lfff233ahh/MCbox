package com.fengqwq.box;

import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import android.util.*;

/**
 * Des加解密工具类
 * 
 * Created by 梦雪 on 2019/08/17.
 * @Description DES算法（加密、解密）文件
 * 欢迎加入Android开发技术交流②群：758110864
 */
public class Des {
	/**
     * 生成key
     *
     * @param password
     * @return
     * @throws Exception
     */
    private static Key generateKey(String password) throws Exception {
        DESKeySpec dks = new DESKeySpec(password.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        return keyFactory.generateSecret(dks);
    }

	/**
     * 
     * @param file 要加密的文件路径
	 * @param destFile 加密后的存放路径
     * @param encryptKey 密钥
     */
    public static void encryptFile(String file, String destFile, String encryptKey) {
		try {
			//实例化IvParameterSpec对象，使用指定的初始化向量
			IvParameterSpec zeroIv=new IvParameterSpec("12345678".getBytes());
			//实例化SecretKeySpec，根据传入的密钥获得字节数组来构造SecretKeySpec
			Key key =generateKey(encryptKey);
			//创建密码器
			Cipher cipher=Cipher.getInstance("DES/CBC/PKCS5Padding");
			//用密钥初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
			//创建缓冲输入流
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			//创建缓冲输出流
			BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(destFile));
			//创建Cipher输入流
			CipherInputStream cis=new CipherInputStream(bis, cipher);
			//在内存中开辟一段缓冲区，接收网络输入流
			byte[] buffer=new byte[1024];
			int len=0;
			while ((len = cis.read(buffer)) != -1) {
				//写入文件
				bos.write(buffer, 0, len);
				//刷新缓冲区
				bos.flush();
			}
			//关闭流
			cis.close();
			bis.close();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    /**
     * 解密的过程与加密的过程大致相同
     * @param file 要解密的文件路径
	 * @param destFile 解密后的存放路径
     * @param decryptKey 密钥
     */

    public static void decryptFile(String file, String destFile, String decryptKey) {
        try {
			//实例化IvParameterSpec对象，使用指定的初始化向量
			IvParameterSpec zeroIv=new IvParameterSpec("12345678".getBytes());
            //实例化SecretKeySpec，根据传入的密钥获得字节数组来构造SecretKeySpec,
            Key key=generateKey(decryptKey);
            //创建密码器
            Cipher cipher=Cipher.getInstance("DES/CBC/PKCS5Padding");
            //用密钥初始化Cipher对象,上面是加密，这是解密模式
            cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
			//创建缓冲输入流
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			//创建缓冲输出流
			BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(destFile));
			//创建Cipher输入流
			CipherInputStream cis=new CipherInputStream(bis, cipher);
			//在内存中开辟一段缓冲区，接收网络输入流
			byte[] buffer=new byte[1024];
			int len=0;
			while ((len = cis.read(buffer)) != -1) {
				//写入文件
				bos.write(buffer, 0, len);
				//刷新缓冲区
				bos.flush();
			}
			//关闭流
			cis.close();
			bis.close();
			bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
