# Что такое внедрение зависимостей (Dependency Injection) в Spring Framework?

* Механизм, позволяющий разрешать зависимости между классами на этапе компиляции
* Способ передачи зависимостей в объекты во время выполнения приложения
* Механизм, обеспечивающий безопасность взаимодействия между компонентами приложения
* Техника, используемая для уменьшения размера и сложности исходного кода
  В Spring Framework внедрение зависимостей происходит на этапе выполнения (runtime).

## Объяснение:

Внедрение зависимостей позволяет передавать объекты, которые требуются классу (например, сервисы или репозитории), в
конструктор, метод или напрямую в поле класса. Это облегчает управление зависимостями и улучшает тестируемость кода.
Spring использует контейнер для управления жизненным циклом объектов и их зависимостями, что позволяет разработчикам
сосредоточиться на бизнес-логике приложения, а не на создании и управлении объектами.

## Другие варианты:

* Механизм, позволяющий разрешать зависимости между классами на этапе компиляции — это не совсем верно, так как
  внедрение
  зависимостей происходит во время ***выполнения (runtime)***.
* Механизм, обеспечивающий безопасность взаимодействия между компонентами приложения — хотя внедрение зависимостей может
  косвенно способствовать этому, его основная цель — управление зависимостями.
* Техника, используемая для уменьшения размера и сложности исходного кода — это не основная цель внедрения зависимостей.
  Однако оно может помочь улучшить читаемость и поддерживаемость кода.

## Объяснение:

Spring использует контейнер для управления жизненным циклом бинов (объектов), их созданием и внедрением зависимостей.
Это происходит во время выполнения приложения, когда Spring контейнер создает объекты и разрешает их зависимости.
Когда приложение запускается, Spring сканирует классы, помеченные аннотациями (например, @Component, @Service,
@Repository), и создает экземпляры этих классов. Затем он внедряет необходимые зависимости в соответствии с
конфигурацией (например, через аннотации @Autowired или конструкторы).
Пример:

```java

@Component
public class MyService {
    private final MyRepository myRepository;

    // Внедрение зависимости через конструктор
    @Autowired
    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

}
```

В этом примере MyRepository будет внедрён в MyService на этапе выполнения, когда Spring контейнер создаёт экземпляр
MyService и разрешает зависимости.

Жизненным циклом управляет спринг-контейнер. В первую очередь после запуска приложения запускается именно он. После
этого контейнер по необходимости и в соответствии с запросами создает экземпляры бинов и внедряет необходимые
зависимости. И, наконец, бины, связанные с контейнером, уничтожаются когда контейнер завершает свою работу. Поэтому,
если мы хотим выполнить какой-то код во время инстанцирования бина или сразу после завершения работы контейнера, один из
самых доступных нам вариантов это вынести его в специальные init() и destroy() методы.

## Ссылки

* <https://habr.com/ru/articles/658273/>
* <https://habr.com/ru/articles/720794/>

# Что такое стартер в контексте Spring Boot?

* Специальный класс, который запускает приложение Spring Boot
* Компонент, который предоставляет настройки по умолчанию для определенной функциональности
* Модуль для автоматической конфигурации безопасности в приложении
* Инструмент для мониторинга и управления состоянием приложения

Spring Boot стартеры — это специальные зависимости (библиотеки), которые содержат набор других библиотек и конфигураций,
необходимых для определенной задачи. Например, стартеры могут включать зависимости для работы с базами данных,
безопасности, веб-сервисов и других компонентов. Это упрощает настройку проекта, так как разработчик может просто
подключить соответствующий стартер, и необходимые зависимости и конфигурации будут добавлены автоматически.

Пример: spring-boot-starter-web предоставляет всё необходимое для создания веб-приложений, включая зависимости для
использования Spring MVC.

Таким образом, правильный ответ: Компонент, который предоставляет настройки по умолчанию для определенной
функциональности.

# Как стартеры Spring Boot работают изнутри для автоматической конфигурации приложения?

- Они используют механизм рефлексии для динамического создания бинов во время выполнения
- Они применяют принцип инверсии управления для инициализации и настройки компонентов
- Они содержат @Configuration классы, которые Spring Boot автоматически обнаруживает и применяет
- Они напрямую модифицируют код приложения для интеграции необходимых зависимостей

Стартеры Spring Boot работают благодаря механизму автоматической конфигурации и условной загрузки бинов.

Правильный ответ: Они содержат @Configuration классы, которые Spring Boot автоматически обнаруживает и применяет.

Механизм работает следующим образом:

1. Стартеры представляют собой набор зависимостей и конфигураций, которые включают в себя классы с аннотацией
   @Configuration.
