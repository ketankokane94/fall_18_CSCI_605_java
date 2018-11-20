/**
 * qClientUdp.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

import java.io.IOException;
import java.net.*;

/**
 * a simple client to connect to a quote server and get quote of the day over UDP
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */


public class qClientUdp extends Thread{
    // socket via which the packets will be sent
    DatagramSocket socket;
    // port data of the server
    int port;
    // Ip address of the server
    InetAddress address;

    /**
     * create a client which will connect to the server on the given address and port
     * @param port
     * @param address
     * @throws IOException
     */
    public qClientUdp(int port,InetAddress address) throws IOException {
             socket = new DatagramSocket();
             this.port = port;
             this.address = address;
    }


    public void run(){
        try {
            // buffer to store data that is to be sent or received
            byte [] buffer = new byte[1000];
            // crate a new packet that is to be sent to the server
            DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
            // set address and port to the packet
            packet.setAddress(address);
            packet.setPort(port);
            // set the data that is to be sent to the server, a handshake mech is that server accepts the client to sent
            // a "GG" message  to show that client knows it had connected to the correct server
            packet.setData("GG".getBytes());
            // send the packet
            socket.send(packet);
            // create a new packet which will contain the information sent by the server
            packet = new DatagramPacket(buffer,buffer.length);
            // receive method of the socket will fill the packet with the data
            socket.receive(packet);
            // use helper method to convert the buffer of bytes to the string
            System.out.println(new Helper().convertToString(packet.getData()));
            // close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            socket = null;
        }

    }

    /**
     * main driver of the client
     * @param args
     */
    public static void main(String args[]){
        try {
            // create a new client which will connect to the given host on port 12345
            qClientUdp clientUdp = new qClientUdp(12345,InetAddress.getLocalHost());
            // start the client
            clientUdp.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
