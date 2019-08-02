package cn.learncoding.java.se.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PipedInputStream;
import java.io.PushbackInputStream;
import java.io.SequenceInputStream;
import java.util.jar.JarInputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;

/**
 * 输入字节流  由外界流入程序内
 * 
 * @author learncoding.cn
 *
 */
public class InputStreamLearn {
	
	public static void main(String[] args) throws Throwable {
		//文件输入流，读取文件
		InputStream fileInputStream = new FileInputStream(new File(""));
		//byte数组输入流，读取byte数组
		InputStream byteArrayInputStream = new ByteArrayInputStream(new byte[0]);
		//对象反序列化
		InputStream objectInputStream = new ObjectInputStream(fileInputStream);
		//管道输入流应该连接到管道输出流；负责读取数据。
		InputStream pipedInputStream = new PipedInputStream();
		//将多个数据源拼接成一个数据源
		InputStream sequenceInputStream = new SequenceInputStream(fileInputStream, byteArrayInputStream);
		
		/**
		 * FilterInputStream 包含其他一些输入流，它将这些流用作其基本数据源，
		 * 它可以直接传输数据或提供一些额外的功能.
		 */
		
		//提供缓冲区
		InputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		//提供数据完整性校验功能。 
		InputStream checkedInputStream = new CheckedInputStream(fileInputStream, new CRC32());
		//提供解密功能 
		InputStream CipherInputStream = new CipherInputStream(fileInputStream, Cipher.getInstance("DES/CBC/PKCS5Padding"));
		//提供读取基本 Java 数据类型功能 
		InputStream dataInputStream = new DataInputStream(fileInputStream);
		//提供读取回退功能，即可重复读取
		InputStream pushbackInputStream = new PushbackInputStream(fileInputStream);
		//提供读取Zip压缩文件内容功能 
		InputStream zipInputStream = new ZipInputStream(fileInputStream);
		//提供读取jar包内容功能 
		InputStream jarInputStream = new JarInputStream(fileInputStream);
		//提供读取GZIP压缩文件内容功能 
		InputStream gZIPInputStream = new GZIPInputStream(fileInputStream);
	}

}
