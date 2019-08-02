package cn.learncoding.java.se.io.practice;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 
 * 文件内存映射
 * 
 * @author learncoding.cn
 *
 */
public class MemoryMapped {
	
	public static void main(String[] args) {
		Path path2 = Paths.get(System.getProperty("user.dir"), "test.txt");
		FileChannel channel = null;
		try {
			if (!Files.exists(path2)) {
				Files.createFile(path2);
			}
//			或者RandomAccessFile、FileInputStream 或 FileOutputStream 的  getChannel()方法得到
			channel = FileChannel.open(path2, StandardOpenOption.TRUNCATE_EXISTING, 
					StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.READ);
			MappedByteBuffer mappedByteBuffer = channel.map(MapMode.READ_WRITE, 0, 1024);
			byte[] data = new byte[1024];
			for (int i = 0; i < data.length; i++) {
				data[i] = (byte) i;
			}
			mappedByteBuffer.put(data);
			mappedByteBuffer.flip();
			channel.force(true);
			mappedByteBuffer.get(data);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (channel != null) {
					channel.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
