package pertemuan_3;

import java.util.LinkedList;
import java.util.Queue;

public class AntrianQueue {
    public static void main(String[] args) {
        int n = 1000000;

        Queue<Integer> antrian = new LinkedList<>();

        long start = System.nanoTime();

        // pelanggan datang
        for (int i = 0; i < n; i++) {
            antrian.add(i);
        }

        // pelanggan dilayani dari depan
        while (!antrian.isEmpty()) {
            antrian.poll();
        }

        long end = System.nanoTime();

        System.out.println("Waktu yang dibutuhkan oleh Queue: " + (end - start) + " ns");
    }
}