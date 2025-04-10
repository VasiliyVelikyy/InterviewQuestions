* Какие из перечисленных являются ключевыми компонентами JVM (Java Virtual Machine)?

- [x] Компилятор JIT (Just-In-Time)
- [x] Сборщик мусора
- [x] Интерпретатор
- [ ] Менеджер памяти

* Какой сборщик мусора в JVM не использует паузы для работы с большими кучами памяти, чтобы минимизировать задержки в
  приложениях?

- [ ] Serial Garbage Collector
- [ ] Parallel Garbage Collector
- [ ] CMS (Concurrent Mark Sweep) Garbage Collector
- [x] G1 (Garbage-First) Garbage Collector

* Java Collections FrameworkКакой из следующих интерфейсов не является частью Java Collections Framework?

- [ ] List
- [ ] Set
- [ ] Map
- [x] Stream

* Какой из следующих методов используется в Stream API для выполнения операций сокращения, например, для подсчета
  элементов, нахождения суммы или нахождения максимального элемента в потоке?

- [ ] map()
- [ ] filter()
- [x] reduce()
- [ ] collect()

* Какой ключевой слово в Java используется для обеспечения того, чтобы только один поток исполнял блок кода
  одновременно?

- [x] synchronized
- [ ] volatile
- [ ] static
- [ ] transient

* Какой метод класса Object в Java используется для того, чтобы заставить текущий поток ждать, пока другой поток не
  вызовет
  notify() или notifyAll() на том же объекте?

- [x] wait()
- [ ] sleep()
- [ ] yield()
- [ ] join()

* Какая структура данных наилучшим образом подходит для реализации кэша, где важны как быстрый доступ к элементам, так и
  возможность учитывать порядок их использования?

- [ ] HashMap
- [x] LinkedHashMap
- [ ] ArrayList
- [ ] TreeSet

* Какая структура данных обеспечивает наиболее эффективное добавление и удаление элементов в начале списка?

- [x] LinkedList
- [ ] ArrayList
- [ ] HashSet
- [ ] ArrayDeque

* Какой механизм в ООП позволяет одному классу использовать свойства и методы другого класса?

- [ ] Инкапсуляция
- [ ] Полиморфизм
- [x] Наследование
- [ ] Композиция

* Какой принцип ООП позволяет разным классам иметь методы с одинаковым именем, но разной реализацией?

- [ ] Инкапсуляция
- [x] Полиморфизм
- [ ] Наследование
- [ ] Абстракция

* Какова цель профилирования приложения?

- [ ] Увеличение количества функций приложения
- [ ] Улучшение пользовательского интерфейса
- [x] Оптимизация производительности и использования ресурсов
- [ ] Расширение сетевых возможностей приложения

* Какой из следующих видов профилировщиков анализирует использование памяти приложением?

- [ ] CPU Profiler
- [x] Memory Profiler
- [ ] Thread Profiler
- [ ] I/O Profiler

* Какой метод является наиболее эффективным при поиске утечек памяти в Java приложениях?

- [ ] Проверка кода на наличие циклических зависимостей
- [ ] Мониторинг использования CPU
- [x] Анализ heap dump
- [ ] Трассировка сетевого трафика

* Какой инструмент может быть использован для анализа heap dump в Java приложениях?

- [ ] JConsole
- [x] VisualVM
- [ ] Git
- [ ] Jenkins

* Какой термин описывает время выполнения алгоритма в худшем случае?

- [ ] Амортизационная сложность
- [ ] Средняя сложность
- [ ] Лучшая сложность
- [x] Сложность в худшем случае

* Какая нотация чаще всего используется для описания сложности алгоритмов?

- [x] Нотация O (Большое О)
- [ ] Нотация Ω (Большое Омега)
- [ ] Нотация Θ (Большое Тета)
- [ ] Нотация о (малое о)

* Какое действие поможет оптимизировать следующий SQL запрос для улучшения его производительности?

