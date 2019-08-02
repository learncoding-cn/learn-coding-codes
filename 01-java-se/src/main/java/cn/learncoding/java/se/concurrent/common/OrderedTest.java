package cn.learncoding.java.se.concurrent.common;

public class OrderedTest {
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(){
		      public void run() {
		    	  
		      }
		 };
		    Thread t2 = new Thread(){
		      public void run() {
		    	  
		      }
		    };
		    t1.start();
		    t2.start();
		    t1.join();
		    t2.join();
	}
	
	static class Data{
		int[] array = new int[]{0,0,0,0,0,
								0,0,0,0,0};
		
		public Data() {
			for (int i = 0; i < array.length; i++) {
				array[i] = 1;
			}
		}
		
		public int sum(){
			int sum = 0;
			for (int i : array) {
				sum += array[i];
			}
			return sum;
		}
	}

}
