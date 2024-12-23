import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(1234);
            System.out.println("Server is listening on port 1234");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String fileName = in.readLine();
                File file = new File(fileName);

                if (file.exists() && !file.isDirectory()) {
                    BufferedReader fileReader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = fileReader.readLine()) != null) {
                        out.println(line);
                    }
                    fileReader.close();
                } else {
                    out.println("File not found.");
                }

                in.close();
                out.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
