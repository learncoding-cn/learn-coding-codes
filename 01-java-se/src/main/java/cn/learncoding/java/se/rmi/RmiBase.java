package cn.learncoding.java.se.rmi;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiBase {
	
	private volatile static boolean flag = false;
	
	public interface Compute extends Remote {
	    int add(int a, int b) throws RemoteException;
	}
	
	public static class ComputeEngine implements Compute, Serializable {

		private static final long serialVersionUID = 1781212503701026062L;

		@Override
		public int add(int a, int b) throws RemoteException {
			return a+b;
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
	       int result = comp.add(1, 2);
	       System.out.println(result);
	       flag = false;
	    }

}
