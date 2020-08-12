package ExampleThread;

import static ExampleThread.ThreadColor.ANSI_GREEN;
import static ExampleThread.ThreadColor.ANSI_PURPLE;

public class Main {

    public static void main(String[] args) {
        System.out.println(ANSI_PURPLE+"Hey, I am from main Thread");

        Thread anotherThread=new AnotherThread();
        anotherThread.setName("== AnotherThread ==");
        anotherThread.start();

/*        new Thread(){
            public void run(){
                System.out.println("Hello,From Anonymous class");
            }
        }.start();*/

        new Thread(() -> System.out.println(ANSI_GREEN+"Hello,From Anonymous class")).start(); //Anonymous class

        Thread myRunnable=new Thread(new MyRunnable());
        myRunnable.start();

        System.out.println(ANSI_PURPLE+"Hey, I am again from main Thread");
    }
}
