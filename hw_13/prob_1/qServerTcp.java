
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class  qServerTcp extends Thread{
    ServerSocket server;

    public qServerTcp(int portNumber) throws IOException {
        server = new ServerSocket(portNumber);
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


    public static void main(String args[]){
        try {
            qServerTcp serverUdp = new qServerTcp(12345);
            serverUdp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}