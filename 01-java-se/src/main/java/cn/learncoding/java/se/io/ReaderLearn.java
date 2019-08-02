package cn.learncoding.java.se.io;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PipedReader;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

public class ReaderLearn {

	public static void main(String[] args) throws Throwable {
		InputStream is = new FileInputStream("");
		
		//将字节流作为字符流输入，是字节流通向字符流的桥梁
		Reader InputStreamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
		
		//用来读取字符文件的便捷类,是FileInputStream和InputStreamReader的组合
		Reader fileReader = new FileReader("");
		
		//读取charArray
		Reader charArrayReader = new CharArrayReader(new char[0]);
		
		//提供缓冲区 从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。 
		Reader bufferedReader = new BufferedReader(fileReader);		

		//字符管道输入流
		Reader pipedReader = new PipedReader();
		
		//一个源为字符串的字符流
		Reader stringReader  = new StringReader("");
		
		//提供读取回退功能，即可重复读取
		Reader pushbackReader = new PushbackReader(fileReader);
	}
}
