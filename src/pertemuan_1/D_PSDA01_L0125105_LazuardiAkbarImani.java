package pertemuan_1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class D_PSDA01_L0125105_LazuardiAkbarImani {
    public static void main(String[] args) throws Exception {
        // Error Handling (agar program tidak langsung crash saat terjadi error)
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nYou're about to exit the program. Exiting program now...");
        }));
        try (Scanner input = new Scanner(System.in)) {
            // Variabel dan Tipe Data
            int jumlahData;

            System.out.println("========================================");
            System.out.println("             BMI CALCULATOR             ");
            System.out.println("           by: Lazuardi Akbar           ");
            System.out.println("========================================\n");

            // Menggunakan loop untuk memastikan input jumlah data valid (Control Flow)
            while (true) {
                try {
                    System.out.print("Enter the number of data entries (type 0 to exit): ");
                    jumlahData = input.nextInt();

                    if (jumlahData == 0) { // Jika user ingin keluar program (ketik 0)
                        System.out.println("Exiting program...");
                        return;
                    } else if (jumlahData < 0) { // Jika user memasukkan input negatif
                        System.out.println("[ERROR] Input cannot be negative!");
                        continue;
                    }
                    break; // Keluar dari loop jika input valid
                } catch (InputMismatchException e) { // Jika user memasukkan input selain int
                    System.out.println("[ERROR] Please enter a valid integer!");
                    input.next();
                }
            }

            // Array
            double[] daftarBMI = new double[jumlahData];

            // Control Flow (Looping Input)
            for (int i = 0; i < jumlahData; i++) {
                System.out.println("\n------ Data " + (i + 1) + " ------");

                // Menggunakan loop untuk memastikan input berat dan tinggi valid
                while (true) {
                    try {
                        System.out.print("Weight (kg): ");
                        double berat = input.nextDouble();

                        System.out.print("Height (cm): ");
                        double tinggiCm = input.nextDouble();

                        if (berat <= 0 || tinggiCm <= 0) { // Validasi agar berat dan tinggi harus positif
                            System.out.println("[ERROR] Weight and height must be positive values!");
                            continue;
                        }

                        // Logika Perhitungan (Operator)
                        double tinggiMeter = tinggiCm / 100;
                        daftarBMI[i] = berat / (tinggiMeter * tinggiMeter);
                        break;
                    } catch (InputMismatchException e) { // Jika user memasukkan input selain double untuk berat atau tinggi
                        System.out.println("[ERROR] Please enter valid numbers for weight and height!");
                        input.next();
                        i--;
                    }
                }
            }

            System.out.println("\n======= ANALYSIS RESULTS =======");

            // Control Flow (Looping Output & Logika)
            for (int i = 0; i < daftarBMI.length; i++) {
                double bmi = daftarBMI[i];
                String status;

                // Logika Penentuan Status (Operator Perbandingan & Logika)
                if (bmi < 18.5) {
                    status = "Kurus (Underweight)";
                } else if (bmi >= 18.5 && bmi <= 25) {
                    status = "Normal (Ideal)";
                } else if (bmi > 25 && bmi <= 30) {
                    status = "Gemuk (Overweight)";
                } else {
                    status = "Obesitas";
                }

                System.out.printf("%d | BMI: %.2f | Status: %s\n", (i + 1), bmi, status);
            }
        } catch (Exception e) { // Menangkap semua jenis error terjadi selama program running
            System.out.println("[ERROR] Input session has ended.");
        }
    }
}
