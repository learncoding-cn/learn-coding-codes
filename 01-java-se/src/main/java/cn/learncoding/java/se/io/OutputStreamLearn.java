package cn.learncoding.java.se.io;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.jar.JarOutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;

/**
 * 输出字节流  由程序流出到外界
 * 
 * @author learncoding.cn
 *
 */
public class OutputStreamLearn {

	public static void main(String[] args) throws Throwable {
		//文件输出流,写入文件 
		OutputStream fileOutputStream = new FileOutputStream(new File(""));
		//byte数组输出流
		OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		//对象序列化
		OutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		//管道输出流,负责写入数据。
		OutputStream pipedOutputStream = new PipedOutputStream();
		
		/**
		 * FilterOutputStream 包含其他一些输出流，它将这些流用作其基本数据源，
		 * 它可以直接传输数据或提供一些额外的功能.
		 */
		//提供缓冲区
		OutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
		//提供数据完整性校验功能。 
		OutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new CRC32());
		//提供解密功能 
		OutputStream CipherOutputStream = new CipherOutputStream(fileOutputStream, Cipher.getInstance("DES/CBC/PKCS5Padding"));
		//提供读取基本 Java 数据类型功能 
		OutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
		//提供读取Zip压缩文件内容功能 
		OutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
		//提供读取jar包内容功能 
		OutputStream jarOutputStream = new JarOutputStream(fileOutputStream);
		//提供读取GZIP压缩文件内容功能 
		OutputStream gZIPOutputStream = new GZIPOutputStream(fileOutputStream);
		//向输出流打印对象的格式化表示形式。
		OutputStream printStream = new PrintStream(fileOutputStream);
		
	}
}
