import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class RPCClientGUI extends JFrame {
    private final PrintStream printStream;
    private final JTextArea textArea;
    private final JTextField field1, field2;
    private final JComboBox<String> operationBox;

    @SuppressWarnings("CallToThreadStartDuringObjectConstruction")
    public RPCClientGUI(String ipAddress, int port) throws IOException {
        // Setup koneksi ke server
        Socket rpcClient = new Socket(ipAddress, port);

        // GUI setup
        setTitle("RPC Calculator Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel input
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.add(new JLabel("First number:"));
        field1 = new JTextField();
        inputPanel.add(field1);

        inputPanel.add(new JLabel("Second number:"));
        field2 = new JTextField();
        inputPanel.add(field2);

        inputPanel.add(new JLabel("Operation:"));
        operationBox = new JComboBox<>(new String[]{"add", "sub", "mul", "div", "mod"});
        inputPanel.add(operationBox);

        add(inputPanel, BorderLayout.NORTH);

        // Text area untuk response server
        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Tombol kirim
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> sendMessage());
        add(sendButton, BorderLayout.SOUTH);

        setVisible(true);

        // Thread untuk baca response dari server
        new Thread(() -> {
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(rpcClient.getInputStream())
                );
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append("Server: " + line + "\n");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Disconnected from server!");
                System.exit(0);
            }
        }).start();

        printStream = new PrintStream(rpcClient.getOutputStream(), true);
    }

    private void sendMessage() {
        try {
            int f1 = Integer.parseInt(field1.getText().trim());
            int s1 = Integer.parseInt(field2.getText().trim());
            String operation = (String) operationBox.getSelectedItem();

            printStream.println(operation + ":" + f1 + ":" + s1);
            textArea.append("You: " + operation + " " + f1 + " " + s1 + "\n");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers!");
        }
    }

    public static void main(String[] args) {
        try {
            String ipAddress = JOptionPane.showInputDialog("Enter server IP address:");
            String portStr = JOptionPane.showInputDialog("Enter port:");
            int port = Integer.parseInt(portStr);

            new RPCClientGUI(ipAddress, port);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Unable to connect to server!");
        }
    }
}