2. Эти классы содержат инструкции для создания бинов и настройки компонентов на основе присутствующих в проекте
   зависимостей и конфигураций.
3. Автоматическая конфигурация активируется с помощью аннотации @EnableAutoConfiguration или косвенно через
   @SpringBootApplication.
4. Spring Boot загружает классы конфигурации при запуске приложения, используя механизм условной конфигурации (например,
   через аннотации @ConditionalOnClass, @ConditionalOnMissingBean и другие). Это позволяет активировать компоненты
   только
   при выполнении определенных условий, таких как наличие нужного класса в пути к классам или отсутствие уже созданного
   бина.
   Таким образом, стартеры не модифицируют код приложения напрямую и не используют рефлексию для создания бинов. Они
   следуют принципам инверсии управления, но ключевую роль в автоматической конфигурации играет обнаружение и применение
   @Configuration классов.

## Правильный ответ — Они содержат @Configuration классы, которые Spring Boot автоматически обнаруживает и применяет.

# Какие основные цели использования прокси-объектов в Spring Framework?

1. Реализация внедрения зависимостей и аспектно-ориентированного программирования (AOP): Это главная цель использования
   прокси-объектов в Spring. Прокси-объекты позволяют внедрять дополнительное поведение в существующие объекты без
   необходимости изменения их кода. В контексте AOP это может быть добавление таких аспектов, как транзакции,
   логирование,
   безопасность и другие кросс-функциональные аспекты.

2. Добавление дополнительной безопасности к методам компонентов: Прокси-объекты могут использоваться для внедрения
   аспектов
   безопасности, таких как проверка прав доступа перед выполнением методов. Это позволяет централизованно управлять
   политиками безопасности.
   Остальные варианты:

* Улучшение производительности приложения: Прокси-объекты сами по себе не направлены на улучшение производительности,
  хотя
  в некоторых случаях, например, при использовании кэширования на основе AOP, это может косвенно улучшить
  производительность.

* Оптимизация работы сетевых запросов: Прокси-объекты в Spring не предназначены для оптимизации сетевых запросов
  напрямую.
  Для оптимизации сетевых запросов используются другие техники и инструменты, такие как кэширование, использование
  асинхронных вызовов и другие подходы.

Транзакция в спринге многие думаю работает так - спринг в байткод добаваляет методы открытия конекшена, выполнения метода , коммита или ролбеа и закрытия конекшона. На самом деле там механизм проксирования. Работа в рантайме - создается доп класс прокси (оберкти), где есть вызова траназкшнл менеджера, вызов оригинального метода объекта и вызов опять менеджера для закрытия. Этот код обернет наш метод помеченный @Transactional до и после - это так называемые АСПЕКТЫ. Через него реализованы в спринге изменения нашего кода

@Async- все работает асинхронно - спринг налету генерит новый класс. Гененирует тредпул, открываается новый поток который открывает метод в новом потоке. Спринг  - подменяет объекты. Это можно увидеть в дебаге - вызвав getclass, там будем имя класса который нам подменил спринг- который добавляет логику транзакции к нашей основной логики через дизайн паатерн Proxy.
Еще один пример - когда есть стектрейс оишбки - куча вызовово объектов прокси. И лишь в середине где то на 2х строчках вызов нашего метода


```text
at org.hibernate.query.sql.internal.NativeSelectQueryPlanImpl.performList(NativeSelectQueryPlanImpl.java:135) ~[hibernate-core-6.6.5.Final.jar!/:6.6.5.Final]
    at org.hibernate.query.sql.internal.NativeQueryImpl.doList(NativeQueryImpl.java:693) ~[hibernate-core-6.6.5.Final.jar!/:6.6.5.Final]
    at org.hibernate.query.spi.AbstractSelectionQuery.list(AbstractSelectionQuery.java:143) ~[hibernate-core-6.6.5.Final.jar!/:6.6.5.Final]
    at org.hibernate.query.Query.getResultList(Query.java:120) ~[hibernate-core-6.6.5.Final.jar!/:6.6.5.Final]
    at ru.planningmediator.repository.MetricsRepository.getUniqueAddresses(MetricsRepository.java:80) ~[!/:0.0.2-SNAPSHOT]
    at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[?:?]
    at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) ~[?:?]
    at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[?:?]
    at java.base/java.lang.reflect.Method.invoke(Method.java:568) ~[?:?]
    at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:359) ~[spring-aop-6.2.2.jar!/:6.2.2]
    at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:196) ~[spring-aop-6.2.2.jar!/:6.2.2]
    at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163) ~[spring-aop-6.2.2.jar!/:6.2.2]
    at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:380) ~[spring-tx-6.2.2.jar!/:6.2.2]
    at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119) ~[spring-tx-6.2.2.jar!/:6.2.2]
    at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.2.2.jar!/:6.2.2]
    at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:727) ~[spring-aop-6.2.2.jar!/:6.2.2]
    at ru.vtb.dlvr.planningmediator.repository.MetricsRepository$$SpringCGLIB$$0.getUniqueAddresses(<generated>) ~[!/:0.0.2-SNAPSHOT]

at ru.vtb.dlvr.planningmediator.service.monitoring.MetricService.collectMetrics(MetricService.java:53) ~[!/:0.0.2-SNAPSHOT]
    at ru.vtb.dlvr.planningmediator.service.monitoring.MetricService.schedule(MetricService.java:32) ~[!/:0.0.2-SNAPSHOT]
    at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[?:?]

```

