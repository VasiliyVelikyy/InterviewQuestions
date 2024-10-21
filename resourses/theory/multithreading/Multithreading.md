## ExecutorService

ExecutorService – это интерфейс из пакета java.util.concurrent, который предоставляет фреймворк для асинхронного
выполнения задач. Он позволяет управлять потоками и задачами без необходимости явно создавать и управлять потоками
руками. Это делает код более читаемым, упрощает работу с многопоточностью и повышает производительность приложения.

Зачем он нужен?

В многопоточных приложениях часто возникает необходимость выполнения задач параллельно или асинхронно. Вместо того чтобы
каждый раз создавать новые потоки вручную, что может быть неэффективно и увеличивать сложность кода, ExecutorService
предоставляет удобные методы для выполнения задач в уже существующем пуле потоков. Это позволяет повторно использовать
потоки и управлять ими эффективно.

Как он используется?

Может быть создан через статические методы класса Executors, например, newFixedThreadPool(int), newCachedThreadPool(),
newSingleThreadExecutor(), которые позволяют создавать пулы потоков с фиксированным числом потоков, кэширующие пулы
потоков (создают новые потоки по необходимости и переиспользуют старые, когда они освобождаются) и пулы для выполнения
задач последовательно в одном потоке соответственно.

После его создания, можно отправлять задачи на выполнение, используя методы execute(Runnable) для задач без результата
или submit(Callable<T>) для задач, возвращающих результат. Он также предоставляет методы для управления состоянием
выполнения, например, shutdown() для остановки приема новых задач и завершения уже запущенных.

Пример:

``` ExecutorService executor = Executors.newFixedThreadPool(10); // Создаем пул из 10 потоков

executor.submit(() -> {
    // Задача, выполняемая в одном из потоков пула
    System.out.println("Асинхронная задача " + Thread.currentThread().getName());
});

executor.shutdown(); // Завершаем работу ExecutorService после выполнения всех задач

ExecutorService – это мощный инструмент для управления асинхронными задачами и потоками в Java. Он упрощает работу с многопоточностью, позволяя эффективно использовать системные ресурсы и упрощает код, делая его более читаемым и поддерживаемым.
```

ExecutorService помогает организовать выполнение задач в пуле потоков, управлять этими потоками и обрабатывать
результаты выполнения задач.

> https://habr.com/ru/articles/260953/    
> https://habr.com/ru/articles/116363/  
> https://habr.com/ru/articles/554608/
> https://javarush.com/quests/lectures/jru.module2.lecture18
>

## Какие способы существуют чтобы создать поток

Первый способ:

* Определить класс — наследник класса Thread и переопределить метод run().
* Создать экземпляр своего класса и вызвать метод start().

```java
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello, I’m " + Thread.currentThread());
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
//Вывод: Hello, I’m Thread[Thread-0,5,main]
```

Второй способ:

* Реализовать интерфейс Runnable и метод run().
* Создать экземпляр Thread и передать в конструктор свой Runnable (экземпляр класса, реализующий этот интерфейс).

```java
class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.print("Hello, I’m " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        // Первый параметр: экземпляр Runnable
        // Второй параметр: своё имя (необязательно) 
        Thread myThread = new Thread(new MyThread(), "Leo");
        myThread.start();
    }
}
//Вывод: Hello, I’m Leo
```

Третий способ - с помощью ExecutorService

```java
public class FactorialTask implements Callable<Integer> {
    // ...
    public Integer call() throws InvalidParamaterException {

        if (number < 0) {
            throw new InvalidParamaterException("Number should be positive");
        }
        // ...
    }
}

@Test(expected = ExecutionException.class)
public void whenException_ThenCallableThrowsIt() {

    FactorialCallableTask task = new FactorialCallableTask(-5);
    Future<Integer> future = executorService.submit(task);
    Integer result = future.get().intValue();
}
```

## Разница между интерфейсами Runnable и Callable

С первых дней существования Java многопоточность была основным аспектом языка. Runnable – это основной интерфейс,
предоставляемый для многопоточных задач, а в Java 1.5 появился Callable как улучшенная версия Runnable.
В этом руководстве мы рассмотрим различия и области применения обоих интерфейсов.
Механизм исполнения
Оба интерфейса предназначены для представления задачи, которая может выполняться несколькими потоками. Мы можем
запускать Runnable задачи, используя класс Thread или ExecutorService, Callable объекты запускаются только с помощью
ExecutorService.
Давайте подробнее рассмотрим, как эти интерфейсы обрабатывают возвращаемые значения.
Runnable
Интерфейс Runnable является функциональным интерфейсом и имеет единственный метод run(), который не принимает никаких
параметров и не возвращает никаких значений. Это бывает нужно в ситуациях, когда нам не нужен результат выполнения
потока, например, протоколирование входящих событий:

