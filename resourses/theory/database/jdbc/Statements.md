## Statements

## Statement

Интерфейс Statement содержит основные функции для выполнения команд SQL.
начала давайте создадим объект Statement :

```java

try(Statement stmt = con.createStatement()){
// use stmt here
        }

```

Опять же, нам следует работать с Statement внутри блока try-with-resources для автоматического управления ресурсами.

В любом случае, выполнение инструкций SQL может быть осуществлено с помощью трех методов:

* executeQuery() для инструкций SELECT
* executeUpdate() для обновления данных или структуры базы данных. Возвращает количество затронутых записей
* execute() можно использовать в обоих случаях выше, когда результат неизвестен
  Давайте используем метод execute(), чтобы добавить таблицу студентов в нашу базу данных:

```
String tableSql = "CREATE TABLE IF NOT EXISTS employees"
        + "(emp_id int PRIMARY KEY AUTO_INCREMENT, name varchar(30),"
        + "position varchar(30), salary double)";
  stmt.execute(tableSql);
```

При использовании метода execute() для обновления данных метод ```stmt.getUpdateCount()``` возвращает количество
затронутых
строк.

Если результат равен 0, то либо ни одна строка не была затронута, либо это была команда обновления структуры базы
данных.

Если значение равно -1, то команда была запросом SELECT; тогда мы можем получить результат с помощью
stmt.getResultSet() .

Далее добавим запись в нашу таблицу с помощью метода executeUpdate() :

```
String insertSql = "INSERT INTO employees(name, position, salary)"
+ " VALUES('john', 'developer', 2000)";
  stmt.executeUpdate(insertSql);
```

Метод возвращает количество затронутых строк для команды, которая обновляет строки, или 0 для команды, которая обновляет
структуру базы данных.

Мы можем извлечь записи из таблицы с помощью метода executeQuery() , который возвращает объект типа ResultSet :

```
String selectSql = "SELECT * FROM employees";
try (ResultSet resultSet = stmt.executeQuery(selectSql)) {
// use resultSet here
}
```

Мы должны убедиться, что закрыли экземпляры ResultSet после использования. В противном случае мы можем держать базовый
курсор открытым гораздо дольше, чем ожидалось. Для этого рекомендуется использовать блок try-with-resources , как в
нашем примере выше.

## PreparedStatement

Это интерфейс в Java Database Connectivity (JDBC) API, который предоставляет механизм для выполнения SQL-запросов с
параметрами. Он облегчает работу с SQL-запросами, улучшает производительность и предотвращает SQL-инъекции.
Объекты PreparedStatement содержат предварительно скомпилированные последовательности SQL. Они могут иметь один или
несколько параметров, обозначенных вопросительным знаком.

Преимущества PreparedStatement
PreparedStatement имеет несколько преимуществ перед Statement, которые делают его предпочтительным выбором для
выполнения параметризованных запросов. Вот список основных преимуществ:

* Защита от SQL-инъекций: PreparedStatement использует параметризованные запросы и плейсхолдеры, что предотвращает
  SQL-инъекции, так как пользовательские данные передаются отдельно от самого запроса и обрабатываются автоматически.
* Предварительная компиляция и кэширование планов выполнения: PreparedStatement представляет собой предварительно
  скомпилированный SQL-запрос, который может быть кэширован и многократно использован с разными параметрами. Это снижает
  накладные расходы на компиляцию и оптимизацию запроса при каждом выполнении, улучшая производительность.
* Повторное использование: PreparedStatement может быть многократно использован для выполнения одного и того же запроса
  с разными параметрами, что сокращает накладные расходы на создание новых объектов запросов и снижает нагрузку на
  сборщик мусора.
* Эффективность передачи данных: PreparedStatement обеспечивает эффективную передачу данных между приложением и базой
  данных, особенно для больших двоичных данных (BLOB и CLOB), таких, как изображения или документы. Значения параметров
  передаются отдельно от запроса, что оптимизирует передачу данных и снижает нагрузку на сеть и обработку строк.
