package cn.learncoding.java.se.concurrent.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class FalseSharingFail {
	
	static long start = 0;
	
	static Data data = new Data();
	
	public static class Data{
//		volatile 
		int i;
//		volatile 
		int y;
	}
	
	static void inc2(){
		for (int k = 0; k < 1000_000; k++) {
			data.i = k;
			if (k % 1000_00 == 0) {
				System.out.printf("num %d, y: %d\n", k/1000_00, data.y);
			}
		}
	}
	
	static void inc(){
		for (int k = 0; k < 1000_000; k++) {
			data.y = k;
			if (k % 1000_00 == 0) {
				System.out.printf("num %d, i: %d\n", k/1000_00, data.i);
			}
		}
	}
	/**	volatile	   i		   y
	 *	两个都没有 		7103588		7075364      没有可见性，各自在各自线程中，不同步
	 *	i有，y没有	 	30413158 	6460519	 i每次更新都会从主存中读取，
	 * 	y有，i没有		6668995		31075151
	 * 	二者都有		50923374	69254852
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int num = 2;

		final CyclicBarrier barrier = new CyclicBarrier(num, new Runnable() {
	            @Override
	            public void run() {
	            	start = System.nanoTime();
	            }
	        });
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					barrier.await();
					inc2();
					long end = System.nanoTime();
					System.out.printf("inc2 end, used time is %d \n", end - start);
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		try {
			barrier.await();
			inc();
			long end = System.nanoTime();
			System.out.printf("inc end, used time is %d \n", end - start);
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		
	}
	
}
