package cn.learncoding.java.se.rmi;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RmiGeneral2 {
	
	public int test(int a, Integer b, Integer c){
		return a+b+c;
	}
	
	public int test(int a, int b, int c){
		return (a+b+c) * 2;
	}
	
	public int testList(List<String> list){
		return 1;
	}
	
	private volatile static boolean flag = false;

	public interface Compute extends Remote {
	    <T> T execute(MethodEunm test, Object... params) throws RemoteException;
	}
	
	enum MethodEunm{
		TEST("cn.learncoding.java.se.rmi.RmiGeneral2","test", Integer.TYPE, Integer.class, Integer.class),
		TEST_DOUBLE("cn.learncoding.java.se.rmi.RmiGeneral2","test", Integer.TYPE, Integer.TYPE, Integer.TYPE),
		TEST_LIST("cn.learncoding.java.se.rmi.RmiGeneral2","testList", List.class);
		
		private String className;
		private String methodName;
		private Class<?>[] params;
		
		MethodEunm(String className, String methodName, Class<?>... params){
			this.className = className;
			this.methodName = methodName;
			this.params = params;
		}

		public String getClassName() {
			return className;
		}

		public String getMethodName() {
			return methodName;
		}

		public Class<?>[] getParams() {
			return params;
		}
	}
	
	public static class MethodCacheData{
		private Object object;
		private Method method;
		
		public MethodCacheData(Object object, Method method) {
			super();
			this.object = object;
			this.method = method;
		}
		
		public Object getObject() {
			return object;
		}
		public void setObject(Object object) {
			this.object = object;
		}
		public Method getMethod() {
			return method;
		}
		public void setMethod(Method method) {
			this.method = method;
		}
	}
	
	public static class ComputeEngine implements Compute, Serializable {

		private static final long serialVersionUID = 1781212503701026062L;
		
		private static final HashMap<MethodEunm, MethodCacheData> methodCache = new HashMap<>();
		
		static{
			MethodEunm[] methods = MethodEunm.values();
			for (MethodEunm methodEunm : methods) {
				try {
					Class<?> clazz = Class.forName(methodEunm.getClassName());
					Method method = clazz.getDeclaredMethod(methodEunm.getMethodName(), methodEunm.getParams());
					methodCache.put(methodEunm, new MethodCacheData(clazz.newInstance(), method));
				} catch (NoSuchMethodException | SecurityException | ClassNotFoundException 
						| InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T> T execute(MethodEunm method, Object... params) throws RemoteException {
			try {
				MethodCacheData data = methodCache.get(method);
				return (T) data.getMethod().invoke(data.getObject(), params);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
				e.printStackTrace();
				throw new RemoteException("remote invoke error", e);
			}
		}
	}

	private static void startRmiServer() {
		try {
		    String name = "Compute";
		    LocateRegistry.createRegistry(1099);
		    Naming.rebind(name, new ComputeEngine());
		    System.out.println("RMI server runing");
		    flag = true;
		    while (flag) {
		    	 Thread.sleep(100);
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	   public static void main(String[] args) throws Exception{
		   new Thread(new Runnable() {
				@Override
				public void run() {
					  startRmiServer();
				}
		   }).start();
		   
		   while (!flag) {
		    	 Thread.sleep(100);
			}
		   String name = "Compute";
	       Registry registry = LocateRegistry.getRegistry("localhost", 1099);
	       Compute comp = (Compute) registry.lookup(name);
	       int result = comp.execute(MethodEunm.TEST_DOUBLE, new Object[]{1,2,3});
	       System.out.println(result);
	       result = comp.execute(MethodEunm.TEST_LIST, new ArrayList<Integer>());
	       System.out.println(result);
	       flag = false;
	    }

}
