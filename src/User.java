import java.util.Random;

public class User implements Runnable {

    final Random random = new Random();
    final int requiredSeats = 50;
    final String[] firstNames = {"John", "James", "Bill", "David", "Doug", "Chris", "Dan", "Tom", "Clayton", "Connor", "Sarah", "Nikki", "Madison", "Kim", "Michelle", "Olivia", "Elizabeth", "Alicia", "Viana", "Christine"};
    final String[] lastNames = {"Doe", "Jones", "West", "Long", "Johnson", "White", "Walker", "Brown", "Clark", "Wilson"};
    String firstName;
    String lastName;
    BusList BL;

    public User(BusList B){
        BL = B;
       firstName = firstNames[random.nextInt(firstNames.length - 1 )];
       lastName = lastNames[random.nextInt(lastNames.length - 1 )];
    }
    public void run(){

        bookTicket(BL);
        bookTicket(BL);
        bookTicket(BL);
        bookTicket(BL);
        bookTicket(BL);
        bookTicket(BL);


    }

    public void bookTicket(BusList b){
        try{
            //Bus temp = b.searchForValidBus(requiredSeats);
            Bus ref = b.searchForValidBus(requiredSeats);

            //temp
            if(ref != null){
                Bus temp = new Bus(ref.busID, ref.remainingSeats, ref.passengers);
                b.insertPassengers(temp, requiredSeats, firstName.concat(" " + lastName));
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

    public void read(BusList busList){

    }
}
