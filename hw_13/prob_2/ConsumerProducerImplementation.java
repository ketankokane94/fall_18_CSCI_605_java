import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ConsumerProducerImplementation extends UnicastRemoteObject implements ConsumerProducerInterface
{

    public ConsumerProducerImplementation() throws RemoteException{
        Storage.capacityOfBuffer = 100;
    }

    @Override
    public String consumeProduce(String workDone) throws RemoteException {
        Storage storage =  new Storage(workDone);

        // Store original lists before starting threads
        ArrayList<Integer> item1_init = Storage.item1;
        ArrayList<Integer> item2_init = Storage.item2;
        ArrayList<Integer> item3_init = Storage.item3;

        storage.start();
        try {
            storage.join();
        }
        catch (InterruptedException ie) {

            // If interrupted then revert to lists before thread start
            Storage.item1 = item1_init;
            Storage.item2 = item2_init;
            Storage.item3 = item3_init;

            return "Unsuccessful";
        }
        //System.out.println(Storage.item1.size() + " " + Storage.item2.size() + " " + Storage.item3.size() + " ");
        return Storage.success_status;
    }

    @Override
    public int updateCapacity(int capacity) throws RemoteException {
        if(Storage.capacityOfBuffer < capacity && !Storage.lists_created) {
            Storage.capacityOfBuffer = capacity;
        }

        System.out.println(Storage.capacityOfBuffer);

        return Storage.capacityOfBuffer;
    }
}
