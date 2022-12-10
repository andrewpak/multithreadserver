import java.net.*;
import java.io.*;

public class Server {
    ServerSocket serverSocket = null;
    public Server () throws Exception {
        int port = 3000;
        serverSocket = new ServerSocket(port);

        while(true){
                System.out.println("server listening on port: " + port);
                Socket s = serverSocket.accept();
                System.out.println("Client connected");
                new ServerHelper(s).start();
        }
    }


    public static void main(String[] args) throws Exception {
        new Server();
    }
}
