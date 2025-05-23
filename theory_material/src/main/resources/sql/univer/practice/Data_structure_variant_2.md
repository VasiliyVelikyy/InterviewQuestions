# Вариант 2
Задание : Разработать учет вкладов в банке
# Перед выполнением, нужно поднять oracle в docker

<https://hub.docker.com/r/gvenzl/oracle-xe>

```docker run -d -p 1521:1521 -e ORACLE_PASSWORD=4525 gvenzl/oracle-xe```

Создаем схему UNIVER

* Таблица Клиенты (CLIENT)
    - "ID": NUMBER Уникальный идентификатор клиента, первичный ключ;
    - "FIRST_NAME": VARCHAR2(100) Имя клиента;
    - "LAST_NAME": VARCHAR2(100) Фамилия клиента;
    - "SURNAME": VARCHAR2(100) Отчество клиента;
    - "PASSPORT_NUM": VARCHAR2(100) Серия и номер паспорта клиента;

* Таблица Вкладов (BANK_ACCOUNT)
    - "ID" NUMBER(10,0) Уникальный идентификатор вклада, первичный ключ;
    - "CLIENT_ID" NUMBER(10,0) Уникальный идентификатор клиента, внешний ключ на таблицу CLIENT;
    - "DEPOSITE" NUMBER(38,2) Сумма депозита в формате 100,00;
    - "RATE" NUMBER(10,2) Процентная ставка депозита;
    - "DAY_OF_DEPOSITE" NUMBER(10,0) Период депозита в днях;
    - "INCOME" NUMBER(38,2) Доход по истечению срока вклада, с учетом налога;
    - "CAPITALIZATION" CHAR(1) Имеется ли ежемесячная капитализация;
    - "CURRENCY" VARCHAR2(20) Тип валюты вклада;
    - "TAX" NUMBER(38,2) Удержанный налог;
	- "DT_SYS" DATE Дата вставки записи

Таблица CLIENT имеет связь один-ко-многим к таблице BANK_ACCOUNT через внешний ключ CLIENT_ID.
Тоесть у одного клиента может быть много счетов.
В логике скриптов учтен расчет колонки INCOME по тригеру.
Тоесть расчет проводиться в зависимости от параметра CAPITALIZATION

Если капитализации нет (Значение CAPITALIZATION='N') то формула расчета дохода S=(P *I *T/K )/100
Где S — выплаченные проценты,
P — первоначальная сумма вложений,
I — годовая ставка,
T — количество дней вклада,
K — количество дней в году — 365

Если капитализация есть (Значение CAPITALIZATION='Y') то
S=P* (1+N/12)^T - P
Где S — выплаченные проценты,
P — начальный депозит,
N — годовая ставка, разделенная на 100,
T — срок договора в месяцах.

Расчет колонки TAX по тригеру: берется INCOME и умножаеться на 0.13

Имеется автоинкрементация колонок ID в таблицах CLIENT и BANK_ACCOUNT по тригеру.

SQL DDL

* Создание таблицы CLIENT
Создать пользователя/схему UNIVER

Каждую команду выполнять попорядку
```sql

CREATE TABLE "UNIVER"."CLIENT"
(
    "ID"           NUMBER,
    "FIRST_NAME"   VARCHAR2(100) NOT NULL ENABLE,
    "LAST_NAME"    VARCHAR2(100) NOT NULL ENABLE,
    "SURNAME"      VARCHAR2(100) NOT NULL ENABLE,
    "PASSPORT_NUM" VARCHAR2(100) NOT NULL ENABLE,
    CONSTRAINT "CLIENT_PK" PRIMARY KEY ("ID"),
    CONSTRAINT "CLIENT_UN" UNIQUE ("PASSPORT_NUM"));

CREATE UNIQUE INDEX "UNIVER"."CLIENT_PK" ON "UNIVER"."CLIENT" ("ID") PCTFREE 10 INITRANS 2 MAXTRANS 255 TABLESPACE "USERS";
CREATE UNIQUE INDEX "UNIVER"."CLIENT_UN" ON "UNIVER"."CLIENT" ("PASSPORT_NUM")   PCTFREE 10 INITRANS 2 MAXTRANS 255 TABLESPACE "USERS"

COMMENT
ON COLUMN "UNIVER"."CLIENT".FIRST_NAME IS 'Имя клиента';
COMMENT
ON COLUMN "UNIVER"."CLIENT".LAST_NAME IS 'Фамилия клиента';
COMMENT
ON COLUMN "UNIVER"."CLIENT".SURNAME IS 'Отчество клиента';
COMMENT
ON COLUMN "UNIVER"."CLIENT".PASSPORT_NUM IS 'Серия и номер паспорта клиента';


--последовательность для таблицы CLIENT
CREATE SEQUENCE CLIENT_ID_SEQ START WITH 1;

--тригер на вставку авто-инкремента таблицы client колонки id перед каждым инсертом
CREATE
OR REPLACE TRIGGER  CLIENT_ID_TR
  BEFORE INSERT ON client              
  FOR EACH ROW
BEGIN   
 	:NEW.id:= CLIENT_ID_SEQ.NEXTVAL;
END;


```

