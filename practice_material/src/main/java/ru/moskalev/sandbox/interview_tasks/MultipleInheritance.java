package ru.moskalev.sandbox.interview_tasks;

//Демонстрация что множественное наследование есть.
//Но это работает только с интерфейсом. Класс не может наследоваться от множества супер классов
public class MultipleInheritance {
}


interface A {

}

interface B {

}

interface C extends A, B {

}

class D {

}

class E {

}

//class I extends D, E { }

