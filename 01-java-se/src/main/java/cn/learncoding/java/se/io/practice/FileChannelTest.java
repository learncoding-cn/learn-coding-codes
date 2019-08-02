package cn.learncoding.java.se.io.practice;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
/**
 * 用于读取、写入、映射和操作文件的通道 使用ByteBuffer
 * 
 * @author learncoding.cn
 *
 */
public class FileChannelTest {
	public static void main(String[] args) {
		String root = System.getProperty("user.dir");
		Path path2 = Paths.get(root, "src", "cn", "learncoding", "file", "FileChannelTest.java");
		Path path = Paths.get(root, path2.toFile().getName());
		FileChannel channel2 = null;
		FileChannel channel = null;
		try {
			channel = FileChannel.open(path, StandardOpenOption.TRUNCATE_EXISTING, 
					StandardOpenOption.CREATE,StandardOpenOption.WRITE, StandardOpenOption.READ);
			channel2 = FileChannel.open(path2, StandardOpenOption.READ);
//			使用通道进行复制文件
			channel.transferFrom(channel2, 0, channel2.size());
			
			byte[] data = new byte[1024];
			for (int i = 0; i < data.length; i++) {
				data[i] = (byte) i;
			}
			ByteBuffer buf = ByteBuffer.allocate(1024);
			buf.put(data);
//			从buf中读出已经写入的数据，需要flip 重置limit=position position=0 
			buf.flip();
			channel.position(channel.size());
			channel.write(buf);
//			持久化数据到磁盘
			channel.force(true);
			
			buf.clear();
			channel.read(buf);
			buf.get(data, 0, buf.remaining());
			System.out.println(Arrays.toString(data));
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
