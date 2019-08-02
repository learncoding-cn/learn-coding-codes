package cn.learncoding.java.se.enums;

/**
 * @author learncoding.cn
 */
public enum OrderStatus {
	UNFINISHED(0,"未完成"),
	WAIT_FOR_PAY(1,"待支付"),
	PAID(2,"已支付"),
	CANCEL(3,"已取消");
	
	private int status;
	
	private String desc;
	
	private OrderStatus(int status, String desc){
		this.status = status;
		this.desc = desc;
	}
	
	public int getStatus() {
		return status;
	}

	public String getDesc() {
		return desc;
	}
	
	public static String getDescByStatus(int status) {
		OrderStatus[] array = OrderStatus.values();
		for (OrderStatus orderStatus : array) {
			if (orderStatus.getStatus() == status) {
				return orderStatus.getDesc();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(OrderStatus.PAID.getDesc() + "的订单的数据库中存储状态为：" + OrderStatus.PAID.getStatus());
		System.out.println("数据库中存储状态为 1 的订单状态为: " + OrderStatus.getDescByStatus(1));
	}
}