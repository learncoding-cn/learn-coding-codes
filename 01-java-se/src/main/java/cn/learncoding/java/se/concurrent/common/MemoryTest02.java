package cn.learncoding.java.se.concurrent.common;

public class MemoryTest02 {

    /**
     *
     *  缓存 L1 L2 L3
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int j = 1; j <= 512 * 1024; j = j << 1 ) {
            int[] arr = new int[j * 1024];
            int steps = 64 * 1024 * 1024;
            int lengthMod = arr.length - 1;
            long start = System.currentTimeMillis();
            for (int i = 0; i < steps; i++) {
                arr[(i * 16) & lengthMod]++; // (x & lengthMod) is equal to (x % arr.Length)
            }
            System.out.println(arr.length / 1024 + " KB :" + (System.currentTimeMillis() - start));
        }
    }
}
