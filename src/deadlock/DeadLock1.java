package deadlock;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.*;

public class DeadLock1 {
    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        Thread thread1 = new Thread(new ThreadNeed(lock1, lock2));
        Thread thread2 = new Thread(new ThreadNeed(lock2, lock1));


        thread1.start();
        thread2.start();



    }


    public static class ThreadNeed implements Runnable
    {
        private ReentrantLock lock1;

        public ThreadNeed(ReentrantLock lock1, ReentrantLock lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        private ReentrantLock lock2;



        @Override
        public void run() {

            try {
                lock1.lock();
                sleep(1000);
                lock2.lock();
                sleep(500);
                System.out.println("......");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
                lock2.unlock();
            }


        }
    }


}
