### Изучение семейства интерфейсов RowSet:

JdbcRowSet, CachedRowSet, WebRowSet


Объекты типа RowSet являются альтернативой объектам типа ResultSet. Интерфейс RowSet расширяет интерфейс ResultSet и определяет дополнительные методы, которые позволяют работать с компонентной архитектурой JavaBean. Кроме того, объекты данного типа могут использоваться при отсутствии постоянного соединения с базой данных. При этом объект типа RowSet будет автоматически соединяться с базой при необходимости получения из нее данных. Интерфейс входит в пакет javax.sql и должен быть реализован поставщиком JDBC классов (драйверов).

Для использования реализации возможностей данного интерфейса компанией Oracle необходимо импортировать пакет oracle.jdbc.rowset, который находится в архиве ocrs12.jar. После чего можно использовать классы OracleCachedRowSet, OracleJDBCRowSet и OracleWebRowSet, применяющие интерфейс javax.sql.RowSet.

Интерфейсы ResultSet и RowSet имеют два принципиальных отличия:

1. Интерфейс RowSet поддерживает компонентную модель JavaBeans. Это позволяет изменять свойства объектов используя визуальные средства разработки компонентов. Кроме того, объекты типа RowSet могут информировать службы обработки событий, например о событии обновления строк.

2. Объекты типа RowSet могут использоваться как при наличии постоянного соединения с базой данных, так и при отсутствии соединения. В режиме постоянного соединения с базой данных использование объектов типа RowSet аналогично использованию объектов типа ResultSet. В режиме отсутствия постоянного соединения наличие соединения с базой данных необходимо только при заполнении объекта типа RowSet данными из базы. После чего соединение разрывается автоматически и данные для обработки будут выбираться далее из памяти.

Еще одно полезное преимущество интерфейса RowSet состоит в реализации прокручиваемых и обновляемых курсоров в виде надстройки над драйверами, которые сами этого не поддерживают.

Объекты типа RowSet могут создавать события, что позволяет оповещать о их происхождении другие компоненты JavaBeans, применяющие интерфейс RowSetListener, который предусматривает три основных метода для обработки одноименных событий: rowChanged(), rowSetChenged() и cursorMoved(). Первое событие (row changes) происходит при изменении при изменении строки, например, когда выполняется UPDATE SQL-предложение. Следующее событие (rowset changes) происходит, когда клиент изменяет весь набор строк. И последнее событие (cursor movements) происходит при перемещении курсора с одной строки на другую, например при выполнении методов next(), previous() или relative().

Интерфейс RowSet не только расширяет интерфейс ResultSet, но и предусматривает установку некоторый свойств, заменяющих использование интерфейсов Connection, Statement, PreparedStatement и CallableStatement. Выполнение простого запроса с использованием интерфейса RowSet может выглядеть следующим образом:

import java.sql.*;
import javax.sql.*;
import oracle.jdbc.rowset.*;

public class JdbcRS {


