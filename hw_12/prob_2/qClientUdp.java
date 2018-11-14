import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class qClientUdp extends Thread{

    Socket socket;

    public qClientUdp(int port) throws IOException {
             socket = new Socket(InetAddress.getLocalHost(),port);
    }
    public void run(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(bufferedReader.readLine());
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