```SELECT * FROM Orders WHERE CustomerID IN (SELECT CustomerID FROM Customers WHERE Country = ‘Germany');```

- [ ] Добавление индекса к столбцу Country в таблице Customers
- [x] Использование INNER JOIN вместо подзапроса
- [ ] Увеличение размеров памяти у сервера базы данных
- [ ] Изменение типа данных столбца CustomerID
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/database/db.md)

* Какой тип JOIN в SQL используется для возвращения всех строк из левой таблицы и сопоставленных строк из правой
  таблицы?

- [ ] INNER JOIN
- [x] LEFT JOIN
- [ ] RIGHT JOIN
- [ ] FULL JOIN

* Какой из следующих вариантов лучше всего описывает цель использования индексов в базах данных?

- [ ] Увеличение объема хранимых данных
- [ ] Улучшение безопасности данных
- [x] Оптимизация скорости запросов
- [ ] Автоматическое шифрование данных

* Какое утверждение наилучшим образом описывает различие между первичным ключом и составным ключом в базе данных?

- [x] Первичный ключ может содержать только одно поле, в то время как составной ключ состоит из двух или более полей.
- [ ] Первичный ключ автоматически создает индексы, в то время как составной ключ требует ручного создания индексов.
- [ ] Первичный ключ используется только для индексации, в то время как составной ключ используется для шифрования
  данных.
- [ ] Первичный ключ и составной ключ функционально идентичны и отличаются только названием.
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/database/db.md)

* Какой компонент ACID обеспечивает, что транзакция либо полностью выполнится, либо не выполнится вообще?

- [x] Atomicity (Атомарность)
- [ ] Consistency (Согласованность)
- [ ] Isolation (Изоляция)
- [ ] Durability (Стойкость)

* Какой этап нормализации устраняет частичные функциональные зависимости?

- [ ] Первая нормальная форма (1NF)
- [x] Вторая нормальная форма (2NF)
- [ ] Третья нормальная форма (3NF)
- [ ] Нормальная форма Бойса-Кодда (BCNF)
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/database/normalization_db.md)

* Какой метод миграции данных наиболее подходит для больших объемов данных с минимальным временем простоя?

- [x] Перенос данных в реальном времени
- [ ] Использование ETL-процессов (Извлечение, Трансформация, Загрузка)
- [ ] Прямое копирование файлов базы данных
- [ ] Построчное копирование данных
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/database/db.md)

* Какой шаг является критически важным после завершения миграции данных?

- [ ] Повторное использование старой базы данных для других задач
- [ ] Немедленное удаление исходных данных для освобождения места
- [x] Проведение тестирования целостности и точности мигрированных данных
- [ ] Увеличение объема хранилища для мигрированных данных
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/database/db.md)

* Какой принцип SOLID подразумевает, что классы должны быть открыты для расширения, но закрыты для модификации?

- [ ] Single Responsibility Principle
- [x] Open/Closed Principle
- [ ] Liskov Substitution Principle
- [ ] Dependency Inversion Principle

* Какой принцип SOLID утверждает, что объекты в программе можно заменить их наследниками без изменения правильности
  программы?

- [ ] Single Responsibility Principle
- [ ] Interface Segregation Principle
- [x] Liskov Substitution Principle
- [ ] Dependency Inversion Principle

* Какой компонент микросервисной архитектуры отвечает за маршрутизацию запросов клиента к соответствующему сервису?

- [ ] Service Mesh
- [x] API Gateway
- [ ] Config Server
- [ ] Service Registry
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/patterns/softwarePatterns.md)

* Какой паттерн позволяет микросервисам динамически обнаруживать и взаимодействовать друг с другом?

- [ ] Service Mesh
- [ ] API Gateway
- [x] Service Discovery
- [ ] Circuit Breaker
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/patterns/softwarePatterns.md)

* Какой паттерн проектирования GoF позволяет создавать объекты, не специфицируя точные классы объектов, которые будут
  созданы?

