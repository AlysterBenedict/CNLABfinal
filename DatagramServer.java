import java.net.*;
import java.io.*;

public class DatagramServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        BufferedReader reader = null;

        try {
            socket = new DatagramSocket(9876);
            reader = new BufferedReader(new InputStreamReader(System.in));
            InetAddress clientAddress = InetAddress.getByName("localhost");

            while (true) {
                System.out.print("Enter message: ");
                String message = reader.readLine();
                byte[] buffer = message.getBytes();

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, clientAddress, 9877);
                socket.send(packet);

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Server shutting down...");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
