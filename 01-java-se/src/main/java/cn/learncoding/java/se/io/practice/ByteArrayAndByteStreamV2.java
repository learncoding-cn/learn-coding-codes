package cn.learncoding.java.se.io.practice;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * byte数组和字节流的互相转换
 * 
 * @author learncoding.cn
 *
 */
public class ByteArrayAndByteStreamV2 {
	
	public static void main(String[] args){
		byte[] bytes = new byte[100];
		for (int i = 0; i < 100; i++) {
			bytes[i] = (byte) i;
		}

		ByteArrayInputStream bis = null;
		ByteArrayOutputStream bos = null;
		try {
//			byte[] 转 ByteArrayInputStream
			bis = new ByteArrayInputStream(bytes);
			bos = new ByteArrayOutputStream(10);
			
//			byte[]写入ByteArrayOutputStream
			bos.write(bytes);
			
//			写入文件中
			bos.writeTo(new FileOutputStream(System.getProperty("user.dir") + File.separator + "byte.txt"));
			
//			ByteArrayOutputStream 转为byte[]
			bytes = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//================v1 -> v2 change start================
			IOUtil.close(bos, bis);
			//================v1 -> v2 change end  ================
		}
	}
}
