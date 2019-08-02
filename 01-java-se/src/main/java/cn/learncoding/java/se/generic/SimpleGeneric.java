package cn.learncoding.java.se.generic;

/**
 * @author learncoding.cn
 */
public class SimpleGeneric {
	
	public static class Holder<T>{
		private T value;

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}
	}
	
	public static void print(Holder<?> holder){
		System.out.println(holder.getValue());
	}
	
	public static void main(String[] args) {
		
		Holder<Long> holder = new Holder<>();
		holder.setValue(new Long(100));
		print(holder);
		
		Holder<String> stringHolder = new Holder<>();
		stringHolder.setValue("learncoding.cn");
		print(stringHolder);
	}
	
	public static <E> E changeType(Class<E> clazz, Object object){
		if (clazz.isInstance(object)) {
			return (E)object;
		}else {
			throw new ClassCastException();
		}
	}
	
//	public static void main(String[] args) {
//		Number number = changeType(Number.class, new Integer(10));
//		System.out.println(number);
//	}
	
//	public static <E extends Number> double add(E... nums){
//		double result = 0;
//		for (Number e : nums) {
//			result += e.doubleValue();
//		}
//		return result;
//	}
//	
//	public static void main(String[] args) {
//		double result = add(1L, (byte)5, 10, 0.25f, new Double(5));
//		System.out.println(result);
//	}
}
