# Задачи на запросы, процедуры

* Структура БД
  <https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/sql/univer/practice/variant2/Data_structure_variant_2.md>

### ЗАДАНИЕ 1

1) Создать запросы на выборку данных производящих выбор всех строк
   из таблиц, выбор нескольких строк с помощью предиката
   WHERE с различными операторами: BETWEEN, LIKE, AND, OR, NOT >, <, =, <> (не менее 8-ми запросов).

   Решение:

    ```sql
    --1
    SELECT *
    FROM BANK_ACCOUNT ba
    WHERE DEPOSITE BETWEEN 100000 AND 200000
    
    --2
    SELECT *
    FROM BANK_ACCOUNT ba
    WHERE CURRENCY LIKE 'RUB'
    
    --3
    SELECT *
    FROM BANK_ACCOUNT ba
    WHERE CURRENCY LIKE 'RUB'
      AND CURRENCY LIKE 'USD'
    
    --4
    SELECT *
    FROM BANK_ACCOUNT ba
    WHERE CURRENCY LIKE 'RUB'
       OR CAPITALIZATION LIKE 'Y'
    
    --5
    SELECT *
    FROM BANK_ACCOUNT ba
    WHERE CAPITALIZATION <> 'Y'
    
    --6
    SELECT *
    FROM BANK_ACCOUNT ba
    WHERE DEPOSITE <> 10000
    
    --7
    SELECT *
    FROM BANK_ACCOUNT ba
    WHERE RATE BETWEEN 15 AND 21
    
    --8
    SELECT *
    FROM BANK_ACCOUNT ba
    WHERE DAY_OF_DEPOSITE >= 365

    ```
2) Создать запросы, производящие соединение таблиц: внутреннее, левое внешнее, правое внешнее, полное внешнее,
   декартовое произведение (не менее 4-х запросов).

   Решение:

    ```sql
    
       --1
    SELECT *
    FROM BANK_ACCOUNT ba
             JOIN CLIENT c ON c.ID = ba.CLIENT_ID
    
    --2
    SELECT c.ID
    FROM BANK_ACCOUNT ba
             LEFT JOIN CLIENT c ON c.ID = ba.CLIENT_ID
    
    --3
    SELECT c.ID
    FROM BANK_ACCOUNT ba
             RIGHT JOIN CLIENT c ON c.ID = ba.CLIENT_ID
    
    --4
    SELECT *
    FROM BANK_ACCOUNT ba
             FULL JOIN CLIENT c ON c.ID = ba.CLIENT_ID
    
    --5
    SELECT *
    FROM BANK_ACCOUNT ba
             CROSS JOIN CLIENT
    
    ```

3) С помощью оператора SELECT произвести следующие реляционные операции: объединение, разность, пересечение (не менее
   2-х запросов на каждую операцию).

   Решение:

    ```sql
    
    SELECT *
    FROM BANK_ACCOUNT ba
    WHERE DEPOSITE = 100000
    UNION ALL
    SELECT *
    FROM BANK_ACCOUNT ba
    WHERE RATE = 25
    
    SELECT *
    FROM BANK_ACCOUNT ba
    WHERE DEPOSITE = 100000
        MINUS
    SELECT *
    FROM BANK_ACCOUNT ba
    WHERE RATE = 25
    
    
    SELECT *
    FROM BANK_ACCOUNT ba
    WHERE DEPOSITE = 100000
    UNION
    SELECT *
    FROM BANK_ACCOUNT ba
    WHERE DEPOSITE <> 200000;
    ```

4) Создать запросы, содержащие подзапросы и операторы IN, ANY, ALL (не менее 4-х запросов).

   Решение:

    ```sql
    
    --1
    SELECT *
    FROM BANK_ACCOUNT ba
    WHERE ba.CLIENT_ID IN
          (SELECT ID FROM CLIENT c WHERE c.SURNAME LIKE 'Petrovich')
    
    --2
    SELECT *
    FROM BANK_ACCOUNT ba
    WHERE ba.CLIENT_ID = ANY
          (SELECT ID FROM CLIENT c WHERE c.SURNAME LIKE 'Petrovich' OR c.SURNAME LIKE 'Futher')
    
    --3
    SELECT *
    FROM BANK_ACCOUNT ba
    WHERE ba.DEPOSITE >
              ALL (SELECT ba2.DEPOSITE FROM BANK_ACCOUNT ba2 WHERE ba2.CLIENT_ID = 1)
    
    --4
    SELECT *
    FROM BANK_ACCOUNT ba
    WHERE ba.CAPITALIZATION IN ('Y', 'N')
    ```