наш вызов repository.MetricsRepository.getUniqueAddresses

# Основное преимущество внедрения зависимостей (Dependency Injection, DI) в Spring Framework заключается в:

Уменьшение связанности между компонентами

Внедрение зависимостей позволяет создавать более гибкую и поддерживаемую архитектуру, так как компоненты могут
взаимодействовать друг с другом через интерфейсы и зависимости, которые предоставляются извне, а не создаются внутри
самих компонентов. Это снижает степень связности между компонентами, делает код более модульным и упрощает его
тестирование и поддержку.

# Чем отличается автоматическое внедрение зависимостей (Autowiring) от явного внедрения зависимостей в Spring?

- Автоматическое внедрение зависимостей требует явного указания зависимостей в конфигурационных файлах, в то время как
  явное внедрение происходит автоматически
- Автоматическое внедрение зависимостей использует рефлексию для определения зависимостей, в то время как явное
  внедрение требует явного создания и передачи зависимостей
- Автоматическое внедрение зависимостей происходит на этапе выполнения приложения, в то время как явное внедрение должно
  быть определено на этапе компиляции
- Автоматическое внедрение зависимостей позволяет Spring самостоятельно определять и внедрять зависимости, основываясь
  на типах и аннотациях, в то время как явное внедрение требует явного указания зависимостей в конфигурационных файлах
  или коде

Правильный ответ на вопрос о различии между автоматическим внедрением зависимостей (Autowiring) и явным внедрением
зависимостей в Spring:

* Автоматическое внедрение зависимостей позволяет Spring самостоятельно определять и внедрять зависимости, основываясь
  на
  типах и аннотациях, в то время как явное внедрение требует явного указания зависимостей в конфигурационных файлах или
  коде.

## Объяснение:

Автоматическое внедрение зависимостей (Autowiring) позволяет Spring автоматически разрешать и внедрять зависимости,
используя информацию о типах бинов и аннотации, такие как @Autowired. Это упрощает конфигурацию, поскольку разработчику
не нужно явно указывать, как должны быть внедрены зависимости.

Явное внедрение зависимостей требует, чтобы разработчик явно указывал, какие зависимости должны быть внедрены, обычно
через конфигурационные файлы XML или в коде с использованием методов, таких как конструкторы или сеттеры. Это требует
больше ручного контроля и настройки, но может быть более явным и предсказуемым в некоторых случаях.

## Остальные варианты:

* Автоматическое внедрение зависимостей использует рефлексию для определения зависимостей, это верно, но это не основное
  различие, так как явное внедрение также может использовать рефлексию.

* Автоматическое внедрение зависимостей происходит на этапе выполнения приложения, это верно, но явное внедрение также
  может быть конфигурировано в коде, который выполняется на этапе компиляции.

* Явное внедрение определено на этапе компиляции не является точным. Конфигурации могут быть определены на этапе
  компиляции или выполнения, в зависимости от подхода к конфигурации, будь то XML или аннотации в коде.

# Какие основные этапы определяют жизненный цикл бина в Spring Framework?

Основные этапы жизненного цикла бина в Spring Framework включают:

***Инстанцирование, инициализация, уничтожение***

Вот подробное объяснение каждого из этих этапов:

1. Инстанцирование: На этом этапе Spring создает экземпляр бина. Это происходит путем вызова конструктора или фабричного
   метода, в зависимости от конфигурации.

2. Инициализация: После создания бина Spring выполняет инициализацию, которая может включать настройку свойств
   (внедрение зависимостей), установку значений полей и вызов методов инициализации. Это может быть выполнено
   автоматически через
   аннотации (@PostConstruct) или в соответствии с конфигурацией в XML или Java-коде.

