# Что такое функциональный интерфейс?

Функциональный интерфейс также известен как интерфейс единого абстрактного метода (Single Abstract Method, SAM). Как
следует из названия, он может иметь не более одного абстрактного метода.

Функциональный интерфейс может иметь несколько статических методов и методов по умолчанию с реализацией, приватные
методы с реализацией
а также дополнительный ОДИН абстрактный метод. Чтобы пометить интерфейс как функциональный, используется аннотация
@FunctionalInterface. Она нужна, чтобы избежать ошибочного объявления дополнительных методов.
Эту аннотацию можно не добавлять

Что делает функциональный интерфейс таким популярным, так это возможность использования лямбда-выражений для создания
интерфейсов без использования анонимных и громоздких реализаций классов.

Использование ключевого слова abstract в функциональных интерфейсах необязательно, поскольку методы, определенные внутри
интерфейса, по умолчанию являются абстрактными.

**Основное назначение – использование в лямбда выражениях и method reference.**

```java
import java.util.function.Predicate;

//Определяем свой функциональный интерфейс
@FunctionalInterface
interface MyPredicate {
    boolean test(Integer value);
}

public class Tester {
    public static void main(String[] args) throws Exception {
        MyPredicate myPredicate = x -> x > 0;
        System.out.println(myPredicate.test(10));   //true

        //Аналогично, но используется встроенный функциональный интерфейс java.util.function.Predicate
        Predicate<Integer> predicate = x -> x > 0;
        System.out.println(predicate.test(-10));    //false
    }
}

```

Но оказывается есть один тонкий момент, описанный в Java Language Specification: “interfaces do not inherit from Object,
but rather implicitly declare many of the same methods as Object.”

Это означает, что функциональные интерфейсы могут содержать дополнительно абстрактные методы, определенные в классе
Object. Код ниже валиден, ошибок компиляции и времени выполнения не будет:

```java

@FunctionalInterface
public interface Comparator<T> {
    int compare(T o1, T o2);

    boolean equals(Object obj);

}
```