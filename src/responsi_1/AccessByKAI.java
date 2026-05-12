/*
Nama : Lazuardi Akbar Imani
NIM  : L0125105
Kelas: Informatika 2025D
*/

package responsi_1;
import java.util.*;

// 1. TREE BIASA - Daop -> Stasiun -> Kereta -> Kedatangan/Keberangkatan
class TreeNode {
    String data;
    List<TreeNode> cabang = new ArrayList<>();
    public TreeNode(String d) { data = d; }
    public void tambah(TreeNode n) { cabang.add(n); }
    public void print(String p) {
        System.out.println(p + "└── " + data);
        for (TreeNode n : cabang) n.print(p + "    ");
    }
}

// 2. BINARY TREE - Keputusan Tipe Kursi (Prioritas vs Umum)
class SeatDecisionNode {
    String hasil;
    SeatDecisionNode yaKiri, tidakKanan;
    public SeatDecisionNode(String p) { hasil = p; }
}

// 3. BINARY SEARCH TREE - Database Pencarian Rute & Jadwal
class Kereta {
    int jamAwal; 
    String nama;
    List<String> daftarStasiun = new ArrayList<>();
    Map<String, String[]> jadwalPerStasiun = new HashMap<>(); // Menyimpan [Kedatangan, Keberangkatan] per stasiun

    // Fungsi untuk format ruteWaktu: "KODE,JamTiba,JamBerangkat" -> ex: "SLO,08.30,08.30"
    public Kereta(int j, String n, String... ruteWaktu) {
        this.jamAwal = j; this.nama = n;
        for (String rw : ruteWaktu) {
            String[] split = rw.split(",");
            daftarStasiun.add(split[0]); 
            jadwalPerStasiun.put(split[0], new String[]{split[1], split[2]});
        }
    }
}

class BSTJadwal {
    class Node { Kereta data; Node kiri, kanan; public Node(Kereta k) { data = k; } }
    Node root;
    public void insert(Kereta k) { root = insertRec(root, k); }
    private Node insertRec(Node r, Kereta k) {
        if (r == null) return new Node(k);
        if (k.jamAwal < r.data.jamAwal) r.kiri = insertRec(r.kiri, k);
        else r.kanan = insertRec(r.kanan, k);
        return r;
    }
    
    // FUNGSI PENCARIAN RUTE
    public boolean cariKeretaRute(Node r, String asal, String tujuan, boolean ditemukan) {
        if (r != null) {
            ditemukan = cariKeretaRute(r.kiri, asal, tujuan, ditemukan);
            List<String> rute = r.data.daftarStasiun;
            
            if (rute.contains(asal) && rute.contains(tujuan)) {
                if (rute.indexOf(asal) < rute.indexOf(tujuan)) {
                    String berangkatAsal = r.data.jadwalPerStasiun.get(asal)[1];
                    String tibaTujuan = r.data.jadwalPerStasiun.get(tujuan)[0];
                    System.out.println("   [" + berangkatAsal + " -> " + tibaTujuan + "] KA " + r.data.nama);
                    ditemukan = true;
                }
            }
            ditemukan = cariKeretaRute(r.kanan, asal, tujuan, ditemukan);
        }
        return ditemukan;
    }
}

public class AccessByKAI {
    static List<String> ruteLengkap = new ArrayList<>(); // LIST
    static Set<String> stasiunValid = new LinkedHashSet<>(); // SET
    static Map<String, String> dbTiket = new HashMap<>(); // MAP
    static Queue<String> antreanPesanan = new LinkedList<>(); // QUEUE
    static Stack<String> riwayatCetak = new Stack<>(); // STACK

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            BSTJadwal bst = new BSTJadwal();
            List<Kereta> semuaKereta = new ArrayList<>();
            
            // --- Data Stasion ---
            stasiunValid.addAll(Arrays.asList("SLO", "KT", "YK", "KTA", "KM", "KYA", "PWT", "CN", "CKR", "JNG", "GMR"));
            ruteLengkap.addAll(stasiunValid);
            
