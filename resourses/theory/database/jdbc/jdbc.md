## Connection

Итак, у нас есть JDBC драйвер, есть JDBC API. Как мы помним, JDBC расшифровывается как Java DataBase Connectivity.
Поэтому, всё начинается с Connectivity - возможности устанавливать подключение. А подключение — это Connection.

Обратимся снова к тексту спецификации JDBC и посмотрим на оглавление. В главе "CHAPTER 4 Overview" (overview - обзор)
обратимся к разделу "4.1 Establishing a Connection" (установление соединения) сказано, что существует два способа
подключения к БД:

* Через DriverManager
* Через DataSource

Разберёмся с DriverManager'ом. Как сказано, DriverManager позволяет подключиться к базе данных по указанному URL, а так
же загружает JDBC Driver'ы, который он нашёл в CLASSPATH (а раньше, до JDBC 4.0 загружать класс драйвера надо было
самостоятельно).

Про соединение с БД есть отдельная глава "CHAPTER 9 Connections". Нас интересует, как получить соединение через
DriverManager, поэтому нам интересен раздел "9.3 The DriverManager Class". В нём указано, как мы можем получить доступ к
БД:

```java

Connection con = DriverManager.getConnection(url, user, passwd);
```

Параметры можно взять с сайта выбранной нами базы данных. В нашем случае это H2 — "H2 Cheat Sheet".

Перейдём в подготовленный Gradle'ом класс AppTest. Он содержит JUnit тесты. JUnit тест — это метод, который помечен
аннотацией @Test. Юнит тесты не являются темой данного обзора, поэтому просто ограничимся пониманием того, что это
описанные определённым образом методы, цель которых что-то протестировать.

Согласно специфкиации JDBC и сайту H2 проверим, что мы получили подключение к БД. Напишем метод получения подключения:

```java
private Connection getNewConnection() throws SQLException {
    String url = "jdbc:h2:mem:test";
    String user = "sa";
    String passwd = "sa";
    return DriverManager.getConnection(url, user, passwd);
}
```

Теперь напишем тест для этого метода, который проверит, что подключение действительно устанавливается:

```java

@Test
public void shouldGetJdbcConnection() throws SQLException {
    try (Connection connection = getNewConnection()) {
        assertTrue(connection.isValid(1));
        assertFalse(connection.isClosed());
    }
}
```

Данный тест при выполнении проверит, что полученное подключение валидное (корректно созданное) и что оно не закрыто.
Благодаря использованию конструкции try-with-resources мы освободим ресурсы после того, как они нам больше не нужны. Это
убережёт нас от "провисших" соединений и утечек памяти.

Так как любые действия с БД требуют подключения, то давайте для остальных тестовых методов, помеченных @Test, обеспечим
в начале теста Connection, который мы освободим после теста. Для этого нам понадобится две аннотации: @Before и @After

Добавим в класс AppTest новое поле, которое будет хранить JDBC подключение для тестов:

private static Connection connection;

И добавим новые методы:

```java

@Before
public void init() throws SQLException {
    connection = getNewConnection();
}

@After
public void close() throws SQLException {
    connection.close();
}

```

Теперь, любому тестовому методу гарантируется наличие JDBC connection и он не должен каждый раз сам его создавать.

## Statements

* Statement: SQL выражение, которое не содержит параметров
* PreparedStatement : Подготовленное SQL выражение, содержащее входные параметры
* CallableStatement : SQL выражение с возможностью получить возвращаемое значение из хранимых процедур (SQL Stored
  Procedures).
  Итак, имея подключение, мы можем в рамках этого подключения выполнить какой-нибудь запрос. Поэтому, логично, что
  экземпляр SQL выражения изначально мы получаем из Connection.

Начать нужно с создания таблицы. Опишем запрос создания таблицы в виде переменной типа String. Как это сделать?
Воспользуемся каким-нибудь обучающим руководством, вроде "sqltutorial.org", "sqlbolt.com", "postgresqltutorial.com", "
codecademy.com".

Воспользуемся, например, примером из курса SQL на khanacademy.org. Добавим метод выполнения выражения в БД:

private int executeUpdate(String query) throws SQLException {
Statement statement = connection.createStatement();

	int result = statement.executeUpdate(query);
	return result;

}

Добавим метод создания тестовой таблицы с использованием прошлого метода:

```java

private void createCustomerTable() throws SQLException {
    String customerTableQuery = "CREATE TABLE customers " +
            "(id INTEGER PRIMARY KEY, name TEXT, age INTEGER)";
    String customerEntryQuery = "INSERT INTO customers " +
            "VALUES (73, 'Brian', 33)";
    executeUpdate(customerTableQuery);
    executeUpdate(customerEntryQuery);
}

```

Теперь протестируем это:

```java

@Test
public void shouldCreateCustomerTable() throws SQLException {
    createCustomerTable();
    connection.createStatement().execute("SELECT * FROM customers");
}
```

Теперь давайте выполним запрос, да ещё и с параметром:

