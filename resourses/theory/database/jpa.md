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