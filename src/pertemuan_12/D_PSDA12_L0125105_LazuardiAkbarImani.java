/* 
Nama: Lazuardi Akbar Imani
NIM: L0125105
Kelas: Informatika D
*/
package pertemuan_12;

public class D_PSDA12_L0125105_LazuardiAkbarImani {

    // ==========================================
    // BUBBLE SORT
    // ==========================================
    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // Jika tidak ada yang ditukar, array sudah terurut
            if (!swapped) break;
        }
    }

    // ==========================================
    // SELECTION SORT
    // ==========================================
    static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min_idx];
            arr[min_idx] = temp;
        }
    }

    // ==========================================
    // INSERTION SORT
    // ==========================================
    static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // ==========================================
    // QUICK SORT
    // ==========================================
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(int[] arr, int low, int high) {
        // Mengubah pivot ke elemen terakhir untuk mensimulasikan worst case pada data terurut
        int pivot = arr[high];
        
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // ==========================================
    // MERGE SORT
    // ==========================================
    static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++; k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++; k++;
        }
    }

    // ==========================================
    // MAIN FUNCTION
    // ==========================================
    public static void main(String[] args) {
        int size = 20000; 
        int[] originalData = new int[size];

        // 1. Buat data 100% terurut terlebih dahulu
        for (int i = 0; i < size; i++) {
            originalData[i] = i;
        }

        // 2. Acak 1% dari data untuk mensimulasikan 1% data yang salah posisi
        int elementsToShuffle = (int) (size * 0.01);
        for (int i = 0; i < elementsToShuffle; i += 2) {
            // Swap dengan elemen yang jaraknya dekat (hanya bergeser sekitar 5 langkah)
            int temp = originalData[i];
            originalData[i] = originalData[i + 5];
            originalData[i + 5] = temp;
        }

        System.out.println("Initializing...");
        System.out.println("Loading " + size + " elements into memory...");
        System.out.println("Changing order of 1% data...");
        System.out.println("Starting benchmark...\n");

        // Clone array agar setiap algoritma memproses data awal yang sama persis
        int[] arrBubble = originalData.clone();
        int[] arrSelection = originalData.clone();
        int[] arrInsertion = originalData.clone();
        int[] arrQuick = originalData.clone();
        int[] arrMerge = originalData.clone();

        long startTime, endTime;

        // 1. Uji Bubble Sort
        startTime = System.nanoTime();
        bubbleSort(arrBubble);
        endTime = System.nanoTime();
        System.out.println("Bubble Sort    : " + (endTime - startTime) + " ns");

        // 2. Uji Selection Sort
        startTime = System.nanoTime();
        selectionSort(arrSelection);
        endTime = System.nanoTime();
        System.out.println("Selection Sort : " + (endTime - startTime) + " ns");

        // 3. Uji Insertion Sort
        startTime = System.nanoTime();
        insertionSort(arrInsertion);
        endTime = System.nanoTime();
        System.out.println("Insertion Sort : " + (endTime - startTime) + " ns");

        // 4. Uji Quick Sort
        startTime = System.nanoTime();
        quickSort(arrQuick, 0, arrQuick.length - 1);
        endTime = System.nanoTime();
        System.out.println("Quick Sort     : " + (endTime - startTime) + " ns");

        // 5. Uji Merge Sort
        startTime = System.nanoTime();
        mergeSort(arrMerge, 0, arrMerge.length - 1);
        endTime = System.nanoTime();
        System.out.println("Merge Sort     : " + (endTime - startTime) + " ns");

        System.out.println("\nBenchmark completed.");
        System.out.println("Total time: " + ((endTime - startTime) * 5) + " ns (average of 5 runs)");
        System.out.println("\n======================================");
        System.out.println("        Lazuardi Akbar Imani");
        System.out.println("            L0125105");
        System.out.println("======================================");
    }
}