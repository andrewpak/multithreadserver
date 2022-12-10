import java.net.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerHelper extends Thread {
    private InputStreamReader in;
    private BufferedReader br;
    private Socket s;
    public ServerHelper(Socket s) {
        this.s = s;
   }
  
   public void run() {
        
        try{
            System.out.println("Server helper running..");
            in = new InputStreamReader(s.getInputStream());
            br = new BufferedReader(in);
            String str = br.readLine();
            System.out.println("client: " + str);

        }
        catch(IOException e){
            e.printStackTrace();
        }
   }
   
}
