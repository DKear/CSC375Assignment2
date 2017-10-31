import java.util.ArrayList;
import java.util.Random;

public class Main2 {
    public static void main(String args[]){
        final Random random = new Random();
        final String[] firstNames = {"John", "James", "Bill", "David", "Doug", "Chris", "Dan", "Tom", "Clayton", "Connor", "Sarah", "Nikki", "Madison", "Kim", "Michelle", "Olivia", "Elizabeth", "Alicia", "Viana", "Christine"};
        final String[] lastNames = {"Doe", "Jones", "West", "Long", "Johnson", "White", "Walker", "Brown", "Clark", "Wilson"};
        PriorityBlockingQBusList Buses = new PriorityBlockingQBusList();
        ArrayList<Thread> threads = new ArrayList<>();

        Buses.BusList.add(new Bus("Bus 1", 50, new String[50]));
        Buses.BusList.add(new Bus("Bus 2", 50, new String[50]));
        Buses.BusList.add(new Bus("Bus 3", 50, new String[50]));
        Buses.BusList.add(new Bus("Bus 4", 50, new String[50]));
        Buses.BusList.add(new Bus("Bus 5", 50, new String[50]));

        for(int i = 0; i < 50; i++){
            threads.add(new Thread(new PriorityBlockingQViewingUser(Buses, firstNames[random.nextInt(firstNames.length-1)], lastNames[random.nextInt(lastNames.length-1)])));
        }

        for(int i = 0; i < 5; i++){
            threads.add(new Thread(new PriorityBlockingQBookingUser(Buses, firstNames[random.nextInt(firstNames.length-1)], lastNames[random.nextInt(lastNames.length-1)],random.nextInt(49))));
        }

        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
        }
        for (int i = 0; i < threads.size(); i++) {
            try {
                threads.get(i).join();
            } catch (Exception e) {

            }
        }

    }
}
