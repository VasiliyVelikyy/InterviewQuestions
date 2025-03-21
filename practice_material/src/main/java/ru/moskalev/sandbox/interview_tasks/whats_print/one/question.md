# Модификаторы доступа закрытия по возрастанию уровня закрытости

- public, protected, default, private

# Как избежать сериализации переменной члена класса?

- transient

private transient int age; // это поле НЕ будет сериализовано

# Примером какого паттерна будет класс Math2 класс

```java
class Math {
    public int calc(int a, int b, int operation, int operandsCount) {
        /**/
    }
}

class Math2 {
    public static int negate(int a) {
        return Math().calc(a, 0, 0, 1);
    }

    public static int add(int a, int b) {
        return return Math().calc(a, b, 5, 2);
    }
}
```

- Singleton
- Adapter
- Decorator
- Strategy
- Facade
- Builder

### Объяснение

Паттерн Facade предоставляет упрощённый интерфейс для работы с более сложной системой. В данном примере класс Math2
создает упрощенный интерфейс для работы с классом Math, скрывая сложность его методов (например, различные параметры для
операций).

В Math2 предоставляются методы, такие как negate и add, которые делегируют выполнение вычислений через объект Math (
метод calc), но с более простым интерфейсом.
Метод calc в классе Math требует нескольких параметров, что делает его интерфейс более сложным. Math2 же скрывает эту
сложность, предоставляя более понятные методы.
Это соответствует сути Facade — создание единого интерфейса для работы с более сложной подсистемой.

# Чир означает переопределение метода в java (Override)?

Изменение поведения метода класса относительно родительского

# Какие из следующих утверждений о методе hashCode() являются неверными?

- Значение, возвращаемое методом hashCode(), используется в некоторых классах коллекций для помощи в поиске объектов.

- Метод hashCode() обязан возвращать положительное значение типа int.

- Метод hashCode() в классе String унаследован от класса Object.

- Два новых пустых объекта String будут иметь одинаковые хэш-коды.

Теперь давайте разберем, какие из этих утверждений неверны:

### Объяснение

Верно. Метод hashCode() действительно используется в коллекциях, таких как HashMap или HashSet, для быстрого поиска
объектов.

Неверно. Метод hashCode() может возвращать как положительные, так и отрицательные значения типа int.

Неверно. Метод hashCode() в классе String переопределен и не является унаследованным от класса Object.

Верно. Два пустых объекта String будут иметь одинаковые хэш-коды, так как их содержимое одинаково (пустая строка).

# Какой класс или интерфейс определяет методы wait(), notify(), и notifyAll()

- Object