package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


//公共资源类，线程获取这个类调用它的生产和消费方法
public class ResourceByCondition {
    private String name;  //商品名称
    private int count = 1;  //商品编号
    private boolean flag = false;  //是否有烤鸭

    //创建一个锁对象。
    Lock lock = new ReentrantLock();

    //通过已有的锁获取两组监视器，一组监视生产者，一组监视消费者。
    Condition producer_con = lock.newCondition();
    Condition consumer_con = lock.newCondition();

    /**
     * 生产
     * @param name
     */
    public  void product(String name)
    {
        lock.lock();  //上锁，避免一个商品生产了两次或者被消费了两次（避免两个线程count++结果只加了1的情况）

        try
        {
            while(flag){ //有烤鸭
                try{producer_con.await();}catch(InterruptedException e){}  //有烤鸭，则加入producer_con对应的等待队列

            }
            this.name = name + count;
            count++;
            System.out.println(Thread.currentThread().getName()+"...生产者5.0..."+this.name);
            flag = true;  //使消费者线程可以跳过while(！flag)运行下去
            Thread.sleep(4000);
            consumer_con.signal();//直接唤醒消费线程
        } catch (InterruptedException e)
        {
            System.out.println("InterruptedException");
        }
        finally
        {
            lock.unlock();  //解锁，以便让下一个线程可以执行。
        }
    }

    /**
     * 消费
     */
    public  void consume()
    {
        lock.lock();
        try
        {
            while(!flag){ //没有烤鸭
                try{consumer_con.await();}catch(InterruptedException e){}  //没有烤鸭，则加入consumer_con对应的等待队列
            }
            System.out.println(Thread.currentThread().getName()+"...消费者.5.0......."+this.name);//消费烤鸭1
            flag = false;
            Thread.sleep(4000);

            producer_con.signal();//直接唤醒生产线程
        }catch (InterruptedException e)
        {
            System.out.println("InterruptedException");
        }
        finally
        {
            lock.unlock();
        }
    }
}