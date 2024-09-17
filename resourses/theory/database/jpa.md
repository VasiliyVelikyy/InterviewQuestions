### Какие способы реализации пагинации данных с использованием JpaRepository в Spring Data JPA?

Для реализации пагинации данных с использованием JpaRepository в Spring Data JPA можно использовать следующие подходы:

1. Методы findAll(Pageable pageable): Это основной метод репозитория, который позволяет выполнять пагинацию. Метод
   findAll(
   Pageable pageable) возвращает объект Page, который содержит результаты для указанной страницы, а также метаинформацию
   о
   пагинации, такую как общее количество элементов и количество страниц.

Пример:

```java
public interface UserRepository extends JpaRepository<User, Long> {
}

// В сервисе или контроллере
@Autowired
private UserRepository userRepository;

public Page<User> getUsers(Pageable pageable) {
    return userRepository.findAll(pageable);
}
```

Использование класса PageRequest: Класс PageRequest используется для создания объектов Pageable, которые затем можно
передать в методы пагинации. PageRequest позволяет указать номер страницы и размер страницы.

Пример:

```java
Pageable pageable = PageRequest.of(0, 10); // Первая страница, 10 элементов на странице
Page<User> page = userRepository.findAll(pageable);
```

Методы с аннотацией @Query и параметром Pageable: Вы можете использовать аннотацию @Query для выполнения произвольных
запросов с поддержкой пагинации. Параметр Pageable позволяет передавать параметры пагинации в запрос.

Пример:

```java
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.status = :status")
    Page<User> findByStatus(@Param("status") String status, Pageable pageable);
}
```

Использование аннотации @PageableDefault: В контроллерах можно использовать аннотацию @PageableDefault для задания
значений по умолчанию для параметров пагинации.

Пример:

```java

@GetMapping("/users")
public Page<User> getUsers(@PageableDefault(size = 10, page = 0) Pageable pageable) {
    return userRepository.findAll(pageable);
}
```

Не являются правильными:

Методы findAll() и findByCriteria(): Метод findAll() без параметров возвращает все элементы без пагинации. Метод
findByCriteria() является произвольным методом, который не поддерживает пагинацию напрямую.
Методы findAllByCriteria(Pageable pageable): Метод findAllByCriteria() не является частью стандартного интерфейса
JpaRepository и не поддерживается по умолчанию.
Использование класса PageRequest и методов findPage(): findPage() не является стандартным методом JpaRepository. Метод
findAll(Pageable pageable) используется для пагинации, а PageRequest — это способ создания объектов Pageable.
Таким образом, для реализации пагинации вы можете использовать методы findAll(Pageable pageable), классы PageRequest и
аннотацию @Query с параметром Pageable

### Какие способы описания составного ключа (composite key) в Entity существуют в JPA?

#### Составной первичный ключ

Составной первичный ключ у меня получился в таблице с содержимым заказа: одно поле соответствует релизу из каталога, а
другое – заказу. Логично, что сочетание заказ-релиз уникально. В случае, если в заказе оказывается несколько одинаковых
релизов (например, клиент покупает две пластинки: одну себе в коллекцию, а другую – другу), то мы просто увеличиваем
поле quantity.

Итак, код класса, соответствующего этой сущности получается довольно простым и коротким (спасибо скажем lombok и
аннотации @Data, которая позволяет скрывать геттеры и сеттеры и конструкторы):

```java

@Entity
@Data
public class OrderItem {

    private Order order;

    private Release release;

    private int quantity;

    private OrderItemStatus status;

}
```

Теперь пришло время обозначить первичный ключ. Это можно сделать двумя способами: с помощью аннотации @IdClass или
@Embeddable.
И в том и в другом случае для первичного ключа нужно создать отдельный класс, в который требуется поместить
оба поля и который должен реализовать интерфейс Serializable. Я называю свой класс OrderItemPK, и выглядит он пока что
следующим образом:

```java
public class OrderItemPK implements Serializable {

    private Order order;

    private Release release;

    //убрал для краткости геттеры, сеттеры

}
```

Не забудьте также переопределить методы equals(Object o) и hash().

### Составной первичный ключ и @IdClass

Если вы выбираете аннотацию @IdClass, то разместить её нужно перед классом, в котором первичный ключ находится – то есть
в моём случае перед OrderItem. В скобках необходимо указать имя класса с самим ключом, то есть OrderItemPK. При этом оба
поля дублируются: они оказываются и в классе-ключе и в классе-сущности, каждый с привычной аннотацией

```java

@Entity
@Data
@IdClass(OrderItemPK.class)
public class OrderItem {

    @Id
    private Order order;

    @Id
    private Release release;

    private int quantity;

    private OrderItemStatus status;

}
```

Преимущества этого подхода в том, что вы можете обращаться к полям, входящим в первичный ключ, по отдельности без
посредников. Других преимуществ я лично не уловил, потому что сам этим методом пользоваться не стал.

#### Составной первичный ключ и @EmbeddedId

Я предпочёл альтернативный вариант. В этом случае класс с сущностью мы подписываем @Embeddable, и нам не приходится
дублировать поля. Вместо полей, которые выходят в составной ключ, мы просто помещаем объект типа OrderItemPK с
аннотацией @EmbeddedId вместо привычного @Id. Получаются такие классы:.

```java

@Embeddable
public class OrderItemPK implements Serializable {

    private Order order;

    private Release release;

}

@Entity
@Data
public class OrderItem {

    @EmbeddedId
    private OrderItemPK pk;

    private int quantity;

    private OrderItemStatus status;

}
```

