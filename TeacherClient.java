import java.io.BufferedReader;
import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.random.*;
public class TeacherClient extends Thread {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    public static long time = System.currentTimeMillis();
    public TeacherClient () {
        try {
            msg("teacher created");
            this.socket = new Socket("localhost", 3000);
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } 
        catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void msg(String m){
        System.out.println("[" + (System.currentTimeMillis() - time) + "] " + m);
    }

    public void run (){
        try {

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
            pr.println("waitForAll");
            pr.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException  {
        new TeacherClient().start();;
    }
}
