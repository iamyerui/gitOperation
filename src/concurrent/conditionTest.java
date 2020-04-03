package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class conditionTest {

    public final Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();


    public static void main(String[] args) {
        conditionTest test = new conditionTest();

        Consumer consumer = test.new Consumer();
        Producer producer = test.new Producer();
        consumer.start();
        producer.start();


    }

    class Consumer extends Thread{

        @Override
        public void run() {
            consume();
        }

        private void consume() {

            try {
                lock.lock();
                System.out.println("我在等一个新信号"+this.currentThread().getName());
                condition.await();

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally{
                System.out.println("拿到一个信号"+this.currentThread().getName());
                lock.unlock();
            }

        }
    }

    class Producer extends Thread{

        @Override
        public void run() {


            try {
                lock.lock();
                System.out.println("我发送一个新信号"+this.currentThread().getName());
                condition.signalAll();



            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("成功发送了一个信号"+this.currentThread().getName());

            }



        }
    }


}