- [ ] Адаптер
- [ ] Строитель
- [x] Фабричный метод
- [ ] Одиночка
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/patterns/softwarePatterns.md)

* Какой паттерн проектирования GoF позволяет объекту изменять свое поведение, когда его внутреннее состояние меняется?

- [ ] Стратегия
- [ ] Декоратор
- [x] Состояние
- [ ] Наблюдатель
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/patterns/softwarePatterns.md)

* Какое утверждение наиболее точно описывает синхронное взаимодействие в архитектуре приложений?

- [ ] Клиент отправляет запрос и может продолжать свою работу, не ожидая ответа
- [x] Клиент отправляет запрос и блокируется до получения ответа от сервера
- [ ] Взаимодействие происходит без использования HTTP протокола
- [ ] Ответы сервера передаются без предварительных запросов от клиента

* Какое утверждение лучше всего описывает асинхронное взаимодействие в архитектуре приложений?

- [ ] Клиент должен оставаться в онлайн и ожидать ответа после отправки запроса
- [ ] Клиент и сервер должны синхронизировать свои часы для успешного взаимодействия
- [x] Клиент отправляет запрос и продолжает свою работу без ожидания немедленного ответа
- [ ] Асинхронное взаимодействие возможно только при использовании веб-сокетов

* Какой файл используется для определения окружения и инструкций для создания образа Docker?

- [x] Dockerfile
- [ ] docker-compose.yml
- [ ] Dockerimage.txt
- [ ] Dockerconfig.ini
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/docker/docker.md)

* Какая команда Docker используется для запуска контейнера из образа?

- [ ] docker start
- [x] docker run
- [ ] docker pull
- [ ] docker create
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/docker/docker.md)

* Какая инструкция в Dockerfile используется для копирования файлов из локальной файловой системы в образ?

- [x] COPY
- [ ] ADD
- [ ] PASTE
- [ ] MOVE
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/docker/docker.md)

* Какое преимущество предоставляет многоступенчатая сборка в Dockerfile?

- [x] Уменьшает размер конечного образа, удаляя ненужные зависимости
- [ ] Автоматически обновляет образ при изменении исходного кода
- [ ] Увеличивает скорость сборки образа за счет кэширования
- [ ] Добавляет дополнительный уровень безопасности путем изоляции стадий сборки
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/docker/docker.md)

* Как в docker-compose.yml файле обозначается сервис, который должен быть создан?

- [x] Под ключом services
- [ ] Под ключом containers
- [ ] Под ключом applications
- [ ] Под ключом components
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/docker/docker.md)

* Как в docker-compose.yml указать, что один сервис зависит от другого и должен быть запущен после него?

- [ ] Используя ключ links
- [x] Используя ключ depends_on
- [ ] Используя ключ after
- [ ] Используя ключ before
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/docker/docker.md)

* При подготовке Dockerfile для развертывания в Kubernetes, какую практику следует применять для обеспечения
  безопасности?

- [ ] Запускать приложение от пользователя root
- [ ] Использовать многоступенчатую сборку для уменьшения размера образа
- [ ] Задавать статические IP-адреса для контейнеров
- [x] Использовать непривилегированного пользователя для запуска приложения
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/docker/docker.md)

* Какую особенность следует учитывать при подготовке Dockerfile для совместимости с OpenShift?

- [ ] OpenShift требует, чтобы все образы были основаны на образах Red Hat
- [ ] Приложения должны слушать только порт 80
- [x] Образы должны поддерживать запуск от произвольного пользователя
- [ ] Все внешние зависимости должны быть упакованы внутрь образа
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/docker/docker.md)

* Какой компонент в архитектуре Kubernetes отвечает за распределение и управление сетевым трафиком между контейнерами?

- [ ] Kubelet
- [x] Kube-proxy
- [ ] API Server
- [ ] Scheduler
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/docker/docker.md)

* Что такое внедрение зависимостей (Dependency Injection) в Spring Framework?

