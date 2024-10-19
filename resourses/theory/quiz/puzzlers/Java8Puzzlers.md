# Что выведет код

A. молоко/хлеб/колбаса
B. молоко/хлеб/колбаса/яйца, яйца ещё!
C. молоко/хлеб/колбаса/ConcurrentModificationException
D. ConcurrentModificationException

```java
 private static void task1() {
    List<String> list = new ArrayList<>();
    list.add("молоко");
    list.add("хлеб");
    list.add("колбаса");
    Stream<String> stream = list.stream();
    list.add("яйца, яйца ещё!");
    stream.forEach(System.out::println);
}
```

Ответ- lateBinding - все изменения в стриме проигрываются только тогда, когда происходит терминальная операция

# Что выведет код

    A. молоко/хлеб/колбаса
    B. молоко/хлеб/колбаса/яйца, яйца ещё!
    C. молоко/хлеб/колбаса/ConcurrentModificationException
    D. молоко/хлеб/яйца, яйца ещё!

```java
    private static void task2() {
    List<String> list = new ArrayList<String>();
    list.add("молоко");
    list.add("хлеб");
    list.add("колбаса");
    list = list.subList(0, 2); //не надо колбасу!
    Stream<String> stream = list.stream();
    list.add("яйца, яйца ещё!");
    stream.forEach(System.out::println);
}
```

Здесь был баг так как sublist().spliterator was is not late binding

# В чём разница между строчками 1 и 2?

    A. 1 компилируется, 2 нет
    B. 2 компилируется, 1 нет
    C. Что в лоб, что по лбу, обе нормально сработают.
    D. Без разницы, обе не компилируются.

```java
    public void killAll() {
    ExecutorService ex = Executors.newSingleThreadExecutor();
    List<String> sentence = Arrays.asList("Казнить");
    ex.submit(() -> Files.write(Paths.get("Приговор.txt"), sentence)); // 1
    ex.submit(() -> {
        Files.write(Paths.get("Приговор.txt"), sentence);
    }); // 2
}
```

Метод write кидает check IOException и метод возвращает Path
Нужно разобраться как ExecutorService понимает что это Callable(возвращает V и кидает exception)или Runnable(не
возвращает B).
В 1 случае если мы не используем фигурных скобок - ExecutorService по return type понимает что это Callable а в нем
exception продекларирован
В 2 случае поставили фигурные скобки. Если мы хотим что-то вернуть мы должны написать возврат, но мы не
написали. Сервис определяет это как Runnable - у него Exception не продекларирован- и требует обработки try catch

}