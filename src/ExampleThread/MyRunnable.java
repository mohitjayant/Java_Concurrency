package ExampleThread;

import static ExampleThread.ThreadColor.ANSI_RED;

public class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println(ANSI_RED + "Hello from MyRunnable implementation of run()");
    }
}
