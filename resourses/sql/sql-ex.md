Схема БД состоит из четырех таблиц:
Product(maker, model, type)
PC(code, model, speed, ram, hd, cd, price)
Laptop(code, model, speed, ram, hd, price, screen)
Printer(code, model, color, type, price)
Таблица Product представляет производителя (maker), номер модели (model) и тип ('PC' - ПК, 'Laptop' - ПК-блокнот или '
Printer' - принтер). Предполагается, что номера моделей в таблице Product уникальны для всех производителей и типов
продуктов. В таблице PC для каждого ПК, однозначно определяемого уникальным кодом – code, указаны модель – model (
внешний ключ к таблице Product), скорость - speed (процессора в мегагерцах), объем памяти - ram (в мегабайтах), размер
диска - hd (в гигабайтах), скорость считывающего устройства - cd (например, '4x') и цена - price (в долларах). Таблица
Laptop аналогична таблице РС за исключением того, что вместо скорости CD содержит размер экрана -screen (в дюймах). В
таблице Printer для каждой модели принтера указывается, является ли он цветным - color ('y', если цветной), тип
принтера - type (лазерный – 'Laser', струйный – 'Jet' или матричный – 'Matrix') и цена - price.

1) Найдите номер модели, скорость и размер жесткого диска для всех ПК стоимостью менее 500 дол. Вывести: model, speed и
   hd
   ```sql
   SELECT model, speed, hd
   FROM pc
   WHERE price<500
   ```
2) Найдите производителей принтеров. Вывести: maker
   ```sql
   SELECT DISTINCT p.maker FROM product p
   WHERE type='Printer'
   ```

3) Найдите номер модели, объем памяти и размеры экранов ПК-блокнотов, цена которых превышает 1000 дол
   ```sql
       SELECT l.model, l.ram, l.screen
       FROM Laptop l
       WHERE l.price > 1000
   ```

4) Найдите все записи таблицы Printer для цветных принтеров.
   ```sql
   SELECT *
   FROM Printer p
   WHERE p.color = 'y'
   ```

5) Найдите номер модели, скорость и размер жесткого диска ПК, имеющих 12x или 24x CD и цену менее 600 дол.

    ```sql
    SELECT p.model, p.speed, p.hd
    FROM PC p
    WHERE p.cd = '12x'
       OR p.cd = '24x'
    INTERSECT
    SELECT p.model, p.speed, p.hd
    FROM PC p
    WHERE p.price < 600
    ```

   или

    ```sql
    SELECT p.model, p.speed, p.hd
    FROM PC p
    WHERE (p.cd = '12x' OR p.cd = '24x')
      AND p.price < 600
    ```

6) Для каждого производителя, выпускающего ПК-блокноты c объёмом жесткого диска не менее 10 Гбайт, найти скорости таких
   ПК-блокнотов. Вывод: производитель, скорость.
    ```sql
    SELECT DISTINCT p.maker,l.speed
    FROM Product p
    JOIN Laptop l ON l.model = p.model
    WHERE l.hd >= 10
    ```
7) Найдите номера моделей и цены всех имеющихся в продаже продуктов (любого типа) производителя B (латинская буква).
   ```sql
   SELECT  p.model, pc.price 
   FROM Product p
   JOIN PC pc ON pc.model = p.model
   WHERE p.maker = 'B'
   
   UNION 
   
   SELECT p.model, l.price 
   FROM Product p
   JOIN Laptop l ON l.model = p.model
   WHERE p.maker = 'B'
   
   UNION 
   
   SELECT p.model, pr.price 
   FROM Product p
   JOIN Printer pr ON pr.model = p.model
   WHERE p.maker = 'B'
   ```

8) Найдите производителя, выпускающего ПК, но не ПК-блокноты.
   ```sql
   SELECT p.maker FROM Product p
   WHERE type = 'PC'
   EXCEPT
   SELECT p.maker FROM Product p
   WHERE type = 'Laptop'
   ```

9) Найдите производителей ПК с процессором не менее 450 Мгц. Вывести: Maker
   ```sql
   SELECT DISTINCT p.maker FROM Product p
   JOIN PC ON p.model = PC.model
   WHERE PC.speed >= 450
    ```

10) Найдите модели принтеров, имеющих самую высокую цену. Вывести: model, price
   ```sql
   SELECT model, price
   FROM Laptop l
   WHERE price = (SELECT MAX(price) 
                   FROM Laptop)
   ```   