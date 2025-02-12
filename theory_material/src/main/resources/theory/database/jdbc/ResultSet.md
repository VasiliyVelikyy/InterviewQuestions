# ResultSet

ResultSet использует метод next() для перехода к следующей строке.

Давайте сначала создадим класс Employee для хранения полученных записей:

```java

public class Employee {
    private int id;
    private String name;
    private String position;
    private double salary;

    // standard constructor, getters, setters
}

```

Далее давайте пройдемся по ResultSet и создадим объект Employee для каждой записи:

```
String selectSql = "SELECT * FROM employees"; 
try (ResultSet resultSet = stmt.executeQuery(selectSql)) {
    List<Employee> employees = new ArrayList<>(); 
    while (resultSet.next()) { 
        Employee emp = new Employee(); 
        emp.setId(resultSet.getInt("emp_id")); 
        emp.setName(resultSet.getString("name")); 
        emp.setPosition(resultSet.getString("position")); 
        emp.setSalary(resultSet.getDouble("salary")); 
        employees.add(emp); 
    }
  }
 }
}

```

Извлечение значения для каждой ячейки таблицы можно выполнить с помощью методов типа getX( ), где X представляет собой
тип данных ячейки.

Методы getX() можно использовать с параметром int , представляющим порядок ячейки, или параметром String ,
представляющим имя столбца. Последний вариант предпочтительнее в случае, если мы изменяем порядок столбцов в запросе.

Неявно объект ResultSet можно перемещать только вперед, и его нельзя изменять.

Если мы хотим использовать ResultSet для обновления данных и их перемещения в обоих направлениях, нам необходимо создать
объект Statement с дополнительными параметрами:

```
stmt = con.createStatement(
    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_UPDATABLE
);
```

Для навигации по этому типу ResultSet мы можем использовать один из методов:

* first(), last(), beforeFirst(), beforeLast() – для перехода к первой или последней строке ResultSet или к строке перед
  ними
* next(), previous() – для перемещения вперед и назад в ResultSet
* getRow() – для получения текущего номера строки
* moveToInsertRow(), moveToCurrentRow() – для перехода к новой пустой строке для вставки и обратно к текущей, если на
  новой строке
* absolute(int row) – перейти к указанной строке
  относительное (int nrRows) – переместить курсор на указанное количество строк
  Обновление ResultSet можно выполнить с помощью методов в формате updateX() , где X — тип данных ячейки. Эти методы
  обновляют только объект ResultSet , а не таблицы базы данных.

Чтобы сохранить изменения ResultSet в базе данных, необходимо дополнительно использовать один из методов:

* updateRow() – для сохранения изменений текущей строки в базе данных
* insertRow(), deleteRow() – для добавления новой строки или удаления текущей из базы данных
* refreshRow() – для обновления ResultSet с учетом любых изменений в базе данных
* cancelRowUpdates() – для отмены изменений, внесенных в текущую строку
  Давайте рассмотрим пример использования некоторых из этих методов путем обновления данных в таблице сотрудников:

```
try (Statement updatableStmt = con.createStatement(
  ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
    try (ResultSet updatableResultSet = updatableStmt.executeQuery(selectSql)) {
        updatableResultSet.moveToInsertRow();
        updatableResultSet.updateString("name", "mark");
        updatableResultSet.updateString("position", "analyst");
        updatableResultSet.updateDouble("salary", 2000);
        updatableResultSet.insertRow();
    }
}
}

```