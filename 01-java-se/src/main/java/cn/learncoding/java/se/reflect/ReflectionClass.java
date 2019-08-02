package cn.learncoding.java.se.reflect;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionClass {
	
	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD,ElementType.TYPE})
	public static @interface Ann{
		String name() default "";
	}
	
	public static enum EnumTest{
	}
	
	public static interface IFather{
	}
	public static class Father implements IFather{
	}
	@Ann(name = "IFather")
	public static class Child extends Father implements IFather{
		private String name;
		public Child() {}
		public String test(){return name;}
	}
	
	public static void main(String[] args) throws Exception{
		Class<Child> clazz = Child.class;
		System.out.println("带包名的类名 ：" + clazz.getName());
		System.out.println("不带包名的类名 ：" + clazz.getSimpleName());
		System.out.println("底层类的规范化名称 ：" + clazz.getCanonicalName());
		System.out.println("判断IFather.class是不是此clazz的父类：" + IFather.class.isAssignableFrom(clazz));
		System.out.println("判断此对象是不是此class或其子类的实体：" + IFather.class.isInstance(new Child()));
		System.out.println("判断此对象是不是此class或其子类的实体：" + (new Child() instanceof IFather));
		
		Annotation[] annotations = clazz.getDeclaredAnnotations();
		System.out.println("获取类上全部的注解：" + Arrays.toString(annotations));
		Ann ann = clazz.getDeclaredAnnotation(Ann.class);
		System.out.println("获取类上注解信息：" + ann.name());
		
		Constructor<?>[] constructors = clazz.getDeclaredConstructors();
		System.out.println("获取类全部的构造函数：" + Arrays.toString(constructors));
		Constructor<Child> constructor = clazz.getDeclaredConstructor();
		System.out.println("获取类构造函数信息：" + constructor.getName());
		
		Field[] fields = clazz.getDeclaredFields();
		System.out.println("获取类全部的属性：" + Arrays.toString(fields));
		Field field = clazz.getDeclaredField("name");
		System.out.println("获取类属性信息：" + field.getName());
		
		Method[] methods = clazz.getDeclaredMethods();
		System.out.println("获取类全部方法：" + Arrays.toString(methods));
		Method method = clazz.getDeclaredMethod("test");
		System.out.println("获取类方法信息：" + method.getName());
		
		
		
		System.out.println("判断底层类是否是成员类：" + clazz.isMemberClass());
		System.out.println("判断底层类是否是接口：" + IFather.class.isInterface());
		System.out.println("判断底层类是否是枚举：" + EnumTest.class.isEnum());
		
		System.out.println("判断底层类是否是数组：" + String[].class.isArray());
		System.out.println("数组成员类型的 Class ：" + String[].class.getComponentType().getName());
		
		Class<?> class1 = int[].class.getComponentType();
		System.out.println("数组成员类型的 Class ：" + class1.getName());
		System.out.println("判断底层类是否是基本类型 ：" + class1.isPrimitive());
		
	}

}
