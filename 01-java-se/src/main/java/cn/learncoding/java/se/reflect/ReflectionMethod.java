package cn.learncoding.java.se.reflect;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionMethod {
	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD,ElementType.TYPE})
	public static @interface Ann{
		String name() default "";
	}

	public static class Child{
		
		private String test(){return "test";}
		
		@Ann(name = "test")
		public String test(String name){return "test " + name;}
	}
	/**
	 *  深入学习 jdk7 MethodHandle
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		Object object = new Child();
//		根据方法名和方法参数类型获取对应方法
		Method method = object.getClass().getDeclaredMethod("test", String.class);
		System.out.println("方法名：" + method.getName());
//		方法是public 直接可以invoke
		String result = (String) method.invoke(object, "王五");
		System.out.println("方法结果：" + result);
		
		System.out.println("是否带有可变数量的参数" + method.isVarArgs());
		Class<?>[] class1 = method.getParameterTypes();
		System.out.println("获取方法请求参数的全部类型：" + Arrays.toString(class1));
		System.out.println("方法返回类型 ： " +  method.getReturnType());
		
		Annotation[] annotations = method.getDeclaredAnnotations();
		System.out.println("获取方法上全部的注解：" + Arrays.toString(annotations));
		Ann ann = method.getDeclaredAnnotation(Ann.class);
		System.out.println("获取方法上注解信息：" + ann.name());
		
		
		
		System.out.println("=====================方法反射执行==========================");
		Method method2 = object.getClass().getDeclaredMethod("test");
		System.out.println("方法名：" + method2.getName());
//		方法是private，需要手动设置可操作权限
		method2.setAccessible(true);
		String result2 = (String) method2.invoke(object);
		System.out.println("方法结果：" + result2);	
	}

}
