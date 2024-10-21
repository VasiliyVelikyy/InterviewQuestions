## Методы класса Object

* public String toString()
  Возвращает строковое представление объекта.

* public native int hashCode()
* public boolean equals(Object obj)
  Пара методов, которые используются для сравнения объектов.

* public final native Class getClass()
  Возвращает специальный объект, который описывает текущий класс.

* public final native void notify()
* public final native void notifyAll()
* public final native void wait(long timeout)
* public final void wait(long timeout, intnanos)
* public final void wait()
  Методы для контроля доступа к объекту из различных нитей. Управление синхронизацией нитей.

* protected void finalize()
  Метод позволяет «освободить» родные не-Java ресурсы: закрыть файлы, потоки и т.д.

* protected native Object clone()
  Метод позволяет клонировать объект: создает дубликат объекта

# Статическое связывание

Что выведет эта программа? Collection, Set или HashSet?

```java
public class Example {
    public static void main(String[] args) {
        Collection collection = new HashSet();
        print(collection);
    }

    private static void print(Collection collection) {
        System.out.println("collection");
    }

    private static void print(Set collection) {
        System.out.println("set");
    }

    private static void print(HashSet collection) {
        System.out.println("hashSet");
    }
}
```

Приватные, статические и final-методы связываются
при помощи статического связывания (Раннего), а виртуальные – динамического. Аналогично, лучший пример статического
связывания –
перегрузка методов, а переопределение – динамического.

### Что такое функциональный интерфейс?

Функциональный интерфейс также известен как интерфейс единого абстрактного метода (Single Abstract Method, SAM). Как
следует из названия, он может иметь не более одного абстрактного метода.

Функциональный интерфейс может иметь несколько статических методов и методов по умолчанию с реализацией, а также
дополнительный абстрактный метод. Чтобы пометить интерфейс как функциональный, используется аннотация
@FunctionalInterface. Она нужна, чтобы избежать ошибочного объявления дополнительных методов.

Что делает функциональный интерфейс таким популярным, так это возможность использования лямбда-выражений для создания
интерфейсов без использования анонимных и громоздких реализаций классов.

Использование ключевого слова abstract в функциональных интерфейсах необязательно, поскольку методы, определенные внутри
интерфейса, по умолчанию являются абстрактными.