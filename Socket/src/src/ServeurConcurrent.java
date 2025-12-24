package src;

import java.net.ServerSocket;
import java.net.Socket;

public class ServeurConcurrent {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Serveur en attente de connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nouveau client connecté");

                Thread thread = new Thread(
                        new ClientHandler(clientSocket));
                thread.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
