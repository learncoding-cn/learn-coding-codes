package cn.learncoding.java.se.concurrent.tools;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceLearn {
	/**
	 * 解决ABA的问题，给每个对象加上一个时间戳
	 * 
	 * 线程1准备用CAS将变量的值由A替换为B，在此之前，线程2将变量的值由A替换为C，
	 * 又由C替换为A，然后线程1执行CAS时发现变量的值仍然为A，所以CAS成功。
	 * 但实际上这时的现场已经和最初不同了，尽管CAS成功
	 * @param args
	 */
	public static void main(String[] args) {
		AtomicStampedReference<Integer> re = new AtomicStampedReference<>(1, 0);
		System.out.println("Reference: " + re.getReference() + "  Stamp:" + re.getStamp());

		//当Reference=1，Stamp=0时更新 Reference=2，Stamp=1
		boolean result = re.compareAndSet(1, 2, 0, 1);
		System.out.println(result);
		System.out.println("Reference: " + re.getReference() + "  Stamp:" + re.getStamp());

		//设置Reference=1，Stamp=2
		re.set(1, 2);
		System.out.println("Reference: " + re.getReference() + "  Stamp:" + re.getStamp());

		//当Reference=1，Stamp=0时更新 Reference=2，Stamp=3   即使Reference一致，但Stamp不同，所以失败
		result = re.compareAndSet(2, 1, 0, 3);
		System.out.println(result);
		System.out.println("Reference: " + re.getReference() + "  Stamp:" + re.getStamp());

	}

}