```
public interface Runnable {
public void run();
}
// Давайте посмотрим на пример:
public class LoggingTask implements Runnable {
    private Logger logger = LoggerFactory.getLogger(LoggingTask.class);

    @Override
    public void run() {
        logger.info("Hello");
    }
}
```

В этом примере поток просто читает сообщение из очереди и регистрирует его в файле журнала. Из задачи не возвращается
никакого значения.

Мы можем запустить задачу с помощью ExecutorService:

В этом примере поток просто прочитает сообщение из очереди и зарегистрирует его в файле журнала. Из задачи не
возвращается никакого значения.

Мы можем запустить задачу с помощью ExecutorService:

```


public void execute() {
    exeService = Executors.newSingleThreadExecutor();
    Future future = exeService.submit(new LoggingTask());
    exeService.shutdown();
}
//В этом случае объект Future не будет иметь никакого значения.
Callable
Callable интерфейс – это generic интерфейс, содержащий один метод call(), который возвращает generic значение V:

public interface Callable<V> {
    V call() throws Exception;
}
```

Давайте рассмотрим вычисление факториала числа:
public class FactorialTask implements Callable<Integer> {
int number;

    // стандартные конструкторы

    public Integer call() throws InvalidParamaterException {
        int factor = 1;
        // ...

        for(int count = number; count > 1; count--) {
            factor = factor * count;
        }

        return factor;
    }

}
Результат метода call() возвращается в Future объекте:
@Test
public void whenTaskSubmitted_ThenFutureResultObtained(){
FactorialTask task = new FactorialTask(7);
Future<Integer> future = executorService.submit(task);

    assertEquals(5040, future.get().intValue());

}
Обработка исключений
Давайте посмотрим, насколько они подходят для обработки исключений.
Runnable
Поскольку в сигнатуре метода не указан блок throws, у нас нет возможности распространять дальше проверяемые исключения.
Callable
Метод call() Callable содержит блок throws Exception, поэтому мы можем легко распространять дальше проверяемые
исключения:
public class FactorialTask implements Callable<Integer> {

    public Integer call() throws InvalidParamaterException {

        if(number < 0) {
            throw new InvalidParamaterException("This should be positive");
        }

    }

}
В случае запуска Callable объекта с использованием ExecutorService исключения собираются в Future объекте. Мы можем
проверить его, выполнив вызов метода Future.get().

Последует ExecutionException, которое оборачивает исходное исключение:

В случае запуска вызываемого объекта с использованием ExecutorService исключения собираются в будущем объекте. Мы можем
проверить это, выполнив вызов метода Future.get().

Это вызовет ExecutionException, которое оборачивает исходное исключение:

```
@Test(expected = ExecutionException.class)
public void whenException_ThenCallableThrowsIt() {

    FactorialCallableTask task = new FactorialCallableTask(-4);
    Future<Integer> future = executorService.submit(task);
    Integer result = future.get().intValue();
}
```

В приведенном выше тесте выдается исключение ExecutionException, поскольку мы передаем недопустимое число. Мы можем
вызвать метод getCause() для этого объекта исключения, чтобы получить исходное проверяемое исключение.

Если мы не вызовем метод get() из Future класса, исключение, вызванное методом call(), не будет возвращено, и задача
по-прежнему будет помечена как выполненная:

В приведенном выше тесте выдается исключение ExecutionException, поскольку мы передаем недопустимое число. Мы можем
вызвать метод getCause() для этого объекта исключения, чтобы получить исходное проверенное исключение.

Если мы не вызовем метод get() будущего класса, исключение, вызванное методом call(), не будет возвращено, и задача
по-прежнему будет помечена как выполненная:

```
@Test
public void whenException_ThenCallableDoesntThrowsItIfGetIsNotCalled(){
FactorialCallableTask task = new FactorialCallableTask(-4);
Future<Integer> future = executorService.submit(task);

    assertEquals(false, future.isDone());
}
```

Приведенный выше тест пройдет успешно, даже несмотря на то, что мы выдали исключение для отрицательных значений
параметра FactorialCallableTask.

## Отличие synchronize, volatile, static, transient

Ключевое слово synchronized используется в Java для обеспечения того, чтобы только один поток исполнял блок кода
одновременно.

- synchronized: Используется для синхронизации доступа к критическим секциям кода, гарантируя, что только один поток
  может
  выполнить этот блок кода в любой момент времени.
- volatile: Обозначает переменную, значение которой может изменяться разными потоками, и обеспечивает видимость
  изменений
  для всех потоков, но не блокирует доступ.
