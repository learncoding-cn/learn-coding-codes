package cn.learncoding.java.se.concurrent.thread;

public class ProducerConsumer {
  private String value = "";
  private boolean hasValue = false;
  public void produce(String value) {
    while (hasValue) {
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println(Thread.currentThread().getName() + " Producing " + value + " as the next consumable");
    this.value = value;
    hasValue = true;
  }
  public String consume() {
    while (!hasValue) {
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    String value = this.value;
    hasValue = false;
    System.out.println(Thread.currentThread().getName() + " Consumed " + value);
    return value;
  }
}