* Строгая типизация параметров: PreparedStatement обеспечивает строгую типизацию параметров, что позволяет дополнительно
  снизить риск SQL-инъекций и обеспечить лучшую проверку типов на стадии выполнения. Если данные не соответствуют
  ожидаемому типу, возникнет исключение, предотвращая выполнение некорректного запроса.
* Более простая работа с параметризованными запросами: PreparedStatement упрощает работу с параметризованными запросами,
  так как автоматически обрабатывает экранирование специальных символов и типы данных, что уменьшает вероятность ошибок
  при формировании запросов и улучшает читаемость кода.

Основы работы с PreparedStatement
Работа с PreparedStatement в JDBC проходит в несколько этапов:

Загрузка драйвера и установка соединения: Сначала необходимо загрузить JDBC-драйвер и установить соединение с базой
данных. Это можно сделать с помощью следующего кода:

```

Class.forName("com.example.jdbc.Driver");
Connection connection = DriverManager.getConnection("jdbc:example://localhost:5432/database_name", "username", "password");
```

Создание PreparedStatement: Создайте экземпляр PreparedStatement с помощью метода prepareStatement() объекта Connection.
В качестве аргумента передайте SQL-запрос с плейсхолдерами (?) для параметров.

```
String query = "INSERT INTO users (name, email, age) VALUES (?, ?, ?)";
PreparedStatement preparedStatement = connection.prepareStatement(query);

```

Установка параметров: Используйте соответствующие методы set для установки значений параметров вместо плейсхолдеров.
Например, setString(), setInt(), setDate() и т. д. Учтите, что индексация параметров начинается с 1.

```
preparedStatement.setString(1, "John Doe");
preparedStatement.setString(2, "john.doe@example.com");
preparedStatement.setInt(3, 30);
```

Выполнение запроса: Выполните запрос, вызвав метод executeUpdate() (для запросов на изменение данных) или
executeQuery() (для запросов на выборку данных) объекта PreparedStatement.
```int affectedRows = preparedStatement.executeUpdate();```
Обработка результатов (для запросов на выборку данных): Если выполняется запрос на выборку данных, с помощью метода
executeQuery() получите объект ResultSet, содержащий результаты запроса. Затем можно пройтись по результатам и получить
значения столбцов.

```
ResultSet resultSet = preparedStatement.executeQuery();
while (resultSet.next()) {
int id = resultSet.getInt("id");
String name = resultSet.getString("name");
String email = resultSet.getString("email");
int age = resultSet.getInt("age");
// Обработка полученных данных
}
```

Закрытие ресурсов: После завершения работы с PreparedStatement и ResultSet (если применимо), закройте их, вызвав метод
close(). Также закройте соединение с базой данных.
Использование PreparedStatement помогает улучшить производительность за счет предварительной компиляции SQL-запроса и
возможности повторного использования компилированного запроса с разными параметрами. Когда вы выполняете один и тот же
запрос с разными параметрами несколько раз, база данных может кэшировать и повторно использовать план выполнения, что
уменьшает накладные расходы на компиляцию и оптимизацию запроса каждый раз.

Кроме того, использование PreparedStatement предотвращает SQL-инъекции, поскольку значения параметров передаются
отдельно от самого запроса и автоматически экранируются. Это гарантирует, что введенные пользователем данные не могут
изменить структуру исходного SQL-запроса.

Вот пример использования PreparedStatement для выполнения запроса на выборку данных с параметром:

```
String query = "SELECT * FROM users WHERE age > ?";
PreparedStatement preparedStatement = connection.prepareStatement(query);
preparedStatement.setInt(1, 25);
ResultSet resultSet = preparedStatement.executeQuery();

while (resultSet.next()) {
int id = resultSet.getInt("id");
String name = resultSet.getString("name");
String email = resultSet.getString("email");
int age = resultSet.getInt("age");
// Обработка полученных данных
}

resultSet.close();
preparedStatement.close();
connection.close();
```

В этом примере мы выбираем всех пользователей старше 25 лет из таблицы users. Значение возраста передается в качестве
параметра, что позволяет легко изменить условие, не меняя структуру SQL-запроса.

### Защита от SQL-инъекций

