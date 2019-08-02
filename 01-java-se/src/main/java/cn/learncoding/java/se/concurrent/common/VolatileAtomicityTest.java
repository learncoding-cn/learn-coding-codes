package cn.learncoding.java.se.concurrent.common;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author learncoding.cn
 */
public class VolatileAtomicityTest {

    private static final long length = 10000;

    private static long num = 0;

    private static volatile long volatileNum = 0;

    private static AtomicLong atomicNum = new AtomicLong(0);

    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(){
            public void run() {
                for (int i = 0; i < length; i++) {
                    num++;
                    volatileNum++;
                    atomicNum.incrementAndGet();
                }
            }
        };
        Thread t2 = new Thread(){
            public void run() {
                for (int i = 0; i < length; i++) {
                    num++;
                    volatileNum++;
                    atomicNum.incrementAndGet();
                }
            }
        };
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(" atomicNum = " + atomicNum.get() + "; volatileNum = " + volatileNum + "; num = " + num);
    }
}
