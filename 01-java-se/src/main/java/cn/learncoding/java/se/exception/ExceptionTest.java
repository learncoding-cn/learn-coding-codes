package cn.learncoding.java.se.exception;

import java.net.ConnectException;
/**
 * @author learncoding.cn
 */
public class ExceptionTest {
	public static void main(String[] args) {
		try {
			throw new ConnectException("网络连接异常，需要捕获");
		} catch (ConnectException e) {
			e.printStackTrace();
		}
		throw new NullPointerException("空指针异常，无需捕获");
	}
}
