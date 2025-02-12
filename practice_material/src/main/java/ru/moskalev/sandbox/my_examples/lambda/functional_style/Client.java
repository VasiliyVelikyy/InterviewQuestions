package ru.moskalev.sandbox.my_examples.lambda.functional_style;

public class Client {
    private String name;
    private int sum;
    private boolean isActive;

    public Client(String name, int sum, boolean isActive) {
        this.name = name;
        this.sum = sum;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public int getSum() {
        return sum;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
