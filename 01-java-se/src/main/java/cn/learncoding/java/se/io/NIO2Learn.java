package cn.learncoding.java.se.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * @author learncoding.cn
 */
public class NIO2Learn {

	public static void main(String[] args) {
		//项目根目录
		 String root = System.getProperty("user.dir");
		 
		//项目根目录下获取测试文件testNIO2.txt和testNIO2Copy.txt的路径
		 Path path = Paths.get(root, "testNIO2.txt");
		 Path pathCopy = Paths.get(root, "testNIO2Copy.txt");
		 
		 //path和file的相互转化
		 File file = path.toFile();
		 path = file.toPath();
		 
		 try {
			 //testNIO2.txt 文件 已存在则删除
			 Files.deleteIfExists(path);
			 
			 String msg = "learncoding.cn";
			 System.out.println("写入文件内容：" + msg);
			 byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
			 //文件写入 没有则创建
			 Files.write(path, bytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
			 //按byte数组读取文件内容
			 byte[] data = Files.readAllBytes(path);
			 System.out.println("读取文件内容：" + new String(data, StandardCharsets.UTF_8));
			 //文件复制  已存在则覆盖
			 Files.copy(path, pathCopy, StandardCopyOption.REPLACE_EXISTING);
			 //文件追加写入
			 Files.write(pathCopy, bytes, StandardOpenOption.APPEND, StandardOpenOption.WRITE);
			 //按行读取文件
			 List<String> list = Files.readAllLines(pathCopy, StandardCharsets.UTF_8);
			 System.out.println("读取文件内容：" + list.get(0));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
