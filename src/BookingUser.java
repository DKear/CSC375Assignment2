public class BookingUser implements Runnable {


    int requiredSeats;
    String firstName;
    String lastName;
    CustomBusList BL;

    public BookingUser(CustomBusList B, String fn, String ln, int rs){
        BL = B;
       firstName = fn;
       lastName = ln;
       requiredSeats = rs;
    }
    public void run(){



        try {
            BL.bookTicket(this);
        }catch(InterruptedException e){
        }


    }

    public void userBookTicket(CustomBusList b) throws InterruptedException{
        b.bookTicket(this);
    }

    public void bookTicket(CustomBusList b){
        try{
            //Bus temp = b.searchForValidBus(requiredSeats);
            Bus ref = b.searchForValidBus(requiredSeats);

            //temp
            if(ref != null){
                Bus temp = new Bus(ref.busID, ref.remainingSeats, ref.passengers);
                b.insertPassengers(temp, requiredSeats, firstName + " " + lastName);
                temp.remainingSeats = temp.remainingSeats - requiredSeats;
                b.delete(temp.busID);
                b.insertionSort(b.head);
                b.push(temp);
                b.insertionSort(b.head);



            } else{
                //Bus replacement = temp;
                System.out.println("ALL BUSSES FULL");

            }
        }catch (Exception e){
            System.out.println("Something wrong with BookTicket");
        }

    }

    public void read(CustomBusList customBusList){

    }
}
