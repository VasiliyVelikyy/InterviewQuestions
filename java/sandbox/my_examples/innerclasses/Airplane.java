package sandbox.my_examples.innerclasses;

public class Airplane {
    private String name, id, flight;
    private Wing leftWing = new Wing("Red", "X3"), rightWing = new Wing("Blue", "X3");

    public Airplane(String name, String id, String flight) {
        this.name = name;
        this.id = id;
        this.flight = flight;
    }

    public Airplane(String name, Wing leftWing, Wing rightWing) {
        this.name = name;
        this.leftWing = leftWing;
        this.rightWing = rightWing;
    }

    public class Wing {
        private String color, model;

        private Wing(String color, String model) {
            this.color = color;
            this.model = model;
        }

        // getters/setters
    }

    public static void main(String[] args) {
//        Airplane airplane = new Airplane("airbus-456",
//                new Airplane.Wing("black", "a3"),
//                new Airplane.Wing("black", "a3"));
        //неполучиться отдельно создать внутренний класс
    }


}
