package block1.lambda.functional_interface;

@FunctionalInterface
public interface SimpleFuncInterface {
    public void doWork();
   // public void isFunctionalInterface(); //среда разработки будет ругаться, поому что у фунц интрефейса не может быть 2 асбрактных метода
    public String toString();// эти абстракт методы допускаються так как методы из класса java.lang.Object
    public boolean equals(Object o);
}