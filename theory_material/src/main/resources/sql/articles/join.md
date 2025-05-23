# Явные операции соединения

В предложении FROM может быть указана явная операция соединения двух и более таблиц. Среди ряда операций соединения,
описанных в стандарте языка SQL, многими серверами баз данных поддерживается только операция соединения по предикату.
Синтаксис соединения по предикату имеет вид:

```
FROM <таблица 1>
[INNER]
{{LEFT | RIGHT | FULL } [OUTER]} JOIN <таблица 2>
[ON <предикат>]
```

Соединение может быть либо внутренним (INNER), либо одним из внешних (OUTER). Служебные слова INNER и OUTER можно
опускать, поскольку внешнее соединение однозначно определяется его типом — LEFT (левое), RIGHT (правое) или FULL (
полное), а просто JOIN будет означать внутреннее соединение.

Предикат определяет условие соединения строк из разных таблиц. При этом INNER JOIN означает, что в результирующий набор
попадут только те соединения строк двух таблиц, для которых значение предиката равно TRUE. Как правило, предикат
определяет эквисоединение по внешнему и первичному ключам соединяемых таблиц, хотя это не обязательно.

### Пример 5.6.3.

Найти производителя, номер модели и цену каждого компьютера, имеющегося в базе данных:

```sql

SELECT maker,
       Product.model AS model_1,
       PC.model      AS model_2,
       price
FROM Product
         INNER JOIN
     PC ON PC.model = Product.model
ORDER BY maker, model_2;

```

В данном примере в результирующем наборе будут соединяться только те строки из таблиц РС и Product, у которых совпадают
номера моделей.

Для визуального контроля в результирующий набор включен как номер модели из таблицы PC, так и из таблицы Product:

| Maker  | 	model_1	 | model_2 | 	price |
|--------|-----------|---------|--------|
| A	1232 | 	1232     | 	600    |
| A	1232 | 	1232     | 	400    |
| A	1232 | 	1232     | 	350    |
| A	1232 | 	1232     | 	350    |
| A	1233 | 	1233     | 	600    |
| A	1233 | 	1233     | 	950    |
| A	1233 | 	1233     | 	980    |
| A	1233 | 	1233     | 	970    |
| B	1121 | 	1121     | 	850    |
| B	1121 | 	1121     | 	850    |
| B	1121 | 	1121     | 	850    |
| E	1260 | 	1260     | 	350    |

Внешнее соединение **LEFT JOIN** означает, что помимо строк, для которых выполняется условие предиката, в результирующий
набор попадут все остальные строки из первой таблицы (левой). При этом отсутствующие значения столбцов из правой таблицы
будут заменены NULL-значениями.

# Пример 5.6.4

Привести все модели ПК, их производителей и цену:

```sql

SELECT maker, Product.model AS model_1, pc.model AS model_2, price
FROM Product
         LEFT JOIN
     PC ON PC.model = Product.model
WHERE type = 'pc'
ORDER BY maker, PC.model;

```

Обратите внимание на то, что по сравнению с предыдущим примером пришлось использовать предложение WHERE для отбора
только производителей ПК. В противном случае в результирующий набор попали бы также и модели портативных компьютеров, и
принтеров. В рассмотренном ранее примере это условие было бы излишним, так как соединялись только те строки, у которых
совпадали номера моделей, и одной из таблиц была таблица PC, содержащая только модели ПК. В результате выполнения
запроса получим:

| Maker  | 	model_1 | 	model_2 | 	price |
|--------|----------|----------|--------|
| A	1232 | 	1232    | 	600     |
| A	1232 | 	1232    | 	400     |
| A	1232 | 	1232    | 	350     |
| A	1232 | 	1232    | 	350     |
| A	1233 | 	1233    | 	600     |
| A	1233 | 	1233    | 	950     |
| A	1233 | 	1233    | 	980     |
| B	1121 | 	1121    | 	850     |
| B	1121 | 	1121    | 	850     |
| B	1121 | 	1121    | 	850     |
| E	2111 | 	NULL    | 	NULL    |
| E	2112 | 	NULL    | 	NULL    |
| E	1260 | 	1260    | 	350     |

Поскольку моделей 2111 и 2112 из таблицы Product нет в таблице PС, в столбцах из таблицы PС содержится NULL.

