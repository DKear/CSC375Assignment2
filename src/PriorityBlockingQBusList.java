package org.sample;
import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQBusList {
    PriorityBlockingQueue<Bus> BusList = new PriorityBlockingQueue<>();

    public Bus searchForValidBus(int seatsRequested){

        Iterator<Bus> itr = BusList.iterator();
        Bus temp = itr.next();

            while(itr.hasNext()){
                if(temp.remainingSeats >= seatsRequested & temp.remainingSeats > 0){
                    return temp;
                } else{
                    temp = itr.next();
                }
            }
         return null;
    }

    public void insertPassengers(Bus b, int sr, String n){
        for (int j = 50 -b.remainingSeats, i =0; i<sr; i++, j++){
            b.passengers[j] = (n);
        }
        System.out.println(n + " booked " + sr + " seats on " + b.busID);
    }

    public void delete(String s){
        Iterator<Bus> itr = BusList.iterator();

            Bus temp = itr.next();
            while(itr.hasNext()){
                if(temp.busID.equals(s)){
                    //BusList.remove(b);
                    break;
                }else{
                    itr.next();
                }
            }

    }

    synchronized public void bookTicket(PriorityBlockingQBookingUser u){
        Bus ref = this.searchForValidBus(u.requiredSeats);

        if(ref != null){
            Bus temp = new Bus(ref.busID, ref.remainingSeats, ref.passengers);
            this.BusList.remove(ref);

            this.insertPassengers(temp, u.requiredSeats, u.firstName.concat(" " + u.lastName));
            temp.remainingSeats = temp.remainingSeats - u.requiredSeats;
            //this.delete(temp.busID);
            this.BusList.add(temp);

        } else{
            System.out.println(u.firstName + " " + u.lastName + " looked to book, but found no valid bus.");
        }
    }

    public void readList(PriorityBlockingQViewingUser u){
        Iterator<Bus> itr = BusList.iterator();
        while(itr.hasNext()){
            System.out.println(u.firstName + " " + u.lastName + " viewed " + itr.next().busID);
        }
    }
}
