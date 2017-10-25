import java.util.concurrent.locks.ReadWriteLock;

public class Bus {

    volatile public Bus prev;
    volatile public Bus next;
    volatile public int remainingSeats = 50;
    final Lock lock = new Lock();
    final public User[] passengers = new User[50];

}
