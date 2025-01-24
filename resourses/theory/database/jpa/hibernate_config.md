Настройка для создания таблиц на основе сущностей

```yml
  jpa:
    properties:
      hibernate:
        hbm2ddl:
          auto: create
```

spring.jpa.hibernate.ddl-auto This is actually a shortcut for the "hibernate.hbm2ddl.auto" property.
For the record, the spring.jpa.hibernate.ddl-auto property is Spring Data JPA specific and is their way to specify a value that will eventually be passed to Hibernate under the property it knows, hibernate.hbm2ddl.auto.

The values create, create-drop, validate, and update basically influence how the schema tool management will manipulate the database schema at startup.
5.1 Автоматическое создание схемы данных
При стартовом конфигурировании Hibernate можно включить очень много интересных настроек. Я не стал их приводить раньше,
чтоб не распыляться. А вот в конце уровня думаю про некоторые из них все же рассказать.

Первая такая настройка — это параметр ***hbm2ddl.auto***. У нее может быть 5 различных значений:

* validate Валидация: Hibernate проверит, совпадают ли имена и типа колонок и полей в базе и в аннотациях. Это самый
  частый режим.
* update Апдейт: Hibernate обновит таблицы в базе, если они или их колонки отличаются от ожидаемых.
* create Пересоздание: Hibernate удалит все таблицы в базе и создаст их заново на основе данных из аннотаций.
* create-drop Создание-удаление. В начале работы Hibernate создаст все таблицы, в конце работы – удалит их за собой.
* none Hibernate вообще ничего не будет делать. Если где-то база не совпадает с ожиданием, то будут сыпаться ошибки во
  время выполнения запросов.

5.2 Логирование запросов
Вторая очень полезная настройка Hibernate — это логирование всех его запросов в базу: все запросы к базе дублируются в
консоль. Это очень полезная функция, если ты вносишь изменения в код, связанный с Hibernate.
Во-первых, ты лучше поймешь, как твои запросы преобразуются в SQL. Во-вторых, легче и раньше сможешь находить ошибки. А
они точно будут. Не всегда Hibernate работает так, как мы ожидаем. Особенно часто это связано с аннотациями: ты
понимаешь их по-своему, а Hibernate — по-своему.

Настройка, включающая логирование, называется hibernate.show_sql. Если выставить ее значение в true, то в консоль будут
писаться запросы к базе. Так же в паре с ним используется параметр hibernate.format_sql, который позволяет задать
удобный формат SQL-запроса в логе.

Еще один способ логировать запросы к базе — это использовать стандартный логгер. Все дало в том, что Hibernate и так
пишет свои запросы в стандартный логер, но только с областью видимости — DEBUG. Тебе нужно поменять два свойства в своем
стандартном логере:

```properties
logging.level.org.hibernate.SQL=debug
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=trace
```

Изменение же уровня BasicBinder на trace добавит нам параметры запросы, правда, в слегка непривычной форме — поочередное
перечислением после самого запроса.

Третий подход: использование специального proxy-драйвера к базе данных.

Например, log4jdbc или p6spy. Оба прокси рабочие и на них есть стартеры, хотя на log4jdbc давно не было коммитов на
момент написания статьи.
``` xml
<dependency>
    <groupId>com.integralblue</groupId>
    <artifactId>log4jdbc-spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>com.github.gavlyukovskiy</groupId>
    <artifactId>p6spy-spring-boot-starter</artifactId>
</dependency>
```
В детали вдаваться не буду, просто хочу, чтобы ты знал, что так можно.

5.3 Диалекты SQL
И еще немного справочной информации.

Hibernate поддерживает работу с очень большим количеством СУБД. Каждая их них реализует стандартный набор функций SQL и еще какие-то свои. Или разные версии SQL. Поэтому для работы с этими СУБД нужно сказать Hibernate, какой диалект языка SQL ему использовать.

Вот список наиболее популярных диалектов:

* PostgreSQL	org.hibernate.dialect.PostgreSQLDialect
* SAP DB	org.hibernate.dialect.SAPDBDialect
* Sybase	org.hibernate.dialect.SybaseDialect
* Informix	org.hibernate.dialect.InformixDialect
* Microsoft SQL Server 2008	org.hibernate.dialect.SQLServer2008Dialect
* MySQL	org.hibernate.dialect.MySQLDialect
* Oracle (any version)	org.hibernate.dialect.OracleDialect
* Oracle 11g	org.hibernate.dialect.Oracle10gDialect
Конечно, таких диалектов может быть сколько угодно. Ты можешь написать свою СУБД и свой собственный диалект к ней.

Почему важно указывать правильный диалект?

У каждой базы данных типы данных могут немного отличаться. Поэтому чтобы Hibernate прямо идеально работал именно так, как ты от него ожидаешь, нужно указать ему, какой диалект SQL-языка ему нужно использовать.

```properties
hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
hibernate.connection.driver_class= org.postgresql.Driver
hibernate.connection.url= jdbc:postgresql://localhost/test
hibernate.connection.username=root
hibernate.connection.password=secret
hibernate.show_sql=true
hibernate.hbm2ddl=validate
```
<https://javarush.com/quests/lectures/questhibernate.level09.lecture04>