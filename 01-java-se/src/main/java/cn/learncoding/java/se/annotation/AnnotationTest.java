package cn.learncoding.java.se.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author learncoding.cn
 */
public class AnnotationTest {
	
	@Documented//生成文档
	@Retention(RetentionPolicy.RUNTIME)//运行时存在
	@Target({ElementType.TYPE})//作用于方法和类上
	@Inherited//子类可用
	public static @interface FoodCategory{
		String value();
		boolean eatDirectly() default false;
	}
	
	@FoodCategory(value = "水果", eatDirectly = true)
	public static class Apple{
	}
	
	public static class BigApple extends Apple{
	}
	
	@FoodCategory("蔬菜")
	public static class Potato{
	}
	
	public static void main(String[] args){
		FoodCategory category = Apple.class.getDeclaredAnnotation(FoodCategory.class);
		System.out.println(Apple.class.getSimpleName() + "的种类：" + category.value() + "，是否可直接吃：" + category.eatDirectly());
		category = BigApple.class.getAnnotation(FoodCategory.class);
		System.out.println(BigApple.class.getSimpleName() + "的种类：" + category.value() + "，是否可直接吃：" + category.eatDirectly());
		category = Potato.class.getAnnotation(FoodCategory.class);
		System.out.println(Potato.class.getSimpleName() + "的种类：" + category.value() + "， 是否可直接吃：" + category.eatDirectly());
	} 
}
