package threads.thread1;

/**
 * @program: selfplay
 * @description: 表示人的类, 人将不断地通过门
 * @author: zx
 * @create: 2018-08-26 13:09
 **/
public class UserThread extends Thread{
    private  final Gate gate;
    private final  String myname;
    private final  String myaddress;

    public UserThread(Gate gate,String myname,String myaddress) {
        this.gate = gate;
        this.myname = myname;
        this.myaddress = myaddress;
    }


    @Override
    public void run() {
        System.out.println("begin    "+myname+"   "+myaddress);
        for (int i = 0; i < 1000000; i++) {

            try {
                gate.pass(myname,myaddress,false);
//                gate.pass(myname,myaddress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
