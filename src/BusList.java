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

     void push(Bus b) throws InterruptedException {
         //System.out.println("----------------push--------------");
         //this.lock.writeLock();
         //try {

             Bus newBus = new Bus(b.busID, b.remainingSeats, b.passengers);

             newBus.next = head;
             head = newBus;
         //} finally {
         //    this.lock.writeUnlock();
         //}
     }



    // function to sort a singly linked list using insertion sort
      void insertionSort(Bus headref) throws InterruptedException{
          //System.out.println("------------------insertionSort---------");
        // Initialize sorted linked list

        //this.lock.writeLock();
        //try {

            sorted = null;


            Bus current = headref;

            while (current != null) {
                //System.out.println("loop at 59 running");


                Bus next = current.next;


                sortedInsert(current);

                current = next;


            }


            head = sorted;
        //}finally {
        //    this.lock.writeUnlock();
        //}
     }

    /*
    * function to insert a new_Bus in a list. Note that
    * this function expects a pointer to head_ref as this
    * can modify the head of the input linked list
    * (similar to push())
    */
     private void sortedInsert(Bus newBus)throws InterruptedException {
         //System.out.println("-----------sortedInsert-------------");
        /* Special case for the head end */
         if (sorted == null || sorted.remainingSeats >= newBus.remainingSeats) {
             newBus.next = sorted;
             sorted = newBus;

         } else {
             Bus current = sorted;
            /* Locate the Bus before the point of insertion */
             while (current.next != null && current.next.remainingSeats < newBus.remainingSeats) {
                 //System.out.println("loop at 95 running");

                 current = current.next;
             }

                 newBus.next = current.next;
                 current.next = newBus;
             }
         }


     public Bus searchForValidBus(int seatsRequested) throws InterruptedException{
         //System.out.println("-------------searchForValidBus-----------------");

         //this.lock.writeLock();
         //try {
             Bus temp = head;

             if (head.remainingSeats >= seatsRequested & head.remainingSeats > 0) {

                 return head;

             } else {
                 while (temp != null) {

                     //System.out.println("loop at 118 running");
                     if (temp.remainingSeats >= seatsRequested) {

                         return temp;


                     } else {
                         temp = temp.next;
                     }
                 }
             }
             return null;

         //}finally {


         //    this.lock.writeUnlock();
         //}


    }
    public Bus searchByString(String s) throws InterruptedException{


        Bus temp = head;

        if(head.busID.equals(s)){
            synchronized (head) {
                return head;
            }
        } else{
            while(temp.next != null){
                //System.out.println("loop at 148 running");


                if(temp.busID.equals(s)){
                    synchronized (temp) {
                        return temp;
                    }

                } else{
                    temp = temp.next;
                }
            }
        }
        return null;
    }

     public void delete(String s) throws InterruptedException{
         //System.out.println("----------------------------delete------------------");
         //this.lock.writeLock();
         //try {
             if (head.busID.equals(s)) {
                 head = head.next;
             } else {
                 Bus temp = head;
                 while (temp.next != null) {

                     //System.out.println("loop at 172 running");
                     if (temp.next.busID.equals(s)) {
                         temp.next = temp.next.next;
                         break;
                     } else {
                         temp = temp.next;
                     }
                 }
             }
         //}finally {


         //    this.lock.writeUnlock();
         //}
    }

    public void insertPassengers(Bus b, int sr, String n) throws InterruptedException{
        //System.out.println("----------------insertPassengers----------------");
        //this.lock.writeLock();
        //try {
            for (int j = 50 - b.remainingSeats, i = 0; i < sr; i++, j++) {
                //System.out.println("insertPassenger looping");
                b.passengers[j] = (n);
            }
            //System.out.println("insertPassenger loop terminated");
        //}finally {
            //System.out.println("should unlock now");
        //    this.lock.writeUnlock();
        //}
    }

    public void bookTicket(User u) throws InterruptedException{
        System.out.println("Entered bookTicket");

        this.lock.writeLock();
        try {
            //Bus temp = b.searchForValidBus(requiredSeats);
            Bus ref = this.searchForValidBus(u.requiredSeats);

            //temp
            if (ref != null) {
                Bus temp = new Bus(ref.busID, ref.remainingSeats, ref.passengers);
                this.insertPassengers(temp, u.requiredSeats, u.firstName.concat(u.lastName));
                temp.remainingSeats = temp.remainingSeats - u.requiredSeats;
                this.delete(temp.busID);
                this.insertionSort(this.head);
                this.push(temp);
                this.insertionSort(this.head);


            } else {
                //Bus replacement = temp;
                System.out.println("ALL BUSSES FULL");

            }
        }finally{
            this.lock.writeUnlock();
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
