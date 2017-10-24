public class Buses {

    //any thread can read a bus' remaining seats at any time
    //to make a write, a thread first has to read the bus to validate that there are enough seats
    //only one thread can write/modify a bus at a time, any other thread must wait.
    //when a bus is written/modified a copy will be made and inserted into the linkedlist, and the old bus deleted
    //once a thread is signaled/done waiting, it re-reads the thread to verify there is still space
    //if there is no space, it either terminates or it keeps looking (decide)


    class Bus {
        public Bus prev;
        public Bus next;
        public int remainingSeats = 50;
        final public User[] passengers = new User[50];

    }

    public Bus head;
    public Bus tail;

}
