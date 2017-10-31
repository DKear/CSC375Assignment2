
public class Bus implements Comparable{

    volatile public Bus next;
    volatile public int remainingSeats = 50;
    public String busID;
    final Lock lock = new Lock();
    volatile public String[] passengers = new String[50];

    public int compareTo(Object o){
        Bus b = (Bus)o;
        int bval = b.remainingSeats;
        
            return this.remainingSeats - bval;

    }

    public Bus(String iD, int rs, String[] p){
        busID = iD;
        remainingSeats = rs;
        passengers = p;
    }
}
