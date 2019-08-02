package cn.learncoding.java.se.enums;

/**
 * @author learncoding.cn
 */
public class Enumerate {
	public static enum Colour {
		BLANK,WRITE,YELLOW;
	}
	public static void main(String[] args) {
		Colour colour = Colour.WRITE;
		switch (colour) {
			case BLANK:
				break;
			case WRITE:
				System.out.println("switch To：" + colour);
				break;
			case YELLOW:
				break;
			default:
				break;
		}
	}
}
