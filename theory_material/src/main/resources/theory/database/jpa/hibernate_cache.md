# Когда необходимо изменить сущность в spring data достаточно вызвать findBy, изменить поле и не вызывать save

В Spring Data JPA поведение, при котором изменение сущности происходит без явного вызова метода save(),
связано с тем, как работает Hibernate (или другой провайдер JPA), который используется под капотом.
Это называется "automatic dirty checking" (автоматическая проверка изменений). Давайте разберем это подробнее.

1. Что такое "dirty checking"?
   "Dirty checking" — это механизм, используемый Hibernate для отслеживания изменений в объектах, находящихся в
   состоянии attached (привязанных к контексту сущности).
   Когда вы выполняете операцию findBy (например, через репозиторий), Hibernate загружает сущность из базы данных и
   помещает её в Persistence Context (контекст сущности).

   Если вы модифицируете поля этой сущности в пределах транзакции, Hibernate автоматически обнаруживает эти изменения
   перед завершением транзакции и сохраняет их в базу данных без необходимости явно вызывать save().

2. Как это работает?
    - a) Persistence Context.
      Когда вы загружаете сущность через findBy, она становится частью Persistence Context .
      Hibernate начинает отслеживать все изменения, которые происходят с этой сущностью.
    - b) Flush при завершении транзакции.
      Перед завершением транзакции Hibernate выполняет операцию flush, то есть отправляет все незавершенные изменения в
      базу данных.
      Если Hibernate обнаруживает, что состояние сущности изменилось (например, вы изменили значение поля), он
      автоматически генерирует SQL-запрос UPDATE и сохраняет изменения.
    - c) Пример кода
   ```java
  
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.stereotype.Service;
   import org.springframework.transaction.annotation.Transactional;

   @Service
   public class MyService {
   
       @Autowired
       private MyRepository myRepository;
   
       @Transactional
       public void updateEntity(Long id, String newName) {
           // Загружаем сущность
           MyEntity entity = myRepository.findById(id).orElseThrow();
   
           // Изменяем поле
           entity.setName(newName);
   
           // Не нужно вызывать save(), Hibernate выполнит обновление автоматически
       }
   }
   ```

### Объяснение:

- findById(id) : Сущность загружается из базы данных и становится привязанной к Persistence Context.
- Изменение поля : Вы меняете значение поля (setName(newName)).
- Автоматическое обновление : Перед завершением транзакции Hibernate проверяет, были ли изменения в сущности, и если да,
  то выполняет запрос UPDATE.

### Удобство

- Упрощение кода : Вам не нужно явно вызывать save(), что делает код более лаконичным.
- Автоматизация : Hibernate автоматически отслеживает изменения и сохраняет их в базу данных.
- Контроль состояния : Все изменения в сущностях внутри транзакции будут сохранены, даже если вы забудете вызвать
  save().

### Этот механизм работает только в следующих случаях:

- Сущность находится в состоянии attached :
    - a) Она должна быть загружена через JPA-репозиторий (например, findById).
    - б) Если вы создали новую сущность или получили её извне (например, из JSON), её нужно явно сохранить через save().
- В пределах транзакции :
    - Механизм "dirty checking" активируется только внутри транзакции.
    - Если транзакция отсутствует, изменения не будут сохранены.

### Модификация существующей сущности :

- Если вы создаёте новую сущность, её нужно сохранить через save(). -
- Если вы изменяете уже существующую сущность, Hibernate выполнит обновление автоматически.

### Пример работы "dirty checking"

a) Без явного вызова save()

   ```java

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyService {

    @Autowired
    private MyRepository myRepository;

    @Transactional
    public void updateEntity(Long id, String newName) {
        MyEntity entity = myRepository.findById(id).orElseThrow();
        entity.setName(newName); // Изменяем поле
// Транзакция завершится автоматически после выхода из метода
// Hibernate выполнит UPDATE перед завершением транзакции
    }
}
  ```

b) С явным вызовом save()

```java

import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Autowired
    private MyRepository myRepository;

    public void updateEntity(Long id, String newName) {
        MyEntity entity = myRepository.findById(id).orElseThrow();
        entity.setName(newName);

        // Явно сохраняем изменения
        myRepository.save(entity);
    }
}
```

В первом случае Hibernate выполнит обновление автоматически, во втором — явно через save().

### Почему иногда нужно использовать save()?

Хотя "dirty checking" упрощает работу, есть случаи, когда явный вызов save() всё же необходим:

1) Новая сущность :
   Если вы создаете новую сущность, её нужно явно сохранить через save(), так как Hibernate не может отследить её
   изменения.
   ```java
   
   MyEntity newEntity = new MyEntity();
   newEntity.setName("John");
   myRepository.save(newEntity); // Нужен save() для новой сущности
   ```
2) Работа вне транзакции :
   Если метод не помечен аннотацией @Transactional, Hibernate не будет выполнять flush автоматически.
   ```java
   
   public void updateEntityOutsideTransaction(Long id, String newName) {
   MyEntity entity = myRepository.findById(id).orElseThrow();
   entity.setName(newName);
   
       // Здесь save() обязателен, так как нет транзакции
       myRepository.save(entity);
   }
   ```
3) Оптимистическая блокировка :
   Если у вас включена оптимистическая блокировка (@Version), вызов save() нужен для обновления версии сущности.
   ```java
   
   @Transactional
   public void updateWithOptimisticLocking(Long id, String newName) {
   MyEntity entity = myRepository.findById(id).orElseThrow();
   entity.setName(newName);
   
       // Для оптимистической блокировки save() может быть нужен
       myRepository.save(entity);
   }
   ```
4) Принудительное обновление :
   Иногда вам нужно принудительно сохранить изменения до завершения транзакции. В этом случае вызовите save().

### Как Hibernate отслеживает изменения?

Hibernate отслеживает изменения с помощью следующих механизмов:

- Первая уровень кэша (First-Level Cache) : Все загруженные сущности хранятся в кэше первого уровня.
- State comparison : Перед завершением транзакции Hibernate сравнивает текущее состояние сущности с её состоянием на
  момент загрузки. Если обнаруживаются изменения, Hibernate генерирует запрос UPDATE.

### Заключение

Вызов save() не всегда необходим в Spring Data JPA благодаря механизму "dirty checking", реализованному в Hibernate.
Однако этот механизм работает только:

В пределах транзакции.
Для сущностей, загруженных через JPA-репозиторий.
При изменении существующих записей.
Если вы создаете новую сущность, работаете вне транзакции или используете оптимистическую блокировку, вызов save() всё
же требуется.

Это поведение делает Spring Data JPA более удобным для работы с данными, но важно понимать его работу, чтобы избежать
ошибок.
