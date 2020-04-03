package key;

public class ConcreteAction2 extends abstractAction {
    @Override
    void beforeAction() {
        System.out.println("ConcreteAction2   before");
    }


    public static void main(String[] args) {
        Action a1 = new ConcreteAction1();
        Action a2 = new ConcreteAction2();

        System.out.println("a1 " );
        a1.action();
        System.out.println("a2 " );
        a2.action();
    }
}