* Создание таблицы BANK_ACCOUNT

```sql

-- "UNIVER".BANK_ACCOUNT definition
CREATE TABLE "UNIVER"."BANK_ACCOUNT"
(
    "ID"              NUMBER(10,0) NOT NULL ENABLE,
    "CLIENT_ID"       NUMBER(10,0) NOT NULL ENABLE,
    "DEPOSITE"        NUMBER(38,2) NOT NULL ENABLE,
    "RATE"            NUMBER(10,2) NOT NULL ENABLE,
    "DAY_OF_DEPOSITE" NUMBER(10,0) NOT NULL ENABLE,
    "INCOME"          NUMBER(38,2) NOT NULL ENABLE,
    "CAPITALIZATION"  CHAR(1) NOT NULL ENABLE,
    "CURRENCY"        VARCHAR2(20) NOT NULL ENABLE,
    "TAX"             NUMBER(38,2) NOT NULL ENABLE,
	"DT_SYS"		  DATE,
    CONSTRAINT "BANK_ACCOUNT_PK" PRIMARY KEY ("ID")
)

CREATE UNIQUE INDEX "UNIVER"."BANK_ACCOUNT_PK" ON "UNIVER"."BANK_ACCOUNT" ("ID");

COMMENT
ON COLUMN "UNIVER".BANK_ACCOUNT.ID IS 'Уникальный идентификатор записи';
COMMENT
ON COLUMN "UNIVER".BANK_ACCOUNT.CLIENT_ID IS 'Идентификатор пользователя. Ссылка на таблицу client';
COMMENT
ON COLUMN "UNIVER".BANK_ACCOUNT.DEPOSITE IS 'Сумма вклада';
COMMENT
ON COLUMN "UNIVER".BANK_ACCOUNT.RATE IS 'Процентная ставка вклада';
COMMENT
ON COLUMN "UNIVER".BANK_ACCOUNT.DAY_OF_DEPOSITE IS 'Период депозита в днях';
COMMENT
ON COLUMN "UNIVER".BANK_ACCOUNT.INCOME IS 'Доход по истечению срока вклада, с учетом налога';
COMMENT
ON COLUMN "UNIVER".BANK_ACCOUNT.CAPITALIZATION IS 'Имеется ли ежемесячная капитализация';
COMMENT
ON COLUMN "UNIVER".BANK_ACCOUNT.CURRENCY IS 'Тип валюты вклада';
COMMENT
ON COLUMN "UNIVER".BANK_ACCOUNT.TAX IS 'Удержанный налог';


-- "UNIVER".BANK_ACCOUNT foreign keys
ALTER TABLE "UNIVER"."BANK_ACCOUNT"
    ADD CONSTRAINT "FK_CLIENT" FOREIGN KEY ("CLIENT_ID")
        REFERENCES "UNIVER"."CLIENT" ("ID") ON DELETE CASCADE ENABLE;

--последовательность для таблицы BANK_ACCOUNT
CREATE SEQUENCE BANK_ACC_SEQ START WITH 1;

--тригер на вставку авто-инкремента таблицы bank_account колонки id перед каждым инсертом
CREATE
OR REPLACE TRIGGER  BK_ID_TR
  BEFORE INSERT ON bank_account              
  FOR EACH ROW
BEGIN   
 	:NEW.id:= BANK_ACC_SEQ.NEXTVAL;
END; 


--тригер на расчет доходности в зависимости от введеных параметров
--параметры которые нужно ввести в таблицу:
-- deposite - сумма вклада
-- rate процентная ставка
--day_of_deposite -срок квлада в днях
-- capitalization 'N'-без капитализации ежемесячной, 'Y' -с капитализацией
CREATE
OR REPLACE TRIGGER calculate_income
BEFORE INSERT ON bank_account
FOR EACH ROW
DECLARE
tempr NUMBER;
BEGIN
	--расчет дохода если нет капитализации
	IF
:NEW.capitalization ='N' THEN
  		 tempr:= (:NEW.deposite * :NEW.rate * :NEW.day_of_deposite /365) / 100;
  		:NEW.tax := tempr * 13/100 ;
  	 	:NEW.income := tempr - :NEW.tax ;
  	 --расчет дохода если есть капитализация
  	ELSIF
:NEW.capitalization ='Y' THEN
  		tempr := :NEW.deposite * POWER((1 + (:NEW.rate/100) /12 ), TRUNC(:NEW.day_of_deposite/30, 0 )) - :NEW.deposite;
  		:NEW.tax := tempr * 13/100 ;
  	 	:NEW.income := tempr -:NEW.tax ;
END IF;
END;


```
* В процессе вставки на oracle может возникнуть ошибка
```Error synchronizing data with database

Причина:
 SQL Error [1950] [42000]: ORA-01950: нет привилегий на раздел 'USERS'
 ```
 
 нужно выполнить команду
 ```ALTER USER UNIVER quota unlimited on USERS;```
 

