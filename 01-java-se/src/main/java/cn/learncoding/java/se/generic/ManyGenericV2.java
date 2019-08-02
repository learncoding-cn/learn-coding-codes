package cn.learncoding.java.se.generic;
/**
 * @author learncoding.cn
 */
public class ManyGenericV2 {
	
	//泛型接口
	public static interface IOneHolder<T1>{
		public T1 getValue1();
	}
	
	//实现泛型接口时指定T1类型为Long，T1只能是Long类型
	public static class LongOneHolder implements IOneHolder<Long>{
		private Long value1;
		@Override public Long getValue1() {return value1;}
	}
	
	//实现泛型接口时未指定T1类型，T1可以在对象创建时指定任意类型
	public static class OneHolder<T1> implements IOneHolder<T1>{
		private T1 value1;
		@Override public T1 getValue1() {return value1;}
	}
	
	//继承时指定T1类型为Long，T1只能是Long类型
	public static class LongTwoHolder<T2> extends OneHolder<Long>{
		private T2 value2;
	}
	
	//继承时未指定T1类型，T1可以在对象创建时指定任意类型
	public static class TwoHolder<T1, T2> extends OneHolder<T1>{
		private T2 value2;
	}
}
