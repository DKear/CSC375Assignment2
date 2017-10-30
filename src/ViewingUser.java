public class ViewingUser implements Runnable {
    String firstName;
    String lastName;
    CustomBusList BL;

    public ViewingUser(CustomBusList B, String fn, String ln){
        BL = B;
        firstName = fn;
        lastName = ln;
    }

    public void run(){

        try {
            BL.readList(BL.head, this);

        }catch(InterruptedException e){
        }


    }
}
