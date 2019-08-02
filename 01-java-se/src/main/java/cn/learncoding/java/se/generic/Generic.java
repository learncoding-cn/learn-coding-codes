package cn.learncoding.java.se.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 泛型
 * 
 * 可以将符合标准的一类代码抽象化，简化工作量，不重新发明轮子
 * 
 * @author koukaiqiang
 *
 */
public class Generic {
	public static interface IBaseDao<T>{
		public void save(T t);
		public void update(T t);
		public T findById(Serializable id);
		public List<T> findList(Serializable id);
//		由返回值决定返回类型， 默认Object
		public <V> List<V> findListV(Serializable id);
	}
	public static class BaseDaoImpl<T> implements IBaseDao<T>{
		public void save(T t) {}
		public void update(T t) {}
		public T findById(Serializable id) {return null;}
		public List<T> findList(Serializable id) {return null;}
		public <V> List<V> findListV(Serializable id) {return new ArrayList<V>();}
	}
	public static class User{}
	public static class Address{}
	public static class UserDao extends BaseDaoImpl<User>{}
	public static class AddressDao extends BaseDaoImpl<Address>{}
	
	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		userDao.save(new User());
		AddressDao addressDao = new AddressDao();
		addressDao.save(new Address());
		List<String> list = addressDao.findListV(1);
		System.out.println(list);
		System.out.println(addressDao.findListV(1));
	}
}
