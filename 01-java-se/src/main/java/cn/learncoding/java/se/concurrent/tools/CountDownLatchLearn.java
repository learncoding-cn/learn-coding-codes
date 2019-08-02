package cn.learncoding.java.se.concurrent.tools;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchLearn {
	
	/**
	 * 闭锁
	 * 
	 * 让多个线程继续同时执行
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(1);
		
		Runnable runnable = new Runnable() {
	        public void run() {
	            try {
	            	System.out.println(Thread.currentThread().getName() + " before await ");
	                latch.await();
	                System.out.println(Thread.currentThread().getName() + " after await ");
	            } catch (Exception ex) {
	            }
	        }
	    };
	   Thread t1 = new Thread(runnable);
	   Thread t2 = new Thread(runnable);
       t1.start();
       t2.start();
       Thread.sleep(1000);
       System.out.println(" countDown ");
       latch.countDown();
	}
}
