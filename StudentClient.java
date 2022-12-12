import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class StudentClient extends Thread {
    private Socket socket;
    public static long time = System.currentTimeMillis();
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    public int count;
    public StudentClient () {
        msg("Student thread created.");
        try {
            this.socket = new Socket("localhost", 3000);        
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    
    public void msg(String m) {
        System.out.println("[" + (System.currentTimeMillis() - time) + "] " + m);
    }

    public void run() {
        try {
            count++;
            msg("this is count: " + count);
            try {
                Random rand = new Random();
                int r = rand.nextInt(1,10);
                sleep(r * 1000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            msg("Sending request to server..");
            PrintWriter pr = new PrintWriter(socket.getOutputStream());
            // call remote procedure

            pr.println("notifyTeacher");
            pr.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
 //       StudentClient[] studentClientAry = new StudentClient[20];
 //   
 //       for(int i = 0;i < studentClientAry.length;i++){
 //           studentClientAry[i] = new StudentClient();
 //       }

 //       for(int i = 0;i < studentClientAry.length;i++){
 //           studentClientAry[i].start();
 //       }
        new StudentClient().start();
    }

}