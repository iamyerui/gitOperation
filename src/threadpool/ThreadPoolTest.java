package threadpool;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolTest {
    private static int corePoolSize =  1;

    private static final int maxPoolSize = 2;

    private static final int maxQueueSize = 5;

    private static class CustomThreadFactory implements ThreadFactory {
        private int counter;
        private String name;
        private List<String> stats;

        public CustomThreadFactory(String name) {
            counter = 1;
            this.name = name;
            stats = new ArrayList<String>();
        }

        @Override
        public Thread newThread(Runnable runnable) {
            Thread t = new Thread(runnable, name + "-Thread_" + counter);
            counter++;
            stats.add(String.format("Created thread %d with name %s on %s \n", t.getId(), t.getName(), new Date()));
            return t;
        }

        public String getStats() {
            StringBuffer buffer = new StringBuffer();
            Iterator<String> it = stats.iterator();
            while (it.hasNext()) {
                buffer.append(it.next());
            }
            return buffer.toString();
        }
    }

        private static class CustomRejectedPolicy implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("reject .....");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //do nothing
            }
            if (!executor.isShutdown()) {
                executor.execute(r);
            }
        }
    }

    private static ExecutorService executorService = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 10, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(maxQueueSize),  Executors.defaultThreadFactory(), new CustomRejectedPolicy());

    public static void main(String[] args) throws InterruptedException {


        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("haha");
                }
            });

        }


    }
}
