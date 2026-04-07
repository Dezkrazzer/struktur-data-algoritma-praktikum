package pertemuan_3;

import java.util.ArrayList;

public class AntrianArrayList {
    public static void main(String[] args) {
        int n = 1000000;

        ArrayList<Integer> antrian = new ArrayList<>();

        long start = System.nanoTime();

        // pelanggan datang
        for (int i = 0; i < n; i++) {
            antrian.add(i);
        }

        // pelanggan dilayani dari depan
        while (!antrian.isEmpty()) {
            antrian.remove(0);
        }

        long end = System.nanoTime();

        System.out.println("Waktu yang dibutuhkan oleh ArrayList: " + (end - start) + " ns");
    }
}