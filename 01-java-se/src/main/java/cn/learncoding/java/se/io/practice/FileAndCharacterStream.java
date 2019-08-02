package cn.learncoding.java.se.io.practice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Writer;

/**
 * 文件和字符流的转换
 * 
 * @author learncoding.cn
 *
 */
public class FileAndCharacterStream {

	public static void main(String[] args) {
		Writer writer = null;
		LineNumberReader reader = null;
		String root = System.getProperty("user.dir");
		// 当前文件路径
		String path = root + File.separator + "src" + File.separator + "cn" + File.separator + "learncoding"
				+ File.separator + "file" + File.separator + "FileAndCharacterStream.java";
		File file = new File(path);
		try {
			reader = new LineNumberReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter(root + File.separator + file.getName()));
			String data = reader.readLine();
			while (data != null) {
				// 因为readLine读取时会丢弃行结束符，所以在写入每行时加上
				writer.write(data + "\n\t");
				data = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
