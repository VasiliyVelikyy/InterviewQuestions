# Использование в запросе нескольких источников записей стр. 1

Как видно из приведенной в конце предыдущего раздела синтаксической формы оператора SELECT, в предложении FROM
допускается указание нескольких таблиц. Простое перечисление таблиц через запятую практически не используется, поскольку
оно соответствует реляционной операции, которая называется декартовым произведением. То есть в результирующем наборе
каждая строка из одной таблицы будет сочетаться с каждой строкой из другой. Например, для таблиц:

A

| a  | 	b |
|----|----|
| 1	 | 2  |
| 2	 | 1  |

B

| c  | 	d |
|----|----|
| 2	 | 4  |
| 3  | 	3 |

Результат запроса

```sql
SELECT *
FROM A,
     B;
```

будет выглядеть следующим образом:

| a	 | b  | 	c | 	d |
|----|----|----|----|
| 1	 | 2  | 	2 | 	4 |
| 1  | 	2 | 	3 | 	3 |
| 2  | 	1 | 	2 | 	4 |
| 2	 | 1  | 	3 | 	3 |

Поэтому перечисление таблиц, как правило, используется совместно с условием соединения строк из разных таблиц,
указываемым в предложении WHERE. Для приведенных выше таблиц таким условием может быть совпадение значений, скажем, в
столбцах a и c:

```sql
SELECT *
FROM A,
     B
WHERE a = c;
```

Теперь результатом выполнения этого запроса будет следующая таблица:

| a | b | c | d |
|---|---|---|---|
| 2 | 1 | 2 | 4 |

то есть соединяются только те строки таблиц, у которых в указанных столбцах находятся равные значения (эквисоединение).
Естественно, могут быть использованы любые условия, хотя эквисоединение используется чаще всего, поскольку эта операция
воссоздает некую исходную сущность предметной области, декомпозированную на две других в результате процедуры
нормализации в процессе построения логической модели.

Если разные таблицы имеют столбцы с одинаковыми именами, то для однозначности требуется использовать точечную нотацию,
которая называется уточнением имени столбца:

```<имя таблицы>.<имя столбца>```

В тех случаях, когда это не вызывает неоднозначности, использование данной нотации не является обязательным.

### Пример 5.6.1

Найти номер модели и производителя ПК, имеющих цену менее $600:

```sql
SELECT DISTINCT PC.model, maker
FROM PC,
     Product
WHERE PC.model = Product.model
  AND price < 600;
```

В результате каждая модель одного и того же производителя выводится только один раз:

| model | 	maker |
|-------|--------|
| 1232	 | A      |
| 1260  | 	E     |

Иногда в предложении FROM требуется указать одну и ту же таблицу несколько раз. В этом случае обязательным является
переименование.

### Пример 5.6.2

Вывести пары моделей, имеющих одинаковые цены:

```sql
SELECT DISTINCT A.model AS model_1, B.model AS model_2
FROM PC AS A,
     PC B
WHERE A.price = B.price
  AND A.model < B.model;
```

Здесь условие a.model < b.model используется для того, чтобы не выводились одинаковые пары, отличающиеся только
перестановкой, например: {1232, 1233} и {1233, 1232}. DISTINCT применяется для того, чтобы исключить одинаковые строки,
поскольку в таблице PC имеются модели с одинаковыми номерами по одной и той же цене. В результате получим следующую
таблицу:

| model_1 | model_2 |
|---------|---------|
| 1232    | 	1233   |
| 1232    | 	1260   |

Переименование также является обязательным, если в предложении FROM используется подзапрос, так как, в противном случае,
у нас нет возможности уточнения имени столбца из подзапроса. Так, первый пример можно переписать следующим образом:

```sql

SELECT DISTINCT PC.model, maker
FROM PC,
     (SELECT maker, model
      FROM Product) AS Prod
WHERE PC.model = Prod.model
  AND price < 600;

```

Обратите внимание, что в этом случае в других предложениях оператора SELECT уже нельзя использовать квалификатор
Product, поскольку таблица Product уже не используется. Вместо него используется псевдоним Prod. Кроме того, ссылаться
извне теперь можно только на те столбцы таблицы Product, которые перечислены в подзапросе.

За псевдонимом производного табличного выражения может в скобках стоять список имен столбцов, которые будут
использоваться вместо имен табличного выражения. Порядок имен должен, естественно, соответствовать списку столбцов
табличного выражения (в нашем случае - списку в предложении SELECT). Это способ позволяет избежать неоднозначности имен
и, как следствие, необходимости их уточнения. Вот как может выглядеть предыдущий пример:

```sql

SELECT DISTINCT model, maker
FROM PC,
     (SELECT maker, model
      FROM Product) AS Prod(maker, model_1)
WHERE model = model_1
  AND price < 600;

```