- [ ] Механизм, позволяющий разрешать зависимости между классами на этапе компиляции
- [x] Способ передачи зависимостей в объекты во время выполнения приложения
- [ ] Механизм, обеспечивающий безопасность взаимодействия между компонентами приложения
- [ ] Техника, используемая для уменьшения размера и сложности исходного кода
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/spring/spring.md)

* Чем OpenShift отличается от Kubernetes?

- [ ] OpenShift не поддерживает контейнеры
- [x] OpenShift предоставляет дополнительные функции управления, CI/CD и пользовательский интерфейс
- [ ] OpenShift использует собственную систему оркестрации вместо Kubernetes
- [ ] OpenShift поддерживает только контейнеры Docker, в то время как Kubernetes поддерживает любой тип контейнера
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/docker/docker.md)

* Какая команда используется для создания Deployment в Kubernetes, который управляет группой одинаковых подов?

- [x] kubectl create deployment <name> --image=<image>
- [ ] kubectl create pod <name> --image=<image>
- [ ] kubectl apply -f deployment.yaml
- [ ] kubectl set deployment <name> --image=<image>
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/docker/docker.md)

* Для чего в OpenShift используется объект Route и как его создать?

- [ ] Route используется для внутренней маршрутизации между подами; создается с помощью oc create route internal
  --service=<service-name>
- [ ] Route используется для определения политик безопасности; создается с помощью oc create policy <name>
- [x] Route обеспечивает внешний доступ к сервисам; создается с помощью oc expose service <service-name>
- [ ] Route управляет хранилищами данных; создается с помощью oc set storage <storage-name>
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/docker/docker.md)

* Что такое Ingress в контексте Kubernetes?

- [ ] Механизм для внутренней балансировки нагрузки между подами
- [ ] Политика безопасности для контроля входящего трафика
- [x] API объект для управления входящим внешним доступом к сервисам в кластере
- [ ] Инструмент для автоматического развертывания контейнеров
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/docker/docker.md)

* В контексте микросервисной архитектуры и Kubernetes, какая стратегия лучше описывает подход, при котором микросервисы
  взаимодействуют напрямую друг с другом без централизованного управления?

- [ ] Оркестровка
- [x] Хореография
- [ ] Синхронизация
- [ ] Агрегация
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/docker/docker.md)

* Что такое бин в контексте Spring Framework?

- [ ] Сервлет, управляемый контейнером Spring
- [x] Объект, инстанцированный, собранный, управляемый контейнером Spring IoC
- [ ] Динамически генерируемый прокси-класс для аспектно-ориентированного программирования
- [ ] Конфигурационный файл, определяющий структуру Spring приложения

* Каким образом можно зарегистрировать бин в Spring контейнере?

- [ ] Добавлением аннотации @Bean над классом
- [ ] Добавлением аннотации @RegisterBean над методом в конфигурационном классе
- [x] Использованием XML конфигурационного файла
- [ ] Объявлением переменной типа Bean в любом классе
    + ***неправильный вариант 1. @Bean можно ставить над методом в конфигурационном классе***

* Какой скоуп бина в Spring Framework делает его доступным только в пределах одного HTTP запроса?

- [ ] singleton
- [ ] prototype
- [x] request
- [ ] session

* В чем ключевое отличие между скоупами singleton и prototype в Spring Framework?

- [x] singleton создает единственный экземпляр бина на весь контейнер, в то время как prototype создает новый экземпляр
  каждый раз, когда он запрашивается
- [ ] singleton поддерживает асинхронное выполнение, в то время как prototype синхронное
- [ ] singleton используется только для stateless бинов, а prototype только для stateful
- [ ] singleton бины могут быть внедрены только в prototype бины, но не наоборот

* Что такое стартер в контексте Spring Boot?

- [ ] Специальный класс, который запускает приложение Spring Boot
- [x] Компонент, который предоставляет настройки по умолчанию для определенной функциональности
- [ ] Модуль для автоматической конфигурации безопасности в приложении
- [ ] Инструмент для мониторинга и управления состоянием приложения
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/spring/spring.md)

