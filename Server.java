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
                System.out.println("server listening on port: " + port);
                serverSocket = new ServerSocket(port);
                serverSocket.setReuseAddress(true);
                Socket s = serverSocket.accept();

                System.out.println("Client connected");
                new ServerHelper(s).start();
                serverSocket.close();
                // in = new InputStreamReader(s.getInputStream());
                // br = new BufferedReader(in);
                // String str = br.readLine();
                // System.out.println("client: " + str); 
                
            }

            catch (IOException e){
                System.out.println("Unable to listen to port.");
                e.printStackTrace();
            } 

            try {
                serverSocket.close();
            } 
            catch (IOException e){
                e.printStackTrace();
            }
            
        }

    }

    public static void main(String[] args) {
        new Server();
    }
}
