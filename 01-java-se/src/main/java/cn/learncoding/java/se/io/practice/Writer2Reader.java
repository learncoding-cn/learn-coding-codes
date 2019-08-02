package cn.learncoding.java.se.io.practice;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
/**
 * 将输出字符流转化为输入字符流
 * 
 * @author learncoding.cn
 *
 */
public class Writer2Reader {
	
	
	public static void main(String[] args) {
		PipedWriter writer = null;
		PipedReader reader = null;
		
		try {
			reader = new PipedReader();
			writer = new PipedWriter(reader);
			int i = 1;
			writer.write(i);
			while (reader.ready() && i < 100) {
				i = reader.read();
				System.out.println(i);
				writer.write(++i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
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