* Как стартеры Spring Boot работают изнутри для автоматической конфигурации приложения?

- [ ] Они используют механизм рефлексии для динамического создания бинов во время выполнения
- [ ] Они применяют принцип инверсии управления для инициализации и настройки компонентов
- [x] Они содержат @Configuration классы, которые Spring Boot автоматически обнаруживает и применяет
- [ ] Они напрямую модифицируют код приложения для интеграции необходимых зависимостей
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/spring/spring.md)

* Какие основные цели использования прокси-объектов в Spring Framework?

- [ ] Улучшение производительности приложения
- [ ] Добавление дополнительной безопасности к методам компонентов
- [x] Реализация внедрения зависимостей и аспектно-ориентированного программирования (AOP)
- [ ] Оптимизация работы сетевых запросов
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/spring/spring.md)

* Какое основное преимущество внедрения зависимостей в Spring Framework?

- [ ] Уменьшение использования памяти
- [ ] Улучшение производительности
- [x] Уменьшение связанности между компонентами
- [ ] Увеличение сложности исходного кода

* Какие способы внедрения зависимостей поддерживает Spring Framework?

- [x] Конструкторы, методы и поля
- [ ] Файлы конфигурации YAML и JSON
- [ ] Через REST API
- [ ] Внедрение зависимостей не поддерживается в Spring

* Чем отличается автоматическое внедрение зависимостей (Autowiring) от явного внедрения зависимостей в Spring?

- [ ] Автоматическое внедрение зависимостей требует явного указания зависимостей в конфигурационных файлах, в то время
  как явное внедрение происходит автоматически
- [ ] Автоматическое внедрение зависимостей использует рефлексию для определения зависимостей, в то время как явное
  внедрение требует явного создания и передачи зависимостей
- [ ] Автоматическое внедрение зависимостей происходит на этапе выполнения приложения, в то время как явное
  внедрение должно быть определено на этапе компиляции
- [x] Автоматическое внедрение зависимостей позволяет Spring самостоятельно определять и внедрять
  зависимости, основываясь на типах и аннотациях, в то время как явное внедрение требует явного указания
  зависимостей в конфигурационных файлах или коде
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/spring/spring.md)

* Какие этапы включает процесс создания бина в Spring Framework?

- [x] Инстанцирование, настройка свойств, вызов методов
- [ ] Компиляция, тестирование, развертывание
- [ ] Сборка, конфигурирование, запуск
- [ ] Планирование, выполнение, мониторинг

* Какие основные этапы определяют жизненный цикл бина в Spring Framework?

- [ ] Создание, использование, уничтожение
- [ ] Загрузка, выполнение, выгрузка
- [x] Инстанцирование, инициализация, уничтожение
- [ ] Настройка, подключение, обработка
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/spring/spring.md)

* Какое основное отличие между аннотацией @Qualifier и @Primary в Spring Framework?

- [ ] @Qualifier используется для обозначения основного бина, а @Primary для выбора конкретной реализации
- [x] @Qualifier позволяет выбрать бин по имени, а @Primary определяет бин по умолчанию
- [ ] @Qualifier определяет бин по типу, а @Primary по имени
- [ ] @Qualifier используется только для бинов с аннотацией @Autowired, а @Primary для всех типов внедрения зависимостей

* Какие способы существуют для обхода закольцованных зависимостей (circular dependencies) в Spring Framework?

- [ ] Использование аннотации @CircularDependency
- [ ] Изменение порядка объявления бинов в конфигурационном файле
- [x] Внедрение зависимости через методы вместо конструкторов
- [x] Рефакторинг приложения для избегания циклических зависимостей
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/spring/spring.md)

* Что представляет собой Spring Cloud и какие проблемы он помогает решать?

- [ ] Spring Cloud - это микросервисный фреймворк, решающий проблемы масштабируемости и отказоустойчивости приложений
- [ ] Spring Cloud - это расширение Spring Framework для работы с облачными сервисами, такими как AWS, Azure и Google
  Cloud Platform
