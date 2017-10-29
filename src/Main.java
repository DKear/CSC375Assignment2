import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class Main {

    public static void main(String args[]){
        BusList Buses = new BusList();
        //Bus test = new Bus("Bus 2");
        User bookTest = new User(Buses);
        Thread bookThread1 = new Thread(new User(Buses));
        Thread bookThread2 = new Thread(new User(Buses));
        Thread bookThread3 = new Thread(new User(Buses));
        try {
            Buses.push(new Bus("Bus 1", 50, new String[50]));
            Buses.push(new Bus("Bus 2", 50, new String[50]));
            Buses.push(new Bus("Bus 3", 50, new String[50]));
            Buses.push(new Bus("Bus 4", 50, new String[50]));
            Buses.push(new Bus("Bus 5", 50, new String[50]));



            Buses.insertionSort(Buses.head);

            //Bus test = Buses.searchForValidBus(20);


            bookThread1.start();
            //bookThread2.wait(20000,20000);
            bookThread2.start();
            //bookThread3.start();

            bookThread1.join();
            bookThread2.join();
            //bookThread3.join();





        } catch(Exception e){
            System.out.println(e);
        }
    }
}
