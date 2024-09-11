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
- [ ] Увеличение размера памяти сервера базы данных
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
    + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/docker/docker.md)

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
- [ ] API объект для управления входящим внешним доступом к сервисам в кластере
- [ ] Инструмент для автоматического развертывания контейнеров
  + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/docker/docker.md)

* В контексте микросервисной архитектуры и Kubernetes, какая стратегия лучше описывает подход, при котором микросервисы взаимодействуют напрямую друг с другом без централизованного управления?

- [ ] Оркестровка
- [x] Хореография
- [ ] Синхронизация
- [ ] Агрегация
  + [объяснение](https://github.com/VasiliyVelikyy/InterviewQuestions/blob/main/resourses/theory/docker/docker.md)