3. Уничтожение: На последнем этапе жизненного цикла, когда бин больше не нужен, Spring выполняет процесс его
   уничтожения.
   Это может включать вызов методов уничтожения (@PreDestroy) и освобождение ресурсов, используемых бин. Этот этап
   гарантирует, что ресурсы будут корректно освобождены.

## Отличия  ```Инстанцирование, инициализация, уничтожение``` от ```Создание, использование, уничтожение```

Создание, использование, уничтожение: Этот вариант охватывает основные стадии жизненного цикла, но не указывает явно на
инициализацию после создания.

# Какие способы существуют для обхода закольцованных зависимостей (circular dependencies) в Spring Framework?

- Использование аннотации @CircularDependency
- Изменение порядка объявления бинов в конфигурационном файле
- Внедрение зависимости через методы вместо конструкторов
- Рефакторинг приложения для избегания циклических зависимостей

Существуют несколько способов обхода закольцованных зависимостей (circular dependencies). Вот
основные из них:

* Внедрение зависимости через методы вместо конструкторов: Этот подход позволяет решить проблему закольцованных
  зависимостей, так как Spring может создать бины и затем внедрить зависимости через сеттеры или методы, когда бины уже
  частично инициализированы. Это позволяет избежать ситуации, когда конструкторы взаимно зависят друг от друга.

* Рефакторинг приложения для избегания циклических зависимостей: Это долгосрочное решение, которое включает в себя
  пересмотр и изменение архитектуры приложения, чтобы устранить циклические зависимости. Это может включать в себя
  перераспределение ответственности между бинами или использование паттернов проектирования, таких как Dependency
  Inversion Principle.

Остальные варианты:

* Использование аннотации @CircularDependency: В Spring Framework не существует такой аннотации. Проблема закольцованных
  зависимостей требует решения на уровне конфигурации или архитектуры, а не через аннотации.

* Изменение порядка объявления бинов в конфигурационном файле: Изменение порядка объявления бинов в конфигурационном
  файле
  не решает проблему закольцованных зависимостей, так как это не влияет на взаимные зависимости между бинами. Spring
  Framework сам управляет порядком создания бинов, и правильное решение проблемы требует изменения способов внедрения
  зависимостей или рефакторинга кода.

Использование методов вместо конструкторов и рефакторинг приложения — это два наиболее практичных подхода для решения
проблем закольцованных зависимостей в Spring

# Что представляет собой Feign Client в Spring Cloud и какие основные настройки доступны для его конфигурации?

Feign Client — это библиотека для взаимодействия с RESTful API в микросервисных системах, обеспечивающая декларативный
подход к созданию HTTP-клиентов.

Feign Client упрощает создание HTTP-клиентов, позволяя разработчикам описывать REST API с помощью интерфейсов Java и
аннотаций, таких как @FeignClient. Этот подход избавляет от необходимости писать много кода для обработки HTTP-запросов
и ответов.

Основные настройки для конфигурации Feign Client включают:

1. Базовый URL:

```java

@FeignClient(name = "my-service", url = "http://localhost:8080")
public interface MyServiceClient {
    @GetMapping("/endpoint")
    String getData();
}
```

2. Имя клиента: В аннотации @FeignClient можно указать имя клиента, которое может использоваться для обнаружения
   сервиса (например, через Eureka):

```java

@FeignClient(name = "my-service")
public interface MyServiceClient {
    // методы
}
```

3. Конфигурация тайм-аутов: Настройки тайм-аутов можно задать через конфигурацию Feign, например, в файле
   application.yml:

```yaml
feign:
  client:
    config:
      default:
        connectTimeout: 5000
        readTimeout: 10000
```

4. Кодировка и декодирование данных: Можно настроить использование кастомных кодировщиков и декодировщиков:

```java

@FeignClient(name = "my-service", configuration = MyFeignConfiguration.class)
public interface MyServiceClient {
    // методы
}

@Configuration
public class MyFeignConfiguration {
    @Bean
    public Encoder feignEncoder() {
        return new JacksonEncoder();
    }

    @Bean
    public Decoder feignDecoder() {
        return new JacksonDecoder();
    }
}
```

5. Логирование: Можно настроить уровень логирования для Feign клиента:

```yaml
logging:
  level:
    feign:
      client:
        FeignClient: DEBUG
```

6. Ограничение запросов: С помощью RequestInterceptor можно добавить заголовки или модифицировать запросы:

```java

@Component
public class MyRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", "Bearer my-token");
    }
}
```

Эти настройки позволяют гибко настраивать работу Feign Client в зависимости от потребностей приложения

## Что представляет собой Testcontainers и как он используется в приложениях Spring?

