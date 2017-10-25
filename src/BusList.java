public class BusList {

    //any thread can read a bus' remaining seats at any time
    //to make a write, a thread first has to read the bus to validate that there are enough seats
    //only one thread can write/modify a bus at a time, any other thread must wait.
    //when a bus is written/modified a copy will be made and inserted into the linkedlist, and the old bus deleted
    //once a thread is signaled/done waiting, it re-reads the thread to verify there is still space
    //if there is no space, it either terminates or it keeps looking (decide)

    public Bus head;
    public Bus tail;

    private Bus findBusToInsertAfter(Bus bus) throws InterruptedException{
        Bus current = this.head;
        current.lock.readLock();
        bus.lock.readLock();
        //if the new bus has less remaining seats than the current bus (starting at head)
        //return null
        if(bus.remainingSeats < current.remainingSeats){
            current.lock.readUnlock();
            bus.lock.readUnlock();
            return null;
        }
        //new bus has more or equal remaining seats than the current bus
        //while there is a next bus do:
        //-if the new bus has the same amount of seats as the current bus, return the current bus
        //--then check if the new bus has more remaining seats than the current but less than the one after that, if so return current bus
        //-larger than the current and the next, move on to the next. Move on to the next and loop until end.
        while(current.next != null){
            if(bus.remainingSeats == current.remainingSeats){
                current.lock.readUnlock();
                bus.lock.readUnlock();
                return current;
            }else if(bus.remainingSeats > current.remainingSeats & bus.remainingSeats < current.next.remainingSeats){
                current.lock.readUnlock();
                bus.lock.readUnlock();
                return current;
            }
            current.lock.readUnlock();
            bus.lock.readUnlock();
            current.lock.writeLock();
            current = current.next;
            current.lock.writeUnlock();
            current.lock.readLock();
            bus.lock.readLock();
        }
        current.lock.readUnlock();
        bus.lock.readUnlock();
        return current;
    }

    public void insert(int value) throws InterruptedException{
        Bus newBus = new Bus();
        newBus.remainingSeats = value;
        Bus busToInsertAfter;

            if (this.head == null) {
                this.head.lock.writeLock();
                this.head = newBus;
                this.head.next = this.tail;
                this.head.lock.writeUnlock();

            }else{
                busToInsertAfter = findBusToInsertAfter(newBus);
                busToInsertAfter.lock.readLock();
                if(busToInsertAfter == null | busToInsertAfter.remainingSeats != newBus.remainingSeats){
                    //insert the node
                }
                busToInsertAfter.lock.readUnlock();

            }




    }

}
