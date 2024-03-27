package sandbox.my_examples.lambda.functional_interface;

@FunctionalInterface
public interface SimpleFuncInterface {
    public void doWork(); // модификатор public по умолчанию стоит

    // public void isFunctionalInterface(); //среда разработки будет ругаться, поому что у фунц интрефейса не может быть 2 асбрактных метода
    boolean equals(Object o);
}