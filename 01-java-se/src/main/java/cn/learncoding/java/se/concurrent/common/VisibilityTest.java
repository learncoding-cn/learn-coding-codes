package cn.learncoding.java.se.concurrent.common;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author learncoding.cn
 */
public class VisibilityTest {

  private static AtomicLong num = new AtomicLong(0);

  private static volatile boolean flag = false;
  
  public static void main(String args[]) throws Exception {
    Thread t1 = new Thread(){
      public void run() {
        while (true) {
          if (!flag){
            System.out.println(num.incrementAndGet() + "  " + Thread.currentThread().getName() + " flag = " + flag);
            flag = true;
          }
        }
      }
    };
    Thread t2 = new Thread(){
      public void run() {
        while (true) {
          if (flag){
            System.out.println(num.incrementAndGet() + "  " + Thread.currentThread().getName() + " flag = " + flag);
            flag = false;
          }
        }
      }
    };
    t1.start();
    t2.start();
    t1.join();
    t2.join();
  }
}