```java

@Test
public void shouldSelectData() throws SQLException {
    createCustomerTable();
    String query = "SELECT * FROM customers WHERE name = ?";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, "Brian");
    boolean hasResult = statement.execute();
    assertTrue(hasResult);
}
```

JDBC не поддерживает именованные параметры для PreparedStatement, поэтому сами параметры указываются вопросами, а
указывая значение мы указываем индекс вопроса (начиная с 1, а не с нуля). В последнем тесте мы получили true как признак
того, есть ли результат. Но как представлен результат запроса в JDBC API? А представлен он как ResultSet

## Metadata

Кроме запросов, подключение к БД (т.е. экземпляр класса Connection) предоставляет доступ к метаданным - данным о том,
как настроена и как устроена наша база данных. Но для начала озвучим несколько ключевых моментов: URL подключения к
нашей БД: "jdbc:h2:mem:test". test - это название нашей базы данных. Для JDBC API это каталог. И название будет в
верхнем регистре, то есть TEST.

Схема по умолчанию (Default schema) для H2 - PUBLIC.
Теперь, напишем тест, который показывает все пользовательские таблицы. Почему пользовательские? Потому что в базах
данных есть не только пользовательские (те, которые мы сами создали при помощи create table выражений), но и системные
таблицы. Они необходимы, чтобы хранить системную информацию о структуре БД. У каждой БД такие системные таблицы могут
храниться по-разному. Например, в H2 они хранятся в схеме "INFORMATION_SCHEMA".

Интересно, что INFORMATION SCHEMA является общим подходом, но Oracle пошли иным путём. Подробнее можно прочитать
здесь: "INFORMATION_SCHEMA и Oracle".

Напишем тест, получающий метаданные по пользовательским таблицам:

```java

@Test
public void shoudGetMetadata() throws SQLException {
    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet result = metaData.getTables("TEST", "PUBLIC", "%", null);
    List<String> tables = new ArrayList<>();
    while (result.next()) {
        tables.add(result.getString(2) + "." + result.getString(3));
    }
    assertTrue(tables.contains("PUBLIC.CUSTOMERS"));
}

```

JDBC или с чего всё начинается - 9

## Пул подключений

Пулу подключений в спецификации JDBC отведен раздел "Chapter 11 Connection Pooling". В нём же и даётся главное
обоснование необходимости пула подключений. Каждый Coonection - это физическое подключение к БД. Его создание и
закрытие - довольно "дорогая" работа.

JDBC предоставляет лишь API для пула соединений. Поэтому, выбор реализации остаётся за нами. Например, к таким
реализациям относится HikariCP.

Соответственно, нам понадобится добавить пул к нам в зависимости проекта:

```
dependencies {
  implementation 'com.h2database:h2:1.4.197'
  implementation 'com.zaxxer:HikariCP:3.3.1'
  testImplementation 'junit:junit:4.12'
}
```

Теперь надо как-то пул этот задействовать. Для этого нужно выполнить инициализацию источника данных, он же Datasource:

```java

private DataSource getDatasource() {
    HikariConfig config = new HikariConfig();
    config.setUsername("sa");
    config.setPassword("sa");
    config.setJdbcUrl("jdbc:h2:mem:test");
    DataSource ds = new HikariDataSource(config);
    return ds;
}

```

И напишем тест на получение подключения из пула:

```java

@Test
public void shouldGetConnectionFromDataSource() throws SQLException {
    DataSource datasource = getDatasource();
    try (Connection con = datasource.getConnection()) {
        assertTrue(con.isValid(1));
    }
}
```

## Транзакции

Один из самых интересных моментов, связанных с JDBC - это транзакции. В спецификации JDBC им отведена глава "CHAPTER 10
Transactions". Прежде всего стоит понять, что же такое транзакция. Транзакция — это группа логически объединённых
последовательных операций по работе с данными, обрабатываемая или отменяемая целиком.

Когда начинается транзакция при использовании JDBC? Как гласит спецификация, это решает непосредственно JDBC Driver. Но
обычно, новая транзакция начинается тогда, когда текущее SQL выражение (SQL statement) потребует транзакцию и транзакции
ещё не создано.

Когда заканчивается транзакция? Это регулируется атрибутом автокоммита (auto-commit). Если автокоммит включен, то
транзакция будет завершена после того, как SQL выражение будет "выполнено".

