import java.io.IOException;
import java.net.*;

public class qClientUdp extends Thread{
    DatagramSocket socket;
    int port;

    public qClientUdp(int port) throws IOException {
             socket = new DatagramSocket();
             this.port = port;
    }

    public void run(){
        try {
            byte [] buffer = new byte[1000];
            DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
            packet.setAddress(InetAddress.getLocalHost());
            packet.setPort(port);
            packet.setData("GG".getBytes());
            socket.send(packet);

            packet = new DatagramPacket(buffer,buffer.length);
            socket.receive(packet);

            buffer = packet.getData();
            System.out.println(new Helper().convertToString(buffer));
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            socket = null;
        }

    }
    public static void main(String args[]){
        try {
            qClientUdp clientUdp = new qClientUdp(12345);
            clientUdp.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
