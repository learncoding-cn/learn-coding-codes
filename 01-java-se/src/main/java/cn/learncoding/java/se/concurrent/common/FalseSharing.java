package cn.learncoding.java.se.concurrent.common;

/**
 * 
 * FIXME 
 * 				对volatile关键字赋予的其中一个语义是禁止指令重排序优化
 * 
 * 				在每个volatile存储之前发出StoreStore屏障。和在每个volatile存储之后发出StoreLoad障碍。
 * 
 * 				有volatile变量修饰的共享变量进行写操作的时候
 * 					将当前处理器缓存行的数据会写回到系统内存。
					这个写回内存的操作会引起在其他CPU里缓存了该内存地址的数据无效。
					
				如果你的字段是volatile，Java内存模型将
				在读操作前插入一个读屏障指令，保证每次从主存中读取。
					写入前会保证所有之前发生的事已经发生，并且任何更新过的数据值也是可见的，因为内存屏障会把之前的写入值都刷新到缓存。
					
				在写操作后插入一个写屏障指令，保证将write buffer中的数据实时刷新到主存，并置其他CPU缓存中该内存地址的数据无效。
				
				相当于 一个变量 的getset方法上加上synu
				
				内存屏障的确是有开销的——编译器/cpu不能重排序指令，导致不可以尽可能地高效利用CPU，另外刷新缓存亦会有开销。所以不要以为用volatile代替锁操作就一点事都没。
				
				
				http://ifeve.com/falsesharing/
				
				http://ifeve.com/volatile/
				
				内存屏障与JVM并发 http://www.infoq.com/cn/articles/memory_barriers_jvm_concurrency
				
				内存模型 http://www.cs.umd.edu/~pugh/java/memoryModel/
				
				Java内存模型FAQ http://ifeve.com/jmm-faq/		http://www.cs.umd.edu/~pugh/java/memoryModel/jsr-133-faq.html
				
				http://gee.cs.oswego.edu/dl/jmm/cookbook.html
				
				TODO http://gvsmirnov.ru/blog/tech/2014/02/10/jmm-under-the-hood.html
 * 
 * 
 * @author koukaiqiang
 *
 */
public final class FalseSharing implements Runnable {
	public final static int NUM_THREADS = 4; // change
	public final static long ITERATIONS = 500_1000_1000L;
	private final int arrayIndex;

	private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];
	static {
		for (int i = 0; i < longs.length; i++) {
			longs[i] = new VolatileLong();
		}
	}

	public FalseSharing(final int arrayIndex) {
		this.arrayIndex = arrayIndex;
	}
	/**
	 * 伪共享 						37668007971
	 * 填充后 						2847556707
	 * 使用@sun.misc.Contended	2897811352
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {
		final long start = System.nanoTime();
		runTest();
		System.out.println("duration = " + (System.nanoTime() - start));
	}

	private static void runTest() throws InterruptedException {
		Thread[] threads = new Thread[NUM_THREADS];

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new FalseSharing(i));
		}

		for (Thread t : threads) {
			t.start();
		}

		for (Thread t : threads) {
			t.join();
		}
	}

	public void run() {
		long i = ITERATIONS + 1;
		while (0 != --i) {
			longs[arrayIndex].value = i;
		}
	}

	public final static class VolatileLong {
		@SuppressWarnings("restriction")
		@sun.misc.Contended
		public volatile long value = 0L;
//		public long p1, p2, p3, p4, p5, p6; // comment out
	}
}