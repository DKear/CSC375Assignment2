public class PriorityBlockingQViewingUser implements Runnable{
    String firstName;
    String lastName;
    PriorityBlockingQBusList BL;

    public PriorityBlockingQViewingUser(PriorityBlockingQBusList B, String fn, String ln){
        BL = B;
        firstName = fn;
        lastName = ln;
    }

    public void run(){
        try{
            BL.readList(this);
        }catch (Exception e){}
    }

}
