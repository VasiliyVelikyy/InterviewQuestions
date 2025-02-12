package ru.moskalev.sandbox.conf.puzzlers.ng_s04;

public class OrderCar {
    public static void main(String[] args) {
        System.out.println(order("бирюзовый"));
    }

    static <Color> Color order(Color color) {
        class ModelT extends RuntimeException {
            Color color;
            ModelT(Color color) {
                this.color = color; // 1
            }
        }
        if (color.equals(0x000000)) throw new ModelT(color); // 2
        try {
            order(0x000000);
        } catch (ModelT car) {
            color = car.color; // 3
        }
        return color;
    }
    class Color{
    }
}
