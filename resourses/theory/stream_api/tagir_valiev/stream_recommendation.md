<https://www.youtube.com/watch?v=vxikpWnnnCU>

### Stream API: рекомендации лучших собаководов

![pic1.png](../../../picture/stream/valiev/stream_recommendation/pic1.png)
![pic2.png](../../../picture/stream/valiev/stream_recommendation/pic2.png)
![pic3.png](../../../picture/stream/valiev/stream_recommendation/pic3.png)
![pic4.png](../../../picture/stream/valiev/stream_recommendation/pic4.png)
![pic5.png](../../../picture/stream/valiev/stream_recommendation/pic5.png)
![pic6.png](../../../picture/stream/valiev/stream_recommendation/pic6.png)
List.stream нам не поможет, поэтому делаем
IntStream.range - создает поток целых чисел от 0 до list.size, далее mapToObject преобразует в объект где на вход Idx -
целое число.
List.get(i) -быстрая операция
![pic7.png](../../../picture/stream/valiev/stream_recommendation/pic7.png)
В независимости от того в какой структуре хранятся юзеры - мы можем из любой структуры создать стрим, и не будет
накладных расходов.

![pic8.png](../../../picture/stream/valiev/stream_recommendation/pic8.png)
![pic9.png](../../../picture/stream/valiev/stream_recommendation/pic9.png)
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
![pic10.png](../../../picture/stream/valiev/stream_recommendation/pic10.png)
Здесь решение не особо красивое
потому что две последние операции связаны между собой и делают общее дело -
фильтруем по классу, кастуем до требуемого класса

![pic11.png](../../../picture/stream/valiev/stream_recommendation/pic11.png)
Но здесь ломается красота стрим апи - чтение слева направо
Альтернатива

![pic12.png](../../../picture/stream/valiev/stream_recommendation/pic12.png)
Метод select возвращает функцию с элемента на стрим.
Недостаток - затраты на много промежуточных стримов

![pic13.png](../../../picture/stream/valiev/stream_recommendation/pic13.png)
Либа streamEx   
select- здесь написан в качестве библиотеки под stream api

![pic14.png](../../../picture/stream/valiev/stream_recommendation/pic14.png)
Решение через функцию
![pic15.png](../../../picture/stream/valiev/stream_recommendation/pic15.png)

![pic16.png](../../../picture/stream/valiev/stream_recommendation/pic16.png)
Реализация если бы не было java 9
![pic17.png](../../../picture/stream/valiev/stream_recommendation/pic17.png)
![pic18.png](../../../picture/stream/valiev/stream_recommendation/pic18.png)

Терминальные операции
![pic19.png](../../../picture/stream/valiev/stream_recommendation/pic19.png)

![pic20.png](../../../picture/stream/valiev/stream_recommendation/pic20.png)
Решение
![pic21.png](../../../picture/stream/valiev/stream_recommendation/pic21.png)

для упрощение рекомендуеться делать импорт всего collectors
import Collecvtors.*
![pic22.png](../../../picture/stream/valiev/stream_recommendation/pic22.png)
![pic23.png](../../../picture/stream/valiev/stream_recommendation/pic23.png)
![pic24.png](../../../picture/stream/valiev/stream_recommendation/pic24.png)
![pic25.png](../../../picture/stream/valiev/stream_recommendation/pic25.png)
Решение через либу
![pic26.png](../../../picture/stream/valiev/stream_recommendation/pic26.png)
Как делать ненадо
![pic27.png](../../../picture/stream/valiev/stream_recommendation/pic27.png)

Придумываем свою функцию
![pic28.png](../../../picture/stream/valiev/stream_recommendation/pic28.png)
![pic29.png](../../../picture/stream/valiev/stream_recommendation/pic29.png)

![pic30.png](../../../picture/stream/valiev/stream_recommendation/pic30.png)
Но здесь нарушение апи, так как подаем на вход statefull объект а нужно stateless

![pic31.png](../../../picture/stream/valiev/stream_recommendation/pic31.png)
Parallel не работает. Так как в reduce должны подавать сочетательный (ассоциативный оператор)закон/
Параллельный поток идет не по порядку элементов
![pic32.png](../../../picture/stream/valiev/stream_recommendation/pic32.png)
Здесь forEachOrder гарантирует порядок элементов

Любым способом
Внешняя либа
![pic33.png](../../../picture/stream/valiev/stream_recommendation/pic33.png)
через дефолтные стримы
![pic34.png](../../../picture/stream/valiev/stream_recommendation/pic34.png)
Более изящное решение
![pic35.png](../../../picture/stream/valiev/stream_recommendation/pic35.png)

Не делайте так
![pic36.png](../../../picture/stream/valiev/stream_recommendation/pic36.png)
И так не делать
![pic37.png](../../../picture/stream/valiev/stream_recommendation/pic37.png)

Красивый коллектор
![pic38.png](../../../picture/stream/valiev/stream_recommendation/pic38.png)
![pic39.png](../../../picture/stream/valiev/stream_recommendation/pic39.png)
![pic40.png](../../../picture/stream/valiev/stream_recommendation/pic40.png)
Задача и решение со сторонней библиотекой
![pic41.png](../../../picture/stream/valiev/stream_recommendation/pic41.png)
Решение в stream
![pic42.png](../../../picture/stream/valiev/stream_recommendation/pic42.png)
Альтернативные библиотеки
![pic43.png](../../../picture/stream/valiev/stream_recommendation/pic43.png)