Testcontainers — это библиотека тестирования, которая позволяет писать тесты с использованием реальных зависимостей с
помощью одноразовых контейнеров Docker. Она предоставляет программируемый API для создания необходимых зависимых
сервисов в виде контейнеров Docker, чтобы вы могли писать тесты, используя реальные сервисы вместо макетов. Таким
образом, независимо от того, пишете ли вы модульные тесты, тесты API или сквозные тесты, вы можете писать тесты с
использованием реальных зависимостей с помощью одной и той же модели программирования.

* <https://habr.com/ru/articles/700286/>

## Что такое контекст Spring и как его создать?

Контекст (а у него есть даже интерфейс — org.springframework.context.ApplicationContext) — это некоторое окружение, в
котором работает приложение на Spring Framework.

Страшные аббревиатуры DI, IoC — это всё про него. Собственно, контекст создаёт и хранит экземпляры классов вашего
приложения, определяет их зависимости друг с другом и автоматически их задаёт.

Безусловно, для того чтобы Spring создал контекст с экземплярами классов, ему нужно предоставить дополнительную
информацию — мета-данные, из каких классов/объектов состоит ваше приложение, как они создаются, какие у них есть
зависимости и т. д.

Итого: Spring Context + мета-данные = работающее приложение.

Где найти контекст?
Контекст является ключевой функциональностью Spring и лежит в maven-зависимости spring-context (на момент написания —
org.springframework:spring-context:5.1.4.RELEASE). Обычно эта зависимость является транзитивной для остальных проектов
Spring. И если вы, например, подключаете spring-boot-starter, то она подключится автоматически, и не нужно думать про
то, где её взять.

Но если вы хотите попробовать "голый" Spring, т. е. только ту часть, которая называется IoC-контейнер, то достаточно
подключить лишь spring-context.

Итого: подключите org.springframework:spring-context:5.1.4.RELEASE.

Какие бывают контексты и как их создать?
У интерфейса ApplicationContext есть большое количество реализаций:

* ClassPathXmlApplicationContext;
* FileSystemXmlApplicationContext;
* GenericGroovyApplicationContext;
* AnnotationConfigApplicationContext;
* и даже StaticApplicationContext;
* а также некоторые другие.

Они отличаются друг от друга именно тем, каким способом задаются мета-данные и где хранится эта конфигурация. Например:

— ClassPathXmlApplicationContext — метаданные конфигурируются XML-файлом(-ами) и они лежат в classpath, т. е. в ресурсах
модуля;

— FileSystemXmlApplicationContext — метаданные тоже конфигурируются XML-файлом(-ами), но они находятся где-то в файловой
системе, например, /etc/yourapp/spring-context.xml;

— AnnotationConfigApplicationContext — метаданные конфигурируются с помощью аннотаций прямо на классах.

ClassPathXmlApplicationContext будет читать файлы из вашего classpath. Они должны быть в classesпапке вашего
веб-приложения или в jarвашей libпапке.

FileSystemXmlApplicationContext может получить доступ ко всей вашей файловой системе, например c:
/config/applicationContext.xml.

XmlWebApplicationContext конечно, может получить доступ к файлам, содержащимся в вашем веб-приложении, но это не самое
главное. Он реализует WebApplicationContext , а это означает, что он будет обнаруживать бины ServletContextAware ,
регистрировать пользовательские области (запрос, сеанс, ...) среди прочего
Современным способом конфигурирования считаются аннотации (AnnotationConfigApplicationContext), дальше будем создавать
именно их.

Приведём пример создания такого контекста в методе main:

```java

    @ComponentScan
    public class Main {

        public static void main(String[] args) {
            AnnotationConfigApplicationContext context =
                    new AnnotationConfigApplicationContext(Main.class);
        }
    }
```

Внутри конструктора как раз и происходит инициализация контекста из мета-данных.
Как и полагается, в AnnotationConfigApplicationContext мета-данные конфигурируются аннотациями. Несложно заметить
аннотацию @Configuration на Main-классе, и что он передаётся в конструктор контекста. Собственно, Main и есть описание
метаданных.

Итого: создаём контекст.

В результате мы получили работающее приложение на Spring. Правда, пока ещё без бизнес-логики. А что же означает
аннотация @ComponentScan, и как правильно определять и писать бины, мы узнаем в следующей части.

# Какие scope бинов существуют

В Spring Framework существует несколько областей видимости (scopes) для бинов, каждая из которых определяет жизненный
цикл и видимость бина в контексте приложения. Вот основные из них:

1. Singleton: Это область видимости по умолчанию. Бин с данной областью видимости создается один раз на каждый контейнер
   Spring IoC и кэшируется для последующих запросов. Все запросы на получение бина возвращают один и тот же экземпляр
   бина.

