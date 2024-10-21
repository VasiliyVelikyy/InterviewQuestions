package sandbox.my_examples.multithreading;

import java.util.Objects;

public class WaitExample {
    public String propertyOne;
    public int propertyTwo;
    public static void main(String[] args) {
        Object obj = new Object();
        try {
            obj.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WaitExample waitExample = new WaitExample();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WaitExample that = (WaitExample) o;
        return propertyTwo == that.propertyTwo && Objects.equals(propertyOne, that.propertyOne);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(propertyOne);
        result = 31 * result + propertyTwo;
        return result;
    }
}
