package ru.moskalev.sandbox.my_examples.multithreading.join;

/**
 * Но что, если мы хотим, чтобы основной поток ждал завершения выполнения потока — threadTwo
 */
public class JoinExample2 {
    public static void main(String[] args) throws InterruptedException {
        var threadTwo = new Thread(() -> {
            try {
                Thread.sleep(2000);
                int counter = 0;
                for (int i = 0; i < 1000; i++) {
                    counter++;
                }
                var thread = Thread.currentThread().getName();
                System.out.println(thread + " has finished its execution, counter = " + counter);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }, "Counter thread");
        /*Данный пример почти то же самое с предыдущим примером, но с небольшой разницей. Сразу после threadTwo.start() мы добавили вызов метода — join() у потока threadTwo*/
        threadTwo.start();
        threadTwo.join();
        System.out.println("Main method executing");

        /*
        Порядок вывода в этом примере изменился. Сразу после запуска threadTwo, основной поток вызывает методjoin() у потока
         — threadTwo. Это приводит к тому, что основной поток переходит в состояние ожидания и ждет, пока threadTwo не завершит
          свое выполнение. Как видно из вывода, поток — threadTwo завершает выполнение, считает до 1000 и выводит сообщение —
           «Counter has finished its execution, counter = 1000» и заканчивает выполнение. После этого mainThread продолжает
            свое выполнение и выводит следующее сообщение — «Main method executing».

        Вдобавок, если во время выполнения потока — threadTwo возникнет исключение, основной поток продолжит свое
        выполнение аналогично как в случае с успешным выполнением потока — threadTwo, ситуаций deadlock не будет
        */
    }

}