            // --- Data Kereta Api ---
            // Arah ke Gambir
            semuaKereta.add(new Kereta(830, "Argo Lawu (13)", "SLO,08.30,08.30", "KT,08.52,08.54", "YK,09.15,09.18", "PWT,11.07,11.10", "CN,12.55,12.58", "GMR,15.25,15.25"));
            semuaKereta.add(new Kereta(950, "Manahan (61)", "SLO,09.50,09.50", "KT,10.12,10.14", "YK,10.35,10.40", "KTA,11.25,11.30", "KYA,12.25,12.28", "PWT,12.52,12.57", "CN,14.41,14.49", "GMR,17.31,17.31"));
            semuaKereta.add(new Kereta(2035, "Argo Dwipangga (15)", "SLO,20.35,20.35", "KT,20.57,20.59", "YK,21.20,21.23", "PWT,23.12,23.15", "CN,01.00,01.03", "GMR,03.30,03.30"));
            semuaKereta.add(new Kereta(2235, "Manahan (63)", "SLO,22.35,22.35", "KT,22.57,22.59", "YK,23.20,23.25", "PWT,01.40,01.45", "CN,03.35,03.40", "GMR,06.33,06.33"));
            semuaKereta.add(new Kereta(905, "Argo Semeru (5)", "SLO,09.05,09.08", "YK,09.55,10.00", "PWT,12.25,12.30", "CN,14.20,14.25", "GMR,17.15,17.15"));
            semuaKereta.add(new Kereta(1920, "Bima (7)", "SLO,19.20,19.23", "YK,20.10,20.15", "PWT,22.25,22.30", "CN,00.15,00.20", "GMR,03.10,03.10"));
            semuaKereta.add(new Kereta(1455, "Gajayana (35)", "SLO,14.55,14.58", "YK,15.45,15.50", "PWT,18.25,18.30", "CN,20.25,20.30", "GMR,23.15,23.15"));
            semuaKereta.add(new Kereta(1600, "Brawijaya (37)", "SLO,16.00,16.05", "CN,21.30,21.35", "GMR,00.15,00.15"));
            
            // Arah ke Solo (Balik)
            semuaKereta.add(new Kereta(850, "Argo Dwipangga (16)", "GMR,08.50,08.50", "CN,11.35,11.40", "PWT,13.35,13.40", "YK,15.55,16.00", "KT,16.25,16.28", "SLO,16.50,16.50"));
            semuaKereta.add(new Kereta(1030, "Manahan (62)", "GMR,10.30,10.30", "CN,13.15,13.20", "PWT,15.15,15.20", "YK,17.25,17.30", "SLO,18.15,18.15"));
            semuaKereta.add(new Kereta(2045, "Argo Lawu (14)", "GMR,20.45,20.45", "CN,23.30,23.35", "PWT,01.30,01.35", "YK,03.50,03.55", "KT,04.20,04.23", "SLO,04.45,04.45"));
            semuaKereta.add(new Kereta(2250, "Manahan (64)", "GMR,22.50,22.50", "CN,01.35,01.40", "PWT,03.35,03.40", "YK,05.50,05.55", "SLO,06.40,06.40"));
            semuaKereta.add(new Kereta(620, "Argo Semeru (6)", "GMR,06.20,06.20", "CN,09.05,09.10", "PWT,11.05,11.10", "YK,13.25,13.30", "SLO,14.20,14.23"));
            semuaKereta.add(new Kereta(1700, "Bima (8)", "GMR,17.00,17.00", "CN,19.45,19.50", "PWT,21.45,21.50", "YK,23.55,00.05", "SLO,00.55,00.58"));
            semuaKereta.add(new Kereta(1850, "Gajayana (36)", "GMR,18.50,18.50", "CN,21.35,21.40", "PWT,23.35,23.40", "YK,01.55,02.05", "SLO,02.55,02.58"));
            semuaKereta.add(new Kereta(1545, "Brawijaya (38)", "GMR,15.45,15.45", "CN,18.30,18.35", "SLO,23.55,23.58"));
            
            // Masukkan kereta ke dalam BST
            for (Kereta k : semuaKereta) bst.insert(k);
            
            // --- TREE OTOMATIS BERDASARKAN SEMUA KERETA & JADWAL ---
            TreeNode infoKAI = buatTreeInformasiDaop(semuaKereta);
            
            // --- DECISION TREE (Kategori Penumpang Prioritas) ---
            SeatDecisionNode rootSeat = new SeatDecisionNode("Apakah penumpang berusia >= 60 tahun?");
            rootSeat.yaKiri = new SeatDecisionNode("PRIORITAS LANSIA");
            rootSeat.tidakKanan = new SeatDecisionNode("PENUMPANG UMUM");
            
