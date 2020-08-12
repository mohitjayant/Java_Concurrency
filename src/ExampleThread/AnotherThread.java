package ExampleThread;

import static ExampleThread.ThreadColor.ANSI_BLUE;

public class AnotherThread extends Thread {

    @Override
    public void run() {
        System.out.println(ANSI_BLUE+"hey,I am from " + currentThread().getName());

        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e){
            System.out.println(ANSI_BLUE + "Another thread woke me up");
        }

        System.out.println(ANSI_BLUE + "Three seconds have passed and I'm awake");
    }
}
