### Stream API: рекомендации лучших собаководов

![pic1.png](../../../picture/stream/pic1.png)
![pic2.png](../../../picture/stream/pic2.png)
![pic3.png](../../../picture/stream/pic3.png)
![pic4.png](../../../picture/stream/pic4.png)
![pic5.png](../../../picture/stream/pic5.png)
![pic6.png](../../../picture/stream/pic6.png)
List.stream нам не поможет, поэтому делаем
IntStream.range - создает поток целых чисел от 0 до list.size, далее mapToObject преобразует в объект где на вход Idx -
целое число.
List.get(i) -быстрая операция
![pic7.png](../../../picture/stream/pic7.png)
В независимости от того в какой структуре хранятся юзеры - мы можем из любой структуры создать стрим, и не будет
накладных расходов.

![pic8.png](../../../picture/stream/pic8.png)
![pic9.png](../../../picture/stream/pic9.png)
Это решение для 3х элементов

Универсальное решение

```java
    public void generate(List<List<String>> input) {
    Supplier<Stream<String>> s = input.stream()             //создаем стрим из внешнего списка
            //Stream<List<String>>                                //стрим списка строк
            .<Supplier<Stream<String>>>map(list -> list::stream) //каждый список мепим на саплаер стрима
            //Stream<Supplier<Stream<String>>>                  //стрим саплаеров стримов строк
            .reduce((sup1, sup2) -> () -> sup1.get()           //редюс на функция саплаеров. Делаем новый саплаер `-> () ->`
                    .flatMap(e1 -> sup2.get().map(e2 -> e1 + e2))) //
            //Optional<Supplier<Stream<String>>>             //редьюс возвращает Optional
            .orElse(() -> Stream.of(""));               // дефолтное поведение когда исходный список абсолютно пуст, выдаем стрим из 1 пустой строки
    s.get().forEach(System.out::println);
}
```

Следующая задача
![pic10.png](../../../picture/stream/pic10.png)
Здесь решение не особо красивое
потому что две последние операции связаны между собой и делают общее дело -
фильтруем по классу, кастуем до требуемого класса

![pic11.png](../../../picture/stream/pic11.png)
Но здесь ломается красота стрим апи - чтение слева направо
Альтернатива

![pic12.png](../../../picture/stream/pic12.png)
Метод select возвращает функцию с элемента на стрим.
Недостаток - затраты на много промежуточных стримов

![pic12.png](../../../picture/stream/pic12.png)
Либа streamEx   
select- здесь написан в качестве библиотеки под stream api
