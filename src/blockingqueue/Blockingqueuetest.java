package blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class Blockingqueuetest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> bq = new LinkedBlockingQueue<>();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(()->{



                try {
                    for (int i=0; i<100; i++) {
                        Thread.sleep(1000);
                        bq.put("z" + i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }

        }).start();

        new Thread(()->{


                try {
                    for (int i=0; i<100; i++)
                    System.out.println( bq.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }

        }).start();

        countDownLatch.await();
        System.out.println(bq.size());
    }
}
