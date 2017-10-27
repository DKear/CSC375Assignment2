public class Main {
    public static void main(String args[]){
        BusList Buses = new BusList();
        try {
            Buses.push(50, "1");
            Buses.push(51, "2");
            Buses.push(52, "3");
            Buses.push(1, "4");
            Buses.push(2, "5");
            Buses.insertionSort(Buses.head);

            Buses.delete("1");
            Buses.push(20, "1");
            Buses.insertionSort(Buses.head);


        } catch(Exception e){

        }
    }
}
