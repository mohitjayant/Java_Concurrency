package Challenge;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private double balance;
    private String accountNumber;
    private Lock lock;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        lock=new ReentrantLock();
    }

    //Challenge 2

    /*public void deposit(double amount) {
        synchronized (this) {
            balance += amount;
            System.out.println("Available balance after deposit : "+balance);
        }
    }

    public void withdraw(double amount) {
        synchronized (this) {
            balance -= amount;
            System.out.println("Available balance after withdraw : "+balance);
        }
    }*/

    //Challenge 3
    public String getAccountNumber() {
        return accountNumber;
    }

    public void printAccountNumber() {
        System.out.println("Account number = " + accountNumber);
    }


    //Challenge 4
/*    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
        }finally {
            lock.unlock();
        }
    }

    public void withdraw(double amount) {
        lock.lock();
        try {
            balance -= amount;
        }finally {
            lock.unlock();
        }
    }*/




    //Challenge 5
    public void deposit(double amount) {
        boolean status=false;
        try {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    balance += amount;
                    status=true;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Could not get the lock");
            }

        } catch(InterruptedException e) {
            // do something here
        }
        System.out.println("Transaction status = " + status);
    }

    public void withdraw(double amount) {
        boolean status=false;
        try {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    balance -= amount;
                    status=true;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Could not get the lock");
            }

        } catch(InterruptedException e) {
            // do something here
        }
        System.out.println("Transaction status = " + status);
    }
}