PreparedStatement обеспечивает защиту от SQL-инъекций благодаря использованию параметризованных запросов и
плейсхолдеров. Вместо того чтобы включать пользовательские данные непосредственно в SQL-запрос, PreparedStatement
использует плейсхолдеры (знаки вопроса ?) для представления параметров в запросе. Значения параметров передаются
отдельно от самого запроса и автоматически обрабатываются драйвером JDBC.

Когда вы используете PreparedStatement, происходит следующее:

Разделение кода и данных: плейсхолдеры в SQL-запросе указывают, где должны быть вставлены значения параметров. Это
разделяет структуру запроса (код) от пользовательских данных, предотвращая возможность внедрения вредоносного кода.
Экранирование пользовательских данных: Драйвер JDBC автоматически экранирует значения параметров, чтобы они не могли
нарушить структуру запроса. Например, если пользователь вводит строку, содержащую апостроф (например, “O’Reilly”),
драйвер JDBC автоматически экранирует апостроф, предотвращая возможную SQL-инъекцию.
Типизация данных: Также PreparedStatement обеспечивает строгую типизацию параметров, что позволяет дополнительно снизить
риск SQL-инъекций. Когда вы передаете параметры с помощью методов set, вы явно указываете их тип. Если данные не
соответствуют ожидаемому типу, возникнет исключение, предотвращая выполнение некорректного запроса.
Вместе эти механизмы гарантируют, что пользовательские данные не могут изменить структуру исходного SQL-запроса,
предотвращая SQL-инъекции. Важно помнить, что для обеспечения максимальной защиты от SQL-инъекций следует всегда
использовать PreparedStatement для выполнения SQL-запросов с параметрами, особенно когда параметры включают данные,
введенные пользователем.

Если вы попытаетесь передать значение “25; DROP TABLE users” как параметр возраста (age) в предыдущем примере с
использованием PreparedStatement, ваша база данных будет защищена от попытки SQL-инъекции.

В примере мы использовали setInt() для установки параметра возраста, поэтому если вы попытаетесь передать строку “25;
DROP TABLE users”, будет вызвано исключение типа java.lang.NumberFormatException или java.sql.SQLException (в
зависимости от реализации драйвера JDBC), поскольку передаваемое значение не является корректным числом.

Даже если бы вы передавали значение в виде строки, PreparedStatement обрабатывал бы его корректно, и SQL-инъекция не
произошла бы. Вот пример, в котором мы используем setString():

```
String query = "SELECT * FROM users WHERE name = ?";
PreparedStatement preparedStatement = connection.prepareStatement(query);
preparedStatement.setString(1, "25; DROP TABLE users");
ResultSet resultSet = preparedStatement.executeQuery();
```

В этом случае значение “25; DROP TABLE users” будет корректно обработано как строковый параметр и будет вставлено в
запрос с экранированием специальных символов. Запрос будет выглядеть примерно так:

```
SELECT * FROM users WHERE name = '25; DROP TABLE users'
```

Таким образом, благодаря механизмам PreparedStatement, ваша база данных будет защищена от попыток SQL-инъекции.

### Кэширование запроса

Кэширование запросов на стороне базы данных (БД) – это процесс сохранения и повторного использования информации,
связанной с запросом, для ускорения последующих запросов. Когда приложение выполняет SQL-запрос, база данных должна
выполнить несколько этапов, включая анализ, компиляцию и оптимизацию запроса, прежде чем фактически получать и
возвращать данные. Эти этапы могут быть затратными по времени, особенно для сложных запросов. Кэширование запросов
помогает снизить накладные расходы на эти этапы, сохраняя результаты предыдущих запросов и повторно используя их при
выполнении аналогичных запросов.

Процесс кэширования запросов в базе данных может быть разделен на следующие этапы:

* Анализ и компиляция: Когда приложение отправляет SQL-запрос в базу данных, сервер БД анализирует текст запроса и
  преобразует его во внутреннее представление, например, в вид дерева разбора. Затем сервер БД компилирует и
  оптимизирует запрос, выбирая наиболее эффективный план выполнения на основе статистики и доступных индексов.
