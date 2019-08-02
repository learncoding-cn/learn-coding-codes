package cn.learncoding.java.se.generic;
/**
 * @author learncoding.cn
 */
public class ManyGeneric {
	
	public static class TwoHolder<T1,T2>{
		private T1 value1;
		private T2 value2;

		public T1 getValue1() {return value1;}

		public void setValue1(T1 value1) {this.value1 = value1;}

		public T2 getValue2() {return value2;}

		public void setValue2(T2 value2) {this.value2 = value2;}
	}
	
	public static void main(String[] args) {
		TwoHolder<Integer, String> twoHolder = new TwoHolder<>();
		twoHolder.setValue1(100);
		twoHolder.setValue2("learncoding.cn");
		System.out.println(twoHolder.getValue1());
		System.out.println(twoHolder.getValue2());
	}
}
