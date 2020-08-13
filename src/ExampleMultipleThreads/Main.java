package ExampleMultipleThreads;

import static ExampleThread.ThreadColor.*;

public class Main {

    public static void main(String[] args) {

        CountDown countDown=new CountDown();

        CountDownThread t1=new CountDownThread(countDown);
        t1.setName("Thread 1");

        CountDownThread t2=new CountDownThread(countDown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();

    }
}

class CountDown{
    private int i;
    public void doCountdown(){
        String color;
        switch (Thread.currentThread().getName()){
            case "Thread 1":
                color=ANSI_CYAN;
                break;
            case "Thread 2":
                color=ANSI_PURPLE;
                break;
            default:
                color=ANSI_GREEN;
                break;
        }
        //Synchronization of block od statement
        synchronized (this){
            for (i=10;i>0;i--){
                System.out.println(color + Thread.currentThread().getName()+ ": i=" + i);
            }
        }
/*        for (i=10;i>0;i--){
            System.out.println(color + Thread.currentThread().getName()+ ": i=" + i);
        }*/
    }
}

class CountDownThread extends Thread {
    private CountDown threadCountdown;

    public CountDownThread(CountDown countDown){
        this.threadCountdown=countDown;
    }

    public void run() {
        threadCountdown.doCountdown();
    }
}
