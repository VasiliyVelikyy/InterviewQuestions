# Еще раз о подзапросах

Заметим, что в общем случае запрос возвращает множество значений. Поэтому использование подзапроса в предложении WHERE
без предикатов EXISTS, IN, ALL и ANY, которые дают булево значение, может привести к ошибке времени выполнения запроса.

Пример 5.8.3

Найти модели и цены ПК, стоимость которых превышает минимальную стоимость портативных компьютеров:

```sql

SELECT DISTINCT model, price
FROM PC
WHERE price > (SELECT MIN(price)
               FROM Laptop);

```

Этот запрос вполне корректен, так как скалярное значение price сравнивается с подзапросом, который возвращает
единственное значение. В результате получим четыре модели ПК:

| model | price |
|-------|-------|
| 1121  | 850   |
| 1233  | 950   |
| 1233  | 970   |
| 1233  | 980   |

Однако, если в ответ на вопрос «найти модели и цены ПК, стоимость которых совпадает со стоимостью портативных
компьютеров» написать следующий запрос

```sql

SELECT DISTINCT model, price
FROM PC
WHERE price = (SELECT price
               FROM Laptop);

```

то при выполнении последнего мы можем получить такое сообщение об ошибке:

```Subquery returned more than 1 value. This is not permitted when the subquery follows =, !=, <, <= , >, >= or when the subquery is used as an expression.```

(«Подзапрос вернул более одного значения. Это не допускается в тех случаях, когда подзапрос следует
после =, !=, <, <=, >, >= или когда подзапрос используется в качестве выражения».)

Эта ошибка будет возникать при сравнении скалярного значения с подзапросом, который возвращает более одного значения.

Подзапросы, в свою очередь, также могут содержать вложенные запросы.

С другой стороны, подзапрос, возвращающий множество строк и содержащий несколько столбцов, вполне естественно может
использоваться в предложении FROM. Это, например, позволяет ограничить набор столбцов и/или строк при выполнении
операции соединения таблиц.

Пример 5.8.4

Вывести производителя, тип, модель и частоту процессора для Портативных компьютеров, частота процессора которых
превышает 600 МГц.

Этот запрос может быть сформулирован, например, следующим образом:

```sql

SELECT prod.maker, lap.*
FROM (SELECT 'laptop' AS type, model, speed
      FROM laptop
      WHERE speed > 600) AS lap
         INNER JOIN
     (SELECT maker, model
      FROM product) AS prod ON lap.model = prod.model;

```

В результате получим:

| maker | type   | model | speed |
|-------|--------|-------|-------|
| A     | laptop | 1752  | 750   |
| B     | laptop | 1750  | 750   |

Наконец, подзапросы могут присутствовать в предложении SELECT. Это иногда позволяет весьма компактно сформулировать
запрос.

Пример 5.8.5

Найти разницу между средними значениями цены портативных компьютеров и ПК, то есть насколько в среднем портативный
компьютер стоит дороже, чем ПК.

Здесь вообще можно обойтись одним предложением SELECT в основном запросе:

```sql

SELECT (SELECT AVG(price)
        FROM Laptop) -
       (SELECT AVG(price)
        FROM PC) AS dif_price;

```

В результате получим

| dif_price |
|-----------|
| 328.3333  |