            OUTER:
            while (true) {
                System.out.println("\n=============================================");
                System.out.println("                ACCESS BY KAI                  ");
                System.out.println("                 (simulated)                   ");
                System.out.println("=============================================\n");
                System.out.println("1. Pesan Tiket");
                System.out.println("2. Bayar & Cetak Tiket");
                System.out.println("3. Lihat Riwayat Tiket Terakhir");
                System.out.println("4. Jadwal Lengkap Kereta Api");
                System.out.println("5. Keluar");
                System.out.print("\nPilih Menu: ");
                int menu = sc.nextInt();
                sc.nextLine();
                switch (menu) {
                    case 1 -> {
                        System.out.println("\n----- PENCARIAN RUTE -----");
                        System.out.println("Stasiun Tersedia: " + stasiunValid);
                        System.out.print("Stasiun Asal   (ex: SLO) : ");
                        String asal = sc.nextLine().toUpperCase();
                        System.out.print("Stasiun Tujuan (ex: GMR) : ");
                        String tujuan = sc.nextLine().toUpperCase();
                        if (!stasiunValid.contains(asal) || !stasiunValid.contains(tujuan)) {
                            System.out.println("[INVALID] Stasiun Asal atau Tujuan tidak terdaftar!"); continue;
                        }   if (asal.equals(tujuan)) {
                            System.out.println("[INVALID] Stasiun Asal dan Tujuan tidak boleh sama!"); continue;
                        }   System.out.println("\nHasil Pencarian Rute [" + asal + "] -> [" + tujuan + "]:\n");
                        boolean adaKereta = bst.cariKeretaRute(bst.root, asal, tujuan, false);
                        if (!adaKereta) {
                            System.out.println("[INVALID] Tidak ada kereta langsung untuk rute ini."); continue;
                        }   System.out.print("\nPilih kereta (ex: Argo Lawu (13)): ");
                        String pilihanKereta = sc.nextLine();
                        System.out.println("\n----- DATA DIRI PENUMPANG -----");
                        System.out.print("Nama Lengkap : ");
                        String nama = sc.nextLine();
                        System.out.print("NIK          : ");
                        String nik = sc.nextLine();
                        System.out.print("Usia         : ");
                        int usia = sc.nextInt();
                        sc.nextLine();
                        String kategoriPenumpang = (usia >= 60) ? rootSeat.yaKiri.hasil : rootSeat.tidakKanan.hasil;
                        System.out.println("Status Kursi : " + kategoriPenumpang);
                        String[] letak = {"A", "B", "C", "D"};
                        String gerbong = (usia >= 60) ? String.valueOf(1 + new Random().nextInt(2)) : String.valueOf(3 + new Random().nextInt(4));
                        String kursi = (1 + new Random().nextInt(15)) + letak[new Random().nextInt(4)];
                        String nomorKursiTetap = "EKS-" + gerbong + " / " + kursi;
                        System.out.println("Nomor Kursi  : " + nomorKursiTetap);
                        String dataPesanan = String.format("%s|%s|%s|%s-%s|%s|%s", nama, nik, pilihanKereta, asal, tujuan, kategoriPenumpang, nomorKursiTetap);
                        antreanPesanan.add(dataPesanan);
                        System.out.println("\n[SUCCESS] Pesanan tersimpan di antrean! Silakan lanjut ke MENU 2 untuk Pembayaran.");
                    }
                    case 2 -> {
                        if (antreanPesanan.isEmpty()) {
                            System.out.println("[INVALID] Tidak ada pesanan dalam antrean."); continue;
                        }   String data = antreanPesanan.poll();
                        String[] p = data.split("\\|");
                        System.out.println("\n------ PEMBAYARAN ------");
                        System.out.println("Tagihan untuk Sdr. " + p[0] + " :\n");
                        System.out.println("Rute         : " + p[3]);
                        System.out.println("Kereta       : " + p[2]);
                        System.out.println("Tagihan      : Rp 550.000");
                        System.out.print("\nMasukkan Nominal Uang     : Rp ");
                        long bayar = sc.nextLong();
                        sc.nextLine();
                        if (bayar < 550000) {
                            System.out.println("❌ Uang kurang! Transaksi dibatalkan."); continue;
                        }   String bookingID = "KAI-" + (10000 + new Random().nextInt(90000));
                        dbTiket.put(bookingID, data);
                        riwayatCetak.push(bookingID);
                        if (bayar > 550000) {
                            System.out.println("\n\n\n[SUCCESS] PEMBAYARAN BERHASIL!");
                            System.out.println("Kembalian Anda: Rp " + (bayar - 550000) + "\n\n\n");
                        } else {
                            System.out.println("\n\n\n[SUCCESS] PEMBAYARAN BERHASIL!\n\n\n");
                        }
                        cetakBoardingPass(bookingID, p);
                    }
                    case 3 -> {
                        if (riwayatCetak.isEmpty()) {
                            System.out.println("[INVALID] Belum ada tiket yang dicetak.");
                        } else {
                            String idTerakhir = riwayatCetak.peek();
                            String[] p = dbTiket.get(idTerakhir).split("\\|");
                            System.out.println("\n----- TIKET TERAKHIR DICETAK -----");
                            cetakBoardingPass(idTerakhir, p);
                        }
                    }
                    case 4 -> {
                        System.out.println("\n");
                        infoKAI.print("");
                    }
                    case 5 -> {
                        System.out.println("Terima kasih telah menggunakan layanan KAI.\n");
                        System.out.println("===============================");
                        System.out.println("      Lazuardi Akbar Imani     ");
                        System.out.println("            L0125105           ");
                        System.out.println("===============================");
                        break OUTER;
                    }
                    default -> System.out.println("[ERROR] Pilihan tidak valid!");
                }
            }
        }
    }

    // ==============================================================
    // Tree
    // ==============================================================
    static TreeNode buatTreeInformasiDaop(List<Kereta> semuaKereta) {
        TreeNode root = new TreeNode("PAPAN INFORMASI SELURUH JADWAL KERETA API (GAPEKA 2025)");
        
        TreeNode d1 = new TreeNode("DAOP 1 JAKARTA");
        TreeNode d3 = new TreeNode("DAOP 3 CIREBON");
        TreeNode d5 = new TreeNode("DAOP 5 PURWOKERTO");
        TreeNode d6 = new TreeNode("DAOP 6 YOGYAKARTA");
        root.tambah(d1); root.tambah(d3); root.tambah(d5); root.tambah(d6);

        Map<String, TreeNode> mapStasiun = new HashMap<>();
        
        String[] stDaop1 = {"GMR", "JNG", "CKR"};
        for (String st : stDaop1) { TreeNode n = new TreeNode("Stasiun " + st); d1.tambah(n); mapStasiun.put(st, n); }
        
        String[] stDaop3 = {"CN"};
        for (String st : stDaop3) { TreeNode n = new TreeNode("Stasiun " + st); d3.tambah(n); mapStasiun.put(st, n); }
        
        String[] stDaop5 = {"PWT", "KYA", "KM", "KTA"};
        for (String st : stDaop5) { TreeNode n = new TreeNode("Stasiun " + st); d5.tambah(n); mapStasiun.put(st, n); }
        
        String[] stDaop6 = {"YK", "KT", "SLO"};
        for (String st : stDaop6) { TreeNode n = new TreeNode("Stasiun " + st); d6.tambah(n); mapStasiun.put(st, n); }

        // Masukkan SEMUA KERETA dan JAM TIBA/BERANGKAT ke dalam Tree
        for (Kereta k : semuaKereta) {
            for (String kodeStasiun : k.daftarStasiun) {
                TreeNode nodeStasiun = mapStasiun.get(kodeStasiun);
                if (nodeStasiun != null) {
                    TreeNode nodeKereta = new TreeNode("KA " + k.nama);
                    
                    // Ambil jadwal kedatangan dan keberangkatan
                    String[] jadwal = k.jadwalPerStasiun.get(kodeStasiun);
                    nodeKereta.tambah(new TreeNode("Kedatangan    : " + jadwal[0]));
                    nodeKereta.tambah(new TreeNode("Keberangkatan : " + jadwal[1]));
                    
                    nodeStasiun.tambah(nodeKereta);
                }
            }
        }
        return root;
    }

    static void cetakBoardingPass(String id, String[] p) {
        System.out.println("==========================================");
        System.out.println("            BOARDING PASS KAI             ");
        System.out.println("==========================================");
        System.out.println(" BOOKING ID : " + id);
        System.out.println(" NAMA       : " + p[0]);
        System.out.println(" NIK        : " + p[1]);
        System.out.println(" KERETA     : " + p[2]);
        System.out.println(" RUTE       : " + p[3]); 
        System.out.println(" KURSI      : " + p[5] + " (" + p[4] + ")");
        System.out.println(" STATUS     : LUNAS");
        System.out.println("==========================================");
        System.out.println("      Terima kasih telah menggunakan      ");
        System.out.println("        layanan KAI. Selamat jalan!       ");
        System.out.println("==========================================\n");
    }
}