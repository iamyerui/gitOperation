package hashcode;

import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class SetConcurrentThread implements Runnable {


    private Set set;

    public SetConcurrentThread(Set set, CountDownLatch latch) {
        this.set = set;
        this.latch = latch;
    }

    private CountDownLatch latch;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            set.add(new Student("wang" + i));
        }
        latch.countDown();

    }
}
