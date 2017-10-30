import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Random;

public class Main {

    public static void main(String args[]){
        final Random random = new Random();
        final String[] firstNames = {"John", "James", "Bill", "David", "Doug", "Chris", "Dan", "Tom", "Clayton", "Connor", "Sarah", "Nikki", "Madison", "Kim", "Michelle", "Olivia", "Elizabeth", "Alicia", "Viana", "Christine"};
        final String[] lastNames = {"Doe", "Jones", "West", "Long", "Johnson", "White", "Walker", "Brown", "Clark", "Wilson"};
        BusList Buses = new BusList();
        //Bus test = new Bus("Bus 2");
        //User bookTest = new User(Buses);
        Thread bookThread1 = new Thread(new User(Buses, firstNames[random.nextInt(firstNames.length-1)], lastNames[random.nextInt(lastNames.length-1)],25));
        Thread bookThread2 = new Thread(new User(Buses, firstNames[random.nextInt(firstNames.length-1)], lastNames[random.nextInt(lastNames.length-1)],26));
        Thread bookThread3 = new Thread(new User(Buses, firstNames[random.nextInt(firstNames.length-1)], lastNames[random.nextInt(lastNames.length-1)],4));
        try {
            Buses.push(new Bus("Bus 1", 50, new String[50]));
            Buses.push(new Bus("Bus 2", 50, new String[50]));
            Buses.push(new Bus("Bus 3", 50, new String[50]));
            Buses.push(new Bus("Bus 4", 50, new String[50]));
            Buses.push(new Bus("Bus 5", 50, new String[50]));



            //Buses.insertionSort(Buses.head);

            //Bus test = Buses.searchForValidBus(20);


            bookThread1.start();
            //bookThread2.wait(20000,20000);
            bookThread2.start();
            bookThread3.start();

            bookThread1.join();
            bookThread2.join();
            bookThread3.join();





        } catch(Exception e){
            System.out.println(e);
        }
    }
}
