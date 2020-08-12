package ExampleThread;

import static ExampleThread.ThreadColor.*;

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

        Thread myRunnable=new Thread(new MyRunnable(){
            @Override
            public void run() {
                System.out.println(ANSI_RED + "Hello from anonymous class implementation of run()");

                try {
                    anotherThread.join();
                    System.out.println(ANSI_RED + "AnotherThread terminated, or timed out, So i am running again");
                }
                catch (InterruptedException e){
                    System.out.println(ANSI_RED + "I couldn't wait after all.I was interrupted");
                }
            }
        });
        myRunnable.start();

        System.out.println(ANSI_PURPLE+"Hey, I am again from main Thread");
    }
}
