public interface ConsumerProducerInterface extends java.rmi.Remote {
    String consumeProduce(String workDone) throws java.rmi.RemoteException;
    int updateCapacity(int capacity) throws java.rmi.RemoteException;
}