* Создание ключа кэша: Для каждого уникального запроса база данных создает ключ кэша, который служит идентификатором для
  сохраненного плана выполнения. Обычно ключ кэша генерируется на основе хэш-функции текста запроса, но может также
  учитывать другие факторы, такие как значения параметров и статистика таблиц.
* Поиск в кэше: Перед выполнением запроса сервер БД проверяет наличие ключа кэша в кэше планов выполнения. Если ключ
  найден, это означает, что план выполнения для данного запроса уже был ранее создан и сохранен в кэше.
* Использование кэшированного плана: Если план выполнения был найден в кэше, сервер БД использует его для выполнения
  запроса, минуя этапы анализа, компиляции и оптимизации. Это значительно сокращает время выполнения запроса и снижает
  нагрузку на сервер.
* Обновление кэша: Если план выполнения не найден в кэше, сервер БД выполняет запрос с нуля, проходя все этапы, а затем
  сохраняет план выполнения в кэше с соответствующим ключом кэша. В дальнейшем этот план будет доступен для повторного
  использования.
  Важно отметить, что кэширование запросов работает лучше всего для часто повторяющихся запросов, особенно с
  использованием PreparedStatement, который обеспечивает согласованность структуры запроса. В зависимости от настроек и
  реализации СУБД, кэш запросов может иметь ограниченный размер и подвергаться сбросу при изменении структуры таблиц,
  статистики или доступных индексов. Это гарантирует, что кэш планов выполнения остается актуальным и оптимальным для
  текущего состояния базы данных.

При использовании кэшированных запросов, важно следить за их актуальностью и сбалансированностью, чтобы избежать
возможных проблем с производительностью и эффективностью работы базы данных. В некоторых случаях может потребоваться
настроить параметры кэширования, чтобы оптимально соответствовать требованиям конкретного приложения и набора данных.

В целом, кэширование запросов на стороне базы данных является мощным механизмом оптимизации производительности, который
позволяет снизить накладные расходы на выполнение запросов и ускорить доступ к данным. Однако для достижения наилучших
результатов кэширование должно быть правильно настроено и использоваться с осторожностью, с учетом особенностей
конкретной базы данных и приложения.

### Передача больших файлов BLOB и CLOB
PreparedStatement особенно хорошо подходит для передачи больших двоичных данных (BLOB) и символьных данных (CLOB),
поскольку предлагает несколько преимуществ по сравнению с обычным Statement:

Отделение данных от запроса: PreparedStatement позволяет передавать значения параметров отдельно от самого SQL-запроса.
Это облегчает передачу больших объемов данных, таких как BLOB и CLOB, поскольку они передаются непосредственно в базу
данных, минуя обработку и экранирование строк. В случае с Statement, данные должны быть встроены в SQL-запрос, что может
привести к проблемам с кодировкой и увеличению объема передаваемых данных.
Эффективность передачи данных: PreparedStatement обеспечивает эффективную передачу больших двоичных и символьных данных,
так как их обработка и передача оптимизированы для работы с такими данными. Благодаря этому нагрузка на сеть и сервер
базы данных снижается, что улучшает производительность и обеспечивает стабильную работу приложения.
Простота использования: PreparedStatement облегчает работу с BLOB и CLOB, предоставляя удобные методы для установки и
извлечения таких данных, такие как setBlob(), setClob(), getBlob() и getClob(). Эти методы автоматически обрабатывают
данные, учитывая их размер и тип, что упрощает код и снижает риск ошибок.
В целом, PreparedStatement является более подходящим инструментом для работы с BLOB и CLOB благодаря эффективной
передаче данных, отделению данных от запроса и простоте использования. Это упрощает разработку и обеспечивает более
надежную и производительную работу приложений, использующих большие двоичные и символьные данные.

### Вывод
PreparedStatement является предпочтительным выбором для выполнения параметризованных SQL-запросов в Java из-за его
преимуществ перед Statement. Он обеспечивает защиту от SQL-инъекций, улучшенную производительность благодаря
предварительной компиляции и кэшированию, повторное использование, эффективную передачу данных, строгую типизацию
параметров и более простую работу с параметризованными запросами. Эти преимущества делают PreparedStatement более
безопасным, надежным и производительным вариантом для работы с базами данных.

