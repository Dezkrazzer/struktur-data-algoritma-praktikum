package responsi_2;
/*
Nama: Lazuardi Akbar Imani
NIM: L0125105
Kelas: Informatika D
*/
import java.util.*;

// 1. Entitas Data Buku
class Buku {
    String isbn;
    String judul;
    String penulis;
    String kategoriKamar; 

    public Buku(String isbn, String judul, String penulis, String kategoriKamar) {
        this.isbn = isbn;
        this.judul = judul;
        this.penulis = penulis;
        this.kategoriKamar = kategoriKamar;
    }
}

// 2. Implementasi Graph untuk Navigasi
class GrafPerpustakaan {
    final private Map<String, List<String>> adjList;

    public GrafPerpustakaan() {
        adjList = new HashMap<>();
    }

    public void tambahLokasi(String lokasi) {
        adjList.putIfAbsent(lokasi, new ArrayList<>());
    }

    public void tambahJalan(String asal, String tujuan) {
        tambahLokasi(asal);
        tambahLokasi(tujuan);
        adjList.get(asal).add(tujuan);
        adjList.get(tujuan).add(asal); 
    }

    public void cetakRuteNavigasi(String asal, String tujuan) {
        if (!adjList.containsKey(asal) || !adjList.containsKey(tujuan)) {
            System.out.println("[404] Lokasi tidak valid.");
            return;
        }

        Queue<List<String>> queue = new LinkedList<>();
        Set<String> dikunjungi = new HashSet<>();

        queue.add(Arrays.asList(asal));
        dikunjungi.add(asal);

        while (!queue.isEmpty()) {
            List<String> jalur = queue.poll();
            String lokasiSekarang = jalur.get(jalur.size() - 1);

            if (lokasiSekarang.equals(tujuan)) {
                System.out.print("RUTE NAVIGASI: ");
                for (int i = 0; i < jalur.size(); i++) {
                    System.out.print(jalur.get(i));
                    if (i < jalur.size() - 1) System.out.print(" -> ");
                }
                System.out.println();
                cetakDenahPerpustakaan(tujuan);
                return;
            }

            for (String tetangga : adjList.get(lokasiSekarang)) {
                if (!dikunjungi.contains(tetangga)) {
                    dikunjungi.add(tetangga);
                    List<String> jalurBaru = new ArrayList<>(jalur);
                    jalurBaru.add(tetangga);
                    queue.add(jalurBaru);
                }
            }
        }
        System.out.println("[404] Rute tidak ditemukan.");
    }

    // METHOD BARU: Visualisasi Denah ASCII dengan Arah Jalan
    private void cetakDenahPerpustakaan(String tujuan) {
        System.out.println("\n==========================================================================");
        System.out.println("|                        DENAH LOKASI PERPUSTAKAAN                       |");
        System.out.println("==========================================================================");
        System.out.println("|                                                                        |");
        System.out.println("|          [Ruang Fiksi]                              [Ruang Sejarah]    |");
        System.out.println("|               ^                                            ^           |");
        System.out.println("|               |                                            |           |");
        System.out.println("|               +============== [Koridor Barat] =============+           |");
        System.out.println("|                                      ^                                 |");
        System.out.println("|        U                             |                                 |");
        System.out.println("|      B + T     [Pintu Masuk] ===> [Lobby Utama]                        |");
        System.out.println("|        S                             |                                 |");
        System.out.println("|                                      v                                 |");
        System.out.println("|               +============== [Koridor Timur] =============+           |");
        System.out.println("|               |                                            |           |");
        System.out.println("|               v                                            v           |");
        System.out.println("|     [Ruang Sains & Mat.]                       [Ruang Teknologi & Info]|");
        System.out.println("|               |                                                        |");
        System.out.println("|               v                                                        |");
        System.out.println("|      [Area Jurnal Ilmiah]                                              |");
        System.out.println("|                                                                        |");
        System.out.println("==========================================================================");
        System.out.println(" * Titik Tujuan Anda berada di: [" + tujuan + "]\n");
    }
}

public class D_PSDA_Responsi2_L0125105_LAZUARDIAKBARIMANI {

