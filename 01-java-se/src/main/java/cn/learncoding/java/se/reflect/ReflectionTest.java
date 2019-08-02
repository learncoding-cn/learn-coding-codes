package cn.learncoding.java.se.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author learncoding.cn
 */
public class ReflectionTest {
	
	public static class Test{
	
		private int value = 0;
		
		private int addAndGet(int value){
			this.value += value;
			return this.value;
		}
		
		private static Long test(){
			return 1L;
		}
	}
	
	public static void main(String[] args) throws Exception {
		//获取test方法的类对象
		Method testMethod = Test.class.getDeclaredMethod("test");
		//设置可存取  如果有权限访问此方法，此步骤可以忽略
		testMethod.setAccessible(true);
		//执行方法，静态方法不需要传具体对象
		Long result = (Long)testMethod.invoke(null);
		System.out.println(result);
		
		Test test = new Test();
		//获取addAndGet方法的类对象   有参数需要传参数类型
		Method addAndGetMethod = test.getClass().getDeclaredMethod("addAndGet", Integer.TYPE);
		//设置可存取
		addAndGetMethod.setAccessible(true);
		//执行方法，对象方法需要传对应对象，有参数则需要传参
		int result1 = (int)addAndGetMethod.invoke(test, 10);
		System.out.println(result1);
		
		String classFullName = ReflectionTest.class.getName() + "$Test";
		//获取value属性的类对象
		Field field = Class.forName(classFullName).getDeclaredField("value");
		//设置可存取 
		field.setAccessible(true);
		//写入值
		field.set(test, 100);
		//读取值
		result1 = (int)field.get(test);
		System.out.println(result1);
	}

}