2. Prototype: Бин с данной областью видимости создается заново каждый раз, когда он запрашивается из контейнера. Это
   полезно, когда необходимы разные экземпляры бина для разных задач.

3. Request: Эта область видимости используется в веб-приложениях. Бин создается для каждого HTTP-запроса и уничтожается
   после обработки запроса. Бины с данной областью видимости доступны только в контексте одного HTTP-запроса.

4. Session: Бин с данной областью видимости создается для каждой пользовательской сессии. Бин живет в течение всей
   сессии и уничтожается, когда сессия завершается. Это полезно для хранения информации, специфичной для пользователя, в
   течение всего времени его сессии.

5. Global-session

6. Application: Бин с данной областью видимости создается один раз для всего сервлет-контекста. Это полезно для данных,
   которые должны быть общими для всех пользователей и сессий в приложении.

7. WebSocket: Бин с данной областью видимости создается для каждой сессии WebSocket. Это полезно для работы с данными,
   специфичными для каждой WebSocket-сессии.

Области Request, Session,Global-Session Application и WebSocket доступны только при использовании веб-реализации Spring
ApplicationContext (такой как XmlWebApplicationContext). Являются частью пакета org.springframework.web.
Если вы используете эти области с обычными контейнерами Spring
IoC, такими как ClassPathXmlApplicationContext, генерируется исключение IllegalStateException, которое жалуется на
неизвестную область действия компонента.

# Кладется ли prototype в контейнер

Нет, в контейнере только синглтоны. Это важно, ведь если мы пропишем дестрой методы. При закрытии контекста спринг
пробежится по всем синглтонам их вызовет,
а у prototype они работать не будут

# Singleton

По умолчанию этот бин кладется в контекст. Но если его определить как lazy, то он будет создаваться в момент когда его
попросят

# Потокобезопасен ли singleton?

* Нет, Spring Singelton Bean не является потокобезопасным. Вот пример

```java
public class Emmloyee {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

И вот applicationContext.xml

```xml

<bean id="emp" class="com.manikant.Emmloyee" p:id="26" p:name="Manikant Gautam"/>
```

А вот тестовый класс

```java

public class Test {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/manikant/config.xml");
        Emmloyee emp = (Emmloyee) ctx.getBean("emp");
        System.out.println("User " + emp.getName() + " is of age " + emp.getId());
        emp.setName("Changed value");

        Emmloyee emp1 = (Emmloyee) ctx.getBean("emp");
        System.out.println("User " + emp1.getName() + " is of age " + emp1.getId());
        //Вот вывод
        //User Manikant Gautam is of age 26
        //User Changed value is of age 26
    }
}

```

Изменение также value отражается emp.setName("Changed value") на разных bean emp1.

# Потокобезопасный Singletone

Создание потокобезопасного синглтона в Java можно осуществить с использованием различных подходов. Вот несколько
способов:

Используя synchronized метод getInstance():

```java
public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {
        // Приватный конструктор 
    }

    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}
}
```

В этом примере метод getInstance() объявлен как synchronized, что гарантирует, что только один поток может выполнить его
одновременно. Однако, этот подход может вызывать некоторые накладные расходы на производительность из-за блокировки
всего метода при каждом доступе к синглтону.

Используя synchronized блок внутри метода getInstance():

```java
public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {
        // Приватный конструктор 
    }

    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}
}
```

В этом подходе используется double checked locking, который обеспечивает ленивую инициализацию синглтона без
синхронизации при каждом доступе. Однако, реализация double checked locking может быть сложной и подвержена ошибкам.

Используя статический вложенный класс (static nested class):

```java
public class ThreadSafeSingleton {
    private ThreadSafeSingleton() {
        // Приватный конструктор 
    }

    private static class SingletonHelper {
        private static final ThreadSafeSingleton instance = new ThreadSafeSingleton();
    }

    public static ThreadSafeSingleton getInstance() {
        return SingletonHelper.instance;
    }
}

```

В этом подходе экземпляр синглтона создается при загрузке класса SingletonHelper, что гарантирует потокобезопасность.
Это основано на механизме инициализации статических полей в Java.

Используя перечисление (enum):

```java
public enum ThreadSafeSingleton {
    INSTANCE;