Соединение RIGHT JOIN обратно соединению LEFT JOIN, то есть в результирующий набор попадут все строки из второй таблицы,
которые будут соединяться только с теми строками из первой таблицы, для которых выполняется условие соединения. В нашем
случае левое соединение

Product LEFT JOIN PC ON PC.model = Product.model
будет эквивалентно правому соединению

PC RIGHT JOIN Product ON PC.model = Product.model
Запрос же

```sql

SELECT maker, Product.model AS model_1, PC.model AS model_2, price
FROM Product
         RIGHT JOIN
     PC ON PC.model = Product.model
ORDER BY maker, PC.model;

```

даст те же результаты, что и внутреннее соединение, поскольку в правой таблице (PC) нет таких моделей, которые
отсутствовали бы в левой таблице (Product), что вполне естественно для типа связи «один ко многим», которая имеется
между таблицами PC и Product.
Наконец, при полном соединении (FULL JOIN) в результирующую таблицу попадут не только те строки, которые имеют
одинаковые значения в сопоставляемых столбцах, но и все остальные строки исходных таблиц, не имеющие соответствующих
значений в другой таблице. В этих строках все столбцы той таблицы, в которой не было найдено соответствия, заполняются
NULL-значениями. То есть полное соединение представляет собой комбинацию левого и правого внешних соединений. Так,
запрос для таблиц A и B, приведенных в начале главы,

```sql

SELECT A.*, B.*
FROM A
         FULL JOIN
     B ON A.a = B.c;

```

даст следующий результат:

| A	   | b     | 	C	  | d     |
|------|-------|------|-------|
| 1    | 	2	   | NULL | 	NULL |
| 2	   | 1	    | 2	   | 4     |
| NULL | 	NULL | 	3   | 	3    |

Заметим, что это соединение симметрично, то есть A FULL JOIN B эквивалентно B FULL JOIN A. Обратите также внимание на
обозначение A.*, что означает вывести все столбцы таблицы А.

## Ассоциативность и коммутативность соединений

Внутреннее и полное внешнее соединения являются как коммутативными, так и ассоциативными, т.е. для них справедливо
следующее:

```
A [FULL | INNER] JOIN B = B [FULL | INNER] JOIN A
```

и

```
(A [FULL | INNER] JOIN B) [FULL | INNER] JOIN С = A [FULL | INNER] JOIN (B [FULL | INNER] JOIN С) 
```

Очевидно, что левое/правое соединения не коммутативны, т.к.

A LEFT JOIN B = B RIGHT JOIN A
но ассоциативны, например:

```
(A LEFT JOIN B) LEFT JOIN C = A LEFT JOIN (B LEFT JOIN C)
```

С практической точки зрения ассоциативность означает, что мы можем не расставлять скобки, определяющие прядок выполнения
соединений.

Однако закон ассоциативности, справедливый для однотипных соединений, нарушается, если в одном запросе используются
соединения разных типов. Покажем это на примере.

```sql

WITH a(a_id) AS
             (SELECT * FROM (VALUES ('1'), ('2'), ('3')) x(y)),
     b(b_id) AS
             (SELECT * FROM (VALUES ('1'), ('2'), ('4')) x(y)),
     c(c_id) AS
             (SELECT * FROM (VALUES ('5'), ('2'), ('3')) x(y))
SELECT a_id, b_id, c_id
FROM (a LEFT JOIN b ON a_id = b_id)
         INNER JOIN c ON b_id = c_id
UNION ALL
SELECT '', '', ''
UNION ALL
SELECT a_id, b_id, c_id
FROM a
         LEFT JOIN (b INNER JOIN c ON b_id = c_id) ON a_id = b_id;

```

| a_id  | 	b_id | 	c_id |
|-------|-------|-------|
| 2   	 | 2     | 	2    |
|       |       |       |
| 1   	 | NULL  | 	NULL |
| 2     | 	2    | 	2    |
| 3     | 	NULL | 	NULL |

Результаты двух запросов отделены друг от друга пробельной строкой для удобства.

Заметим, что при отсутствии скобок мы получим результат, совпадающий с результатом первого запроса, поскольку соединения
будут выполняться в том порядке, в каком они записаны.