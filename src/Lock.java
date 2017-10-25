public class Lock {

    private int reads = 0;
    private int writes = 0;
    private int writeRequests = 0;
    //private int readRequests = 0;

    public synchronized void readLock() throws InterruptedException{
        while(writes > 0 | writeRequests > 0){
            wait();
        }
        reads ++;
    }

    public synchronized void readUnlock(){
        reads --;
        notifyAll();
    }

    public synchronized  void writeLock() throws InterruptedException{
        writeRequests ++;
        while(reads > 0 | writes > 0){
            wait();
        }
        writeRequests --;
        writes ++;
    }

    public synchronized void writeUnlock() throws InterruptedException{
        writes --;
        notifyAll();
    }
}
