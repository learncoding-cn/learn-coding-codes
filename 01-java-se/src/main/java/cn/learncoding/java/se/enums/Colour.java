package cn.learncoding.java.se.enums;

import java.util.Arrays;

/**
 * @author learncoding.cn
 */
public enum  Colour {
//	  0     1      2    3
    BLANK,WRITE,YELLOW,RED;

    public static void main(String[] args) {
        System.out.println("所有枚举：" + Arrays.toString(Colour.values()));
        System.out.println(Colour.YELLOW.name() + " 的次序：" + Colour.YELLOW.ordinal());
        System.out.println(Colour.YELLOW + " 和 " + Colour.BLANK.toString() +" 的比较结果："+Colour.YELLOW.compareTo(Colour.BLANK));
        System.out.println(Colour.valueOf("BLANK").name());
        System.out.println(Colour.valueOf(Colour.class,"RED").name());
    }
}
