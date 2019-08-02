package cn.learncoding.java.se.io.practice;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
/**
 * @author learncoding.cn
 */
public class SeekableByteChannelTest {
	
	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.dir"), "test.txt");
		SeekableByteChannel channel = null;
		try {
			if (!Files.exists(path)) {
				Files.createFile(path);
			}
			channel = Files.newByteChannel(path, StandardOpenOption.WRITE, StandardOpenOption.READ);
//			初始化数据 
			byte[] data = new byte[10];
			for (int i = 0; i < data.length; i++) {
				data[i] = (byte) i;
			}
			System.out.println("写入数据："+ Arrays.toString(data));
			ByteBuffer buf = ByteBuffer.allocate(10);
			buf.put(data);
			buf.flip();
			channel.write(buf);
			
			byte[] b = new byte[]{0,0,0};
			System.out.println("第5个位置后写入数组："+ Arrays.toString(b));
			channel.position(5);
			channel.write(ByteBuffer.wrap(b));
			
			
			buf.clear();
			channel.position(0);
			channel.read(buf);
			buf.flip();
			buf.get(data, 0, buf.remaining());
			System.out.println("读取数据："+Arrays.toString(data));
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
