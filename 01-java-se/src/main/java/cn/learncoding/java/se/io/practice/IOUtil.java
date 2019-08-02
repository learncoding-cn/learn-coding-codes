package cn.learncoding.java.se.io.practice;

import java.io.Closeable;
import java.io.IOException;

public class IOUtil {
	
	/**
	 * 关闭流通道
	 * @param closeables
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
