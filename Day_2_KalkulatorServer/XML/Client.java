package XML;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.Vector;
import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

public class Client {
    public static void main(String[] args) throws MalformedURLException {
        Scanner scanner = new Scanner(System.in);

        // Input IP & Port server
        System.out.print("Masukkan IP server: ");
        String ip = scanner.nextLine();
        System.out.print("Masukkan port server: ");
        String port = scanner.nextLine();

        // Bentuk URL server
        String serverUrl = "http://" + ip + ":" + port + "/RPC2";
        XmlRpcClient xmlRpcClient = new XmlRpcClient(serverUrl);

        try {
            // Uji koneksi dengan "ping" sederhana ke server
            // misalnya kirim request dummy tanpa parameter
            xmlRpcClient.execute("server.hitungPenjumlahan", new Vector<Integer>() {{
                add(0); add(0);
            }});
            System.out.println("✅ Terhubung ke server di " + serverUrl);
        } catch (Exception e) {
            System.out.println("❌ Gagal terhubung ke server: " + e.getMessage());
            scanner.close();
            return; // keluar program
        }

        Vector<Integer> vector = new Vector<>();

        // Menu
        System.out.println("\n== Aplikasi Kalkulator Client Server ==");
        System.out.println("1. Penjumlahan");
        System.out.println("2. Pengurangan");
        System.out.println("3. Perkalian");
        System.out.println("4. Pembagian");
        System.out.print("Masukkan pilihan Anda: ");
        String pilihan = scanner.nextLine();

        try {
            System.out.print("Masukkan nilai X: ");
            int x = scanner.nextInt();
            System.out.print("Masukkan nilai Y: ");
            int y = scanner.nextInt();

            // Masukkan parameter ke vector
            vector.addElement(x);
            vector.addElement(y);

            int hasil;
            switch (pilihan) {
                case "1":
                    hasil = (Integer) xmlRpcClient.execute("server.hitungPenjumlahan", vector);
                    System.out.println("Hasil: " + hasil);
                    break;
                case "2":
                    hasil = (Integer) xmlRpcClient.execute("server.hitungPengurangan", vector);
                    System.out.println("Hasil: " + hasil);
                    break;
                case "3":
                    hasil = (Integer) xmlRpcClient.execute("server.hitungPerkalian", vector);
                    System.out.println("Hasil: " + hasil);
                    break;
                case "4":
                    hasil = (Integer) xmlRpcClient.execute("server.hitungPembagian", vector);
                    System.out.println("Hasil: " + hasil);
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia");
            }
        } catch (XmlRpcException xmle) {
            xmle.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
