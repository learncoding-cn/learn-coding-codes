package cn.learncoding.java.se.io.practice;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 文件压缩和解压缩 https://github.com/jpountz/lz4-java
 * 
 * @author koukaiqiang
 *
 */
public class Zip2File {
	
	public static void main(String[] args) throws IOException {
		
		 String root = System.getProperty("user.dir");
		 
		 File file = new File(root, "test.zip");
		 if (file.exists()) {
			file.delete();
		}
		 file.createNewFile();
		 
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(file, false), StandardCharsets.UTF_8);
//		设置压缩级别 可选的级别有0（不压缩），以及1(快速压缩)到9（慢速压缩）
		zos.setLevel(6);
		
		File file2 = new File(root, "src\\cn\\learncoding\\file");
		
		File[] files = file2.listFiles();
		
		byte[] buffer = new byte[1024];
		int i = 0;
		for (File file3 : files) {
			zos.putNextEntry(new ZipEntry(file3.getName()));
			InputStream is = new BufferedInputStream(new FileInputStream(file3));
			while ((i = is.read(buffer)) > 0) {
				zos.write(buffer, 0, i);
			}
			is.close();
			zos.closeEntry();
		}
		zos.close();
		
		ZipInputStream zis = new ZipInputStream(new FileInputStream(file), StandardCharsets.UTF_8);
		
		ZipEntry entry = null;
		while ( (entry = zis.getNextEntry()) != null) {
			System.out.println(entry.getName());
		}
		zis.close();
	}

}
