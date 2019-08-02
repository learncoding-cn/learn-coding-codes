package cn.learncoding.java.se.exception;
/**
 * @author learncoding.cn
 */
public class ExceptionDemo {
	//自定义业务异常
	public static class BusinessException extends Exception{
		
		private static final long serialVersionUID = -1634880201329808999L;

		public BusinessException(String message, Exception e){
			  super(message, e);
		  } 
	}
	
	public static void doSomething() throws BusinessException {
		try {
			//业务代码
			throw new NullPointerException("空指针异常");
		} catch (NullPointerException e) {
			throw new BusinessException("业务出现异常", e);
		}
	}
	
	public static void main(String[] args) {
		try {
			doSomething();
		} catch (BusinessException e) {
			//TODO 业务出现异常，呼叫管理员
			e.printStackTrace();
		}
	}
}
