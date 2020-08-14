package ExampleProducerConsumer;

import java.util.Random;
import java.util.concurrent.*;

import static ExampleProducerConsumer.ArrayBlockingQueues.EOF;

public class ArrayBlockingQueues {

    public static final String EOF="EOF";

    public static void main(String[] args) {
        ArrayBlockingQueue<String> buffer=new ArrayBlockingQueue<String>(6);

        ExecutorService executorService= Executors.newFixedThreadPool(5);

        MyProducers producer=new MyProducers(buffer,ThreadColor.ANSI_YELLOW);
        MyConsumers consumer1=new MyConsumers(buffer,ThreadColor.ANSI_PURPLE);
        MyConsumers consumer2=new MyConsumers(buffer,ThreadColor.ANSI_CYAN);


        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);

        Future<String> future=executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(ThreadColor.ANSI_WHITE + "I am being printed from Callable class");
                return "This is a Callable result";
            }
        });

        try {
            System.out.println(future.get());
        }
        catch (ExecutionException e){
            System.out.println("Something Went Wrong");
        }
        catch (InterruptedException e){
            System.out.println("Thread running the task was interrupted");
        }

        executorService.shutdown();
    }
}

class MyProducers implements Runnable{
    private ArrayBlockingQueue<String> buffer;
    private String color;


    public MyProducers(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    public void run() {
        Random random=new Random();
        String[] nums={"1", "2", "3", "4", "5"};

        for (String num:nums){
            try {
                System.out.println(color + "Adding... "+num);
                buffer.put(num);
                Thread.sleep(random.nextInt(1000));
            }
            catch (InterruptedException e){
                System.out.println("Producer was interrupted");
            }
        }

        System.out.println(color + "Adding EOF and exiting....");
        try {
            buffer.put("EOF");
        }catch (InterruptedException e){

        }


    }
}

class MyConsumers implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    String color;

    public MyConsumers(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    public void run(){
        while (true){
            synchronized (buffer){
                try {
                    if (buffer.isEmpty()){
                        continue;
                    }
                    if (buffer.peek().equals(EOF)){
                        System.out.println(color + "Exiting..");
                        break;
                    }
                    else {
                        System.out.println(color + "Removed "+buffer.take());
                    }
                } catch (InterruptedException e){ ;
                }
            }
        }
    }
}
