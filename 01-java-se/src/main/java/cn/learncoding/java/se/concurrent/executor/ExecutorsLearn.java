package cn.learncoding.java.se.concurrent.executor;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorsLearn {
	
	public static void main(String[] args) {
		
		// (JDK5)单个线程的线程池
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		
		// (JDK5)固定线程数的线程池
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
		
		// (JDK5)不固定线程数的线程池,随着任务的增多增加线程，随着任务的减少回收空闲线程
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		
		// (JDK5)单个线程的线程池，可以以延迟或者定时的方式来执行任务
		ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
		
		// (JDK5)固定线程数的线程池，可以以延迟或者定时的方式来执行任务
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
		
		//https://dzone.com/articles/diving-into-java-8s-newworkstealingpools
		// (JDK8) ForkJoinPool 大任务分解为多个小任务，多个线程进行处理
		ExecutorService workStealingPool = Executors.newWorkStealingPool(1);
		
		// 内部完成队列管理  提交任务，然后完成的可以通过take移除，没有则阻塞，poll移除，没有则返回空
		CompletionService<String> completionService = new ExecutorCompletionService<>(singleThreadScheduledExecutor);
	}

}
