package cn.learncoding.java.se.concurrent.tools;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierLearn {
	
	/**
	 * 栏栅
	 * 多个线程，达到对应个数后，触发对应处理逻辑，然后一起执行   
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		final CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
			public void run() {
				  System.out.println("trigger barrier ");
			}
		});
		Runnable runnable = new Runnable() {
	        public void run() {
	            try {
	            	System.out.println(Thread.currentThread().getName() + " before await ");
	            	barrier.await();
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
	}

}
