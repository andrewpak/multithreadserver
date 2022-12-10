import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ServerHelper extends Thread {
    private InputStreamReader in;
    private BufferedReader br;
    private Socket s;
    public static long time = System.currentTimeMillis();
    private static Map<String, Runnable> rpcs = new HashMap<>();
    public ServerHelper(Socket s) {
        this.s = s;
   }

   public static void msg(String m){
     System.out.println("[" + (System.currentTimeMillis() - time) + "] " + m);
   }

   public static void registerRPC(String s, Runnable rpc){
        rpcs.put(s, rpc);
   }

   public static void executeRPC(String s) {
        Runnable rpc = rpcs.get(s);
        if (rpc != null){
            rpc.run();
        }
   }
  
   // skeleton method

   public static void waitForStudents() {
        msg("Executing RPC...");
        try {
          sleep(500);  
        }
        catch (InterruptedException e){
          e.printStackTrace();
        }
        msg("Waiting for students");
   }

   public void run() {
        
        try{
            
            msg("Server helper running..");
            registerRPC("waitStudents", () -> waitForStudents());
            in = new InputStreamReader(s.getInputStream());
            br = new BufferedReader(in);
            String str = br.readLine();
            msg("the client sent: " + str);
            executeRPC(str);

        }
        catch(IOException e){
            //e.printStackTrace();
        }

        msg("Disconnecting..");
   }
   
}
