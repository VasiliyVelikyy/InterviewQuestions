# Получение итоговых значений

Как узнать количество моделей ПК, выпускаемых тем или иным поставщиком? Как определить среднее значение цены на
компьютеры, имеющие одинаковые технические характеристики? На эти и многие другие вопросы, связанные с некоторой
статистической информацией, можно получить ответы при помощи итоговых (агрегатных) функций. Стандартом предусмотрены
следующие агрегатные функции:

| название | 	описание                                            |
|----------|------------------------------------------------------|
| COUNT(*) | 	Возвращает количество строк источника записей       |
| COUNT	   | Возвращает количество значений в указанном столбце   |
| SUM      | 	Возвращает сумму значений в указанном столбце       |
| AVG	     | Возвращает среднее значение в указанном столбце      |
| MIN      | 	Возвращает минимальное значение в указанном столбце |
| MAX	     | Возвращает максимальное значение в указанном столбце |

Все эти функции возвращают единственное значение. При этом функции COUNT, MIN и MAX применимы к данным любого типа, в то
время как SUM и AVG используются только для данных числового типа. Разница между функцией COUNT(*) и COUNT(имя столбца |
выражение) состоит в том, что вторая (как и остальные агрегатные функции) при подсчете не учитывает NULL-значения.

Пример 5.5.1

Найти минимальную и максимальную цену на персональные компьютеры:

```sql

SELECT MIN(price) AS Min_price,
       MAX(price) AS Max_price
FROM PC;
Результатом
будет единственная строка, содержащая агрегатные значения:

```

| Min_price | 	Max_price |
|-----------|------------|
| 350.0	    | 980.0      |

Пример 5.5.2

Найти имеющееся в наличии количество компьютеров, выпущенных производителем А

```sql

SELECT COUNT(*) AS Qty
FROM PC
WHERE model IN (SELECT model
                FROM Product
                WHERE maker = 'A');
В
результате получим


```

| Qty |
|-----|
| 8   |

Пример 5.5.3

Если же нас интересует количество различных моделей, выпускаемых производителем А, то запрос можно сформулировать
следующим образом (пользуясь тем фактом, что в таблице Product номер модели - столбец model - является первичным ключом
и, следовательно, не допускает повторений):

```sql

SELECT COUNT(model) AS Qty_model
FROM Product
WHERE maker = 'A';

```

| Qty_model |
|-----------|
| 7         |

Пример 5.5.4

Найти количество имеющихся различных моделей ПК, выпускаемых производителем А.

Запрос похож на предыдущий, в котором требовалось определить общее число моделей, выпускаемых производителем А. Здесь же
требуется найти число различных моделей в таблице РС (то есть имеющихся в продаже).

Для того чтобы при получении статистических показателей использовались только уникальные значения, при аргументе
агрегатных функций можно применить параметр DISTINCT. Другой параметр - ALL - задействуется по умолчанию и предполагает
подсчет всех возвращаемых (не NULL) значений в столбце. Оператор

```sql

SELECT COUNT(DISTINCT model) AS Qty
FROM PC
WHERE model IN (SELECT model
                FROM Product
                WHERE maker = 'A');

```

даст следующий результат

| Qty |
|-----|
| 2   |

Если же нам требуется получить количество моделей ПК, производимых каждым производителем, то потребуется использовать
предложение GROUP BY, синтаксически следующего после предложения WHERE.