import java.net.ServerSocket;
import java.io.IOException;

public class Server {

    public Server () {

        try {
            ServerSocket serverSocket = null;
            while(true) {
                serverSocket = new ServerSocket(3000);

                serverSocket.close();
            }
        }
	
        catch (IOException e){
            System.out.println("Unable to listen to port.");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Server();
    }
}
