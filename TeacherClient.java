import java.io.BufferedReader;
import java.io.*;
import java.net.Socket;

public class TeacherClient extends Thread {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    public TeacherClient () {
        try {
            System.out.println("teacher created");
            this.socket = new Socket("localhost", 3000);
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } 
        catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void run (){
        try {

            try {
                sleep(5000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Sending message to server..");
            PrintWriter pr = new PrintWriter(socket.getOutputStream());
            pr.println("Hello, world");
            pr.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException  {
        TeacherClient[] teacherClientAry = new TeacherClient[5];

        for(int i = 0;i < teacherClientAry.length;i++){
            teacherClientAry[i] = new TeacherClient();
        }

        for(int i = 0;i < teacherClientAry.length;i++){
            teacherClientAry[i].start();
        }
    }
}