    // Дополнительные поля и методы
    public void doSomething() {
        // Реализация }
    }
}
```

В этом подходе синглтон создается автоматически при загрузке перечисления и гарантируется его уникальность и
потокобезопасность.

Выбор конкретного подхода зависит от требований и контекста вашего приложения. Важно помнить, что потокобезопасность
синглтона - это только один из аспектов, которые следует учитывать при разработке.

# BeanDefinitions что эт такое и что первее создаеться bean или beanDefinitions

BeanDefinition — это специальный интерфейс, через который можно получить доступ к метаданным будущего бина. В
зависимости от того, какая у вас конфигурация, будет использоваться тот или иной механизм парсирования конфигурации (
xml, groove, аннотации и тд).
> <https://habr.com/ru/articles/222579/>

# Жизненный цикл бина

![img.png](media/img.png)

Beans – центральный объект заботы Spring Framework. За кулисами фреймворка с ними происходит множество процессов. Во
многие из них можно вмешаться, добавив собственную логику в разные этапы жизненного цикла. Через следующие этапы
проходит каждый отдельно взятый бин:

1. Инстанцирование объекта. Техническое начало жизни бина, работа конструктора его класса;

2. Установка свойств из конфигурации бина, внедрение зависимостей;

3. Нотификация aware-интерфейсов. BeanNameAware, BeanFactoryAware и другие. Мы уже писали о таких интерфейсах ранее.
   Технически, выполняется системными подтипами BeanPostProcessor, и совпадает с шагом 4;

4. Пре-инициализация – метод postProcessBeforeInitialization() интерфейса BeanPostProcessor;

5. Инициализация. Разные способы применяются в таком порядке:
   • Метод бина с аннотацией @PostConstruct из стандарта JSR-250 (рекомендуемый способ);
   • Метод afterPropertiesSet() бина под интерфейсом InitializingBean;
   • Init-метод. Для отдельного бина его имя устанавливается в параметре определения initMethod. В xml-конфигурации
   можно установить для всех бинов сразу, с помощью default-init-method;

6. Пост-инициализация – метод postProcessAfterInitialization() интерфейса BeanPostProcessor.

# Пример использования BeanPostProcessor

В спринге:
Например за аннотацию @Autowired отвечает AutowiredAnnotationBeanPostProcessor.
Например у нас есть какое-то свойство, которое небходимо использовать во многих местах. И чтобы не писать постоянную
реализацию
можно ввести аннотацию, которую будет обрабатывать BeanPostProcessor и устанавливать в бин. В spring потрошителе
приводили пример
свойства рандомного числа. Написали аннотацию @InjectRandomInt над int переменной. И реализовали BeanPostProcessor, и
каждый раз до создания бина, инициализировалось рандомне число.
Главное незабыть наш реализованный BeanPostProcessor добавить в контекст спринга! Сделав его бином

```java

@Component
public class InjectRandomIntBeanPostProcessor implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(InjectRandomIntBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        LOGGER.info("postProcessBeforeInitialization::beanName = {}, beanClass = {}", beanName, bean.getClass().getSimpleName());

        Field[] fields = bean.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectRandomInt.class)) {
                field.setAccessible(true);
                InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
                ReflectionUtils.setField(field, bean, getRandomIntInRange(annotation.min(), annotation.max()));
            }
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private int getRandomIntInRange(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
```

### Или инициализация логгера

Использование BeanPostProcessor на примере журналирования
Сегодня я хочу рассказать, как можно сделать инициализацию логгера в классе с использованием аннотаций и
BeanPostProcessor

Очень часто мы инициализируем логгер следующим образом:

```java
public class MyClass {
    private static final Logger LOG = LoggerFactory.getLogger(MyClass.class);
}
```

Я покажу, как сделать, чтобы можно было писать вот так:

```java

@Log
private Logger LOG;
Первым делом
нам нужно
объявить аннотацию:

@Retention(RUNTIME)
@Target(FIELD)
@Documented
public @interface Log {
    String category() default "";
}
```

А вторым делом, написать собственный BeanPostProcessor, который бы
устанавливал нам логгер:

```java
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class LoggerPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) {
        ReflectionUtils.doWithFields(bean.getClass(), new FieldProcessor(bean, beanName), new LoggerFieldFilter());
        return bean;
    }

    private static class FieldProcessor implements ReflectionUtils.FieldCallback {
        private final Object bean;
        private final String beanName;

        private FieldProcessor(Object bean, String beanName) {
            this.bean = bean;
            this.beanName = beanName;
        }

        @Override
        public void doWith(Field field) throws IllegalAccessException {
            Log loggerAnnot = field.getAnnotation(Log.class);

            // Sanity check if annotation is on the field with correct type.
            if (field.getType().equals(org.slf4j.Logger.class)) {
                // As user can override logger category - check if it was done.
                String loggerCategory = loggerAnnot.category();
                if (StringUtils.isBlank(loggerCategory)) {
                    // use default category instead.
                    loggerCategory = bean.getClass().getName();
                }
                Logger logger = LoggerFactory.getLogger(loggerCategory);
                ReflectionUtils.makeAccessible(field);
                field.set(bean, logger);
            } else {
                throw new IllegalArgumentException(
                        "Unable to set logger on field '" + field.getName() + "' in bean '" + beanName +
                                "': field should have class " + Logger.class.getName());
            }
        }
    }

    private static class LoggerFieldFilter implements ReflectionUtils.FieldFilter {
        @Override
        public boolean matches(Field field) {
            Log logger = field.getAnnotation(Log.class);
            return null != logger;
        }
    }
}
```

Если вы используете не sfl4j, а, например, log4j, или commons-logging, то нужно немного поправить код внутри метода
doWith
<https://uthark.github.io/2012/04/20/beanpostprocessor/>

# BeanFactoryPostProcessor

Работает раньше BeanPostProcessor и позволяет что то подкрутить в beanDefinition.
Пример работы - когда мы ставим над свойством аннотацию, происходит инжект свойств до создания бин дефинишн

В спринге:

- обработка JavaConfiguration (создание бин-дефинишнов по написанным java методам с аннотацией @Bean) отвечает
  ConfigurationClassPostProcessor
  (особый BeanFactoryPostProcessor).

```java

