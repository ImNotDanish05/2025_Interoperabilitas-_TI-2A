package XML;
import org.apache.xmlrpc.WebServer;
import java.net.InetAddress;

public class Main {
    public static void main(String[] args) {
        try {
            // Bind ke semua interface (0.0.0.0)
            WebServer server = new WebServer(1717, InetAddress.getByName("0.0.0.0"));

            // Tambahkan handler untuk request client
            server.addHandler("server", new Kalkulator());

            // Jalankan server
            server.start();
            System.out.println("✅ Server berjalan di port 1717, bisa diakses dari jaringan lokal.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
