
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class  qServerTcp extends Thread{
    ServerSocket server;
    List<String> Quotes ;

    public qServerTcp(int portNumber) throws IOException {
        server = new ServerSocket(portNumber);
        read();
        System.out.println("server started listening on "+server.getInetAddress() + " on port " + server.getLocalPort());

    }

    public void run(){
        try {
            while (true) {
                Socket client = server.accept();
                System.out.println("a new client connected to the server");
                new Helper(client).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void read() throws IOException {
        Quotes = new ArrayList<>();
        File file = new File(new File("").getCanonicalPath() + "/data.txt");
        String line ;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            while ((line = bufferedReader.readLine())!= null){
                line = line.trim();
                if (line.equals("%%")){
                    if (!sb.toString().equals(""))
                        Quotes.add(sb.toString());
                    sb = new StringBuilder();
                }
                else {
                    sb.append(line);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        try {
            qServerTcp serverUdp = new qServerTcp(12345);
            serverUdp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}