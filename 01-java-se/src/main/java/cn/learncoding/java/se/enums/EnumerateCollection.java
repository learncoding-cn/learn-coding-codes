package cn.learncoding.java.se.enums;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 *  枚举集合
 * 
 * @author learncoding.cn
 *
 */
public class EnumerateCollection {
	
	public static enum Colour {
		BLANK,WRITE,YELLOW,RED;
	}
	
	public static void main(String[] args) {
		 // EnumSet的使用
        EnumSet<Colour> colours = EnumSet.allOf(Colour.class);
        for (Colour colour : colours) {
            System.out.println(colour);
        }
 
        // EnumMap的使用
        EnumMap<Colour, String> map = new EnumMap<>(Colour.class);
        map.put(Colour.BLANK, "黑色");
        map.put(Colour.WRITE, "白色");
        
        for (Iterator<Entry<Colour, String>> iterator = map.entrySet().iterator(); iterator.hasNext();) {
            Entry<Colour, String> entry = iterator.next();
            System.out.println(entry.getKey().name() + ":" + entry.getValue());
        }
	}

}
