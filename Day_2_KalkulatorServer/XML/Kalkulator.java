package XML;
public class Kalkulator {
    int hasil = 0;
    // Method untuk operasi penjumlahan
    public int hitungPenjumlahan(int x, int y) {
        hasil = x + y;
        System.out.println("Operasi Penjumlahan");
        System.out.println(x+" + "+y+" = "+hasil);
        return hasil;
    }
        // Method untuk operasi pengurangan
        public int hitungPengurangan(int x, int y) {
        hasil = x - y;
        System.out.println("Operasi Pengurangan");
        System.out.println(x+" - "+y+" = "+hasil);
        return hasil;
    }
        // Method untuk operasi perkalian
        public int hitungPerkalian(int x, int y) {
        hasil = x * y;
        System.out.println("Operasi Perkalian");
        System.out.println(x+" * "+y+" = "+hasil);
        return hasil;
    }
        // Method untuk operasi pembagian
        public int hitungPembagian(int x, int y) {
        hasil = x / y;
        System.out.println("Operasi Pembagian");
        System.out.println(x+" / "+y+" = "+hasil);
        return hasil;
    }
}
