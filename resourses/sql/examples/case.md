# Оператор CASE

### Пусть требуется вывести список всех моделей ПК с указанием их цены. При этом если модель отсутствует в продаже (ее нет в таблице РС), то вместо цены вывести текст «Нет в наличии».

Список всех моделей ПК с ценами можно получить с помощью запроса:

```sql

SELECT DISTINCT Product.model, price
FROM Product
         LEFT JOIN
     PC ON Product.model = PC.model
WHERE product.type = 'pc';

```

В результирующем наборе отсутствующая цена будет заменена NULL-значением:

| Model | 	price |
|-------|--------|
| 1121	 | 850    |
| 1232	 | 350    |
| 1232	 | 400    |
| 1232	 | 600    |
| 1233	 | 600    |
| 1233	 | 950    |
| 1233	 | 980    |
| 1260	 | 350    |
| 2111	 | NULL   |
| 2112	 | NULL   |

Чтобы заменить NULL-значения нужным текстом, можно воспользоваться оператором CASE:

```sql

SELECT DISTINCT product.model,
                CASE
                    WHEN price IS NULL
                        THEN 'Нет в наличии'
                    ELSE CAST(price AS CHAR(20))
                    END price
FROM Product
         LEFT JOIN
     PC ON Product.model = PC.model
WHERE product.type = 'pc';

```

Оператор CASE в зависимости от указанных условий возвращает одно из множества возможных значений. В нашем примере
условием является проверка на NULL. Если это условие выполняется, то возвращается текст «Нет в наличии», в противном
случае (ELSE) возвращается значение цены.

Здесь есть один принципиальный момент. Поскольку результатом оператора SELECT всегда является таблица, то все значения
любого столбца должны иметь один и тот же тип данных (с учетом неявного приведения типов). Поэтому мы не можем наряду с
ценой (числовой тип) выводить символьную константу. Вот почему к полю price применяется преобразование типов, чтобы
привести его значения к символьному представлению. В результате получим:

| model | 	price         |
|-------|----------------|
| 1121  | 	850           |
| 1232  | 	350           |
| 1232  | 	400           |
| 1232  | 	600           |
| 1233  | 	600           |
| 1233  | 	950           |
| 1233  | 	980           |
| 1260  | 	350           |
| 2111  | 	Нет в наличии |
| 2112  | 	Нет в наличии |

Оператор CASE может быть использован в одной из двух синтаксических форм записи:

1-я форма:

```
CASE <проверяемое выражение>
WHEN <сравниваемое выражение 1>
THEN <возвращаемое значение 1>
…
WHEN <сравниваемое выражение N>
THEN <возвращаемое значение N>
[ELSE <возвращаемое значение>]
END
```

2-я форма:

```
CASE
WHEN <предикат 1>
THEN <возвращаемое значение 1>
…
WHEN <предикат N>
THEN <возвращаемое значение N>
[ELSE <возвращаемое значение>]
END
```

Все предложения WHEN должны иметь одинаковую синтаксическую форму, то есть нельзя смешивать первую и вторую формы. При
использовании первой синтаксической формы условие WHEN удовлетворяется, как только значение проверяемого выражения
станет равным значению выражения, указанного в предложении WHEN. При использовании второй синтаксической формы условие
WHEN удовлетворяется, как только предикат принимает значение TRUE. При удовлетворении условия оператор CASE возвращает
значение, указанное в соответствующем предложении THEN. Если ни одно из условий WHEN не выполнилось, то будет
использовано значение, указанное в предложении ELSE. При отсутствии ELSE, будет возвращено NULL-значение. Если
удовлетворены несколько условий, то будет возвращено значение предложения THEN первого из них, так как остальные просто
не будут проверяться.

В приведенном выше примере была применена вторая форма оператора CASE.

Заметим, что для проверки на NULL стандарт предлагает более короткую форму — оператор COALESCE. Он имеет произвольное
число параметров и возвращает значение первого из них, отличного от NULL. Для двух параметров оператор COALESCE(A, B)
эквивалентен следующему оператору CASE:

```
CASE
    WHEN A IS NOT NULL
    THEN A
    ELSE B
END
```

Решение рассмотренного выше примера при использовании оператора COALESCE можно переписать следующим образом:

```sql

SELECT DISTINCT Product.model,
                COALESCE(CAST(price AS CHAR(20)), 'Нет в наличии') price
FROM Product
         LEFT JOIN
     PC ON Product.model = PC.model
WHERE Product.type = 'pc';

```

Применение первой синтаксической формы оператора CASE можно продемонстрировать на следующем примере.

### Пример 5.10.1

Вывести все имеющиеся модели ПК с указанием цены. Отметить самые дорогие и самые дешевые модели.

```sql
SELECT DISTINCT model,
                price,
                CASE price
                    WHEN (SELECT MAX(price)
                          FROM PC)
                        THEN 'Самый дорогой'
                    WHEN (SELECT MIN(price)
                          FROM PC)
                        THEN 'Самый дешевый'
                    ELSE 'Средняя цена'
                    END comment
FROM PC
WHERE price IS NOT NULL
ORDER BY price;

```

