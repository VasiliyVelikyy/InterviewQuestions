<https://www.youtube.com/watch?v=vxikpWnnnCU>

### Stream API: рекомендации лучших собаководов

![pic1.png](media/pic1.png)
![pic2.png](media/pic2.png)
![pic3.png](media/pic3.png)
![pic4.png](media/pic4.png)
![pic5.png](media/pic5.png)
![pic6.png](media/pic6.png)
List.stream нам не поможет, поэтому делаем
IntStream.range - создает поток целых чисел от 0 до list.size, далее mapToObject преобразует в объект где на вход Idx -
целое число.
List.get(i) -быстрая операция
![pic7.png](media/pic7.png)
В независимости от того в какой структуре хранятся юзеры - мы можем из любой структуры создать стрим, и не будет
накладных расходов.

![pic8.png](media/pic8.png)
![pic9.png](media/pic9.png)
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
![pic10.png](media/pic10.png)
Здесь решение не особо красивое
потому что две последние операции связаны между собой и делают общее дело -
фильтруем по классу, кастуем до требуемого класса

![pic11.png](media/pic11.png)
Но здесь ломается красота стрим апи - чтение слева направо
Альтернатива

![pic12.png](media/pic12.png)
Метод select возвращает функцию с элемента на стрим.
Недостаток - затраты на много промежуточных стримов

![pic13.png](media/pic13.png)
Либа streamEx   
select- здесь написан в качестве библиотеки под stream api

![pic14.png](media/pic14.png)
Решение через функцию
![pic15.png](media/pic15.png)

![pic16.png](media/pic16.png)
Реализация если бы не было java 9
![pic17.png](media/pic17.png)
![pic18.png](media/pic18.png)

Терминальные операции
![pic19.png](media/pic19.png)

![pic20.png](media/pic20.png)
Решение
![pic21.png](media/pic21.png)

для упрощение рекомендуеться делать импорт всего collectors
import Collecvtors.*
![pic22.png](media/pic22.png)
![pic23.png](media/pic23.png)
![pic24.png](media/pic24.png)
![pic25.png](media/pic25.png)
Решение через либу
![pic26.png](media/pic26.png)
Как делать ненадо
![pic27.png](media/pic27.png)

Придумываем свою функцию
![pic28.png](media/pic28.png)
![pic29.png](media/pic29.png)

![pic30.png](media/pic30.png)
Но здесь нарушение апи, так как подаем на вход statefull объект а нужно stateless

![pic31.png](media/pic31.png)
Parallel не работает. Так как в reduce должны подавать сочетательный (ассоциативный оператор)закон/
Параллельный поток идет не по порядку элементов
![pic32.png](media/pic32.png)
Здесь forEachOrder гарантирует порядок элементов

Любым способом
Внешняя либа
![pic33.png](media/pic33.png)
через дефолтные стримы
![pic34.png](media/pic34.png)
Более изящное решение
![pic35.png](media/pic35.png)

Не делайте так
![pic36.png](media/pic36.png)
И так не делать
![pic37.png](media/pic37.png)

Красивый коллектор
![pic38.png](media/pic38.png)
![pic39.png](media/pic39.png)
![pic40.png](media/pic40.png)
Задача и решение со сторонней библиотекой
![pic41.png](media/pic41.png)
Решение в stream
![pic42.png](media/pic42.png)
Альтернативные библиотеки
![pic43.png](media/pic43.png)