    public static void main(String[] args) {
        try {
            
            OracleJDBCRowSet ojrs = new OracleJDBCRowSet();
            ojrs.setUrl("jdbc:oracle:thin:@localhost:1521:orbis");
            ojrs.setUsername("stud");
            ojrs.setPassword("stud");
            ojrs.setCommand("SELECT count(*) FROM н_люди");
            ojrs.execute();
            while (ojrs.next()) {
                System.out.println("Total count of row is: " + ojrs.getInt(1));
            }
            
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("Goodbye!");
    }

}
Соединение с базой данных устанавливается в момент выполнения метода execute(). При выполнении SQL-предложения SELECT объект типа RowSet заполняет себя удовлетворяющими условию запроса данными. Результаты выполнения SQL-предложений INSERT, UPDATE, DELETE и любых DML-предложений игнорируются. В случае ошибки возникает исключение SQLException. Если используется параметризованный запрос, то перед выполнением метода execute() необходимо установить все параметры методами setXXX(), как при использовании объектов типа PreparedStatement, например:

OracleCachedRowSet ocrs = new OracleCachedRowSet();
//
ocrs.setUrl("jdbc:oracle:thin:@localhost:1521:orbis");
ocrs.setUsername("stud");
ocrs.setPassword("stud");
String sql = "SELECT * FROM н_люди WHERE ид = ?";
ocrs.setCommand(sql);
ocrs.setInt(1,123456);
ocrs.execute();



Для извлечения данных из объекта типа RowSet можно использовать RowSet.getXXX() методы, XXX-одноименные с типами данных Java тех переменных, в которые эти данные будут сохранены. Перемещение курсора по набору данных объекта типа RowSet выполняется методами, унаследованными от интерфейса ResultSet:



next() - перемещает курсор на следующую строку результата запроса;

previous() - перемещает курсор на предыдущую строку результата запроса;

beforeFirst() - устанавливает курсор на позицию “до первой строки”; если сразу после выполнения этого метода выполнить метод getXXX() будет выдано исключение типа SQLException;

afterLast() - устанавливает курсор на позицию “после последней строки”; если сразу после выполнения этого метода выполнить метод getXXX() будет выдано исключение типа SQLException;

first() - устанавливает курсор на первую строку результата запроса;

last() - устанавливает курсор на последнюю сторк результата запроса;

absolute() - устанавливает курсор на указанную строку относительно первой строки результата запроса;

relative() - устанавливает курсор на указанную строку относительно текущей строки;

moveToCurrentRow() - устанавливает курсор на строку, номер которой был запомнен в результате выполнения метода moveToInsertRow();

moveToInsertRow() - устанавливает курсор в специальную свободную позицию для заполнения пустой строки значениями с помощью методов updateXXX() и последующей вставки этой строки в базу данных с помощью метода insertRow();

deleteRow() - удаляет строку как из результата запроса, так и из базы данных.

Для задания параметра прокрутки (scrollability) используется метод setType(). Он задает возможность перемещения курсора в разных направления по строкам результата запроса и определяет чувствительность к изменениям данных, которые были изменены в базе после выполнения запроса. Параметр прокрутки может принимать следующие:

TYPE_SCROLL_INSENSITIVE – предусматривает перемещение курсора в любых направлениях, допускает абсолютное и относительное позиционирование курсора. Не отражает изменений данных, сделанных в базе после выполнения запроса.

TYPE_SCROLL_SENSITIVE - предусматривает перемещение курсора в любых направлениях, допускает абсолютное и относительное позиционирование курсора. Отражает изменения данных, сделанных в базе после выполнения запроса.

TYPE_FORWARD_ONLY – параметр по умолчанию, стандартный ResultSet.

Второй параметр устанавливается методом setConcurrency() и отвечает за возможность изменения данных результата запроса, вставки и удаления строк из базы данных. Он может принимать одно из двух значений:

CONCUR_UPDATABLE – позволяет вносить изменения в данные;

CONCUR_READ_ONLY – не позволяет вносить изменения в данные.

Третий параметр позволяет контролировать уровень транзакций. Он определяет возможность доступа к данным объекта типа RowSet в течении транзакции с использованием данных другого объекта типа RowSet. Этот параметр устанавливается методом setTransactionIsolation() и может принимать следующие значения:

TRANSACTION_NONE – указывает, что соединение не поддерживает уровень транзакций;

TRANSACTION_READ_UNCOMMITTED – позволяет объекту типа RowSet читать «грязные», т.е. не подтвержденные (uncommitted) данные из других транзакций;

TRANSACTION_READ_COMMITTED – позволяет объекту типа RowSet читать только подтвержденные (committed) данные из других транзакций;

TRANSACTION_REPEATABLE_READ – предотвращает чтение объектом типа RowSet не подтвержденных (uncommitted) данных или данных, изменившихся после начала выполнения транзакции;

TRANSACTION_SERIALIZABLE – не позволяет объекту типа RowSet читать любые данные из других транзакций.

После завершения обработки данных объекта типа RowSet необходимо закрыть объект методом RowSet.close().

Из перечисленного выше семейства самой простой является реализация интерфейса JdbcRowSet. Работа с объектами типа JdbcRowSet требует постоянного соединения с базой данных. Кроме того, они не могут быть преобразованы в последовательную форму (не serializable), что ограничивает возможности их использования в распределенных системах обработки данных.

Реализация интерфейса CachedRowSet позволяет использовать не постоянное соединение и предоставляет возможность преобразования объектов типа CachedRowSet в последовательную форму, т.е. позволяет сохранять объекты. При отключении соединения объекты типа CachedRowSet создают внутри себя виртуальную таблицу, что позволяет работать с ними, как с отдельными независимыми объектами. После заполнения объекта данными можно сохранить его и использовать совместно сразу несколькими клиентами. Это позволяет уменьшить количество постоянных соединений с базой и сократить частоту их создания. Кроме того объект могут получить мобильные клиенты и использовать его отключившись от сетевого соединения. Ниже приведен пример создания, сохранения и использования объекта типа CachedRowSet:

import java.io.*;
import java.sql.SQLException;
import oracle.jdbc.rowset.OracleCachedRowSet;

public class CachedRS {

