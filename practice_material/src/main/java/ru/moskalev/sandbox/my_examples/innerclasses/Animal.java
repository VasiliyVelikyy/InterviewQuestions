package ru.moskalev.sandbox.my_examples.innerclasses;

public class Animal {
    public void voice() {
        System.out.println("Meow!");
    }

    public static void main(String[] args) {
        Animal doggy = new Animal() {
            @Override
            public void voice() {
                System.out.println("Gav!");
            }
        };

        Animal cat = new Animal();
        Animal cow = new Animal().new Cow();

        cat.voice(); // будет выведено Meow!
        cow.voice(); // будет выведено Moooo!
        doggy.voice(); //будет выведено Gav!
    }

    private class Cow extends Animal {
        @Override
        public void voice() {
            System.out.println("Moooo!");
        }
    }
}