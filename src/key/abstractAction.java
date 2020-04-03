package key;

public abstract class abstractAction implements Action{

    public void action()
    {
        beforeAction();
        System.out.println("action common");
    }

    abstract void beforeAction();
}