- static: Определяет статические переменные и методы, которые принадлежат классу, а не экземпляру.
- transient: Используется для обозначения полей, которые не должны быть сериализованы.

## Где можно ставить synchronized

## Методы wait и notify

Методы wait и notify
Последнее обновление: 27.04.2018

Иногда при взаимодействии потоков встает вопрос о извещении одних потоков о действиях других. Например, действия одного
потока зависят от результата действий другого потока, и надо как-то известить один поток, что второй поток произвел
некую работу. И для подобных ситуаций у класса Object определено ряд методов:

* wait(): освобождает монитор и переводит вызывающий поток в состояние ожидания до тех пор, пока другой поток не вызовет
  метод notify()

* notify(): продолжает работу потока, у которого ранее был вызван метод wait()

* notifyAll(): возобновляет работу всех потоков, у которых ранее был вызван метод wait()

Все эти методы вызываются только из синхронизированного контекста - синхронизированного блока или метода.

Рассмотрим, как мы можем использовать эти методы. Возьмем стандартную задачу из прошлой темы - "
Производитель-Потребитель" ("Producer-Consumer"): пока производитель не произвел продукт, потребитель не может его
купить. Пусть производитель должен произвести 5 товаров, соответственно потребитель должен их все купить. Но при этом
одновременно на складе может находиться не более 3 товаров. Для решения этой задачи задействуем методы wait() и
notify():

```java
public class Program {

    public static void main(String[] args) {

        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

// Класс Магазин, хранящий произведенные товары
class Store {
    private int product = 0;

    public synchronized void get() {
        while (product < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        product--;
        System.out.println("Покупатель купил 1 товар");
        System.out.println("Товаров на складе: " + product);
        notify();
    }

    public synchronized void put() {
        while (product >= 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        product++;
        System.out.println("Производитель добавил 1 товар");
        System.out.println("Товаров на складе: " + product);
        notify();
    }
}

// класс Производитель
class Producer implements Runnable {

    Store store;

    Producer(Store store) {
        this.store = store;
    }

    public void run() {
        for (int i = 1; i < 6; i++) {
            store.put();
        }
    }
}

// Класс Потребитель
class Consumer implements Runnable {

    Store store;

    Consumer(Store store) {
        this.store = store;
    }

    public void run() {
        for (int i = 1; i < 6; i++) {
            store.get();
        }
    }
}
```

Итак, здесь определен класс магазина, потребителя и покупателя. Производитель в методе run() добавляет в объект Store с
помощью его метода put() 5 товаров. Потребитель в методе run() в цикле обращается к методу get объекта Store для
получения этих товаров. Оба метода Store - put и get являются синхронизированными.

Для отслеживания наличия товаров в классе Store проверяем значение переменной product. По умолчанию товара нет, поэтому
переменная равна 0. Метод get() - получение товара должен срабатывать только при наличии хотя бы одного товара. Поэтому
в методе get проверяем, отсутствует ли товар:

1
while (product<1)
Если товар отсутсвует, вызывается метод wait(). Этот метод освобождает монитор объекта Store и блокирует выполнение
метода get, пока для этого же монитора не будет вызван метод notify().

Когда в методе put() добавляется товар и вызывается notify(), то метод get() получает монитор и выходит из конструкции
while (product<1), так как товар добавлен. Затем имитируется получение покупателем товара. Для этого выводится
сообщение, и уменьшается значение product: product--. И в конце вызов метода notify() дает сигнал методу put()
продолжить работу.

В методе put() работает похожая логика, только теперь метод put() должен срабатывать, если в магазине не более трех
товаров. Поэтому в цикле проверяется наличие товара, и если товар уже есть, то освобождаем монитор с помощью wait() и
ждем вызова notify() в методе get().

И теперь программа покажет нам другие результаты:

```
Производитель добавил 1 товар
Товаров на складе: 1
Производитель добавил 1 товар
Товаров на складе: 2
Производитель добавил 1 товар
Товаров на складе: 3
Покупатель купил 1 товар
Товаров на складе: 2
Покупатель купил 1 товар
Товаров на складе: 1
Покупатель купил 1 товар
Товаров на складе: 0
Производитель добавил 1 товар
Товаров на складе: 1
Производитель добавил 1 товар
Товаров на складе: 2
Покупатель купил 1 товар
Товаров на складе: 1
Покупатель купил 1 товар
Товаров на складе: 0
```

Таким образом, с помощью wait() в методе get() мы ожидаем, когда производитель добавит новый продукт. А после добавления
вызываем notify(), как бы говоря, что на складе освободилось одно место, и можно еще добавлять.

А в методе put() с помощью wait() мы ожидаем освобождения места на складе. После того, как место освободится, добавляем
товар и через notify() уведомляем покупателя о том, что он может забирать товар.