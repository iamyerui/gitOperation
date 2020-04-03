package concurrent;


public class Mutil_Producer_ConsumerByCondition {

    public static void main(String[] args) {
        ResourceByCondition r = new ResourceByCondition();
        Mutil_Producer pro = new Mutil_Producer(r);
        Mutil_Consumer con = new Mutil_Consumer(r);
        //生产者线程
        Thread t0 = new Thread(pro, "pro-1");
        Thread t1 = new Thread(pro, "pro-2");
        //消费者线程
        Thread t2 = new Thread(con, "consum-1");
        Thread t3 = new Thread(con, "consum-2");
        //启动线程
        t0.start();
        t1.start();
        t2.start();
        t3.start();
    }
}

/**
 * @decrition 生产者线程
 */
class Mutil_Producer implements Runnable {
    private ResourceByCondition r;

    Mutil_Producer(ResourceByCondition r) {  //传入Resource资源对象
        this.r = r;
    }

    public void run() {
        while (true) {  //while(true)记住格式，无线循环
            r.product("北京烤鸭");
        }
    }
}

/**
 * @decrition 消费者线程
 */
class Mutil_Consumer implements Runnable {
    private ResourceByCondition r;

    Mutil_Consumer(ResourceByCondition r) {
        this.r = r;
    }

    public void run() {
        while (true) {   // while(true)记住格式，无线循环
            r.consume();
        }
    }
}