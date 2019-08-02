package cn.learncoding.java.se.io.practice;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * 
 *  JDK7 Files 和  Paths
 *  简化文件操作
 * 
 * @author koukaiqiang
 * 
 *
 */
public class FilesAndPaths {
	
	public static void main(String[] args) {
	    String root = System.getProperty("user.dir");
		Path path = Paths.get(root, "src", "cn", "learncoding", "file", "FilesAndPaths.java");
		Path path2 = Paths.get(root, path.toFile().getName());
		OutputStream os = null;
		try {
			os = Files.newOutputStream(path2, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
//			文件复制
			Files.copy(path, os);
//			读取文件所有数据行
			/**
			 * 报错 java.nio.charset.MalformedInputException: Input length = 1
			 * 很有可能是文件的编码和读取的编码设置不一致导致
			 */
			List<String> list = Files.readAllLines(path, StandardCharsets.UTF_8);
//			将数据按行写入对应的文件中
			Files.write(path2, list, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
//			按byte[]读取文件数据
			byte[] bytes = Files.readAllBytes(path);
//			将byte[]写入文件中
			Files.write(path2, bytes, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
