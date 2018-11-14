import java.awt.desktop.QuitEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class qServerUdp extends Thread{
    ServerSocket server;
    List<String> Quotes ;

    public qServerUdp(int portNumber) throws IOException {
        server = new ServerSocket(portNumber);
        read();
        System.out.println("server started listening on "+server.getInetAddress() + " on port " + server.getLocalPort());

    }

    public void run(){
        try {
            while (true) {

                Socket client = server.accept();
                System.out.println("a new client connected to the server");
                PrintWriter out = new PrintWriter(client.getOutputStream());
                out.println(Quotes.get(new Random().nextInt(Quotes.size())));
                System.out.println(Quotes.get(((int)Math.random()) % Quotes.size()));
                out.close();
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
            qServerUdp serverUdp = new qServerUdp(12345);
            serverUdp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
