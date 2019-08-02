package cn.learncoding.java.se.concurrent.locks;

import java.util.concurrent.CountDownLatch;
/**
 * @author learncoding.cn
 */
public class SynchronizedLearn {
	
	private static int staticNum;
	private int num;
	private int objectNum;
	private final Object syncObject = new Object();
	
	public static void main(String[] args) throws Throwable{
		// synchronized 修饰静态方法，锁对象是类对象：SynchronizedLearn.class
		setStaticNum(1);
		getStaticNum();
		
		SynchronizedLearn learn = new SynchronizedLearn();
		// synchronized 修饰方法，锁对象是对象本身：learn
		learn.setNum(1);
		learn.getNum();
		
		// synchronized方法块，锁对象是对应对象：syncObject
		learn.setObjectNum(1);
		learn.getObjectNum();
		
		/**
		 * 锁对象验证
		 */
		
		//如果锁对象不匹配，方法结果会在 synchronized out之前输出
		
		testSyncObject(new MethodAdapter() {
			@Override
			public int invoke() {
				return getStaticNum();
			}
		}, SynchronizedLearn.class);
		
		testSyncObject(new MethodAdapter() {
			@Override
			public int invoke() {
				return learn.getNum();
			}
		}, learn);
		
		testSyncObject(new MethodAdapter() {
			@Override
			public int invoke() {
				return learn.getObjectNum();
			}
		}, learn.getSyncObject());
	}
	//synchronized 修饰静态方法，锁对象是类对象：SynchronizedLearn.class	
	public static synchronized int getStaticNum() {
		return staticNum;
	}

	public static synchronized void setStaticNum(int staticNum) {
		SynchronizedLearn.staticNum = staticNum;
	}

	// synchronized 修饰方法，锁对象是对象本身:this
	public synchronized int getNum() {
		return num;
	}

	public synchronized void setNum(int num) {
		this.num = num;
	}

	// synchronized方法块，锁对象是对应对象：syncObject
	public int getObjectNum() {
		synchronized (syncObject) {
			return objectNum;
		}
	}

	public void setObjectNum(int objectNum) {
		synchronized (syncObject) {
			this.objectNum = objectNum;
		}
	}
	
	public Object getSyncObject() {
		return syncObject;
	}
	
	public static interface MethodAdapter{
		int invoke();
	}

	private static void testSyncObject(MethodAdapter adapter, Object syncObject) throws Throwable {
		//使用await让多个线程暂停，然后在countDown后同时继续执行
		final CountDownLatch latch = new CountDownLatch(1);
		
		Thread waitThread = new Thread(new Runnable(){
			@Override
			public void run() {
				synchronized (syncObject) {
					System.out.printf("%-70s synchronized in \n", syncObject);
					try {
						latch.await();
						Thread.sleep(3000);
					} catch (InterruptedException e) {
					}
					System.out.printf("%-70s synchronized out \n", syncObject);
				}
			}
			
		});
		
		Thread getThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					latch.await();
					int num = adapter.invoke();
					System.out.printf("%-70s %d \n", syncObject, num);
				} catch (Throwable e) {
				}
			}
		});
		waitThread.start();
		getThread.start();
		latch.countDown();
	}
}