Что такое "выполнено" зависит от типа SQL выражения:
Data Manipulation Language, он же DML (Insert, Update, Delete)
Транзакция завершается как только завершилось выполнение действия
Select Statements
Транзакция завершается тогда, когда ResultSet будет закрыт (ResultSet#close)
CallableStatement и выражения, возвращающие несколько результатов
Когда все ассоциированные ResultSets будут закрыты и все выходные данные получены (включая кол-во апдейтов)
Так ведёт себя именно JDBC API.

Как обычно, напишем на это тест:

```java

@Test
public void shouldCommitTransaction() throws SQLException {
    connection.setAutoCommit(false);
    String query = "INSERT INTO customers VALUES (1, 'Max', 20)";
    connection.createStatement().executeUpdate(query);
    connection.commit();
    Statement statement = connection.createStatement();
    statement.execute("SELECT * FROM customers");
    ResultSet resultSet = statement.getResultSet();
    int count = 0;
    while (resultSet.next()) {
        count++;
    }
    assertEquals(2, count);
}

```

Всё просто. Но это так, пока у нас всего одна транзакция. А что делать, когда их несколько? Нужно их изолировать друг от
друга. Поэтому, поговорим об уровнях изоляции транзакции и как с ними справляется JDBC.

## Уровни изоляции

Откроем подраздел "10.2 Transaction Isolation Levels" спецификации JDBC.

Тут прежде чем дальше двигаться хочется всомнить про такую штуку, как ACID. ACID описывает требования к транзакционной
системе.

* Atomicity(Атомарность):
  Никакая транзакция не будет зафиксирована в системе частично. Будут либо выполнены все её подоперации, либо не
  выполнено ни одной.
* Consistency(Согласованность):
  Каждая успешная транзакция по определению фиксирует только допустимые результаты.
* Isolation(Изолированность):
  Во время выполнения транзакции параллельные транзакции не должны оказывать влияния на её результат.
* Durability(Долговечность):
  Если транзакция успешно завершенеа, сделанные в ней изменения не будут отменены из-за какого-либо сбоя.
  Говоря про уровни изоляции транзакции мы говорим как раз про требование "Isolation". Изолированность — требование "
  дорогое", поэтому в реальных БД существуют режимы, не полностью изолирующие транзакцию (уровни изолированности
  Repeatable Read и ниже).

На википедии есть отличное объяснение того, какие проблемы могут возникать при работе с транзакциями. Подробнее стоит
прочитать здесь: "Проблемы параллельного доступа с использованием транзакций".

Прежде чем мы напишем наш тест, давайте чуть изменим наш Gradle Build Script: добавим блок с properties, то есть с
настройками нашего проекта:

```
ext {
  h2Version = '1.3.176'
  hikariVersion = '3.3.1'
  junitVersion = '4.12'
}
```

Далее, используем это в версиях:

```
dependencies {
  implementation "com.h2database:h2:${h2Version}"
  implementation "com.zaxxer:HikariCP:${hikariVersion}"
  testImplementation "junit:junit:${junitVersion}"
}
```

Вы могли заметить, что версия h2 стала ниже. Позже мы увидим, зачем. Итак, как же применять уровни изолированности?
Давайте посмотрим сразу небольшой практический пример:

```java

@Test
public void shouldGetReadUncommited() throws SQLException {
    Connection first = getNewConnection();
    assertTrue(first.getMetaData().supportsTransactionIsolationLevel(Connection.TRANSACTION_READ_UNCOMMITTED));
    first.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
    first.setAutoCommit(false);

    String insertQuery = "INSERT INTO customers VALUES (5, 'Max', 15)";
    first.createStatement().executeUpdate(insertQuery);

    int rowCount = 0;
    JdbcRowSet jdbcRs = new JdbcRowSetImpl(getNewConnection());
    jdbcRs.setCommand("SELECT * FROM customers");
    jdbcRs.execute();
    while (jdbcRs.next()) {
        rowCount++;
    }
    assertEquals(2, rowCount);
}

```

Интересно, что данный тест может упасть на вендоре, который не поддерживает TRANSACTION_READ_UNCOMMITTED (например,
sqlite или HSQL). А ещё уровень транзакции может просто не сработать. Помните мы указывали версию драйвера H2 Database?
Если мы поднимем её до h2Version = '1.4.177' и выше, то READ UNCOMMITTED перестанет работать, хотя код мы не меняли. Это
ещё раз доказывает, что выбор вендора и версии драйвера - это не просто буквы, от этого будет в реальности зависеть то,
как будут выполняться ваши запросы.

Про то, как исправить это поведение в версии 1.4.177 и как это не работает в версиях выше можно прочитать здесь: "
Support READ UNCOMMITTED isolation level in MVStore mode".

Итог
Как мы видим, JDBC — это мощный инструмент в руках Java для работы с базами данных. Надеюсь, данный небольшой обзор
поможет стать для Вас отправной точкой или поможет освежить в памяти что-нибудь.

Ну и на закуску немного дополнительных материалов:
Огненный доклад: "Transactions: myths, surprises and opportunities" от Martin Kleppmann
Юрий Ткач: "JPA. Транзакции"
Юрик Ткач: "JDBC - Java для тестировщиков"
Бесплатный курс на Udemy: "JDBC and MySQL"
"Обработка объектов CallableStatement"
IBM Developer: "Java Database Connectivity"
IBM Knowledge Center: "Getting started with JDBC"
<https://www.youtube.com/watch?v=4PKZRQAtf38&t=90s>
<https://www.youtube.com/watch?v=aje9gtFEyC4>
