package cn.learncoding.java.se.concurrent.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletionServiceLearn {
	
	private static final Callable<String> CALLABLE = new Callable<String>() {
		@Override
		public String call() throws Exception {
			return Thread.currentThread().getName();
		}
	};

	public static void main(String[] args) throws Throwable {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
		CompletionService<String> completionService = new ExecutorCompletionService<String>(fixedThreadPool);
		List<Callable<String>> list = new ArrayList<Callable<String>>(2);
		list.add(CALLABLE);
		list.add(CALLABLE);
		for (Callable<String> callable : list) {
			//负责任务接入和提交任务到线程池
			completionService.submit(callable);
		}
        int n = list.size();
        for (int i = 0; i < n; ++i) {
        	//负责结果处理和返回
            String result = completionService.take().get();
            if (result != null) 
                System.out.println(result);
        }
        fixedThreadPool.shutdown();
	}
}
