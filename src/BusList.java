public class BusList {

    public class Bus {

        //volatile public Bus prev;
        volatile public Bus next;
        volatile public int remainingSeats = 50;
        public String busID;
        final Lock lock = new Lock();
        final public User[] passengers = new User[50];

    }
    volatile public Bus head;
    volatile Bus sorted = new Bus();
    final Lock lock = new Lock();




    void push(int val, String s) throws InterruptedException{
		/* allocate Bus */
        Bus newBus = new Bus();
        newBus.remainingSeats = val;
        newBus.busID = s;
		/* link the old list off the new Bus */
		this.lock.writeLock();
        newBus.next = head;
		/* move the head to point to the new Bus */
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
            loopEntered = true;
            this.lock.readUnlock();
            // Store next for next iteration
            current.lock.readLock();
            Bus next = current.next;
            current.lock.readUnlock();
            // insert current in sorted linked list
            this.lock.writeLock();
            sortedInsert(current);
            // Update current
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
    void sortedInsert(Bus newBus)
    {
		/* Special case for the head end */
        if (sorted == null || sorted.remainingSeats >= newBus.remainingSeats)
        {
            newBus.next = sorted;
            sorted = newBus;
        }
        else
        {
            Bus current = sorted;
			/* Locate the Bus before the point of insertion */
            while (current.next != null && current.next.remainingSeats < newBus.remainingSeats)
            {
                current = current.next;
            }
            newBus.next = current.next;
            current.next = newBus;
        }
    }

    public Boolean search(String s){
        Bus temp = head;
        Boolean search = false;

        if(head.busID.equals(s)){
            search = true;
        } else{
            while(temp.next != null){
                if(temp.busID.equals(s)){
                    search = true;
                    break;
                } else{
                    temp = temp.next;
                }
            }
        }
        return search;
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
                loopEntered = true;
                if(temp.next.busID.equals(s)){
                    this.lock.readUnlock();
                    this.lock.writeLock();
                    temp.next = temp.next.next;
                    this.lock.writeUnlock();
                    break;
                }else{
                    this.lock.readUnlock();
                    this.lock.writeLock();
                    temp = temp.next;
                    this.lock.writeUnlock();
                }
            } if(!loopEntered){
                this.lock.readLock();
            }
        }

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