- [x] Spring Cloud - это коллекция инструментов и библиотек для построения и развертывания распределенных систем на
  основе микросервисов
- [ ] Spring Cloud - это библиотека для разработки облачных приложений, обеспечивающая интеграцию с платформами
  виртуализации

* Что представляет собой Feign Client в Spring Cloud и какие основные настройки доступны для его конфигурации?

- [ ] Feign Client - это инструмент для выполнения HTTP-запросов в микросервисных архитектурах, основанный на
  аннотациях, таких как @FeignClient
- [ ] Feign Client - это компонент Spring Cloud для обнаружения сервисов и автоматической настройки маршрутов
- [x] Feign Client - это библиотека для взаимодействия с RESTful API в микросервисных системах, обеспечивающая
  декларативный подход к созданию HTTP-клиентов
- [ ] Feign Client - это компонент Spring Cloud для автоматической генерации клиентских интерфейсов на основе
  Swagger-спецификации
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/spring/spring.md)

* Какие основные настройки доступны для Feign Client в Spring Cloud?

- [x] @FeignClient, url, path
- [ ] connectTimeout, readTimeout, requestInterceptors
- [ ] url, port, path
- [ ] maxConnections, proxyHost, proxyPort

* Что такое Actuator в Spring Boot и какую функциональность он предоставляет?

- [ ] Actuator - это компонент Spring Boot, предоставляющий возможность управления жизненным циклом приложения
- [x] Actuator - это библиотека для мониторинга и управления приложением в реальном времени
- [ ] Actuator - это инструмент для автоматического обнаружения и исправления ошибок в приложении
- [ ] Actuator - это модуль Spring Boot, предоставляющий готовую конфигурацию для развертывания приложения на облачных
  платформах

* Какие паттерны проектирования часто используются при разработке микросервисных приложений?

- [ ] Singleton, Observer, Factory
- [ ] MVC, Front Controller, Data Access Object (DAO)
- [x] Service Discovery, Circuit Breaker, Gateway
- [ ] Builder, Prototype, Strategy
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/patterns/softwarePatterns.md)

* Какие основные типы связей между Entity существуют в Java Persistence API (JPA)?

- [ ] Однонаправленные и двунаправленные
- [ ] Однозначные и многозначные
- [x] Один-к-одному, Один-ко-многим, Многие-к-одному, Многие-ко-многим
- [ ] Первичные и внешние

* Какие способы реализации пагинации данных с использованием JpaRepository в Spring Data JPA?

- [ ] Методы findAll() и findByCriteria()
- [ ] Использование аннотаций @Pageable и @Query
- [x] Методы findAll(Pageable pageable) и findAllByCriteria(Pageable pageable)
- [ ] Использование класса PageRequest и методов findPage()
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/database/jpa.md)

* Какие способы описания составного ключа (composite key) в Entity существуют в JPA?

- [x] Использование аннотации @EmbeddedId
- [x] Определение отдельного класса для составного ключа и его аннотация @IdClass
- [ ] Использование аннотации @CompositeId
- [ ] Напрямую указание полей составного ключа с аннотацией @Id
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/database/jpa.md)

* Какие существуют стратегии загрузки связанных Entity в JPA?

- [x] Ленивая (Lazy) и энергичная (Eager)
- [ ] Отложенная (Deferred) и немедленная (Immediate)
- [ ] Односторонняя (One-sided) и двусторонняя (Two-sided)
- [ ] Ручная (Manual) и автоматическая (Automatic)

* Какие способы существуют для создания нативных SQL запросов в Spring Data JPA?

- [ ] Использование аннотации @NativeQuery
- [ ] Методы, именуемые с префиксом native, например, nativeQueryFindBy...()
- [x] Аннотация @Query с nativeQuery=true
- [ ] Использование класса NativeQuery из пакета javax.persistence
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/database/jpa.md)

* В чем основные различия между RabbitMQ и Apache Kafka?

