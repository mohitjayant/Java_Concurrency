package ExampleThread;

import static ExampleThread.ThreadColor.ANSI_BLUE;

public class AnotherThread extends Thread {

    @Override
    public void run() {
        System.out.println(ANSI_BLUE+"hey,I am from another Thread");
    }
}
