import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Helper extends Thread {
    List<String> Quotes ;
    Socket client;

    public Helper(Socket client) throws IOException {
        read();
        this.client  = client;
    }

    public void run(){
        PrintWriter out = null;
        try {
            out = new PrintWriter(client.getOutputStream());
            sleep(10000);
            out.println(Quotes.get(new Random().nextInt(Quotes.size())));
            //System.out.println(Quotes.get(((int)Math.random()) % Quotes.size()));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
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

}