- [x] RabbitMQ - это распределенная система обмена сообщениями, основанная на протоколе AMQP, в то время как Kafka - это
  распределенная система потоковых данных, основанная на журнале событий.
- [ ] RabbitMQ - это база данных с открытым исходным кодом, а Kafka - это инструмент для развертывания виртуальных
  машин.
- [ ] RabbitMQ обрабатывает только сообщения в реальном времени, в то время как Kafka может обрабатывать как сообщения в
  реальном времени, так и хранимые данные.
- [ ] RabbitMQ используется для обмена сообщениями в микросервисных архитектурах, а Kafka - для обработки потоков данных
  в больших объемах.
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/brokers/brokersOfQuery.md)

* Выберите верное утверждение

- [ ] RabbitMQ обеспечивает более высокую пропускную способность и надежность, чем Kafka.
- [ ] Kafka обладает обработкой больших объемов данных по сравнению с RabbitMQ.
- [ ] Обе системы поддерживают гарантированную доставку сообщений и обеспечивают высокую доступность.
- [ ] RabbitMQ обеспечивает строгую гарантию доставки сообщений в правильном порядке, в то время как Kafka не
  гарантирует порядок сообщений.

* Для чего используется Exchange в RabbitMQ?

- [ ] Для управления правами доступа к очередям
- [ ] Для управления состоянием подключений к брокеру сообщений
- [x] Для определения маршрутизации сообщений к очередям
- [ ] Для управления жизненным циклом сообщений в очереди
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/brokers/brokersOfQuery.md)

* Какие семантики доставки сообщений поддерживаются в Apache Kafka?

- [x] "At Least Once", "Exactly Once", "At Most Once"
- [ ] "Guaranteed", "Unordered" и "Best Effort"
- [ ] "Sequential", "Parallel" и "Concurrent"
- [ ] "Persistent", "Transient" и "Reliable"
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/brokers/brokersOfQuery.md)

* Какие способы оптимизации кластера Apache Kafka можно применить для повышения производительности и надежности?

- [ ] Увеличение размера буферизации и частоты сброса данных на диск
- [x] Добавление большего количества брокеров в кластер и увеличение числа партиций
- [ ] Использование компрессии данных и уменьшение числа реплик
- [ ] Ограничение доступа к темам и уменьшение числа подписчиков
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/brokers/brokersOfQuery.md)

* Что такое мокирование (mocking) в контексте разработки программного обеспечения?

- [ ] Процесс создания копии основного приложения для тестирования
- [x] Замена реальных объектов имитациями (mocks) для тестирования кода
- [ ] Процесс выявления и устранения ошибок в коде
- [ ] Создание виртуального окружения для разработки и тестирования

* Какие факторы следует учитывать при выборе тестовых данных для модульного тестирования?

- [x] Разнообразие входных данных для охвата всех возможных сценариев использования
- [ ] Минимизация объема тестовых данных для ускорения выполнения тестов
- [ ] Использование реальных данных из рабочего окружения для максимальной точности
- [ ] Ориентация на негативные сценарии и крайние случаи

* Какие основные принципы следует соблюдать при написании юнит-тестов?

- [x] Тестируйте только публичные методы класса
- [x] Убедитесь, что каждый тест проверяет только одну конкретную функциональность
- [ ] Используйте только случайно генерированные данные
- [ ] Максимально уменьшайте количество тестов для сокращения времени выполнения

* Что представляет собой Testcontainers и как он используется в приложениях Spring?

- [x] Testcontainers - это инструмент для автоматизированного тестирования контейнеризированных приложений, который
  обеспечивает создание и управление контейнерами Docker в тестовом окружении
- [ ] Testcontainers - это библиотека для создания и управления виртуальными машинами для запуска тестов
- [ ] Testcontainers - это фреймворк для тестирования микросервисов в распределенных системах
- [ ] Testcontainers - это компонент Spring для интеграции с внешними сервисами и базами данных
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/spring/spring.md)