package cn.learncoding.java.se.concurrent.tools;

import java.util.concurrent.Semaphore;

public class SemaphoreLearn {
	
	/**
	 * 控制访问个数  计数信号量
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final Semaphore semaphore = new Semaphore(2,true);
		
		Runnable runnable = new Runnable() {
	        public void run() {
	            try {
	            	semaphore.acquire();
	            	System.out.println(Thread.currentThread().getName() + "acquire ");
	            	Thread.sleep(1000);
	            } catch (Exception ex) {
	            }finally {
	            	System.out.println(Thread.currentThread().getName() + "release ");
	            	semaphore.release();
				}
	        }
	    };
	    
	    for (int i = 0; i < 5; i++) {
			Thread thread = new Thread(runnable);
			thread.start();
		}
	}

}
