import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Lock {

    private int reads = 0;
    private int writes = 0;
    private int writeRequests = 0;
    private int readRequests = 0;

    final ReentrantLock lock = new ReentrantLock();
    final Condition readCondition = lock.newCondition();
    final Condition writeCondition = lock.newCondition();

    //private int readRequests = 0;

    public void readLock() throws InterruptedException{
        lock.lock();
        System.out.println("readLock lock");
        printState();
        try{
            for(;;){
                if(writes == 0 && writeRequests == 0){
                    ++reads;
                    break;
                }
                ++readRequests;
                readCondition.await();
                --readRequests;

            }
        } finally{
            lock.unlock();
            System.out.println("readLock unlock");
            printState();
        }
    }

    public void readUnlock(){
        lock.lock();
        System.out.println("readUnlock lock");
        printState();
        try{
            if(--reads == 0){

                if(writeRequests== 0){
                    writeCondition.signal();
                }else{
                    readCondition.signalAll();
                }
            }
        }finally{
            lock.unlock();
            System.out.println("readUnlock unlock");
            printState();
        }
    }

    public   void writeLock() throws InterruptedException{
        lock.lock();
        System.out.println("writeLock lock");
        printState();
        try{
            for(;;){
                if(reads == 0 && writes==0){
                    break;
                }
                ++writeRequests;
                System.out.println("write Awaiting------");
                writeCondition.await();
                --writeRequests;

            }
            ++writes;
        }finally{
            lock.unlock();
            System.out.println("writeLock unlock");
            printState();
        }
    }

    public void writeUnlock() throws InterruptedException{
        System.out.println("trying to get writeUnlock lock");
        lock.lock();
        System.out.println("writeUnlock lock");
        printState();
        try{
            if(--writes == 0){

                if(writeRequests > 0){
                    writeCondition.signal();
                }else{
                    readCondition.signalAll();
                }
            }
        }finally{
            lock.unlock();
            System.out.println("writeUnlock unlock");
            printState();
        }
    }
    private void printState(){
        System.out.println("reads: " + reads + "\t writes: " + writes + "\t readsWaiting: " + readRequests + "\t writesWaiting: " + writeRequests);
    }
}