@Value("${test.value}}")
private String password;
```

у него есть единственный метод postProcessBeanFactory
Мы можем повлиять на beanFactory и beanDefinition до создания бинов

# Чем аннотация @Repository отличается от @Component

В Spring аннотации @Repository и @Component используются для обозначения классов как бинов, которые управляются
Spring-контейнером. Однако между ними есть важные различия, которые связаны с их предназначением и дополнительными
функциями. Давайте разберем их подробнее.

1. Назначение аннотаций
   @Component
   Это базовая аннотация, которая указывает, что класс является Spring-бином.

Она может использоваться для любого класса, который должен быть управляемым Spring-контейнером.

Это общая аннотация, которая не несет в себе специфической семантики.

@Repository
Это специализированная аннотация, которая используется для классов, работающих с данными (например, DAO — Data Access
Object).

Она наследуется от @Component, поэтому также помечает класс как Spring-бин.

Помимо этого, @Repository добавляет дополнительную функциональность, связанную с обработкой исключений и интеграцией с
механизмами Spring (например, транзакции).

2. Обработка исключений
   Одно из ключевых отличий @Repository — это автоматическая трансляция исключений, специфичных для работы с данными (
   например, SQL-исключения), в исключения Spring.

Как это работает:
Если в классе, помеченном @Repository, возникает исключение, связанное с доступом к данным (например, SQLException),
Spring автоматически преобразует его в одно из исключений из иерархии DataAccessException.

Это позволяет абстрагироваться от специфики конкретной базы данных и работать с унифицированными исключениями.

Пример:

```java

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new UserRowMapper(), id);
    }
}
```

Если в этом коде возникнет SQLException, Spring преобразует его в DataAccessException.

С @Component:
Если бы мы использовали @Component, то исключения не были бы автоматически преобразованы, и пришлось бы вручную
обрабатывать специфичные для базы данных исключения.

3. Семантика и читаемость кода
   @Repository явно указывает, что класс является частью слоя доступа к данным (Data Access Layer). Это делает код более
   читаемым и понятным для других разработчиков.

@Component — это общая аннотация, которая не несет в себе специфической семантики. Она подходит для любых бинов, которые
не относятся к слою данных, сервисам или контроллерам.

4. Интеграция с другими механизмами Spring
   @Repository автоматически интегрируется с механизмами Spring, такими как управление транзакциями (@Transactional) и
   AOP (аспектно-ориентированное программирование).

@Component не предоставляет такой интеграции "из коробки".

5. Когда использовать @Component, а когда @Repository?
   Используйте @Repository, если:
   Класс работает с данными (например, DAO, репозиторий).

Вы хотите, чтобы Spring автоматически обрабатывал исключения, связанные с доступом к данным.

Вы хотите явно указать, что класс является частью слоя доступа к данным.

Используйте @Component, если:
Класс не относится к слою данных, сервисам или контроллерам.

Вы хотите пометить класс как Spring-бин без дополнительной семантики.

Пример использования @Component

```java

@Component
public class NotificationService {

    public void sendNotification(String message) {
        System.out.println("Sending notification: " + message);
    }
}
```

Здесь NotificationService — это просто бин, который не связан с доступом к данным, поэтому используется @Component.

Пример использования @Repository

```java

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new UserRowMapper(), id);
    }
}
```

Здесь UserRepository — это бин, который работает с базой данных, поэтому используется @Repository.


### 
