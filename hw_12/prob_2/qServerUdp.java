/**
 * qServerUdp.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * a simple UDP server to send random quotes to be sent to the client connected
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */


public class qServerUdp extends Thread{
    DatagramSocket server;
    List<String> Quotes ;

    /**
     * creates a server and listens at the given port number
     * @param portNumber
     * @throws IOException
     */
    public qServerUdp(int portNumber) throws IOException {
        server = new DatagramSocket(portNumber);
        read();
        System.out.println("server started listening on "+ server.getLocalAddress() + " on port " + server.getLocalPort());

    }

    public void run(){
        byte [] buffer;
        try {
            while (true) {
                buffer = new byte[1000];
                DatagramPacket datagramPacket = new DatagramPacket(buffer,buffer.length);
                server.receive(datagramPacket);
                // if  client has sent GG in the packet then send the client with quotes else dont do anything
                if (new Helper().convertToString(datagramPacket.getData()).equals("GG")){
                    System.out.println("a new client connected to the server and recieved GG from client");
                    InetAddress clientAddress = datagramPacket.getAddress();
                    int clienrPort = datagramPacket.getPort();
                    buffer = Quotes.get(new Random().nextInt(Quotes.size())).getBytes();
                    datagramPacket = new DatagramPacket(buffer,buffer.length,clientAddress,clienrPort);
                    server.send(datagramPacket);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * helper function which crates a array list of quotes after reading quotes from the file data.txt
     * @throws IOException
     */
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
                        sb.append(line).append("\n");
                    }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * main driver function of the server
     * @param args
     */
    public static void main(String args[]){
        try {
            qServerUdp serverUdp = new qServerUdp(12345);
            serverUdp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
