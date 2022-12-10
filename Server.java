import java.net.*;
import java.io.*;

public class Server {
    private InputStreamReader in;
    private BufferedReader br;
    public Server () {
        int port = 3000;
        ServerSocket serverSocket = null;

        while(true){

            try {
                serverSocket = new ServerSocket(port);
                Socket s = serverSocket.accept();
                System.out.println("Client connected");
                in = new InputStreamReader(s.getInputStream());
                br = new BufferedReader(in);
                String str = br.readLine();
                System.out.println("client: " + str); 
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