Отсутствии дублирования этот подход ещё отличается тем, что в HQL мы обращаемся к отдельным составляющим
первичного ключа немного длиннее: select o.OrderItemPK.release from OrderItem o. Но это не беда, зато можно первичный
ключ использовать, как полноценный цельный объект.

Код классов с диаграмм с отношением @OneToMany
Напоследок выложу все три класса, соответствующие сущностям с диаграммы, которую я поместил в начале статьи.

Про отношения один-ко-многим я уже говорил, поэтому не буду на них заострять внимание, Каждый заказ также связан с
клиентом, но клиентов в этой статье я оставляю за кулисами – от них осталось только поле client с аннотацией @ManyToOne.

Интереснее в этом случае связь @OneToMany с заветной таблицей OrderItem: в атрибуте mappedBy указываем поле pk.order,
так как теперь order это поле объекта pk в классе OrderItem.

```java

@Table(name = "ORDER_INFO")
@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private Client client;

    private OrderStatus status;

    @OneToMany(mappedBy = "pk.order",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    List<OrderItem> orderItems;
}

@Table(name = "ORDER_ITEM")
@Entity
@Data
public class OrderItem {

    @EmbeddedId
    private OrderItemPK pk;

    private int quantity;

    private OrderItemStatus status;

}

@Embeddable
public class OrderItemPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name = "RELEASE_ID")
    private Release release;

}

@Table(name = "RELEASE")
@Entity
@Data
public class Release {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long releaseId;

    @ManyToOne
    @JoinColumn(name = "ALBUM_ID")
    @JsonIgnore
    private Album album;

    private Date releaseDate;

    private String format;

    private String notes;

    private String label;

    private int price;

    private String img;
}
```

* <https://sky.pro/wiki/sql/sozdanie-sostavnogo-pervichnogo-klyucha-v-jpa-primery-koda/>
* <https://egorzimowski.com/2022/05/27/composite-primary-key-in-jpa//>

### Какие способы существуют для создания нативных SQL запросов в Spring Data JPA?

Быстрый ответ
Конечно, это вполне возможно. Для того чтобы использовать прямые SQL-запросы в Spring, можно применять аннотацию @Query
с установленным параметром nativeQuery=true. Возьмем за пример следующий код:

```Java
public interface MyRepository extends JpaRepository<MyEntity, Long> {

    @Query(value = "SELECT * FROM my_table WHERE my_column = :value", nativeQuery = true)
    List<MyEntity> findByColumn(@Param("value") String value);
}
```

И тут мы видим, что с помощью аннотации @Query выполнен нативный SQL-запрос.

Верное использование инструментов: Сценарии применения чистого SQL
Spring Data Repositories предлагает обширные возможности для выполнения стандартных операций. Но иногда возникают
ситуации, когда требуется работать непосредственно с SQL.

#### Создание представлений: Использование проекций

Для создания специальных представлений данных моделей применяются проекции:

```Java
public interface MyEntityProjection {
    String getMyColumn();
}
```

Проекция в репозитории используется следующим образом:

```Java
public interface MyRepository extends JpaRepository<MyEntity, Long> {

    @Query(value = "SELECT my_column as myColumn FROM my_table", nativeQuery = true)
    List<MyEntityProjection> findProjectedBy();
}
```

Прямой доступ с EntityManager
Если требуется более глубокий контроль, можно использовать EntityManager:

```Java

@Autowired
private EntityManager entityManager;

public List<MyEntity> customQueryMethod() {
    return entityManager.createNativeQuery("SELECT * FROM my_table", MyEntity.class).getResultList();
}
```

Пользовательский вывод: Маппинг массивов и классов ответов
Результаты SQL-запросов можно маппировать в массивы:

```
List<Object[]> results = query.getResultList();
for(Object[] result : results){
// Обработка результатов
}
```

Или может быть создан специальный класс для хранения результатов:

```Java
public class CustomResponse {
    private String columnValue; // значение поля может быть любым

// геттеры и сеттеры
}

List<CustomResponse> customResponses = entityManager
        .createNativeQuery("SELECT my_column FROM my_table", CustomResponse.class)
        .getResultList();
```

Безопасность превыше всего: Безопасность и принципы хорошей практики
Очень важно обезопасить SQL-запросы, используя параметризацию:

```Java

@Query(value = "SELECT * FROM my_table WHERE my_column = :value", nativeQuery = true)
List<MyEntity> findByColumn(@Param("value") String value);
```

Проверка пользовательского ввода
Всегда защищайте себя от SQL-инъекций:

```
try {
// Запрос
} catch (PersistenceException e) {
// Обработка исключения
}
```

Анализ производительности
Не забывайте контролировать эффективность запросов:

```SQL
    SELECT *
    FROM my_table
    WHERE my_column = :value LIMIT 10
```

* <https://sky.pro/wiki/java/ispolzovanie-raw-sql-v-spring-data-repository-rukovodstvo/>

### Что такое в Hibernate FetchType.LAZY и FetchType.EAGER

```Java
import java.util.ArrayList;

@OneToOne  // По умолчанию: fetch = FetchType.EAGER
private RelatedEntity otoEntity;

@ManyToOne // По умолчанию: fetch = FetchType.EAGER
private RelatedEntity mtoEntity;

@OneToMany // По умолчанию: fetch = FetchType.LAZY
private Set<RelatedEntity> otmEntities;

@ManyToMany(mappedBy = 'authors') // По умолчанию: fetch = FetchType.LAZY
private List<Books> otmEntities = new ArrayList<Books>();
```

### Проблема n+1 в Hibernate

<https://habr.com/ru/companies/otus/articles/529692/>
<https://habr.com/ru/articles/714704/>