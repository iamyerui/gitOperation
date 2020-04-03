package atomic.practise;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class FinalTest {

    public Map<String, Integer> getMaps() {
        return maps;
    }
    private static volatile AtomicInteger countAtomic = new AtomicInteger(0);
    private static volatile int count = 0;
    private final Map<String, Integer> maps;

    public FinalTest() {
        this.maps = new HashMap<>();
        maps.put("aa",1);
        maps.put("bb",2);
        maps.put("cc",3);

    }



    public static void main(String[] args) {
//        FinalTest test = new FinalTest();
//        Map<String, Integer> result = test.getMaps();
//        result.put("aa", 3);
//        System.out.println(result.get("aa"));

        ExecutorService executorService = Executors.newFixedThreadPool(2000);
        for (int i = 0; i < 2000; i++) {
            executorService.submit(()->{
                        for (int j = 0; j < 20; j++) {
                            System.out.println(Thread.currentThread().getName()+" atomic  " +countAtomic.incrementAndGet());
                            System.out.println(Thread.currentThread().getName()+" volatile " + count++);

                        }
                    }
            );

        }


        executorService.shutdown();


        System.out.println(countAtomic.get());
        System.out.println(count);
    }
    }