5) Создать запросы, содержащие группировку строк с применением агрегатных функций: SUM, COUNT, MIN, MAX, AVG (не менее
   2-х запросов).

    ```sql
    
    --1
    SELECT COUNT(c.ID)
    FROM BANK_ACCOUNT ba
             JOIN CLIENT c ON ba.CLIENT_ID = c.ID
    WHERE c.ID = 1
    
    --2
    SELECT DEPOSITE
    FROM BANK_ACCOUNT ba
             JOIN CLIENT c ON ba.CLIENT_ID = c.ID
    WHERE ba.DEPOSITE >= (SELECT AVG(DEPOSITE) FROM BANK_ACCOUNT)

    ```

6) Создать запросы производящих изменение значение во всех строках таблицы и в некоторых строках таблицы (не менее 2-х
   запросов).

   Решение:

    ```sql
    --1
    UPDATE BANK_ACCOUNT
    SET DEPOSITE = DEPOSITE + 1
    --2
    UPDATE BANK_ACCOUNT
    SET DEPOSITE = DEPOSITE + 100000
    WHERE DEPOSITE >= 200000
    ```

7) Создать запросы производящие удаление некоторых строк в таблицы и всех строк таблицы.

    ```sql
    --1
    TRUNCATE TABLE client;
    
    --2
    DELETE
    FROM BANK_ACCOUNT
    WHERE DEPOSITE >= 20000000;
    
    ```

### ЗАДАНИЕ 2

1) В соответствии с выбранным вариантом задания, создать не менее 2
   процедур обеспечивающих логику программы, описанную в лабораторной работе №1 (функции, которая выполняет база
   данных).

   Решение:

   1.1)

   ```sql
   
   --процедура переводит столбцы в верхний регистр
         CREATE
   OR REPLACE PROCEDURE UpdateNamesToUpperCase IS
   BEGIN
   UPDATE CLIENT
   SET first_name = UPPER(first_name),
       last_name  = UPPER(last_name);
   
   COMMIT; -- Применение изменений
   END UpdateNamesToUpperCase;
   
   
   --ВЫЗОВ ФУНКЦИИ
   CALL UNIVER.UPDATENAMESTOUPPERCASE();
   ```

   1.2)

   ```sql
   -- процедура прибавляет tax к income и обнуляем tax
   --тоесть делает income значение без учета налога
   CREATE
   OR REPLACE PROCEDURE UpdateIncomeAndClearTax IS
   BEGIN
   UPDATE BANK_ACCOUNT
   SET INCOME = INCOME + TAX, --
       TAX    = 0 -- Обнуляем tax
   ;
   
   COMMIT; -- Применяем изменения
   END UpdateIncomeAndClearTax;
   
   --ВЫЗОВ ФУНКЦИИ
   CALL UNIVER.UPDATEINCOMEANDCLEARTAX();
   
   ```

2) Создать 2 вспомогательные функции, которые необходимо вызывать
   из тела процедуры.

   2.1)

   ```sql
   
   --ВЕРНУТЬ ДЕПОЗИТ КОТОРЫЙ ВЫШЕ СРЕДНЕГО ЗНАЧЕНИЯ ВСЕХ ДЕПОЗИТОВ
   CREATE
   OR REPLACE FUNCTION GET_MAX_DEPOSITE_BY_AVG RETURN NUMBER
   IS
   lvValue NUMBER;
   BEGIN
   -- Выполняем запрос и сохраняем результат в переменную
   SELECT MAX(ba.DEPOSITE) -- Нам нужен максимальный депозит, чтобы получить максимальную сумму
   INTO lvValue
   FROM BANK_ACCOUNT ba
            JOIN CLIENT c ON ba.CLIENT_ID = c.ID
   WHERE ba.DEPOSITE >= (SELECT AVG(DEPOSITE) FROM BANK_ACCOUNT);
   
   RETURN lvValue; -- Возвращаем значение
   END GET_MAX_DEPOSITE_BY_AVG;
   
   --ВЫЗОВ ФУНКЦИИ
   SELECT GET_MAX_DEPOSITE_BY_AVG()
   FROM DUAL;
   
   ```

   2.2)

   ```sql
   
   --ВЕРНУТЬ TRUE если есть клиент с 500к депозитом
   CREATE
   OR REPLACE FUNCTION EXIST_CLIENT_WITH_500_K_DEPOSITE RETURN NUMBER
      IS
      lvAnswer BOOLEAN := FALSE;  -- lvAnswer типа BOOLEAN
      lvCount
   NUMBER;
   BEGIN
      -- Выполняем запрос, чтобы посчитать количество клиентов с депозитом 500000
   SELECT COUNT(*)
   INTO lvCount
   FROM BANK_ACCOUNT ba
   WHERE ba.DEPOSITE = 500000;
   
   -- Если найден хотя бы один клиент, то устанавливаем lvAnswer в TRUE
   IF
   lvCount > 0 THEN
      lvAnswer := TRUE;
   ELSE
      lvAnswer := FALSE;
   END IF;
   
      -- Возвращаем 1 (если TRUE) или 0 (если FALSE)
      IF
   lvAnswer THEN
      RETURN 1;
   ELSE
      RETURN 0;
   END IF;
   END EXIST_CLIENT_WITH_500_K_DEPOSITE;
   
   --вызов функции
   SELECT EXIST_CLIENT_WITH_500_K_DEPOSITE()
   FROM DUAL;
   
   ```

