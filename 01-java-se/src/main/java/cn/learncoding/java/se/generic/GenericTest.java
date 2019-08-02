package cn.learncoding.java.se.generic;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author learncoding.cn
 */
public class GenericTest {
	
	private static Map<String, Long> map1 = new HashMap<>();
	private static Map<Long, String> map2 = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		//所有声明的泛型类型，在运行时都会被擦除掉，因此不同泛型类型的map对象的class在运行时都是一致的。
		System.out.println("从泛型对象本身出发，判断不同泛型类型的map对应class是否一致：" + map1.getClass().equals(map2.getClass()));
		
		Field field1 = GenericTest.class.getDeclaredField("map1");
		Field field2 = GenericTest.class.getDeclaredField("map2");
		
		Type type1 = field1.getGenericType();
		Type type2 = field2.getGenericType();
		//要找到泛型对象的泛型类型，只能从泛型对象所属的类中，获取对应属性声明的类型信息，从而拿到对应的泛型类型
		System.out.println("从声明泛型对象的类出发，判断不同泛型类型的map类型是否一致：" + type1.equals(type2));
		
		if (type1 instanceof ParameterizedType) {
			 ParameterizedType pt = (ParameterizedType) type1;
			 Type[] genericClazz = pt.getActualTypeArguments();
			 System.out.println("map1:"+ Arrays.toString(genericClazz));
		}
		
		if (type2 instanceof ParameterizedType) {
			 ParameterizedType pt = (ParameterizedType) type2;
			 Type[] genericClazz = pt.getActualTypeArguments();
			 System.out.println("map2:"+ Arrays.toString(genericClazz));
		}
		
	}
}
