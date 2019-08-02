package cn.learncoding.java.se.io;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author learncoding.cn
 */
public class NIOLearn {
	
	public static void main(String[] args) {
		//项目根目录
		String root = System.getProperty("user.dir");
		
		//项目根目录下创建测试文件testNIO.txt和testNIOCopy.txt 已存在则删除
		File file = new File(root + File.separator + "testNIO.txt");
		File copyFile = new File(root + File.separator + "testNIOCopy.txt");
		if (file.exists()) {
			file.delete();
		}
		if (copyFile.exists()) {
			copyFile.delete();
		}
		try {
			file.createNewFile();
			copyFile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		
		//使用FileOutputStream获取通道并向文件中写入数据
		FileOutputStream os = null;
		FileChannel channel = null;
		try {
			os = new FileOutputStream(file);
			channel = os.getChannel();
			String msg = "learncoding.cn";
			System.out.println("写入文件内容：" + msg);
			
			byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
			
//			方式一   创建堆缓冲区，并填充数据
//			ByteBuffer buffer = ByteBuffer.wrap(bytes);
			
//			方式二  创建堆缓冲区，并填充数据
//			ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
//			buffer.put(bytes);
//			buffer.flip();
			
//			方式三   创建直接缓冲区，并填充数据
			ByteBuffer buffer = ByteBuffer.allocateDirect(bytes.length);
			buffer.put(bytes);
			buffer.flip();
			channel.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			IOUtil.close(channel, os);
		}
		
		//使用FileInputStream获取通道并从文件中读取数据
		FileInputStream is = null;
		try {
			is = new FileInputStream(file);
			channel = is.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(10);
			int num = 0;
			StringBuilder sb = new StringBuilder();
			while((num = channel.read(buffer)) != -1){
				buffer.flip();
				sb.append(new String(buffer.array(), 0, num, StandardCharsets.UTF_8));
			}
			System.out.println("读取文件内容：" + sb);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			IOUtil.close(channel, is);
		}
		
		//使用RandomAccessFile获取通道并复制文件内容到testNIOCopy.txt，然后从中读取数据
		RandomAccessFile file1 = null;
		RandomAccessFile file2 = null;
		FileChannel channel1 = null;
		FileChannel channel2 = null;
		try {
			file1 = new RandomAccessFile(file, "r");
			file2 = new RandomAccessFile(copyFile, "rw");
			channel1 = file1.getChannel();
			channel2 = file2.getChannel();
			//复制文件file内容到copyFile中
			channel2.transferFrom(channel1, 0, file1.length());
			
			ByteBuffer buffer = ByteBuffer.allocate(10);
			int num = 0;
			StringBuilder sb = new StringBuilder();
			while((num = channel2.read(buffer)) != -1){
				buffer.flip();
				sb.append(new String(buffer.array(), 0, num, StandardCharsets.UTF_8));
			}
			System.out.println("读取文件内容：" + sb);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtil.close(channel1, channel2, file1, file2);
		}
	}

	static class IOUtil {
		/**
		 * 关闭流通道
		 */
		public static void close(Closeable...closeables){
			if (closeables == null || closeables.length == 0) {
				return;
			}
			try {
				for (Closeable closeable : closeables) {
					if (closeable != null) {
						closeable.close();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
