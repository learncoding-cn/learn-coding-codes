package cn.learncoding.java.se.rmi;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiGeneral {
	
	public Integer test(Integer a, Integer b, Integer c){
		return a+b+c;
	}
	
	public int test(int a, Integer b, Integer c){
		return (a+b+c) * 2;
	}
	
	private volatile static boolean flag = false;

	public interface Compute extends Remote {
		/**
		 * 必须将参数类型列表传递过来，否则如上面两个test方法一样，不确定到底执行的是哪一个方法
		 * 
		 * @param className
		 * @param methodName
		 * @param params
		 * @param classes
		 * @return
		 * @throws RemoteException
		 */
	    <T> T execute(String className, String methodName, Object[] params, Class<?>[] classes) throws RemoteException;
	}
	
	public static class ComputeEngine implements Compute, Serializable {

		private static final long serialVersionUID = 1781212503701026062L;

		@SuppressWarnings("unchecked")
		@Override
		public <T> T execute(String className, String methodName, Object[] params, Class<?>[] classes) throws RemoteException {
			try {
				Class<?> clazz = Class.forName(className);
				Method method = clazz.getDeclaredMethod(methodName, classes);
				return (T) method.invoke(clazz.newInstance(), params);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| InstantiationException | ClassNotFoundException | NoSuchMethodException | SecurityException e) {
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
	       int result = comp.execute("cn.learncoding.java.se.rmi.RmiGeneral", "test", new Object[]{1,2,3}, new Class<?>[]{Integer.TYPE, Integer.class, Integer.class});
	       System.out.println(result);
	       flag = false;
	    }

}
