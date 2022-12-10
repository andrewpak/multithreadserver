import java.net.*;
import java.io.*;

public class Server {
    ServerSocket serverSocket = null;
    public static long time = System.currentTimeMillis();
    public Server () throws Exception {
        int port = 3000;
        serverSocket = new ServerSocket(port);

        while(true){
                msg("server listening on port: " + port);
                Socket s = serverSocket.accept();
                msg("Client connected");
                new ServerHelper(s).start();
        }
    }

    public void msg(String m){
        System.out.println("["+ (System.currentTimeMillis() - time) + "] " + m );
    }


    public static void main(String[] args) throws Exception {
        new Server();
    }
}
