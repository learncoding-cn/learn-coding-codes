package cn.learncoding.java.se.io;

import java.io.BufferedWriter;
import java.io.CharArrayWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * 输出字符流 由程序流出到外界
 * 
 * @author learncoding.cn
 *
 */
public class WriterLearn {

	public static void main(String[] args) throws Throwable {
		OutputStream os = new FileOutputStream("");
		
		//将字符流作为字节流输出，是字符流通向字节流的桥梁
		Writer outputStreamWriter = new OutputStreamWriter(os, StandardCharsets.UTF_8);
		
		//用来写入字符文件的便捷类,是FileOutputStream和OutputStreamWriter的组合
		Writer fileWriter = new FileWriter("");
		
		//写入charArray，可使用 toCharArray() 和 toString() 获取数据。 
		Writer charArrayWriter = new CharArrayWriter();
		
		//提供缓冲区 缓冲各个字符，从而提供单个字符、数组和字符串的高效写入。
		Writer bufferedWriter = new BufferedWriter(fileWriter);		

		//字符管道输出流
		Writer pipedWriter = new PipedWriter();
		
		//向文本输出流打印对象的格式化表示形式。
		Writer printWriter  = new PrintWriter("");
		
		//一个字符流，可以用其回收在字符串缓冲区中的输出来构造字符串.
		StringWriter stringWriter  = new StringWriter();
		 
	}
}
