package pertemuan_2;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class KlinikSehatSentosa {
    // Deque untuk antrean utama agar bisa addLast (biasa) dan addFirst (darurat)
    private final Deque<String> antrianUtama;
    // Queue biasa untuk panggilan ulang
    private final Queue<String> panggilanUlang;

    // Konstruktor untuk inisialisasi struktur data
    public KlinikSehatSentosa() {
        antrianUtama = new LinkedList<>(); // Untuk menyimpan antrean utama dengan kemampuan addFirst dan addLast
        panggilanUlang = new LinkedList<>(); // Untuk menyimpan pasien yang tidak hadir saat dipanggil pertama kali
    }

    // Method mendaftar pasien biasa
    public void daftarPasien(String nama) {
        antrianUtama.addLast(nama);
        System.out.println("[+] " + nama + " masuk ke antrean utama.");
    }

    // Method mendaftar pasien darurat
    public void daftarPasienDarurat(String nama) {
        antrianUtama.addFirst(nama);
        System.out.println("[+] [DARURAT] " + nama + " diprioritaskan ke paling depan!");
    }

    // Method memanggil pasien dari antrean utama lalu panggilan ulang
    public void panggilPasien(boolean hadir) {
        String pasienDipanggil;
        boolean dariPanggilanUlang = false;

        if (!antrianUtama.isEmpty()) {
            pasienDipanggil = antrianUtama.pollFirst();
        } else if (!panggilanUlang.isEmpty()) {
            pasienDipanggil = panggilanUlang.poll();
            dariPanggilanUlang = true;
        } else {
            System.out.println("Tidak ada pasien dalam antrean.");
            return;
        }

        if (hadir) {
            if (dariPanggilanUlang) {
                System.out.println("[*] " + pasienDipanggil + " hadir saat panggilan ulang dan diperiksa.");
            } else {
                System.out.println("[*] " + pasienDipanggil + " dipanggil dan diperiksa.");
            }
        } else {
            panggilanUlang.add(pasienDipanggil);
            System.out.println("[!] " + pasienDipanggil + " tidak ada di tempat. Masuk daftar panggilan ulang.");
        }
    }

    // Method melihat status antrean
    public void lihatAntrian() {
        System.out.println("\n--- Status Antrean Saat Ini ---");
        System.out.println("Antrean Utama    : " + antrianUtama);
        System.out.println("Panggilan Ulang  : " + panggilanUlang);
        System.out.println("-------------------------------\n");
    }

    public static void main(String[] args) throws Exception {
        KlinikSehatSentosa klinik = new KlinikSehatSentosa();

        System.out.println("=============================================");
        System.out.println("    SISTEM ANTREAN KLINIK SEHAT SENTOSA      ");
        System.out.println("=============================================\n");

        // 1. Adi, Budi, dan Citra datang mendaftar
        klinik.daftarPasien("Adi");
        klinik.daftarPasien("Budi");
        klinik.daftarPasien("Citra");
        
        klinik.lihatAntrian();

        // 2. Dina datang dengan kondisi darurat
        klinik.daftarPasienDarurat("Dina");
        klinik.lihatAntrian();

        // 3. Dokter memanggil pasien. Dina yang darurat dipanggil pertama.
        System.out.println(">>> Dokter mulai memanggil pasien...");
        klinik.panggilPasien(true); // Dina diperiksa
        
        // 4. Dokter memanggil pasien berikutnya (Adi), tapi Adi tidak ada di tempat
        klinik.panggilPasien(false); // Adi tidak ada

        klinik.lihatAntrian();

        // 5. Dokter melanjutkan memanggil sisa antrean utama
        klinik.panggilPasien(true); // Budi diperiksa
        klinik.panggilPasien(true); // Citra diperiksa

        klinik.lihatAntrian();

        // 6. Antrean utama habis, dokter memanggil dari daftar panggilan ulang
        klinik.panggilPasien(true); // Adi dipanggil ulang dan diperiksa
        
        klinik.lihatAntrian();

        System.out.println("=======================================");
        System.out.println("             Created by:               ");
        System.out.println("      Lazuardi Akbar (L0125105)        ");
        System.out.println("     https://github.com/Dezkrazzer     ");
        System.out.println("=======================================");
    }
}