Давайте создадим PreparedStatement , который обновляет записи в таблице сотрудников на основе заданных параметров:

```
String updatePositionSql = "UPDATE employees SET position=? WHERE emp_id=?";
try (PreparedStatement pstmt = con.prepareStatement(updatePositionSql)) {
// use pstmt here
}
```

Чтобы добавить параметры в PreparedStatement , мы можем использовать простые сеттеры – setX() – где X – это тип
параметра, а аргументы метода – это порядок и значение параметра:

```
pstmt.setString(1, "lead developer");
pstmt.setInt(2, 1);
```

Оператор выполняется с помощью одного из трех методов, описанных ранее: executeQuery(), executeUpdate(), execute() без
параметра SQL String :

```
int rowsAffected = pstmt.executeUpdate();
```

Мы также можем установить тайм-аут для PreparedStatement, чтобы ограничить время выполнения запроса до его
автоматической отмены . Это полезно в сценариях, где мы хотим избежать длительных запросов, которые могут заблокировать
систему.

Для установки тайм-аута мы используем метод setQueryTimeout() :

```
String longRunningQuery = "SELECT SLEEP(15)"; // Simulating a long-running query
assertThrows(SQLException.class, () -> {
try (PreparedStatement pstmt = con.prepareStatement(longRunningQuery)) {
    pstmt.setQueryTimeout(5);
    pstmt.executeQuery();
    }
});
```

Это приведет к завершению запроса, если он превысит указанное время ожидания, и вызовет исключение SQLException

## CallableStatement

Интерфейс CallableStatement позволяет вызывать хранимые процедуры.

Чтобы создать объект CallableStatement, мы можем использовать метод prepareCall() объекта Connection :

```
String preparedSql = "{call insertEmployee(?,?,?,?)}";
try (CallableStatement cstmt = con.prepareCall(preparedSql)) {
  // use cstmt here
}
```

Установка значений входных параметров для хранимой процедуры выполняется так же, как в интерфейсе PreparedStatement, с
помощью методов setX() :

```
cstmt.setString(2, "ana");
cstmt.setString(3, "tester");
cstmt.setDouble(4, 2000);
```

Если хранимая процедура имеет выходные параметры, нам необходимо добавить их с помощью метода registerOutParameter() :

```
cstmt.registerOutParameter(1, Types.INTEGER);
```

Затем выполним оператор и получим возвращаемое значение с помощью соответствующего метода getX() :

```
cstmt.execute();
int new_id = cstmt.getInt(1);
```

Например, для работы нам необходимо создать хранимую процедуру в нашей базе данных MySql:

```sql
delimiter
//
CREATE PROCEDURE insertEmployee(OUT emp_id INT,
                                IN emp_name VARCHAR (30), IN POSITION VARCHAR (30), IN salary DOUBLE)
BEGIN
INSERT INTO employees(name, position, salary)
VALUES (emp_name, position, salary);
SET
emp_id = LAST_INSERT_ID();
END
//
delimiter ;
```

Приведенная выше процедура insertEmployee вставит новую запись в таблицу employees, используя заданные параметры, и
вернет идентификатор новой записи в выходном параметре emp_id .

Чтобы иметь возможность запустить хранимую процедуру из Java, пользователь соединения должен иметь доступ к метаданным
хранимой процедуры. Этого можно добиться, предоставив пользователю права на все хранимые процедуры во всех базах данных:

```
GRANT ALL ON mysql.proc TO 'user1';
```

В качестве альтернативы мы можем открыть соединение, установив свойство noAccessToProcedureBodies в значение true :

```
con = DriverManager.getConnection(
"jdbc:mysql://localhost:3306/myDb?noAccessToProcedureBodies=true",
"user1", "pass");
```

Это сообщит API JDBC о том, что у пользователя нет прав на чтение метаданных процедуры, поэтому он создаст все параметры
как параметры строки INOUT .