package hashcode;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Student {
    private String name;

    public Student(String name) {
        super();
        this.name = name;
    }

    public Student() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + "]";
    }

    @Override
    //重写equals
    public boolean equals(Object obj) {
        //先判断传入的参数对象是否是Student对象，若不是直接返回false
        if(obj instanceof Student) {
            //若是，强转成Student对象，并比较属性的值
            Student s = (Student) obj;
            if(this.name.equals(s.name)) {
                //若属性的值相同，则返回true
                return true;
            }
        }
        return false;
    }
//
    @Override
    public int hashCode(){
        /*hashCode方法返回值是int类型，所以重写时需要找到int类型的数据返回，还要保证此方法的返回值与对象的所有属性都相关,所以返回姓名属性的字符串的长度*/
//        return this.name.length();
        int hash = 17;
        hash = hash * 31 + getName().hashCode();
        return hash;
    }

    public static void main(String[] args) throws InterruptedException {
//        Set<Student> stuSet = Collections.synchronizedSet(new HashSet<>());
        Set<Student> stuSet = new HashSet<>();

        CountDownLatch latch = new CountDownLatch(5);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            Runnable runnable = new Thread(new SetConcurrentThread(stuSet,latch));
            executorService.submit(runnable);
        }

        latch.await();

        stuSet.forEach(System.out::println);
        System.out.println(stuSet.size());
    }



}