    // KATEGORI 2: Quick Sort
    static void quickSort(Buku[] arr, int low, int high) {
        if (low < high) {
            int pi = partisi(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partisi(Buku[] arr, int low, int high) {
        String pivot = arr[high].isbn;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j].isbn.compareTo(pivot) < 0) {
                i++;
                Buku temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Buku temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // KATEGORI 3: Binary Search
    static int binarySearch(Buku[] arr, String targetIsbn) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            int res = targetIsbn.compareTo(arr[m].isbn);

            if (res == 0) return m; 
            if (res > 0) l = m + 1; 
            else r = m - 1;         
        }
        return -1; 
    }

    public static void main(String[] args) {
        GrafPerpustakaan petaPerpus = new GrafPerpustakaan();
        petaPerpus.tambahJalan("Lobby Utama", "Koridor Barat");
        petaPerpus.tambahJalan("Lobby Utama", "Koridor Timur");
        petaPerpus.tambahJalan("Koridor Barat", "Ruang Fiksi");
        petaPerpus.tambahJalan("Koridor Barat", "Ruang Sejarah");
        petaPerpus.tambahJalan("Koridor Timur", "Ruang Sains & Matematika");
        petaPerpus.tambahJalan("Koridor Timur", "Ruang Teknologi & Informatika");
        petaPerpus.tambahJalan("Ruang Sains & Matematika", "Area Jurnal Ilmiah");

        Buku[] daftarBuku = {
            new Buku("TI1001", "Struktur Data dan Algoritma dengan Java", "Sianipar", "Ruang Teknologi & Informatika"),
            new Buku("FK2001", "Laskar Pelangi", "Andrea Hirata", "Ruang Fiksi"),
            new Buku("SM3001", "Pengantar Fisika Kuantum", "Prof. Suparmo", "Ruang Sains & Matematika"),
            new Buku("SJ4001", "Sejarah Dunia yang Disembunyikan", "Jonathan Black", "Ruang Sejarah"),
            new Buku("TI1002", "Pemrograman Pemula Node.js", "Rizky Putra", "Ruang Teknologi & Informatika"),
            new Buku("FK2002", "Bumi Manusia", "Pramoedya Ananta Toer", "Ruang Fiksi"),
            new Buku("SM3002", "Kalkulus Edisi Jilid 1", "Purcell", "Ruang Sains & Matematika"),
            new Buku("SJ4002", "Api Sejarah Nusantara", "Ahmad Mansur", "Ruang Sejarah"),
            new Buku("TI1003", "Membangun Aplikasi Flutter & Dart", "Eko Khannedy", "Ruang Teknologi & Informatika"),
            new Buku("FK2003", "Ronggeng Dukuh Paruk", "Ahmad Tohari", "Ruang Fiksi"),
            new Buku("SM3003", "Kimia Organik Dasar", "Dr. Handoko", "Ruang Sains & Matematika"),
            new Buku("SJ4003", "Gajah Mada: Biografi Politik", "Langit Kresna Hariadi", "Ruang Sejarah"),
            new Buku("TI1004", "Dasar Jaringan Komputer Modern", "Iwan Sofyan", "Ruang Teknologi & Informatika"),
            new Buku("FK2004", "Cantik Itu Luka", "Eka Kurniawan", "Ruang Fiksi"),
            new Buku("SM3004", "Biologi Sel dan Molekuler", "Yayan Sanjaya", "Ruang Sains & Matematika"),
            new Buku("SJ4004", "Kronologi Perang Diponegoro", "Peter Carey", "Ruang Sejarah"),
            new Buku("TI1005", "Panduan Cloud Computing Dasar", "Andi Wijaya", "Ruang Teknologi & Informatika"),
            new Buku("FK2005", "Negeri 5 Menara", "A. Fuadi", "Ruang Fiksi"),
            new Buku("SM3005", "Matematika Diskrit Komputasi", "Rinaldi Munir", "Ruang Sains & Matematika"),
            new Buku("SJ4005", "Peradaban Islam Abad Pertengahan", "Badri Yatim", "Ruang Sejarah"),
            new Buku("TI1006", "Kecerdasan Buatan (AI) Konsep Praktis", "Suyanto", "Ruang Teknologi & Informatika"),
            new Buku("FK2006", "Perahu Kertas", "Dee Lestari", "Ruang Fiksi"),
            new Buku("SM3006", "Pengantar Statistika Industri", "Walpole", "Ruang Sains & Matematika"),
            new Buku("SJ4006", "Asal-usul Perang Dunia Ke II", "A.J.P. Taylor", "Ruang Sejarah"),
            new Buku("TI1007", "Sistem Operasi dan Manajemen Linux", "Bambang Hariyanto", "Ruang Teknologi & Informatika")
        };

        // Urutkan data menggunakan Quick Sort
        quickSort(daftarBuku, 0, daftarBuku.length - 1);

        try (Scanner scanner = new Scanner(System.in)) {
            OUTER:
            while (true) {
                System.out.println("================================================");
                System.out.println("|         Sistem Informasi Perpustakaan        |");
                System.out.println("================================================");
                System.out.println("1. Daftar Buku");
                System.out.println("2. Cari Buku");
                System.out.println("3. Keluar Program");
                System.out.print("\nPilih opsi (1/2/3): ");
                int pilihan;
                try {
                    pilihan = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("\n[ERROR] Input tidak valid! Harap masukkan angka (1, 2, atau 3).");
                    scanner.nextLine(); 
                    System.out.println("--------------------------------------------------\n");
                    continue;
                } catch (Exception e) {
                    System.out.println("\n[ERROR] Terjadi kesalahan tak terduga: " + e.getMessage());
                    break; 
                }
                switch (pilihan) {
                    case 1 -> {
                        System.out.println("=========================================================================================");
                        System.out.println("|                                Daftar Buku Perpustakaan                               |");
                        System.out.println("=========================================================================================");
                        System.out.printf("%-10s | %-40s | %-30s\n", "ISBN", "JUDUL BUKU", "LOKASI RUANG");
                        System.out.println("-----------------------------------------------------------------------------------------");
                        for (Buku b : daftarBuku) {
                            System.out.printf("%-10s | %-40s | %-30s\n", b.isbn, b.judul, b.kategoriKamar);
                        }   System.out.println();
                    }
                    case 2 -> {
                        try {
                            System.out.print("\nMasukkan ISBN Buku yang dicari (Contoh: TI1001): ");
                            String inputIsbn = scanner.nextLine().trim().toUpperCase();
                            
                            if (inputIsbn.isEmpty()) {
                                System.out.println("\n[ERROR] ISBN tidak boleh kosong!\n");
                                continue;
                            }
                            
                            System.out.println("\nMencari buku menggunakan Binary Search...");
                            int hasilIndeks = binarySearch(daftarBuku, inputIsbn);
                            
                            if (hasilIndeks != -1) {
                                Buku bukuDitemukan = daftarBuku[hasilIndeks];
                                System.out.println("\n[Informasi Buku]");
                                System.out.println("Judul    : " + bukuDitemukan.judul);
                                System.out.println("Penulis  : " + bukuDitemukan.penulis);
                                System.out.println("ISBN     : " + bukuDitemukan.isbn);
                                System.out.println("Lokasi   : " + bukuDitemukan.kategoriKamar);
                                System.out.println("\n--------------------------------------------------");
                                
                                System.out.println("Menghitung rute tercepat dari Lobby Utama...");
                                petaPerpus.cetakRuteNavigasi("Lobby Utama", bukuDitemukan.kategoriKamar);
                                
                            } else {
                                System.out.println("\n[404] Buku dengan ISBN " + inputIsbn + " tidak ditemukan di perpustakaan.\n");
                            }
                        } catch (Exception e) {
                            System.out.println("\n[ERROR] Terjadi kegagalan saat mencari buku: " + e.getMessage());
                        }
                    }
                    case 3 -> {
                        System.out.println("\nTerima kasih telah menggunakan sistem perpustakaan.");
                        System.out.println("===============================");
                        System.out.println("      Lazuardi Akbar Imani     ");
                        System.out.println("            L0125105           ");
                        System.out.println("===============================");
                        break OUTER;
                    }
                    default -> System.out.println("\n[ERROR] Pilihan tidak valid! Silakan pilih 1, 2, atau 3.\n");
                }
            }
        }
    }
}