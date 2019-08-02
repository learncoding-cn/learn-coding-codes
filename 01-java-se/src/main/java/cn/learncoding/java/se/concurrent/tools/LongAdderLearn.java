package cn.learncoding.java.se.concurrent.tools;

import java.util.concurrent.atomic.LongAdder;

/**
 * 一个或多个变量一起维持初始为零long总和。 当更新（方法add(long) ）跨线程竞争时，
 * 变量集可以动态增长以减少争用。 方法sum() （或等效地， longValue() ）
 * 返回保持总和的整个变量组合的当前总和。 
 * 
 * 这个类是通常优选AtomicLong当多个线程更新时使用，用于诸如收集统计信息，
 * 不用于细粒度同步控制的共同总和。 在低更新争议下，这两类具有相似的特征。 
 * 但是，在高度争议的情况下，这一类的预期吞吐量明显高于牺牲更高的空间消耗。
 *
 */
public class LongAdderLearn {

	/**
	 * 有多个变量组成，每个线程绑定对应的变量，从而修改时只修改对应的变量。
	 * 最终查看结果时，将所有变量相加。达到在修改变量时，消除了竞争。
	 */
	public static void main(String[] args) {
		LongAdder sum = new LongAdder();
		sum.increment();
		sum.add(100);
		System.out.println(sum.sum());
	}
}
