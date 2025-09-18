package XML;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import org.apache.xmlrpc.XmlRpcClient;

public class ClientGUI extends JFrame {
    private JTextField ipField, portField, xField, yField;
    private JComboBox<String> operasiBox;
    private JButton connectButton, hitungButton;
    private JLabel statusLabel, hasilLabel;

    private XmlRpcClient client;

    public ClientGUI() {
        setTitle("Kalkulator Client-Server (XML-RPC)");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel input server
        JPanel serverPanel = new JPanel(new GridLayout(3, 2));
        serverPanel.setBorder(BorderFactory.createTitledBorder("Server"));
        serverPanel.add(new JLabel("IP:"));
        ipField = new JTextField("127.0.0.1");
        serverPanel.add(ipField);
        serverPanel.add(new JLabel("Port:"));
        portField = new JTextField("1717");
        serverPanel.add(portField);

        connectButton = new JButton("Connect");
        serverPanel.add(connectButton);
        statusLabel = new JLabel("Belum terhubung");
        serverPanel.add(statusLabel);

        // Panel input kalkulator
        JPanel kalkPanel = new JPanel(new GridLayout(4, 2));
        kalkPanel.setBorder(BorderFactory.createTitledBorder("Kalkulator"));
        kalkPanel.add(new JLabel("Operasi:"));
        operasiBox = new JComboBox<>(new String[]{"Penjumlahan", "Pengurangan", "Perkalian", "Pembagian"});
        kalkPanel.add(operasiBox);

        kalkPanel.add(new JLabel("X:"));
        xField = new JTextField();
        kalkPanel.add(xField);

        kalkPanel.add(new JLabel("Y:"));
        yField = new JTextField();
        kalkPanel.add(yField);

        hitungButton = new JButton("Hitung");
        kalkPanel.add(hitungButton);
        hasilLabel = new JLabel("Hasil: -");
        kalkPanel.add(hasilLabel);

        // Layout utama
        setLayout(new BorderLayout());
        add(serverPanel, BorderLayout.NORTH);
        add(kalkPanel, BorderLayout.CENTER);

        // Event: Connect
        connectButton.addActionListener(e -> {
            String ip = ipField.getText().trim();
            String port = portField.getText().trim();
            try {
                client = new XmlRpcClient("http://" + ip + ":" + port + "/RPC2");
                // Test ping
                Vector<Integer> v = new Vector<>();
                v.add(0); v.add(0);
                client.execute("server.hitungPenjumlahan", v);
                statusLabel.setText("✅ Terhubung ke " + ip + ":" + port);
            } catch (Exception ex) {
                statusLabel.setText("❌ Gagal: " + ex.getMessage());
                client = null;
            }
        });

        // Event: Hitung
        hitungButton.addActionListener(e -> {
            if (client == null) {
                JOptionPane.showMessageDialog(this, "Belum terhubung ke server!");
                return;
            }
            try {
                int x = Integer.parseInt(xField.getText().trim());
                int y = Integer.parseInt(yField.getText().trim());
                Vector<Integer> v = new Vector<>();
                v.add(x); v.add(y);

                String operasi = (String) operasiBox.getSelectedItem();
                String method = "";
                switch (operasi) {
                    case "Penjumlahan": method = "server.hitungPenjumlahan"; break;
                    case "Pengurangan": method = "server.hitungPengurangan"; break;
                    case "Perkalian": method = "server.hitungPerkalian"; break;
                    case "Pembagian": method = "server.hitungPembagian"; break;
                }

                int hasil = (Integer) client.execute(method, v);
                hasilLabel.setText("Hasil: " + hasil);
            } catch (Exception ex) {
                hasilLabel.setText("Error: " + ex.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ClientGUI().setVisible(true);
        });
    }
}
