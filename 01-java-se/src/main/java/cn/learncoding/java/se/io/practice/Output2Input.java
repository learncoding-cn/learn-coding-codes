package cn.learncoding.java.se.io.practice;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 
 * 输入字节流转为输出字节流
 * 
 * @author learncoding.cn
 *
 */
public class Output2Input {
	public static void main(String[] args) {
		PipedOutputStream pos = null;
		PipedInputStream pis = null;
		
		try {
			pis = new PipedInputStream();
			pos = new PipedOutputStream(pis);
			int i = 1;
			pos.write(i);
			while (pis.available() > 0 && i < 100) {
				int data = pis.read();
				System.out.print(data + " ");
				pos.write(++i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (pis != null) {
					pis.close();
				}
				if (pos != null) {
					pos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
