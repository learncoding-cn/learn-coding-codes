package cn.learncoding.java.se.enums;
/**
 * @author learncoding.cn
 */
public interface Goods {
	
	abstract double getPrice();
	
	public static enum Fruit implements Goods {
		APPLE{
			@Override
			public double getPrice() {
				return 1.0;
			}
		},
		ORANGE{
			@Override
			public double getPrice() {
				return 2.0;
			}
		};
	}
	
	public static enum Vegetables implements Goods {
		POTATO(1.0),
		TOMATO(2.0);
		
		private double price;
		
		private Vegetables(double price) {
			this.price = price;
		}
		@Override
		public double getPrice() {
			return this.price;
		}
	}
	
	public static void main(String[] args) {
		Goods goods = Fruit.APPLE;
		System.out.println("货物"+goods+"的价格：" +goods.getPrice());
		goods = Vegetables.TOMATO;
		System.out.println("货物"+goods+"的价格：" +goods.getPrice());
	}
}