### ЗАДАНИЕ 3

1) Создать обработку исключительных ситуации в процедурах и функциях, определенных в лабораторной работе №2.
2) В каждой процедуре или функции должны быть обработаны по одной пользовательской и системной исключительной ситуации.
   Пользовательские исключительные ситуации должны быть инициированы с помощью оператора RAISE.
3) В каждой процедуре или функции вывести сообщение об исключительной ситуации с помощью функции
   RAISE_APPLICATION_ERROR.

   Решение:

   а)

   ```sql
   
   --Выдаст ошибку так как не найден клиент с депозитом 500к
   
   CREATE
   OR REPLACE FUNCTION EXIST_CLIENT_WITH_500_K_DEPOSITE_ERROR RETURN NUMBER
   IS
   lvAnswer BOOLEAN := FALSE;  -- lvAnswer типа BOOLEAN
   lvCount
   NUMBER;
   BEGIN
   -- Выполняем запрос, чтобы посчитать количество клиентов с депозитом 500000
   SELECT ba.CLIENT_ID
   INTO lvCount
   FROM BANK_ACCOUNT ba
   WHERE ba.DEPOSITE = 500000;
   
   -- Если найден хотя бы один клиент, то устанавливаем lvAnswer в TRUE
   IF
   lvCount > 0 THEN
           lvAnswer := TRUE;
   ELSE
           lvAnswer := FALSE;
   END IF;
   
       -- Возвращаем 1 (если TRUE) или 0 (если FALSE)
       IF
   lvAnswer THEN
           RETURN 1;
   ELSE
           RETURN 0;
   END IF;
   
   EXCEPTION
   -- Обработка системной ошибки
   WHEN NO_DATA_FOUND THEN
   RAISE_APPLICATION_ERROR(-20001, 'Ошибка: Не найдено ни одной записи с депозитом 500000.');
   
       -- Обработка пользовательской ошибки
   WHEN OTHERS THEN
           RAISE_APPLICATION_ERROR(-20002, 'Произошла системная ошибка при выполнении функции: ' || SQLERRM);
   END EXIST_CLIENT_WITH_500_K_DEPOSITE_ERROR;
   
   --вызов процедуры
   SELECT EXIST_CLIENT_WITH_500_K_DEPOSITE_ERROR()
   FROM DUAL;
   
   ```

   б)

   ```sql
   
   CREATE
   OR REPLACE PROCEDURE UpdateIncomeAndClearTax IS
   BEGIN
      -- Выполняем запрос и обновляем значения
   UPDATE CLIENT
   SET income = income + tax, -- Прибавляем tax к income
       tax    = 0 -- Обнуляем tax
   ;
   
   COMMIT; -- Применяем изменения
   
   EXCEPTION
      -- Обработка системной ошибки
      WHEN NO_DATA_FOUND THEN
      RAISE_APPLICATION_ERROR(-20003, 'Ошибка: Не найдено записей для обновления в таблице CLIENT.');
      
          -- Обработка пользовательской ошибки
   WHEN OTHERS THEN
              RAISE_APPLICATION_ERROR(-20004, 'Произошла ошибка при обновлении данных в таблице CLIENT: ' || SQLERRM);
   END UpdateIncomeAndClearTax;
      
      --вызов функции
   SELECT EXIST_CLIENT_WITH_500_K_DEPOSITE()
   FROM DUAL;
   
   ```