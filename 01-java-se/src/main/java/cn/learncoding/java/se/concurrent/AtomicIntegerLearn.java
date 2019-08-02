package cn.learncoding.java.se.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;

/**
 * 一个int 能原子更新的值， 线程安全 使用CAS方式 不进行加锁 效率更高，如果碰撞较低的话
 * 
 * @author kou
 *
 */
public class AtomicIntegerLearn {
	/**
	 * 
	 * java.util.concurrent.atomic 下的类都类似
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		AtomicInteger integer = new AtomicInteger();
		System.out.println(integer);
//		设置值
		integer.set(1);
//		获取值 
		System.out.println(integer.get());
//		自增并获取值
		System.out.println(integer.incrementAndGet());
//		获取值并增加对应值
		System.out.println(integer.getAndAdd(5));
//		获取值并自增
		System.out.println(integer.getAndIncrement());
//		自减并获取值
		System.out.println(integer.decrementAndGet());
//		与给定值进行某些操作并获取值
		System.out.println(integer.accumulateAndGet(5, new IntBinaryOperator() {
			@Override
			public int applyAsInt(int left, int right) {
				return left+right;
			}
		}));
//		比较，如果相同则set
		System.out.println(integer.compareAndSet(13, 14));
/**
 * 		为一个AtomicLong对象设置一个值，jvm会确保其他线程读取到最新值，原子类和voliatile变量也是一样的，
 * 		这是由依赖于硬件的系统指令(如x86的xchg)实现的。
 * 		lazySet却是无法保证这一点的方法，所以其他线程在之后的一小段时间里还是可以读到旧的值。
 * 
 * 		意思是这个方法进行赋值的话，性能更高，但其他线程不会实时看到最新值，
 * 		在对实时更新要求不强烈，但性能有较强要求时可以使用
 */
		integer.lazySet(1);
	}

}
