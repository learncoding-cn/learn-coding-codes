package cn.learncoding.java.se.generic;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;

/**
 * @author learncoding.cn
 */
public class GenericTest2 {
	
	public static class Data<K, V>{
		private K[] k;
	}
	
	private Data<? super Number, ? extends Number> data;
	
	public static void main(String[] args) throws Exception {
		
		Field kField = Data.class.getDeclaredField("k");
		
		Type kType = kField.getGenericType();
		
		if (kType instanceof GenericArrayType) {
			 GenericArrayType arrayType = (GenericArrayType) kType;
			 System.out.println(" GenericArrayType(数组类型):");
			 //属性声明类型
			 System.out.printf("\t %-30s %s \n","getTypeName()", arrayType.getTypeName());  
			 Type ArrayDataType = arrayType.getGenericComponentType();
			 //数组中单个组件的类型
			 System.out.printf("\t %-30s %s \n","getGenericComponentType()", ArrayDataType);  
			 
			 if (ArrayDataType instanceof TypeVariable) {
				 TypeVariable<?> typeVariable = (TypeVariable<?>)ArrayDataType;
				 System.out.println("\n TypeVariable(类型变量):");
				 //返回此类型变量的名称
				 System.out.printf("\t %-30s %s \n","getName()", typeVariable.getName());
				 //返回声明类型
				 System.out.printf("\t %-30s %s \n","getTypeName()", typeVariable.getTypeName());
				 //返回表示此类型变量上边界的 Type 对象的数组。注意，如果未显式声明上边界，则上边界为 Object。
				 System.out.printf("\t %-30s %s \n","getBounds()", Arrays.toString(typeVariable.getBounds()));
				 //返回 GenericDeclaration 对象，该对象表示声明此类型变量的一般声明。即声明变量的类
				 System.out.printf("\t %-30s %s \n","getTypeName()", typeVariable.getGenericDeclaration());
			}
		}

		
		Field field = GenericTest2.class.getDeclaredField("data");
		
		Type type = field.getGenericType();
		 if (type instanceof ParameterizedType) {
			 ParameterizedType pt = (ParameterizedType) type;
			 Type[] genericClazzArray = pt.getActualTypeArguments();
			 System.out.println("\n ParameterizedType(参数化类型):");
			 //返回对应类型声明的有序泛型类型列表
			 System.out.printf("\t %-30s %s \n","getActualTypeArguments()", Arrays.toString(genericClazzArray));
			 //如果对应类型是内部类，那么返回最外层的类型,否则返回 
			 System.out.printf("\t %-30s %s \n","getOwnerType()", pt.getOwnerType());
			 //返回属性声明类型
			 System.out.printf("\t %-30s %s \n","getRawType()", pt.getRawType());
			 
			 if (genericClazzArray[0] instanceof WildcardType) {
				 WildcardType superWildcardType = (WildcardType)genericClazzArray[0];
				 System.out.println("\n WildcardType(通配符类型):");
				 //返回属性声明类型
				 System.out.printf("\t %-30s %s \n","getTypeName()", superWildcardType.getTypeName());
				 //返回表示此类型变量下边界的 Type 对象的数组。
				 System.out.printf("\t %-30s %s \n","getLowerBounds()", Arrays.toString(superWildcardType.getLowerBounds()));
				 //返回表示此类型变量上边界的 Type 对象的数组。
				 System.out.printf("\t %-30s %s \n","getUpperBounds()", Arrays.toString(superWildcardType.getUpperBounds()));
			}
			 if (genericClazzArray[1] instanceof WildcardType) {
				 WildcardType extendsWildcardType = (WildcardType)genericClazzArray[1];
				 System.out.println(" WildcardType(通配符类型):");
				 System.out.printf("\t %-30s %s \n","getTypeName()", extendsWildcardType.getTypeName());
				 System.out.printf("\t %-30s %s \n","getLowerBounds()", Arrays.toString(extendsWildcardType.getLowerBounds()));
				 System.out.printf("\t %-30s %s \n","getUpperBounds()", Arrays.toString(extendsWildcardType.getUpperBounds()));
			}
		}
	}
}