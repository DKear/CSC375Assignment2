import java.util.Random;

public class User implements Runnable {

    final Random random = new Random();
    final int requiredSeats = 0;
    final String[] firstNames = {"John", "James", "Bill", "David", "Doug", "Chris", "Dan", "Tom", "Clayton", "Connor", "Sarah", "Nikki", "Madison", "Kim", "Michelle", "Olivia", "Elizabeth", "Alicia", "Viana", "Christine"};
    final String[] lastNames = {"Doe", "Jones", "West", "Long", "Johnson", "White", "Walker", "Brown", "Clark", "Wilson"};
    String firstName;
    String lastName;

    public User(BusList B){
        try {
            //B.insert(50);
        }catch (Exception e){

        }
    }
    public void run(){

    }

    public void read(BusList busList){

    }
}
