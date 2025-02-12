**### ЗАДАНИЕ 1

1) Создать запросы на выборку данных производящих выбор всех строк из таблиц, выбор нескольких строк с помощьюпредиката
   WHERE с различными операторами: BETWEEN, LIKE, AND, OR, NOT >, <, =, <> (не менее 8-ми запросов).

   Решение:

    ```sql
    --1
    SELECT *
    FROM offerts
    WHERE dt_sys BETWEEN TO_DATE(‘dd / mm / yyyy’, ’10 / 10 / 2024’)
              AND TRUNC(sysdate);
    
    --2
    SELECT *
    FROM print
    WHERE NAME LIKE ‘_ussia%’;
    
    --3
    SELECT *
    FROM print
    WHERE name LIKE ‘_ussia%’ AND t-clnt =1;
    
    --4
    SELECT *
    FROM print
    WHERE name LIKE ‘_ussia%’ OR t-clnt =1;
    
    --5
    SELECT *
    FROM offerts
    WHERE price > 1000;
    
    --6
    SELECT *
    FROM offerts
    WHERE price < 1000;
    
    --7
    SELECT *
    FROM offerts
    WHERE price = 1000;
    
    --8
    SELECT *
    FROM offerts
    WHERE price <> 0;

    ```

2) Создать запросы, производящие соединение таблиц: внутреннее, левое внешнее, правое внешнее, полное внешнее,
   декартовое произведение (не менее 4-х запросов).

    ```sql
    
    --1
    SELECT o.*
    FROM owner o
             INNER JOIN client c ON o.t_client = c.c_clnt;
    
    --2
    SELECT o.*, c.value
    FROM owner o
             LEFT JOIN client c ON o.t_client = c.c_clnt;
    
    --3
    SELECT *
    FROM owner o
             RIGHT JOIN print p ON o.id = p.owner;
    
    --4
    SELECT *
    FROM owner o
             RIGHT JOIN print p ON o.id = p.owner;
    
    --5
    SELECT *
    FROM owner,
         print;
    
    ```

3) С помощью оператора SELECT произвести следующие реляционные операции: объединение, разность, пересечение (не менее
   2-х запросов на каждую операцию).

    ```sql
    
    --1
    SELECT *
    FROM print
    WHERE owner = 1
    UNION ALL
    SELECT *
    FROM print
    WHERE owner = 2;
    
    --2
    SELECT *
    FROM print MINUS
    SELECT *
    FROM print
    WHERE owner = 1;
    
    --3
    SELECT owner
    FROM print
    WHERE name LIKE ‘_ussia%’
    UNION
    SELECT owner
    FROM print
    WHERE index > 333;
    ```

4) Создать запросы, содержащие подзапросы и операторы IN, ANY, ALL (не менее 4-х запросов).

    ```sql
    --1
    SELECT o.*
    FROM offerts o
    WHERE o.price IN (SELECT AVG(o1.price)
                      FROM offerts o1
                      UNION ALL
                      SELECT MAX(o2.price)
                      FROM offerts o2);
    
    --2
    SELECT o.name, COUNT(*)
    FROM offerts o
    GROUP BY name
    HAVING price > ANY (SELECT o1.price
                        FROM offerts o1
                        WHERE price > 1000);
    
    --3
    SELECT o.name, COUNT(*)
    FROM offerts o
    GROUP BY name
    HAVING o.price > ALL (SELECT o1.price
                          FROM offerts o1
                          WHERE price > 1000
                            AND price <= 500);
    
    --4
    SELECT p.*
    FROM print p
    WHERE p.name IN (‘NICE_day’, ‘FUTURE’);
    
    ```

5) Создать запросы, содержащие группировку строк с применением агрегатных функций: SUM, COUNT, MIN, MAX, AVG (не менее
   2-х запросов).

    ```sql
    --1
    SELECT COUNT(*)
    FROM client
    WHERE c_clnt = 1;
    --2
    SELECT COUNT(*)
    FROM offerts o
    WHERE o.price >= (SELECT AVG(o1.price) FROM offerts o1);
    ```

6) Создать запросы производящих изменение значение во всех строках таблицы и в некоторых строках таблицы (не менее 2-х
   запросов).

    ```sql
    --1
    UPDATE offerts
    SET price = 1000
    WHERE price = 950;
    
    --2
    UPDATE offerts
    SET price = price * 2;
    
    ```

7) Создать запросы производящие удаление некоторых строк в таблицы и всех строк таблицы.

Решение:

```sql

--1
TRUNCATE TABLE client;
--2
DELETE
FROM offerts
WHERE price <= 200;

```

### ЗАДАНИЕ 2

1 В соответствии с выбранным вариантом задания, создать не менее 2
процедур обеспечивающих логику программы, описанную в лабораторной работе №1 (функции, которая выполняет база данных).

Решение:
1.1)

```sql
CREATE
OR REPLACE PROCEDURE UPPER_PERSENT_PRICE (i_id_offer NUMBER, i_persent NUMBER)
IS
lvValue NUMBER;
BEGIN
-- Процедура повышает цену оферты на заданное количество процентов 

-- расчет процента
SELECT price * i_persent / 100
INTO lvValue
FROM offerts
WHERE id = i_id_offer;

UPDATE offerts
SET price = price + lvValue
WHERE id = i_id_offer;

END UPPER_PERSENT_PRICE;
```

1.2)

