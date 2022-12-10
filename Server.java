import java.net.*;
import java.io.*;

public class Server {

    public Server () {
        int port = 3000;
        ServerSocket serverSocket = null;
        while(true){

            try {
                serverSocket = new ServerSocket(port);
                serverSocket.close();
            }
        
            catch (IOException e){
                System.out.println("Unable to listen to port.");
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) {
        new Server();
    }
}
