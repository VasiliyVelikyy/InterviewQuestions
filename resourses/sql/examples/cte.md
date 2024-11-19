# Общие табличные выражения (CTE) стр. 1

Чтобы выяснить назначение общих табличных выражений, давайте начнем с примера.

Найти максимальную сумму прихода/расхода среди всех 4-х таблиц базы данных "Вторсырье", а также тип операции, дату и
пункт приема, когда и где она была зафиксирована.

Задачу можно решить, например, следующим способом.

```sql
SELECT inc AS max_sum, type, date, point
FROM ( SELECT inc, 'inc' type, date, point
    FROM Income UNION ALL SELECT inc, 'inc' type, date, point
    FROM Income_o
    UNION ALL
    SELECT out, 'out' type, date, point
    FROM Outcome_o
    UNION ALL
    SELECT out, 'out' type, date, point FROM Outcome ) X
WHERE inc >= ALL ( SELECT inc FROM Income
    UNION ALL
    SELECT inc FROM Income_o
    UNION ALL SELECT out FROM Outcome_o
    UNION ALL SELECT out FROM Outcome );

```

Здесь мы сначала объединяем всю имеющуюся информацию, а затем выбираем только те строки, у которых сумма не меньше, чем
каждая из сумм той же выборки из 4-х таблиц.

Фактически, мы дважды написали код объединений четырех таблиц. Как избежать этого? Можно создать представление, а затем
адресовать запрос уже к нему:

```sql
CREATE VIEW Inc_Out AS
SELECT inc, 'inc' type,
        date, point
        FROM Income
        UNION ALL
SELECT inc, 'inc' type, date, point
FROM Income_o
UNION ALL
SELECT out, 'out' type, date, point
FROM Outcome_o
UNION ALL
SELECT out, 'out' type, date, point
FROM Outcome;
GO
SELECT inc AS max_sum, type, date, point
FROM Inc_Out
WHERE inc >= ALL ( SELECT inc FROM Inc_Out);
```

Так вот, CTE играет роль представления, которое создается в рамках одного запроса и, не сохраняется как объект схемы.
Предыдущий вариант решения можно переписать с помощью CTE следующим образом:

```sql
WITH Inc_Out AS (SELECT inc, 'inc' type, date, point
                FROM Income
                UNION ALL
                SELECT inc, 'inc' type, date, point
                FROM Income_o
                UNION ALL
                SELECT out, 'out' type, date, point
                FROM Outcome_o
                UNION ALL
                SELECT out, 'out' type, date, point
                FROM Outcome )
SELECT inc AS max_sum, type, date, point
FROM Inc_Out
WHERE inc >= ALL ( SELECT inc FROM Inc_Out);
```

Как видите, все аналогично использованию представления за исключением обязательных скобок, ограничивающих запрос;
формально, достаточно лишь заменить CREATE VIEW на WITH. Как и для представления, в скобках после имени CTE может быть
указан список столбцов, если нам потребуется включить их не все из подлежащего запроса и/или переименовать. Например, (я
добавил дополнительно определение минимальной суммы в предыдущий запрос),

```sql

WITH Inc_Out(m_sum, type, date, point) AS (SELECT inc, 'inc' type, date, point
                                            FROM Income
                                            UNION ALL
                                            SELECT inc, 'inc' type, date, point
                                            FROM Income_o
                                            UNION ALL
                                            SELECT out, 'out' type, date, point
                                            FROM Outcome_o
                                            UNION ALL
                                            SELECT out, 'out' type, date, point
                                            FROM Outcome )
SELECT 'max' min_max, *
FROM Inc_Out
WHERE m_sum >= ALL (SELECT m_sum FROM Inc_Out)
UNION ALL
SELECT 'min', *
FROM Inc_Out
WHERE m_sum <= ALL (SELECT m_sum FROM Inc_Out);
```

Общие табличные выражения позволяют существенно уменьшить объем кода, если многократно приходится обращаться к одним и
тем же производным таблицам.

Заметим, что CTE могут использоваться не только с оператором SELECT, но и с другими операторами языка DML. Давайте решим
такую задачу:

Пассажиров рейса 7772 от 11 ноября 2005 года требуется отправить другим ближайшим рейсом, вылетающим позже в тот же день
в тот же пункт назначения.

Т.е. эта задача на обновление записей в таблице Pass_in_trip. Я не буду приводить здесь решение этой задачи, которое не
использует CTE, но вы можете сами это сделать, чтобы сравнить объемы кода двух решений.

Предлагаю пошагово строить решение и представлять результаты в виде запросов на выборку, чтобы вы могли контролировать
результаты, выполняя эти запросы онлайн. Поскольку операторы модификации данных пока запрещены на сайте, я приведу
окончательное решение лишь в самом конце. Начнем с таблицы, которую нужно будет обновить:

```sql

WITH Trip_for_replace AS (SELECT *
                          FROM Pass_in_trip
                          WHERE trip_no = 7772 AND date ='20051129' )
SELECT *
FROM Trip_for_replace;

```

Поскольку CTE играют роль представлений, то их можно в принципе использовать для обновления данных. Слова «в принципе»
означают, что CTE является обновляемым, если выполняются определенные условия, аналогичные условиям обновления
представлений. В частности, в определении должна использоваться только одна таблица без группировки и вычисляемых
столбцов. Отметим, что необходимые условия в нашем случае выполнены.

Теперь нам нужна информация о рейсе 7772 для того, чтобы найти ближайший к нему подходящий рейс. Добавим еще одно CTE в
определение:

