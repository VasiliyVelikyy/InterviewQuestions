# CyclicBarrier

Cyclicbarrier помогает наладить взаимодействие потоков.

Например, есть трехэтапная задача, которая выполняется в 10 рук (потоков). Сначала надо оторвать обои, потом намазать
клеем, потом приклеить. То есть метод run() состоит из трех действий:

```java

public class Worker implements Runnable {`
    @Override
    public void run() {
        System.out.println("оторвать");
        System.out.println("намазать");
        System.out.println("приклеить");
    }
}

```

К сожалению, если просто запустить 10 потоков, то действия будут выполнены вразнобой: один поток уже приклеил, второй
еще отрывает, третий намазывает и т.д.:

```
оторвать
оторвать
намазать
оторвать
намазать
...
```

Всего 30 строк
А нужно, чтобы первые 10 строк были «оторвать», вторые 10 строк — «намазать», а третьи — «приклеить».

Вариант с CyclicBarrier
Для этого в нужных местах проставляется CyclicBarrier. Точнее, у нас даже два CyclicBarrier — они ставятся в тех местах,
где поток должен приостановиться дождаться остальных потоков для продолжения работы. У нас это после «оторвать» и после
«намазать» — сначала все рабочие отрывают обои, ждут друг друга, и только потом приступают к следующему этапу
«намазать». Аналогично после «намазать» каждый останавливается и ждет, когда остальные 9 закончат мазать, и после этого
все продолжают.

Делается это с помощью команды cyclicBarrier.await():

```java

public class Worker implements Runnable {
    private final CyclicBarrier b1;
    private final CyclicBarrier b2;

    public Worker(CyclicBarrier b1, CyclicBarrier b2) {
        this.b1 = b1;
        this.b2 = b2;
    }

    @Override
    public void run() {

        try {
            System.out.println("оторвать");
            b1.await();
            System.out.println("намазать");
            b2.await();
            System.out.println("приклеить");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

```

Команда выбрасывает исключение, поэтому код окружен try/catch.

При создании CyclicBarrier указывается, сколько потоков участвуют во взаимном ожидании:

CyclicBarrier b1=new CyclicBarrier(10);
Таким образом, весь код с созданием двух CyclicBarrier и запуском 10 потоков выглядит так:

```java

public class Worker implements Runnable {
    private final CyclicBarrier b1;
    private final CyclicBarrier b2;

    public Worker(CyclicBarrier b1, CyclicBarrier b2) {
        this.b1 = b1;
        this.b2 = b2;
    }

    @Override
    public void run() {

        try {
            System.out.println("оторвать");
            b1.await();
            System.out.println("намазать");
            b2.await();
            System.out.println("приклеить");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CyclicBarrier b1 = new CyclicBarrier(10);
        CyclicBarrier b2 = new CyclicBarrier(10);
        IntStream.range(0, 10).forEach((i) ->
                new Thread(new Worker(b1, b2)).start());
    }
}

```

Теперь вывод в консоль выглядит последовательно:

```
оторвать
оторвать
...10 раз
намазать
намазать
...10 раз
приклеить
приклеить
...10 раз
```

CyclicBarrier с barrierAction
Еще при создании барьера можно указать действие, которое выполнится перед перешагиванием барьера. Например, когда обои
оторвали, напишем «1 этап закончен», а когда все намазали, напишем «2 этап закончен».

Это действие (в нашем случае вывод в консоль) указывается во втором аргументе конструктора CyclicBarrier в интерфейсе
Runnable (первый аргумент, как вы помните — число потоков).

```java

CyclicBarrier b1 = new CyclicBarrier(10, () -> {
    System.out.println("1 этап закончен");
});
CyclicBarrier b2 = new CyclicBarrier(10, () -> {
    System.out.println("2 этап закончен");
});

```

Теперь результат будет такой:

```
оторвать
оторвать
...10 раз
1 этап закончен
намазать
намазать
...10 раз
2 этап закончен
приклеить
приклеить
...10 раз
```