package pertemuan_4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

public class D_PSDA04_L0125105_LazuardiAkbarImani {
    public static void main(String[] args) throws Exception {
        // 1. PENGGUNAAN MAP: Menyimpan daftar film
        // Key: Kode Film (String), Value: Judul & Jadwal Film (String)
        try (Scanner scanner = new Scanner(System.in)) {
            // 1. PENGGUNAAN MAP: Menyimpan daftar film
            // Key: Kode Film (String), Value: Judul & Jadwal Film (String)
            Map<String, String> daftarFilm = new HashMap<>();
            daftarFilm.put("F1", "Minions - 10:00 WIB");
            daftarFilm.put("F2", "Spiderman - 12:00 WIB");
            daftarFilm.put("F3", "Interstellar - 14:00 WIB");
            daftarFilm.put("F4", "Avengers - 16:00 WIB");
            daftarFilm.put("F5", "Joker - 18:00 WIB");
            
            // 2. PENGGUNAAN SET: Menyimpan daftar kursi yang SUDAH terisi
            // Menggunakan Set karena menjamin tidak ada data kursi yang kembar/duplikat
            Set<String> kursiTerisi = new HashSet<>();
            
            // Simulasi beberapa kursi sudah dipesan oleh orang lain
            kursiTerisi.add("A1");
            kursiTerisi.add("A2");
            
            // 3. PENGGUNAAN MAP: Menyimpan data tiket pengguna
            // Key: Kode Booking ID (String), Value: Detail Tiket (String)
            Map<String, String> databaseTiket = new HashMap<>();
            
            boolean running = true;
            System.out.println("========================================");
            System.out.println("       CinemaGO - Ticketing System      ");
            System.out.println("========================================");
            
            while (running) {
                System.out.println("\nPilihan menu:");
                System.out.println("1. Pesan Tiket");
                System.out.println("2. Cek Tiket Saya");
                System.out.println("3. Keluar");
                System.out.print("Pilih menu (1/2/3): ");
                
                String pilihan = scanner.nextLine();
                
                switch (pilihan) {
                    case "1" -> {
                        // PROSES PEMESANAN
                        System.out.println("\n----- Daftar Film -----");
                        // Menampilkan daftar film dengan iterasi Map (O(n) karena harus menampilkan semua film)
                        for (Map.Entry<String, String> film : daftarFilm.entrySet()) {
                            System.out.println("[" + film.getKey() + "] " + film.getValue());
                        }
                        System.out.println("-----------------------");
                        
                        System.out.print("\nMasukkan Kode Film: ");
                        String kodeFilm = scanner.nextLine().toUpperCase();
                        
                        // Mengecek apakah film ada di dalam Map menggunakan containsKey (O(1) pencarian cepat)
                        if (!daftarFilm.containsKey(kodeFilm)) {
                            System.out.println("[ERROR] Film tidak ditemukan!");
                            break;
                        }
                        
                        System.out.print("Pilih Kursi (misal: A3, B1, C5): ");
                        String kursi = scanner.nextLine().toUpperCase();
                        
                        // PENGGUNAAN SET: Mengecek apakah kursi sudah terisi (O(1) pencarian cepat)
                        if (kursiTerisi.contains(kursi)) {
                            System.out.println("[ERROR] Kursi " + kursi + " sudah dipesan. Silakan pilih kursi lain.");
                        } else {
                            // Jika kosong, masukkan ke Set agar tidak bisa dipesan lagi
                            kursiTerisi.add(kursi);
                            
                            // Buat Kode Booking unik
                            String kodeBooking = "AKBAR-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
                            
                            // Buat detail tiket dan simpan ke Map database tiket
                            String detailTiket = "Film: " + daftarFilm.get(kodeFilm) + " | Kursi: " + kursi;
                            databaseTiket.put(kodeBooking, detailTiket);
                            
                            System.out.println("\n[SUCCESS] Tiket berhasil dipesan.");
                            System.out.println("Kode Booking Anda: " + kodeBooking);
                            System.out.println("!! Simpan kode ini untuk mengecek tiket !!");
                        }
                    }
                        
                    case "2" -> {
                        // PROSES CEK TIKET
                        System.out.print("\nMasukkan Kode Booking Anda: ");
                        String cariBooking = scanner.nextLine().toUpperCase();
                        
                        // Mengecek tiket di dalam Map secara instan
                        if (databaseTiket.containsKey(cariBooking)) {
                            System.out.println("\n--- Detail Tiket Ditemukan ---");
                            System.out.println("Kode: " + cariBooking);
                            System.out.println(databaseTiket.get(cariBooking));
                            System.out.println("-----------------------------");
                        } else {
                            System.out.println("[ERROR] Kode Booking tidak ditemukan.");
                        }
                    }
                        
                    case "3" -> {
                        // Menampilkan pesan keluar dan informasi pembuat aplikasi
                        System.out.println("========================================");
                        System.out.println("              CinemaGO by               ");
                        System.out.println("      Lazuardi Akbar Imani - L0125105   ");
                        System.out.println("========================================");
                        running = false;
                    }
                        
                    default -> System.out.println("[ERROR] Pilihan tidak valid.");
                }
            }
        }
    }
}