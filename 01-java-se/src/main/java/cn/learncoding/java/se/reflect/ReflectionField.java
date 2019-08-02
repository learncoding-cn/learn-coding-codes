package cn.learncoding.java.se.reflect;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Arrays;

public class ReflectionField {
	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public static @interface Ann{
		String name() default "";
	}
	public static class Child{
		@Ann(name = "test")
		private String privateName;
	}
	
	public static void main(String[] args) throws Exception{
		Object object = new Child();
		
		Field[] fields = object.getClass().getDeclaredFields();
		System.out.println("获取所有属性：" + Arrays.toString(fields));
//		可以读取任何属性
		Field field = object.getClass().getDeclaredField("privateName");
		System.out.println("属性名：" + field.getName());
//		因为是private属性，需要手动设置可操作权限
		field.setAccessible(true);
//		读取object对象的此属性值
		String privateName = (String) field.get(object);
		System.out.println("原属性值 :" + privateName);
		
//		设置object对象的此属性值
		field.set(object, "王五");
		privateName = (String) field.get(object);
		System.out.println("设置属性值 :" + privateName);
		
		System.out.println("属性Class：" + field.getGenericType());
		System.out.println("属性所在的对象的Class：" + field.getDeclaringClass());
		
		;
		Annotation[] annotations = field.getDeclaredAnnotations();
		System.out.println("获取属性上全部的注解：" + Arrays.toString(annotations));
		Ann ann = field.getDeclaredAnnotation(Ann.class);
		System.out.println("获取属性上注解信息：" + ann.name());
		
	}

}
