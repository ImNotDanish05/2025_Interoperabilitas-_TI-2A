import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCServer {
    private final ServerSocket serverSocket;

    @SuppressWarnings("CallToThreadStartDuringObjectConstruction")
    public RPCServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        String localIP = InetAddress.getLocalHost().getHostAddress();
        System.out.println("Server is running on " + localIP + ":" + port);

        while (true) {
            Socket rpcClient = serverSocket.accept();
            String address = rpcClient.getRemoteSocketAddress().toString();
            System.out.println("New client connected : " + address);

            new Thread(() -> {
                try {
                    addHook(rpcClient);
                } catch (IOException ex) {
                    System.err.println("Client disconnected " + address);
                }
            }).start();
        }
    }

    private void addHook(Socket rpcClient) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(rpcClient.getInputStream())
        );
        String line;

        while ((line = reader.readLine()) != null) {
            System.out.println("Client request : " + line);

            String[] commands = line.split(":", 3);
            int operand1 = Integer.parseInt(commands[1]);
            int operand2 = Integer.parseInt(commands[2]);

            int result;
            String message = "";

            switch (commands[0]) {
                case "add":
                    result = operand1 + operand2;
                    message = operand1 + " + " + operand2 + " = " + result;
                    break;
                case "sub":
                    result = operand1 - operand2;
                    message = operand1 + " - " + operand2 + " = " + result;
                    break;
                case "mul":
                    result = operand1 * operand2;
                    message = operand1 + " * " + operand2 + " = " + result;
                    break;
                case "div":
                    result = operand1 / operand2;
                    message = operand1 + " / " + operand2 + " = " + result;
                    break;
                case "mod":
                    result = operand1 % operand2;
                    message = operand1 + " % " + operand2 + " = " + result;
                    break;
                default:
                    message = "Unknown command: " + commands[0];
                    break;
            }

            PrintStream printStream = new PrintStream(rpcClient.getOutputStream(), true);
            printStream.println(message);
        }
    }

    public static void main(String[] args) throws Exception {
        new RPCServer(3000);
    }
}
