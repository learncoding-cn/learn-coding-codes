package cn.learncoding.java.se.concurrent.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExecutorServiceLearn {
	
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
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		//有返回值的线程任务执行
		doCallableTask(singleThreadExecutor);
		//没有值返回的线程任务执行
		doRunableTaskOne(singleThreadExecutor);
		//当线程执行成功后，返回给定的结果
		doRunableTaskTwo(singleThreadExecutor);
		//当线程执行成功后，返回null
		doRunableTaskThree(singleThreadExecutor);
		//多个线程任务执行，全部完成后返回
		doSomeTaskReturnAll(singleThreadExecutor);
		//多个线程任务执行，一个完成后立即返回，其他取消
		doSomeTaskReturnAny(singleThreadExecutor);
		singleThreadExecutor.shutdown();
	}

	private static void doRunableTaskOne(ExecutorService singleThreadExecutor)
			throws InterruptedException, ExecutionException {
		singleThreadExecutor.execute(RUNNABLE);
	}
	
	
	private static void doRunableTaskTwo(ExecutorService singleThreadExecutor)
			throws InterruptedException, ExecutionException {
		Future<String> future = singleThreadExecutor.submit(RUNNABLE, "success");
		String result = future.get();
		System.out.println(result);
	}
	
	private static void doRunableTaskThree(ExecutorService singleThreadExecutor)
			throws InterruptedException, ExecutionException {
		Future<?> future = singleThreadExecutor.submit(RUNNABLE);
		Object result = future.get();
		System.out.println(result);
	}

	private static void doCallableTask(ExecutorService singleThreadExecutor)
			throws InterruptedException, ExecutionException {
		Future<String> future = singleThreadExecutor.submit(CALLABLE);
		String result = future.get();
		System.out.println(result);
	}
	
	private static void doSomeTaskReturnAll(ExecutorService singleThreadExecutor)
			throws InterruptedException, ExecutionException {
		List<Callable<String>> list = new ArrayList<Callable<String>>(2);
		list.add(CALLABLE);
		list.add(CALLABLE);
		List<Future<String>> resultList = singleThreadExecutor.invokeAll(list, 5, TimeUnit.SECONDS);
		for (Future<String> future : resultList) {
			String result = future.get();
			System.out.println(result);
		}
	}
	
	private static void doSomeTaskReturnAny(ExecutorService singleThreadExecutor)
			throws InterruptedException, ExecutionException, TimeoutException {
		List<Callable<String>> list = new ArrayList<Callable<String>>(2);
		list.add(CALLABLE);
		list.add(CALLABLE);
		String result = singleThreadExecutor.invokeAny(list, 5, TimeUnit.SECONDS);
		System.out.println(result);
	}
}
