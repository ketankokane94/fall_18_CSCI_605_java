import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class qServerUdp extends Thread{
    ServerSocket server;

    public qServerUdp(int portNumber) throws IOException {
        server = new ServerSocket(portNumber);
        System.out.println("server started listening on "+server.getInetAddress() + " on port " + server.getLocalPort());

    }

    public void run(){
        try {
            while (true) {
                Socket client = server.accept();
                System.out.println("a new client connected to the server");
                PrintWriter out = new PrintWriter(client.getOutputStream());
                out.println("Hello you connected to the server");
                out.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String args[]){
        int port = 12345;
        try {
            qServerUdp serverUdp = new qServerUdp(12345);
            serverUdp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
