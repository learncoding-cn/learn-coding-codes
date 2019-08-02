package cn.learncoding.java.se.concurrent.thread;

/**
 * @author learncoding.cn
 */
public class ThreadLearn {
	
	public static void main(String[] args) throws Exception {
		newThreadWayOne();
		newThreadWayTwo();
	}
	
	/**
	 * 方式1 	 重写Thread类的run方法
	 * @throws InterruptedException
	 */
	private static void newThreadWayOne() throws InterruptedException {
		Thread thread = new Thread(){
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		};
		thread.start();
		thread.join();
	}
	
	/**
	 * 方式2 	实现Runnable接口，并作为Thread的构造函数参数
	 * @throws InterruptedException
	 */
	private static void newThreadWayTwo() throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		});
		thread.start();
		thread.join();
	}
}
