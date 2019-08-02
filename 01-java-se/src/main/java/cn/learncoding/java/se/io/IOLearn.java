package cn.learncoding.java.se.io;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * @author learncoding.cn
 */
public class IOLearn {
	
	public static void main(String[] args) {
		//项目根目录
		String root = System.getProperty("user.dir");
		File directory = new File(root);
		if (!directory.exists() || !directory.isDirectory()) {
			System.err.println("文件目录 " + root + " 不存在或不是目录！");
			return;
		}
		//项目根目录下创建测试文件testFile.txt 已存在则删除
		File file = new File(root + File.separator + "testFile.txt");
		if (file.exists()) {
			System.out.println("文件 " + file.getAbsolutePath() + " 已存在，删除");
			boolean deleteFileResult = file.delete();
			if (!deleteFileResult) {
				System.err.println("文件 " + file.getAbsolutePath() + " 删除失败！");
				return;
			}
		}
		
		try {
			boolean createFileResult = file.createNewFile();
			if (!createFileResult) {
				System.err.println("文件 " + file.getAbsolutePath() + " 创建失败！");
				return;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		
		//使用字节流向文件中写入数据
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			String msg = "learncoding.cn";
			System.out.println("写入文件内容：" + msg);
			os.write(msg.getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			IOUtil.close(os);
		}
		
		//使用字节流从文件中读取数据
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			byte[] bytes = new byte[10];
			int num = 0;
			StringBuilder sb = new StringBuilder();
			while((num = is.read(bytes)) != -1){
				sb.append(new String(bytes, 0, num, StandardCharsets.UTF_8));
			}
			System.out.println("读取文件内容：" + sb);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			IOUtil.close(is);
		}
		//使用字符流向文件中追加数据
		Writer writer = null;
		try {
			writer = new FileWriter(file, true);
			String msg = " learncoding.cn";
			System.out.println("写入文件内容：" + msg);
			writer.append(msg);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			IOUtil.close(writer);
		}
		//使用字符流从文件中读取数据
		Reader reader = null;
		try {
			reader = new FileReader(file);
			char[] array = new char[10];
			int num = 0;
			StringBuilder sb = new StringBuilder();
			while((num = reader.read(array)) != -1){
				sb.append(new String(array, 0, num));
			}
			System.out.println("读取文件内容：" + sb);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			IOUtil.close(reader);
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