    //Constant to hold file name used to store the CachedRowSet
    private final static String CRS_FILE_LOC ="cachedrs.crs";

    public static void main(String[] args) throws Exception {
        try {
            //Create serialized CachedRowSet
            writeCachedRowSet();

            //Create CachedRowSet from serialized object
            OracleCachedRowSet crs = readCachedRowSet();

            //Display values
            while(crs.next()){
                System.out.print("Фамилия: " + crs.getString("фамилия"));
                System.out.print(", Имя: " + crs.getString("имя"));
                System.out.print(", Отчество: " + crs.getString("отчество"));
                System.out.print(", Дата рождения: " + crs.getDate("дата_рождения"));
                System.out.println();
            }
            //Close resource
            crs.close();
        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }//end main

    public static void writeCachedRowSet() throws Exception{
        //Instantiate a CachedRowSet object, set connection parameters
        OracleCachedRowSet crs = new OracleCachedRowSet();
        crs.setUrl("jdbc:oracle:thin:@localhost:1521:orbis");
        crs.setUsername("stud");
        crs.setPassword("stud");

        //Set and execute the command. Notice the parameter query.
        String sql = "SELECT * FROM н_люди WHERE ид = ?";
        crs.setCommand(sql);
        crs.setInt(1,123456);
        crs.execute();

        //Serialize CachedRowSet object.
        FileOutputStream fos = new FileOutputStream(CRS_FILE_LOC);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(crs);
        out.close();
        crs.close();
    }//end writeCachedRowSet()

    public static OracleCachedRowSet readCachedRowSet() throws Exception{
        //Read serialized CachedRowSet object from storage
        FileInputStream fis = new FileInputStream(CRS_FILE_LOC);
        ObjectInputStream in = new ObjectInputStream(fis);
        OracleCachedRowSet crs = (OracleCachedRowSet)in.readObject();
        fis.close();
        in.close();
        return crs;
    }//end readCachedRowSet()

}//end CachedRS



Для демонстрации использования объекта типа CachedRowSet при отсутствии соединения с базой данных в примере созданы два метода. Метод writeCachedRowSet() создает объект, заполняет его данными и сохраняет его. Метод readCachedRowSet() читает с диска сохраненный объект и возвращает его вызывающему методу. В реальных приложениях объекты сохраняются с использованием службы имен и каталогов посредством интерфейса JNDI. Для изучения этой возможности используйте в качестве службы имен и каталогов файловую систему (см. примеры предыдущего занятия) и самостоятельно модернизируйте приведенный выше пример.

Использование объектов типа CachedRowSet не ограничивается только просмотром данных. Можно изменять и удалять существующие строки, а так же добавлять новые. Объект типа CachedRowSet хранит в памяти и оригинальную и измененную копию данных и работает с ними при отсутствии соединения с базой. Для внесения изменений в базу из виртуальной таблицы объекта необходимо использование нового (не унаследованного от интерфейса ResultSet) метода – acceptChanges(). Вызов этого метода заставляет объект типа CachedRowSet соединиться с базой данных и внести в нее изменения. Если в процессе внесения изменений возникли ошибки, то выдается исключение SQLException. Ниже приведены примеры программного кода, иллюстрирующие эти возможности на данных таблиц, созданных на предыдущих занятиях.
```
//Populate a CachedRowSet object, crs
String sql = "SELECT * FROM номера_телефонов WHERE ид_л = ?";
crs.setCommand(sql);
crs.setInt(1,125704);
crs.execute();

//Make rowset updatable
crs.setReadOnly(false);

//*** Update example
//Move to first and only row and give myself a raise
crs.first();
crs.updateString(2,"1131313");
//Signal changes are finished
crs.updateRow();
//Write records to database
crs.acceptChanges();



//*** Insert new row example
//Move cursor to the insert row position
crs.moveToInsertRow();
//Add the data for the new row
crs.updateInt(1,120848);
crs.updateString(2,"9332323");
crs.updateString(3,"1−1−02");
crs.updateInt(4,1);
//write the rows to the rowset
crs.insertRow();
//Submit the data to the data source
crs.acceptChanges();

```

До тех пор, пока не выполнен метод acceptChanges(), можно «откатить» изменения данных, так как в объекте хранятся обе таблицы – и оригинальная (с исходными данными) и измененная. Для этого необходимо выполнить метод restoreOriginal(). Этот метод эквивалентен SQL-предложению ROLLBACK. Второй метод – cancelRowUpdates() - отменяет изменения, сделанные в текущей строке.

Для изучения указанных выше методов необходимо самостоятельно создать класс, демонстрирующий обновление существующих данных, добавление новых данных и отмену сделанных изменений, как полную, так и частичную.

# Знакомство с RowSet
Как ты уже знаешь, стандарту JDBC уже почти 20 лет, и он немного устарел. В него потихоньку добавляют новые типы и новые классы, но не везде это можно сделать красиво. И одно из таких мест — это ResultSet.

Работу с базой данных можно сделать более эффективной, но интерфейс ResultSet слабо для этого подходит. Кроме того, мы нигде явно не создаем его объекты, нам их возвращает метод executeQuery().

Создатели JDBC не стали долго думать и сделали механизм, полностью параллельный всему, что было до этого. И называться он стал RowSet.

Вот его основные преимущества:

RowSet расширяет интерфейс ResultSet, поэтому его функции более мощные, чем у ResultSet.
RowSet более гибко перемещается по данным таблицы и может прокручивать назад и вперед.
RowSet поддерживает кэшированные данные, которые можно использовать даже после закрытия соединения.
RowSet поддерживает новый метод подключения, ты можешь подключиться к базе данных без подключения. Также он поддерживает чтение источника данных XML.
RowSet поддерживает фильтр данных.
RowSet также поддерживает операции соединения таблиц.

Типы RowSet:

CachedRowSet
FilteredRowSet
JdbcRowSet
JoinRowSet
WebRowSet
### оздание объекта RowSet
Есть три разных способа получить рабочий объект .

Во-первых, его можно заполнить данными из ResultSet, полученного классическим способом.

Например, мы можем закешировать данные ResultSet с помощью CachedRowSet:

```

Statement statement = connection.createStatement();
ResultSet results = statement.executeQuery("SELECT * FROM user");

RowSetFactory factory = RowSetProvider.newFactory();
CachedRowSet crs = factory.createCachedRowSet();
crs.populate(results);		// Используем ResultSet для заполнения
```
Во-вторых, можно создать полностью автономный объект RowSet, создав для него свое собственное подключение к базе данных:

```
JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet();
rowSet.setUrl("jdbc:mysql://localhost:3306/test");
rowSet.setUsername("root");
rowSet.setPassword("secret");

rowSet.setCommand("SELECT * FROM user");
rowSet.execute();
```
И в-третьих, можно подключить RowSet к уже существующему соединению:
```
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
JdbcRowSet rowSet = new JdbcRowSetImpl(con);

rowSet.setCommand("SELECT * FROM user");
rowSet.execute();
```
6.3 Примеры работы с RowSet
Пример первый: кэширование.

Давай напишем код, где с помощью CachedRowSet закешируем все данные и будем читать их из уже закрытого соединения:
```
Statement statement = connection.createStatement();
ResultSet results = statement.executeQuery("SELECT * FROM user");

RowSetFactory factory = RowSetProvider.newFactory();
CachedRowSet crs = factory.createCachedRowSet();
crs.populate(results);	// Используем ResultSet для заполнения

connection.close();		// Закрываем соединение

// Данные из кэша все еще доступны
while(crs.next()) {
System.out.println(crs.getString(1)+"\t"+ crs.getString(2)+"\t"+ crs.getString(3));
}
Пример второй: изменение строк через RowSet:

// Подключаемся к базе данных
CachedRowSet crs = rsf.createCachedRowSet();
crs.setUrl("jdbc:mysql://localhost/test");
crs.setUsername("root");
crs.setPassword("root");
crs.setCommand("SELECT * FROM user");
crs.execute();

// Этот тип операции может изменить только автономный RowSet
// Сначала перемещаем указатель на пустую (новую) строку, текущая позиция запоминается
crs.moveToInsertRow();
crs.updateString(1, Random.nextInt());
crs.updateString(2, "Клон" + System.currentTimeMillis());
crs.updateString(3, "Женский");
crs.insertRow();  // Добавляем текущую (новую) строку к остальные строкам
crs.moveToCurrentRow(); // Возвращаем указатель на ту строку, где он был до вставки

crs.beforeFirst();
while(crs.next()) {
System.out.println(crs.getString(1) + "," + crs.getString(2) + "," + crs.getString(3));
}
```
// А теперь мы можем все наши изменения залить в базу
```
Connection con = DriverManager.getConnection("jdbc:mysql://localhost/dbtest", "root", "root");
con.setAutoCommit(false); // Нужно для синхронизации
crs.acceptChanges(con);// Синхронизировать данные в базу данных
```
Если тебе интересно, как это работает, можешь ознакомиться с темой в официальной документации. Моя задача в настоящий момент — просто рассказать, что такое есть.