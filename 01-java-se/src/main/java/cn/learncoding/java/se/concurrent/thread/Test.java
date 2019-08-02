package cn.learncoding.java.se.concurrent.thread;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kou on 2018/5/3.
 */
public class Test {

    public static void main(String[] args) throws InterruptedException{
        final ProducerConsumer producerConsumer = new ProducerConsumer();
        final List<String> values = Arrays.asList("1","2","3","4","5","6","7","8","9");
        Thread writerThread = new Thread(() -> values.stream()
                .forEach(producerConsumer::produce));
        Thread readerThread = new Thread(() -> {
            for (int i = 0; i < values.size(); i++) {
                producerConsumer.consume();
            }
        });
        writerThread.start();
        readerThread.start();
        writerThread.join();
        readerThread.join();
    }
}
