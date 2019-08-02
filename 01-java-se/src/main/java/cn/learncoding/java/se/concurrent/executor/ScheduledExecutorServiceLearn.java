package cn.learncoding.java.se.concurrent.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceLearn {
	
	private static final Callable<String> CALLABLE = new Callable<String>() {
		@Override
		public String call() throws Exception {
			return Thread.currentThread().getName();
		}
	};
	
	private static final Runnable RUNNABLE = new Runnable() {
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName());
		}
	};
	
	public static void main(String[] args) throws Throwable {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
		//延迟一次执行，成功后返回对应结果
		oneDelayCallable(scheduledThreadPool);
		//延迟一次执行，成功后返回null
		oneDelayRunable(scheduledThreadPool);
		//初始延迟时间后开始执行，然后每间隔固定时间进行执行
		fixedRateRunable(scheduledThreadPool);
		//初始延迟时间后开始执行，然后每上次任务完成到下次任务开始，间隔固定时间进行执行
		fixedDelayRunable(scheduledThreadPool);
		scheduledThreadPool.shutdown();
	}
	
	private static void oneDelayCallable(ScheduledExecutorService scheduledThreadPool)
			throws InterruptedException, ExecutionException {
		ScheduledFuture<String> future = scheduledThreadPool.schedule(CALLABLE, 0, TimeUnit.SECONDS);
		String result = future.get();
		System.out.println(result);
	}

	private static void oneDelayRunable(ScheduledExecutorService scheduledThreadPool)
			throws InterruptedException, ExecutionException {
		ScheduledFuture<?> future = scheduledThreadPool.schedule(RUNNABLE, 0, TimeUnit.SECONDS);
		Object result = future.get();
		System.out.println(result);
	}
	
	private static void fixedRateRunable(ScheduledExecutorService scheduledThreadPool)
			throws InterruptedException, ExecutionException {
		ScheduledFuture<?> future = scheduledThreadPool.scheduleAtFixedRate(RUNNABLE, 0, 1, TimeUnit.SECONDS);
		Thread.sleep(3000);
		future.cancel(true);
	}
	
	private static void fixedDelayRunable(ScheduledExecutorService scheduledThreadPool)
			throws InterruptedException, ExecutionException {
		ScheduledFuture<?> future = scheduledThreadPool.scheduleWithFixedDelay(RUNNABLE, 0, 1, TimeUnit.SECONDS);
		Thread.sleep(3000);
		future.cancel(true);
	}


}
