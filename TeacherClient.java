import java.io.BufferedReader;
import java.io.*;
import java.net.Socket;

public class TeacherClient extends Thread {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    public TeacherClient (Socket  socket) {
        try {
            this.socket = socket;
            this.bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            new Thread(this).start();
        } 
        catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void run (){

    }
    public static void main(String[] args) throws IOException  {
        Socket socket = new Socket("localhost", 3000);
        new TeacherClient(socket);
    }
    
}