```sql


CREATE
OR REPLACE PROCEDURE UPPER_PRICE (i_value NUMBER)
IS
lvValue NUMBER;
BEGIN
/* Процедура повышает цену оферт на заданную сумму*/

UPDATE offerts
SET price = price + i_value;

END UPPER_PRICE; 
```

2 Создать 2 вспомогательные функции, которые необходимо вызывать
из тела процедуры.

2.1)

```sql
CREATE
OR REPLACE FUNCTION GET_NUMBER_EXPENSIVE () RETURN NUMBER
IS
lvValue NUMBER;
BEGIN

--Функция возвращает количество офферт иностранных изданий, превышающих средную цену 

SELECT COUNT(*)
INTO lvValue
FROM offerts o
         INNER JOIN subscriptions s ON o.id = s.id_subscribe
         INNER JOIN print p ON s.id_print = p.id
         INNER JOIN owner ow ON p.owner = ow.id
WHERE o.price > (SELECT AVG(o1.price) FROM offerts o1)
  AND ow.t_clnt = 6;

RETURN lvValue;

END GET_NUMBER_EXPENSIVE;
```

2.2)

```sql


CREATE
OR REPLACE FUNCTION EXISTS_FOREIGNER () RETURN NUMBER
IS
lvAnswer  BOOLEAN := FALSE;
lvCount
NUMBER;
BEGIN
/* Функция проверяет есть ли владельцы иностранных изданий */

SELECT COUNT(*)
INTO lvCount
FROM owner o
WHERE o.t_clnt = 6; -- владелец иностранных изданий

IF
lvCount > 0  THEN
lvAnswer := TRUE;
END IF;

RETURN lvAnswer;

END EXISTS_FOREIGNER; 
```

```sql


CREATE
OR REPLACE PROCEDURE SETTLEMENT ()
IS
lvValue NUMBER;
BEGIN
/* Процедура урегулирования цен на инсотранные издания*/

IF
EXISTS_FOREIGNER THEN
IF GET_NUMBER_EXPENSIVE > 20
UPDATE offerts
SET price = price - 100
WHERE id IN (SELECT o.id
             FROM offerts o
                      INNER JOIN subscriptions s ON o.id = s.id_subscribe
                      INNER JOIN print p ON s.id_print = p.id
                      INNER JOIN owner ow ON p.owner = ow.id
             WHERE o.price >= (SELECT AVG(o1.price) FROM offerts o1)
               AND ow.t_clnt = 6);
END IF;
END IF;

END SETTLEMENT;

```

### ЗАДАНИЕ 1

1) Создать обработку исключительных ситуации в процедурах и функциях, определенных в лабораторной работе №2.
2) В каждой процедуре или функции должны быть обработаны по одной пользовательской и системной исключительной ситуации.
   Пользовательские исключительные ситуации должны быть инициированы с помощью оператора RAISE.
3) В каждой процедуре или функции вывести сообщение об исключительной ситуации с помощью функции
   RAISE_APPLICATION_ERROR.

   Решение:

а)

```sql

CREATE
OR REPLACE PROCEDURE UPPER_PERSENT_PRICE (i_id_offer NUMBER, i_persent NUMBER)
IS
lvValue   NUMBER;
lvDate
DATE;
leIsClose
EXCEPTION;
BEGIN
/* Процедура повышает цену оферты на заданное количество процентов */

          -- Оферта просрочена, необходимо удаление из БД
SELECT
INTO lvDate
FROM offerts
WHERE dt_end IF lvDate <= sysdate THEN
              RAISE leIsClose;
END IF;

-- расчет процента
SELECT price * i_persent / 100
INTO lvValue
FROM offerts
WHERE id = i_id_offer;

UPDATE offerts
SET price = price + lvValue
WHERE id = i_id_offer;
EXCEPTION
WHEN NO_DATA_FOUND THEN
RAISE_APPLICATION_ERROR (-2000,
‘Выбранной оферты не существует
’);
WHEN leIsClose THEN
DELETE
FROM offerts
WHERE id = i_id_offer;
RAISE_APPLICATION_ERROR
(-2001,
‘Оферта просрочена и была удалена
’);

END UPPER_PERSENT_PRICE; 
```

б)

   ```sql
   CREATE
OR REPLACE FUNCTION GET_NUMBER_EXPENSIVE () RETURN NUMBER
   IS
   lvValue     NUMBER;
   leSoSmall
NUMBER;
   lvMax
NUMBER;
BEGIN
   /* Функция возвращает количество офферт иностранных изданий, превышающих средную цену */
   
   --Для вызова дополнительной переоценки
SELECT id
INTO lvMax
FROM offerts o
WHERE o.price > 100000;

SELECT COUNT(*)
INTO lvValue
FROM offerts o
         INNER JOIN subscriptions s ON o.id = s.id_subscribe
         INNER JOIN print p ON s.id_print = p.id
         INNER JOIN owner ow ON p.owner = ow.id
WHERE o.price > (SELECT AVG(o1.price) FROM offerts o1)
  AND ow.t_clnt = 6;

IF
lvValue <20 THEN
   RAISE leSoSmall;
END IF;

RETURN lvValue;

EXCEPTION
   WHEN leIsClose THEN
   RAISE_APPLICATION_ERROR (-2003,
‘Иностранных изданий крайне мало. Урегулирование не требуется
’);
WHEN TOO_MANY_ROWS THEN
   RAISE_APPLICATION_ERROR (-2004,
‘Присутствует более одной дорогой иностранной подписки. Требуется дополнительное урегулирование
’);

END GET_NUMBER_EXPENSIVE; 
   ```

