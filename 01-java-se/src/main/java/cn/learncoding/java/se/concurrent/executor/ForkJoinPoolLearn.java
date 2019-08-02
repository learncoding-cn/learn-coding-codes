package cn.learncoding.java.se.concurrent.executor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * https://stackoverflow.com/questions/41337451/detailed-difference-between-java8-forkjoinpool-and-executors-newworkstealingpool
 * 
 * @author learncoding.cn
 *
 */
public class ForkJoinPoolLearn {
	
	public static void main(String[] args) throws Throwable {
		 ForkJoinPool pool = ForkJoinPool.commonPool();
		 int[] array = new int[100];
		 for (int i = 0; i < array.length; i++) {
			 array[i] = i;
		}
		 Future<Integer> future = pool.submit(new SumTask(array));
		 Integer result = future.get();
		 System.out.println(result);
	     pool.shutdown();
	}
	
	static class SumTask extends RecursiveTask<Integer>{
	    private static final int LIMIT = 20; //每个小任务 最多只累加20个数
	    private int arry[];
	    private int start;
	    private int end;
	    
	    public SumTask(int[] arry) {
			super();
			this.arry = arry;
			this.start = 0;
		    this.end = arry.length;
		}

		public SumTask(int[] arry, int start, int end) {
	        super();
	        this.arry = arry;
	        this.start = start;
	        this.end = end;
	    }

	    @Override
	    protected Integer compute() {
	        int sum =0;
	        
	      //处理小任务
	        if(end - start < LIMIT){
	            for(int i = start; i < end; i++){
	                sum += arry[i];
	            }
	            return sum;
	            
	        //大任务分解成小任务    
	        }else {
	            int middle = (start + end)/2;
	            SumTask left = new SumTask(arry, start, middle);
	            SumTask right = new SumTask(arry, middle, end);
	            left.fork();
	            right.fork();
	            return left.join()+right.join();
	        }
	    }
	}

}
