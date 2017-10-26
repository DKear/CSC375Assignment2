public class BusList {

    //any thread can read a bus' remaining seats at any time
    //to make a write, a thread first has to read the bus to validate that there are enough seats
    //only one thread can write/modify a bus at a time, any other thread must wait.
    //when a bus is written/modified a copy will be made and inserted into the linkedlist, and the old bus deleted
    //once a thread is signaled/done waiting, it re-reads the thread to verify there is still space
    //if there is no space, it either terminates or it keeps looking (decide)

    public Bus head;
    public int size;

    private synchronized Bus findBusToInsertAfter(Bus newBus) {
        Bus curBus = this.head;
        //if the new bus has less remaining seats than the current bus (starting at head)
        //return null
        if (newBus.remainingSeats < curBus.remainingSeats) {
            return null;
        }
        //new bus has more or equal remaining seats than the current bus
        //while there is a next bus do:
        //-if the new bus has the same amount of seats as the current bus, return the current bus
        //--then check if the new bus has more remaining seats than the current but less than the one after that, if so return current bus
        //-larger than the current and the next, move on to the next. Move on to the next and loop until end.
        while(curBus.next != null) {
            if (newBus.remainingSeats == curBus.remainingSeats) {
                return curBus;
            }
            else if (newBus.remainingSeats > curBus.remainingSeats && newBus.remainingSeats < curBus.next.remainingSeats) {
                return curBus;
            }
            curBus = curBus.next;
        }
        return curBus;
    }

    public synchronized void insert(int value) {
        Bus newBus = new Bus();
        newBus.remainingSeats = value;
        Bus busToInsertAfter;
        if (this.head == null) {
            this.head = newBus;
            this.size++;
        } else {
            busToInsertAfter = findBusToInsertAfter(newBus);
            if (busToInsertAfter == null || busToInsertAfter.remainingSeats != newBus.remainingSeats) {

                Bus tmpBus;
                if (busToInsertAfter == null) {
                    this.head.prev = newBus;
                    tmpBus  = this.head;
                    this.head = newBus;
                    this.head.next = tmpBus;
                    if (this.size <= 1) {
                    }
                } else if (busToInsertAfter.next == null) {
                    newBus.prev = busToInsertAfter;
                    busToInsertAfter.next = newBus;
                } else {
                    Bus prevBus, nextBus;
                    prevBus = busToInsertAfter;
                    nextBus = busToInsertAfter.next;
                    prevBus.next = newBus;
                    newBus.prev = prevBus;
                    newBus.next = nextBus;
                    nextBus.prev = newBus;
                }
                this.size++;
            }

        }
    }

}
