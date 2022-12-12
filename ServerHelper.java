import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ServerHelper extends Thread {
    private InputStreamReader in;
    private BufferedReader br;
    private Socket s;
    private int count;
    public Object convey;
    public static long time = System.currentTimeMillis();
    private static Map<String, Runnable> rpcs = new HashMap<>();
    public ServerHelper(Socket s, Object c, int myCount) {
        this.s = s;
        this.convey = c;
        this.count = myCount;
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
  

// synchronized remote procedures //

   public synchronized void waitForAll(Object t) {
     msg("Executing RPC waitForAll..." );
     try {
          synchronized(t){
               t.wait();
          }
          msg("teacher exited");
     }
     catch (Exception e){
          e.printStackTrace();
     }
   }

   public synchronized void notifyTeacher(Object t){
     msg("Executing RPC notifyTeacher...");
     try {
          msg("About to notify teacher..");
          synchronized(t){
               t.notify();
          }
     }
     catch (Exception e){
          e.printStackTrace();
     }
   }

   public void run() {
        
        try{
            
            msg("Server helper running..");
            registerRPC("waitForAll", () -> waitForAll(convey));
            registerRPC("notifyTeacher", () -> notifyTeacher(convey));
            in = new InputStreamReader(s.getInputStream());
            br = new BufferedReader(in);
            String str = br.readLine();
            msg("the client sent: " + str);
            
            if(rpcs.get(str) != null) {
               executeRPC(str);
            }

        }

        catch(IOException e){
            //e.printStackTrace();
        }

        msg("Disconnecting..");
   }
   
}
