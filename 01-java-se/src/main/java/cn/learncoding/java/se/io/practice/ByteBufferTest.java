package cn.learncoding.java.se.io.practice;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author learncoding.cn
 */
public class ByteBufferTest {
	
	public static void main(String[] args) {
//		在堆上分配内存    FULLGC 会进行回收  操作时会有一次额外的复制操作
		ByteBuffer buffer = ByteBuffer.allocate(1024);
//		堆外内存，即在JVM外，系统中分配的直接内存，FULLGC不进行回收 ，进行IO时 没有额外的复制操作，效率更高
		ByteBuffer buffer1 = ByteBuffer.allocateDirect(1024);
		
//		ByteBuffer中有三个变量    position代表现在位置   limit代表可达到最大位置   capacity代表容量
		/**
		 * 最开始分配完成 position 0，limit 1024，capacity 1024
		 */
		buffer.put((byte) 1);
		/**
		 * 放入数据后 position 1，limit 1024，capacity 1024
		 */
		buffer.put(new byte[]{1,1,1,1});
		/**
		 * 放入数据后 position 5，limit 1024，capacity 1024
		 */
		buffer.flip();
		/**
		 * flip后  position 0， limit 5， capacity 1024
		 * 这样刚才写入的数据都可以进行读取了  remaining() 返回 limit - position，剩余数据量
		 */
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes);
		System.out.println(Arrays.toString(bytes));
		/**
		 * get后  position 5， limit 5， capacity 1024
		 */
		buffer.limit(buffer.capacity());
		/**
		 * limit后  position 5， limit 1024， capacity 1024
		 */
		buffer.put((byte) 2);
		/**
		 * put后  position 6， limit 1024， capacity 1024
		 */
		buffer.flip();
		/**
		 * flip后  position 0， limit 6， capacity 1024
		 */
		byte b = buffer.get();
		/**
		 * get后  position 1， limit 6， capacity 1024
		 */
		buffer.mark();
		/**
		 * mark后  position 1， limit 6， capacity 1024, mark=1  标记位， 可通过reset()恢复到此位置
		 */
		buffer.get(new byte[3]);
		/**
		 * get后  position 4， limit 6， capacity 1024, mark=1  标记位
		 */
		buffer.reset();
		/**
		 * reset后  position 1， limit 6， capacity 1024, mark=1  标记位
		 */
		buffer.rewind();
		/**
		 * rewind后  position 0， limit 6， capacity 1024, mark=-1
		 * 清除mark标志，position置0
		 */
		buffer.clear();
		/**
		 * clear后  position 0， limit 1024， capacity 1024, mark=-1
		 */
	}

}
