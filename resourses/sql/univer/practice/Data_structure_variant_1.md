# Вариант 1
![img.png](../../../articles/pics/img3.png)
```sql

-- Таблица типов клиента 
CREATE TABLE client
(
    c_clnt NUMBER NOT NULL,               --ключ
    value  VARCHAR2 (50) NOT NULL UNIQUE, --значение
    CONSTRAINT client_pk PRIMARY KEY (c_clnt)
);

-- Таблица владельцев печатного издания 
CREATE TABLE owner
(
    id       NUMBER NOT NULL,
    fis_name VARCHAR2 (100),          --имя для физ лица
    cur_name VARCHAR2 (100) NOT NULL, --наименование
    phone    VARCHAR(11),             --телефон
    t_cint   NUMBER,                  -- тип клиента
    CONSTRAINT owner_pk PRIMARY KEY (id),
    CONSTRAINT fk_client
        FOREIGN KEY (t_clnt)
            REFERENCES client (c_clnt)
);

-- Таблица печатных изданий 
CREATE TABLE print
(
    id        NUMBER NOT NULL,
    index_num NUMBER NOT NULL UNIQUE, --индекс
    NAME      VARCHAR(100),           -- наименвание
    owner     NUMBER,                 --владелец печатного издания
    CONSTRAINT print_pk PRIMARY KEY (id),
    CONSTRAINT fk_owner
        FOREIGN KEY (owner)
            REFERENCES owner (id)
);


--Таблица связи печатного издания и подписки  
CREATE TABLE subscriptions
(
    id_print     NUMBER NOT NULL UNIQUE,
    id_subscribe NUMBER NOT NULL UNIQUE
);

-- Таблица оферт 
CREATE TABLE offerts
(
    id     NUMBER NOT NULL,
    name   VARCHAR2 (50),     --наименование подписки
    price  NUMBER,            --цена
    dt     sys DATE NOT NULL, --дата вставки
    dt_beg DATE,              --начало периода
    dt_end DATE,              --конец периода
    CONSTRAINT offert_pk PRIMARY KEY (id)
);

-- Триггер заполнения даты 
CREATE
OR REPLACE TRIGGER
tr_cur_date BEFORE INSERT ON
offerts FOR EACH ROW
BEGIN
SELECT sysdate
INTO
    new.dt_sys
FROM dual;
END;
```