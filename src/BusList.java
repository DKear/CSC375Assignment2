//import com.sun.org.apache.xpath.internal.operations.Bool;

public class BusList {

    /*public class Bus {

        //volatile public Bus prev;
        volatile public Bus next;
        volatile public int remainingSeats = 50;
        public String busID;
        final Lock lock = new Lock();
        final public String[] passengers = new String[50];

    }*/
    volatile public Bus head;
    volatile Bus sorted;
    final private Lock lock = new Lock();




    /*
    void push(int val, String s) throws InterruptedException{
		// allocate Bus
        Bus newBus = new Bus();
        newBus.remainingSeats = val;
        newBus.busID = s;
		// link the old list off the new Bus
		this.lock.writeLock();
        newBus.next = head;
		// move the head to point to the new Bus
        head = newBus;
        this.lock.writeUnlock();
    }
    */

     void push(Bus b) throws InterruptedException{

        Bus newBus = new Bus(b.busID, b.remainingSeats, b.passengers);
		/* link the old list off the new Bus */
        this.lock.writeLock();
        newBus.next = head;
        this.lock.writeUnlock();
		/* move the head to point to the new Bus */
		this.lock.writeLock();
        head = newBus;
        this.lock.writeUnlock();
    }



    // function to sort a singly linked list using insertion sort
     void insertionSort(Bus headref) throws InterruptedException{
        // Initialize sorted linked list

        this.lock.writeLock();
        sorted = null;
        this.lock.writeUnlock();

        this.lock.writeLock();
        Bus current = headref;
        this.lock.writeUnlock();
        // Traverse the given linked list and insert every
        // Bus to sorted
        boolean loopEntered = false;
        this.lock.readLock();
        while (current != null){
            System.out.println("loop at 63 running");
            if(!loopEntered){
                this.lock.readUnlock();
            }
            loopEntered = true;
            // Store next for next iteration
            this.lock.readLock();
            Bus next = current.next;
            this.lock.readUnlock();
            // insert current in sorted linked list
            //this.lock.writeLock();
            sortedInsert(current);
            //this.lock.writeUnlock();
            // Update current
            this.lock.writeLock();
            current = next;
            this.lock.writeUnlock();

        }
        if(!loopEntered) {
            this.lock.readUnlock();
        }
        // Update head_ref to point to sorted linked list
        this.lock.writeLock();
        head = sorted;
        this.lock.writeUnlock();
    }

    /*
    * function to insert a new_Bus in a list. Note that
    * this function expects a pointer to head_ref as this
    * can modify the head of the input linked list
    * (similar to push())
    */
    void sortedInsert(Bus newBus)throws InterruptedException{
		/* Special case for the head end */
		this.lock.readLock();
        if (sorted == null || sorted.remainingSeats >= newBus.remainingSeats)
        {
            this.lock.readUnlock();
            this.lock.writeLock();
            newBus.next = sorted;
            this.lock.writeUnlock();
            this.lock.writeLock();
            sorted = newBus;
            this.lock.writeUnlock();

        }
        else
        {
            this.lock.readUnlock();
            this.lock.writeLock();
            Bus current = sorted;
            this.lock.writeUnlock();
            boolean loopEntered = false;
            this.lock.readLock();
			/* Locate the Bus before the point of insertion */
            while (current.next != null && current.next.remainingSeats < newBus.remainingSeats){
                if(!loopEntered){
                    this.lock.readUnlock();
                }
                loopEntered = true;
                System.out.println("loop at 107 running");
                this.lock.writeLock();
                current = current.next;
                this.lock.writeUnlock();
            }if(!loopEntered) {
            this.lock.readUnlock();
        }
            this.lock.writeLock();
            newBus.next = current.next;
            this.lock.writeUnlock();
            this.lock.writeLock();
            current.next = newBus;
            this.lock.writeUnlock();
        }
    }

     public Bus searchForValidBus(int seatsRequested) throws InterruptedException{
        this.lock.readLock();
        Bus temp = head;
        this.lock.readUnlock();

        this.lock.readLock();
        if(head.remainingSeats >= seatsRequested & head.remainingSeats > 0){
            this.lock.readUnlock();
            synchronized (head) {
                return head;
            }
        } else{
            this.lock.readUnlock();
            this.lock.readLock();
            boolean loopEntered = false;
            while(temp != null){
                if(!loopEntered){
                    this.lock.readUnlock();
                }
                System.out.println("loop at 132 running");
                loopEntered = true;
                if(temp.remainingSeats >= seatsRequested){
                    synchronized (temp) {
                        return temp;
                    }

                } else{
                    this.lock.writeLock();
                    temp = temp.next;
                    this.lock.writeUnlock();
                }
            }if(!loopEntered){
                this.lock.readUnlock();
            }
        }
        return null;
    }
    public Bus searchByString(String s) throws InterruptedException{
        this.lock.readLock();
        Bus temp = head;
        this.lock.readUnlock();
        Boolean search = false;

        this.lock.readLock();
        if(head.busID.equals(s)){
            this.lock.readUnlock();
            synchronized (head) {
                return head;
            }
        } else{
            this.lock.readUnlock();
            this.lock.readLock();
            boolean loopEntered = false;
            while(temp.next != null){
                if(!loopEntered){
                    this.lock.readUnlock();
                }
                System.out.println("loop at 169 running");
                loopEntered = true;
                if(temp.busID.equals(s)){
                    synchronized (temp) {
                        return temp;
                    }

                } else{
                    this.lock.writeLock();
                    temp = temp.next;
                    this.lock.writeUnlock();
                }
            }if(!loopEntered){
                this.lock.readUnlock();
            }
        }
        return null;
    }

     public void delete(String s) throws InterruptedException{
        this.lock.readLock();
        if(head.busID.equals(s)){
            this.lock.readUnlock();
            this.lock.writeLock();
            head = head.next;
            this.lock.writeUnlock();
        } else{
            this.lock.readUnlock();
            this.lock.writeLock();
            Bus temp = head;
            this.lock.writeUnlock();
            boolean loopEntered = false;
            this.lock.readLock();
            while (temp.next != null){
                if(!loopEntered){
                    this.lock.readUnlock();
                }
                System.out.println("loop at 205 running");
                loopEntered = true;
                if(temp.next.busID.equals(s)){
                    this.lock.writeLock();
                    temp.next = temp.next.next;
                    this.lock.writeUnlock();
                    break;
                }else{
                    this.lock.writeLock();
                    temp = temp.next;
                    this.lock.writeUnlock();
                }
            } if(!loopEntered){
                this.lock.readLock();
            }
        }

    }

    public void insertPassengers(Bus b, int sr, String n) throws InterruptedException{
        this.lock.writeLock();
        for (int i = 50 - b.remainingSeats; i < sr; i++) {
            b.passengers[i] = (n);
        }
        this.lock.writeUnlock();
    }

    /* Function to print linked list */
    void printlist(Bus head)
    {
        while (head != null)
        {
            System.out.print(head.remainingSeats + " ");
            head = head.next;
        }
    }

}