* Тестовое наполнение БД

```sql

INSERT INTO CLIENT (FIRST_NAME, LAST_NAME, SURNAME, PASSPORT_NUM)
VALUES ('Petr', 'Petrov', 'Petrovich', '2222 222222'),
       ('Ivan', 'Ivanov', 'Ivanovich', '1111 111111');
	   
--На некоторых версиях Oracle может не сработать тогда
INSERT ALL

 INTO UNIVER.CLIENT (ID,FIRST_NAME, LAST_NAME, SURNAME, PASSPORT_NUM) VALUES (1,'Petr', 'Petrov', 'Petrovich', '2222 222222')
 INTO UNIVER.CLIENT (ID,FIRST_NAME, LAST_NAME, SURNAME, PASSPORT_NUM) VALUES (2,'Ivan', 'Ivanov', 'Ivanovich', '1111 111111')
 SELECT 1 FROM DUAL;

INSERT INTO BANK_ACCOUNT (CLIENT_ID, DEPOSITE, RATE, DAY_OF_DEPOSITE, CAPITALIZATION, CURRENCY)
VALUES (2, 200000, 25, 182, 'N', 'RUB'),
       (1, 10000, 25, 730, , 'Y', 'RUB'),
       (1, 100000, 21, 365, 'N', 'RUB'),
       (2, 200000, 25, 182, 'Y', 'RUB'),
       (2, 50000, 20.2, 365, 'Y', 'RUB');





```

* 5 различных запросов к БД

```sql
--подсчет количества депозитов больше или равно 200тыс
SELECT COUNT(*)
FROM BANK_ACCOUNT ba
WHERE DEPOSITE >= 200000

--подсчет количества депозитов по каждому клиенту
SELECT client_id, COUNT(client_id)
FROM BANK_ACCOUNT ba
GROUP BY CLIENT_ID

--Вывести номер паспорта клиента, общую сумму депазитов у которого в сумме более 400тыс на вкладах
SELECT c.PASSPORT_NUM, SUM(ba.DEPOSITE)
FROM CLIENT c
         JOIN BANK_ACCOUNT ba ON c.ID = ba.CLIENT_ID
GROUP BY c.PASSPORT_NUM
HAVING SUM(ba.DEPOSITE) >= 400000

--вывести паспорта клиентов у которых доходность между 18000 и 22000
SELECT c.PASSPORT_NUM, income
FROM CLIENT c
         JOIN BANK_ACCOUNT ba ON c.ID = ba.CLIENT_ID
WHERE INCOME BETWEEN 18000 AND 22000

--Вывести имена фамилии и отчества клиентов конкатинацией
SELECT c.LAST_NAME || ' ' || c.FIRST_NAME || ' ' || c.SURNAME
FROM CLIENT c

```