```sql

WITH Trip_for_replace AS (SELECT *
                          FROM Pass_in_trip
                          WHERE trip_no = 7772 AND date ='20051129' ), Trip_7772 AS (
SELECT *
FROM Trip
WHERE trip_no=7772 )
SELECT *
FROM Trip_7772;

```

Обратите внимание, что в одном запросе можно определить любое количество общих табличных выражений. И что особенно
важно, CTE может включать ссылку на другое CTE, чем мы, собственно, сейчас и воспользуемся (обратите внимание на ссылку
Trip_7772 в определении Trip_candidates).

```sql

WITH Trip_for_replace AS (SELECT *
                          FROM Pass_in_trip
                          WHERE trip_no = 7772 AND date ='20051129' ), Trip_7772 AS (
SELECT *
FROM Trip
WHERE trip_no=7772 )
    , Trip_candidates AS (
SELECT Trip.*
FROM Trip, Trip_7772
WHERE Trip.town_from+Trip.town_to = Trip_7772.town_from +
    Trip_7772.town_to
  AND Trip.time_out
    > Trip_7772.time_out )
SELECT *
FROM Trip_candidates;

```

Trip_candidates – это табличное выражение, которое определяет кандидатов на замену, а именно, рейсы, которые вылетают
позже, чем 7772, и которые совершаются между теми же городами. Я использую конкатенацию строк town_from+town_to, чтобы
не писать отдельные критерии для пункта отправления и места назначения.

Найдем теперь среди строк-кандидатов наиболее близкий по времени рейс:

```sql

WITH Trip_for_replace AS (SELECT *
                          FROM Pass_in_trip
                          WHERE trip_no = 7772 AND date ='20051129' ), Trip_7772 AS (
SELECT *
FROM Trip
WHERE trip_no=7772 )
    , Trip_candidates AS (
SELECT Trip.*
FROM Trip, Trip_7772
WHERE Trip.town_from+Trip.town_to = Trip_7772.town_from +
    Trip_7772.town_to
  AND Trip.time_out
    > Trip_7772.time_out )
    , Trip_replace AS (
SELECT *
FROM Trip_candidates
WHERE time_out <= ALL (SELECT time_out FROM Trip_candidates) )
SELECT *
FROM Trip_replace;

```

Теперь нам осталось последний оператор SELECT заменить на UPDATE, чтобы решить задачу:

```sql

WITH Trip_for_replace AS (SELECT *
                          FROM Pass_in_trip
                          WHERE trip_no = 7772 AND date ='20051129' ), Trip_7772 AS (
SELECT *
FROM Trip
WHERE trip_no=7772 )
    , Trip_candidates AS (
SELECT Trip.*
FROM Trip, Trip_7772
WHERE Trip.town_from+Trip.town_to = Trip_7772.town_from +
    Trip_7772.town_to
  AND Trip.time_out
    > Trip_7772.time_out )
    , Trip_replace AS (
SELECT *
FROM Trip_candidates
WHERE time_out <= ALL (SELECT time_out FROM Trip_candidates) )
UPDATE Trip_for_replace
SET trip_no = (SELECT trip_no FROM Trip_replace);

```

Здесь мы исходим из довольно естественного предположения о том, что между заданными городами нет двух рейсов, которые бы
отправлялись в одно и то же время в одном направлении. В противном случае, понадобился бы дополнительный критерий для
отбора единственного рейса, т.к. наша цель – обновление данных, а не представление всех возможных кандидатов на замену.

С использованием CTE с оператором DELETE вы можете познакомиться на примере удаления дубликатов строк из таблицы.

Запрос, который мы использовали для удаления дубликатов в SQL Server

```sql

WITH CTE AS (SELECT name, ROW_NUMBER() OVER(PARTITION BY name ORDER BY name) rnk
             FROM T)
DELETE
FROM CTE
WHERE rnk > 1;

```

в PostgreSQL завершится ошибкой:
ОШИБКА:  отношение "cte" не существует

Эта ошибка означает, что мы можем удалять строки из базовых таблиц, но не из CTE. Тем не менее, возможно выполнить
удаление дубликатов одним запросом, используя CTE.

Поступим следующим образом:

Удалим все строки из базовой таблицы, возвращая их в табличное выражение (первое CTE).
Используя результат 1 шага, формируем уникальные строки, которые должны остаться в таблице (второе CTE).
Вставляем строки, полученные на шаге 2 в базовую таблицу.
Воспользуемся таблицей из цитируемого примера, чтобы написать запрос:

```sql

CREATE TABLE T
(
    name varchar(10)
);
INSERT INTO T
VALUES ('John'),
       ('Smith'),
       ('John'),
       ('Smith'),
       ('Smith'),
       ('Tom');

```

Вот и сам запрос

```sql

WITH t_deleted AS
             (
DELETE
FROM T returning *), -- 1 шаг
    t_inserted AS
    (
SELECT name, ROW_NUMBER() OVER(PARTITION BY name ORDER BY name) rnk
FROM t_deleted) -- 2 шаг
INSERT
INTO T
SELECT name
FROM t_inserted
WHERE rnk = 1; -- 3 шаг (сюда мы перенесли условие отбора из 2 шага для сокращения кода)

```

Если теперь выполнить запрос

```sql

SELECT *
FROM T;

```

то получим требуемый результат

| name  |
|-------|
| John  |
| Smith |
| Tom   |
