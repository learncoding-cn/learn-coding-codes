package cn.learncoding.java.se.concurrent.common;

public class OrderTest {
	private static int i = -1;
	private static int flag = -1;
	private static boolean reset = true;
	
	private static int num0 = 0;
	private static int num1 = 0;
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(){
		      public void run() {
		    	  while (true) {
		    		  if (reset) {
		    			  i = 1;
					      flag = 1;
					      reset = false;
					  }else{
						  flag = 0;
						  i = 0;
					      reset = true;
					  }
				}
		      }
		    };
		    Thread t2 = new Thread(){
		      public void run() {
				    while (true) {
				    	if (flag == 1) {
				    		  if (i == 0) {
				    			  num0++;
					    		  if (num0 % 100 == 0) {
					    			  System.out.println("0 次数：" + num0 + "\t 1 次数：" + num1);
								 }
							  }else if (i == 1) {
								  num1++;
							  }
				    		  
				    		  //重置本线程缓冲
				    		  flag = -1;
				    		  i = -1;
						}
			    	}
		      }
		    };
		    t1.start();
		    t2.start();
		    t1.join();
		    t2.join();
		    
	}

}