В результате выполнения запроса получим:

| model	 | price	 | comment        |
|--------|--------|----------------|
| 1232   | 	350.0 | 	Самый дешевый |
| 1260   | 	350.0 | 	Самый дешевый |
| 1232   | 	400.0 | 	Средняя цена  |
| 1232   | 	600.0 | 	Средняя цена  |
| 1233   | 	600.0 | 	Средняя цена  |
| 1121   | 	850.0 | 	Средняя цена  |
| 1233   | 	950.0 | 	Средняя цена  |
| 1233   | 	980.0 | 	Самый дорогой |

Оператор CASE может быть использован не только в предложении SELECT. Здесь вы можете найти другие примеры его
использования.
Рассмотрим еще несколько примеров.

### Посчитать количество рейсов из Ростова в Москву, и количество рейсов, выполняемых в остальные города.

Здесь мы можем воспользоваться вычисляемым столбцом, по значениям которого будем выполнять группировку:

```sql
SELECT flag, COUNT(*) qty
FROM (SELECT CASE WHEN town_to = 'Moscow' THEN 'Moscow' ELSE 'Other' END flag
      FROM Trip
      WHERE town_from = 'Rostov') X
GROUP BY flag;

```

| flag   | 	qty |
|--------|------|
| Moscow | 	4   |
| Other  | 	2   |

### Посчитать общее количество рейсов из Ростова и количество рейсов, пунктом назначения которых не является Москва.

В этой задаче тоже требуется выполнить агрегацию по двум выборкам, при этом одна из выборок является подмножеством
второй. Поэтому здесь напрямую не подойдёт вычисляемый столбец, по которому можно выполнить группировку. Это годилось
для решения предыдущей задачи, когда множество делилось на собственные непересекающиеся подмножества, по каждому из
которых требовалось выполнить агрегацию.

Для решения данной задачи мы можем посчитать количество по всему множеству и использовать подзапрос для подсчета
значений в подмножестве (второе обращение к таблице) или использовать CASE в сочетании с агрегатной функцией, чтобы
избежать повторного чтения таблицы. Давайте посмотрим, как оценит оптимизатор эти варианты.

Использование подзапроса

```sql
SELECT COUNT(*)                    total,
       (SELECT COUNT(*)
        FROM Trip
        WHERE town_from = 'Rostov'
          AND town_to <> 'Moscow') non_moscow
FROM Trip
WHERE town_from = 'Rostov';
```

Использование CASE с агрегатной функцией

```sql
SELECT COUNT(*)                                             total_qty,
       SUM(CASE WHEN town_to <> 'Moscow' THEN 1 ELSE 0 END) non_moscow
FROM Trip
WHERE town_from = 'Rostov';
```

Результат, естественно, будет одинаков:

| total | 	non_moscow |
|-------|-------------|
| 6     | 	2          |

а вот стоимость второго запроса, как и ожидалось, оказалась вдвое ниже.

Вы можете сравнить реальное время выполнения, если сгенерируете достаточный объём данных.

Второй вариант можно записать более компактно, если использовать функцию NULLIF - сокращенный вариант частного случая
использования CASE:

```sql
SELECT COUNT(*)                         total_qty,
       COUNT(NULLIF(town_to, 'Moscow')) non_moscow
FROM Trip
WHERE town_from = 'Rostov';
```

Функция NULLIF возвращает NULL, если её аргументы равны, или первый аргумент в противном случае.

В решении используется тот факт, что агрегатные функции не учитывают NULL-значения, которые появляются в аргументе
функции COUNT тогда, когда город прибытия равен 'Moscow'.

Начиная с версии 2012, в SQL Server появилась функция IIF, хорошо известная тем, кто использует VBA. Эта функция
является альтернативой выражению CASE в MS Access и имеет следующий синтаксис:

```IIF(<условие>, <выражение, если условие истинно>, <выражение, если условие не истинно>)```
Функция возвращает результат вычисления выражения из второго аргумента, если условие есть TRUE; в противном случае
возвращается результат вычисления выражения из третьего аргумента. Таким образом, функция

```IIF(condition, expression_1, expression_2)```
эквивалентна следующему выражению CASE:
```CASE WHEN condition THEN expression_1 ELSE expression_2 END```
С помощью функции IIF мы можем переписать решение первой задачи следующим образом:

```sql
SELECT DISTINCT product.model,
                IIF(price IS NULL, N'Нет в наличии', CAST(price AS CHAR(20))) price
FROM Product
         LEFT JOIN
     PC ON Product.model = PC.model
WHERE product.type = 'PC';

```

В том случае, если вариантов ветвления больше двух, можно использовать вложенные функции IIF. Например, для решения
задачи 5.10.1 можно использовать такой запрос:

```sql
SELECT DISTINCT model,
                price,
                IIF(price = (SELECT MAX(price) FROM PC), N'Самый дорогой',
                    IIF(price = (SELECT MIN(price) FROM PC), N'Самый дешевый', N'Средняя цена')) comment
FROM PC
ORDER BY price;
```

Если так и дальше пойдет, то скоро в T-SQL появится оператор SWITCH. :-)