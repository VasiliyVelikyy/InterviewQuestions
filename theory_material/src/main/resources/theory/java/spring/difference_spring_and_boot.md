## Вопросы

### Что такое Spring Boot?

Spring Boot — мощный инструмент, который позволяет разработчикам быстро создавать приложения на основе Spring
фреймворка прикладывая для этого минимум усилий.
Т.к. в данном фреймворке реализован принцип инверсии зависимостей, с его помощью можно легко настраивать и запускать
приложения, используя встроенные компоненты, что делает Spring Boot популярным выбором среди разработчиков.

### Так, а Spring что такое?

Spring — это фреймворк, реализующий принцип инверсии зависимостей и предоставляющий набор инструментов и библиотек,
которые упрощают и ускоряют процесс разработки, позволяя сосредоточиться на бизнес-логике приложения.

### В чем отличия?

Главными понятиями Spring Boot является стартеры и автоконфигурации.

Отлиичие спринга от спринг бута - в спринге нужно самому конфигурировать кучу объектов, например для работы c hibernate -  TransactionalManager,
DataSource, EntityManagerFactoryBean и тд. А спритнг бут решил что все конфиги общие - даваайте вынесем все в property файл. Пароль урл от базы.
Бибилиотеки которые имеют префикс spring-boot-starter подразумевают что в них заложены конфигурации, которые автоматически настраивают объекты связанные с работой стартера.

В итоге мы имеем спринг бут - еще более автоматизированный фремворк. Бут упростил и облегчил интеграцию с другими модулями. С одной стороны хорошо - можно использовать фреймворк с низкой точкой знаний но это и проблема тоже - при переходе на спринг у нас будет недостаточно знаний какие настройки под капотом происходят, они видят только property файл.


Для чего в спринге javaConfig- возможность делать класс конфигураций и создавать бины на основе чужих классов при промощи аннотации @Bean.  В xml можно было писать названия чужих классов.
#### Cтартеры

***Коротко:*** Набор библиотек с уже настроенными параметрами. Уже сделанный jar с бинами (либо мы сами можем его
сделать). Его boot затягивает в проект и использует конфигурации из стартера. Тоесть все настраивает сам.

***Полно:*** Преднастроенные наборы зависимостей, которые позволяют
быстро подключать нужный функционал к приложению, не задумываясь о сложных настройках зависимостей.
Каждый стартер — это одна Maven-зависимость, которая сама подтягивает все необходимые библиотеки (transitive
dependencies) для определённого сценария использования.

#### Автоконфигурации

Классы которые находятся в стартере и готовые к использованию

### А мы не можем в чистом спринге взять и сделать JAR с бинами и конфигом и просто подключить его к приложению?

Можно — в чистом Spring (без Spring Boot) тоже можно создать JAR с бинами и конфигурацией и подключить его к другому
проекту.
Но Spring Boot упрощает этот процесс, особенно благодаря стартерам и автоматическим настройкам. Стартеры в Spring Boot —
это не просто JAR-файлы, а умные модули с автонастройкой

| Возможность                       | Spring                                            | spring boot                                   |
|-----------------------------------|---------------------------------------------------|-----------------------------------------------|
| Создание JAR с бинами             | Можно                                             | Можно                                         |
| Автоматическое обнаружение бинов  | Нет (нужно вручную импортировать или сканировать) | Да (через автонастройки и `spring.factories`) |
| Автоконфигурация                  | Нет                                               | Да                                            |
| Удобство повторного использования | Требует ручной настройки                          | Гораздо проще                                 |

Для того чтобы бут нашел конфигурационные файлы и на основе их создал бины, нужно в дериктории 
`resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`
добавить путь до файла например `ru.moskalev.MyAppConfig`

#### Почему Spring Boot удобнее?

1) Spring Boot позволяет делать так называемые автонастраиваемые стартеры, которые:

   Самостоятельно регистрируют свои бины при подключении JAR-файла.
   Не требуют ручного @Import или `@ComponentScan`.
   Могут считывать настройки из `application.properties`.
   Это достигается за счёт:

   Файла `META-INF/spring.factories`
   Аннотации `@EnableAutoConfiguration`
   Использования условных аннотаций (`@ConditionalOnClass, @ConditionalOnMissingBean` и др.)
2) Деплой в embedeed сервер и простота дебагинга. В спринге если используется tomcat с разверткой в дериктории webapps,
   нужно исп для дебагинга танец с бубном.

### Как в boot происходит поиск бинов?

В большинстве случаев в Spring Boot аннотация `@ComponentScan` не требуется, потому что она уже неявно применяется
через `@SpringBootApplication` .

Что делает `@ComponentScan`?
Эта аннотация указывает Spring, где искать компоненты (`@Component, @Service, @Repository` и т.д.), чтобы автоматически
зарегистрировать их как бины в контексте.

```java
@ComponentScan(basePackages = "com.example")
```

🚀 А как это работает в Spring Boot?
В Spring Boot основная аннотация — это `@SpringBootApplication`, которая включает в себя три других :

```java
@Configuration
@EnableAutoConfiguration
@ComponentScan
```

##### Boot Так же как и чистый спринг поддерживает xml конфигурацию.

Spring Boot предпочитает конфигурацию на основе Java. Хотя можно использовать SpringApplication и с XML-исходниками, мы
обычно рекомендуем, чтобы основным исходником был один класс, аннотированный @Configuration. Обычно класс, определяющий
метод main, является подходящим кандидатом на роль основной @Configuration.

##### Импорт дополнительных конфигурационных классов

Вам не нужно помещать все аннотации @Configuration в один класс. Можно использовать аннотацию @Import для импорта
дополнительных классов конфигурации. Кроме того, можно использовать аннотацию @ComponentScan для автоматического сбора
всех компонентов Spring, включая классы с аннотацией @Configuration.

##### Импорт XML-конфигурации

Если в обязательном порядке требуется использовать конфигурацию на основе XML, мы рекомендуем все же начать с класса,
помеченного аннотацией @Configuration. Затем можно использовать аннотацию @ImportResource для загрузки конфигурационных
XML-файлов.

##### Автоконфигурация

Автоконфигурация в Spring Boot пытается автоматически конфигурировать ваше приложение Spring на основе добавленных
jar-зависимостей. Например, если HSQLDB находится в вашем classpath, но вы не сконфигурировали вручную никаких бинов для
подключения к базе данных, то Spring Boot автоматически сконфигурирует резидентную базу данных.
Необходимо явно согласиться на автоконфигурацию, добавив аннотации @EnableAutoConfiguration или @SpringBootApplication в
один из ваших классов с аннотацией @Configuration.

### А как в чистом спринге происходит поиск бинов?

1. Через аннотацию @ComponentScan в конфигурационном классе
2. Через XML-конфигурацию
   Если вы используете XML вместо Java-based конфигурации, можно использовать <context:component-scan>.
3. Смешанный подход
    - Запуск через Java-config, но подключение XML-конфигурации
   ```java
    @Configuration
    @ComponentScan("com.example.app")
    @ImportResource("classpath:applicationContext.xml") // ← Подключаем XML
    public class AppConfig {
    }
    ```
   запуск контекст
   `AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);`
    - Запуск из XML, использование @ComponentScan и аннотаций.
      Если ты стартуешь с XML, но хочешь использовать `@Component, @Service` и т.д., просто добавь в XML:
      ```<context:component-scan base-package="com.example.app"/>```

      ```xml
       <?xml version="1.0" encoding="UTF-8"?>
       <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:context="http://www.springframework.org/schema/context"
            xsi:schemaLocation="http://www.springframework.org/schema/beans
                                http://www.springframework.org/schema/beans/spring-beans.xsd
                                http://www.springframework.org/schema/context
                                http://www.springframework.org/schema/context/spring-context.xsd">

        <!-- Включаем сканирование компонентов -->
        <context:component-scan base-package="com.example.app"/>

        <!-- Можно определить бины вручную -->
        <bean id="myBean" class="com.example.app.MyBeanFromXml">
        <property name="message" value="Hello from XML!" />
        </bean>

       </beans>
      ```

### Какие бины автоконфигурирет boot?

#### DataSource

в чистом спринге нам нужно

```java

@Bean
public DataSource dataSource() {
    return DataSourceBuilder.create()
            .url("jdbc:mysql://localhost:3306/mydb")
            .username("root")
            .password("pass")
            .build();
}
```

В Spring Boot: добавление в файл properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=pass
```

#### DataSourceTransactionManager

Назначение: управление транзакциями для JDBC.
В Spring Boot: создаётся автоматически, если есть DataSource.
В чистом Spring: нужно создавать вручную или подключать Spring Transaction.

```java

@Bean
public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
}
```

#### JdbcTemplate

Назначение: упрощает работу с SQL-запросами.
В Spring Boot: создаётся автоматически, если есть DataSource.
В чистом Spring: нужно добавлять вручную.

```java

@Bean
public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
}
```

#### EntityManagerFactory

В Spring Boot: создаётся через `HibernateJpaAutoConfiguration`, если есть зависимости JPA и БД.
В чистом Spring: нужно ручное настройка через `LocalContainerEntityManagerFactoryBean`.

#### MultipartResolver

Назначение: обработка загрузки файлов (multipart/form-data).
В Spring Boot: создаётся автоматически при наличии зависимости spring-web.
В чистом Spring: нужно добавлять вручную:

```java

@Bean
public MultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver();
}
```

#### EmbeddedServletContainerCustomizer

Назначение: настройка встроенного сервера (например, порт, SSL, параметры соединения).
В Spring Boot: доступен по умолчанию.
Пример использования для бута:

```java

@Bean
public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
    return factory -> factory.setPort(8081);
}
```

1. Для чистого спринга нужно настроить параметры сервлет-контейнера.
   Всё делается вручную через web.xml или программно при развертывании на сервере , например, Tomcat, Jetty и т.д.
   Дескриптор развертывания.
   ```xml
   <!-- web.xml -->
   <web-app version="4.0" xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
                                http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd">
   
       <display-name>My Spring App</display-name>
   
       <!-- Можно настроить параметры контекста -->
       <context-param>
           <param-name>contextConfigLocation</param-name>
           <param-value>/WEB-INF/applicationContext.xml</param-value>
       </context-param>
   
       <!-- Listener для загрузки Spring контекста -->
       <listener>
           <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
       </listener>
   
       <!-- DispatcherServlet -->
       <servlet>
           <servlet-name>dispatcher</servlet-name>
           <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
           <init-param>
               <param-name>contextConfigLocation</param-name>
               <param-value>/WEB-INF/dispatcher-servlet.xml</param-value>
           </init-param>
           <load-on-startup>1</load-on-startup>
       </servlet>
   
       <servlet-mapping>
           <servlet-name>dispatcher</servlet-name>
           <url-pattern>/</url-pattern>
       </servlet-mapping>
   
   </web-app>
   ```
   Но в web.xml нельзя настроить порт, SSL, таймауты соединения и т.д.

   Приложение упаковывается в war и засовываеться в папку webapps tomcat или используеться сервер jetty (обычно
   зависимость embedded)
2. Через server.xml / context.xml если вы используете Tomcat
   ```xml
   <Server port="8005" shutdown="SHUTDOWN">
     <Service name="Catalina">
       <Connector port="8080" protocol="HTTP/1.1"
                  connectionTimeout="20000"
                  redirectPort="8443" />
       ...
     </Service>
   </Server>
   ```
   Там можно:

   Менять порт (port)
   Настраивать SSL
   Управлять пулом потоков
   И многое другое
3. Программная настройка (если запускаешь свой embedded сервер)
   Если ты хочешь запустить embedded Tomcat/Jetty вручную (как в Spring Boot), но без Spring Boot, то можно сделать это
   вручную:
   Пример: Embedded Tomcat в чистом Spring
   ```java
   public class MyTomcatApp {
       public static void main(String[] args) throws Exception {
           Tomcat tomcat = new Tomcat();
           tomcat.setPort(8081); // ← изменение порта
           tomcat.getConnector(); // автоматически создаёт Connector
   
           Context context = tomcat.addContext("/", new File(".").getAbsolutePath());
   
           // Регистрация DispatcherServlet
           Tomcat.addServlet(context, "dispatcher", new DispatcherServlet(new MyWebConfig()));
           context.addServletMappingDecoded("/*", "dispatcher");
   
           tomcat.start();
           tomcat.getServer().await();
       }
   }
   
   @Configuration
   @EnableWebMvc
   @ComponentScan("com.example.app")
   class MyWebConfig implements WebMvcConfigurer {}
   ```

#### Jackson2ObjectMapperBuilderCustomizer

Назначение: кастомизация сериализации JSON.
В Spring Boot: применяется ко всем REST-контроллерам.
Например, изменить формат даты:

```java

@Bean
public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
    return builder -> builder.simpleDateFormat("yyyy-MM-dd");
}
```

#### ErrorController

Назначение: обработка ошибок и вывод страницы/JSON при ошибках.
В Spring Boot: реализуется через BasicErrorController.
В чистом Spring: нужно писать свой @ControllerAdvice или @ExceptionHandler.

#### PropertyPlaceholderConfigurer / PropertySourcesPlaceholderConfigurer

Назначение: чтение значений из application.properties.
В Spring Boot: уже настроен по умолчанию.
В чистом Spring: нужно добавлять вручную:

```java

@Bean
public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
}
```

#### DispatcherServlet

Если используем Spring MVC
DispatcherServlet — это главный контроллер в Spring MVC , который принимает все HTTP-запросы и направляет их к нужным
контроллерам, обработчикам и представлениям.

1. через web.xml (традиционный способ) как показано выше `org.springframework.web.servlet.DispatcherServlet` И отдельный
   файл: dispatcher-servlet.xml
   ```xml
   <!-- dispatcher-servlet.xml -->
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:context="http://www.springframework.org/schema/context"
          xmlns:mvc="http://www.springframework.org/schema/mvc"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="
              http://www.springframework.org/schema/beans
              http://www.springframework.org/schema/beans/spring-beans.xsd
              http://www.springframework.org/schema/context
              http://www.springframework.org/schema/context/spring-context.xsd
              http://www.springframework.org/schema/mvc
              http://www.springframework.org/schema/mvc/spring-mvc.xsd">
   
       <!-- Сканирование контроллеров -->
       <context:component-scan base-package="com.example.controller"/>
   
       <!-- Включаем поддержку @RequestMapping -->
       <mvc:annotation-driven/>
   
       <!-- Настройка ViewResolver -->
       <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
           <property name="prefix" value="/WEB-INF/views/"/>
           <property name="suffix" value=".jsp"/>
       </bean>
   
   </beans>
   ```
2. Через Java-based конфигурацию (без web.xml)

```java
public class MyWebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Создаем контекст для DispatcherServlet
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);

        // Регистрируем DispatcherServlet
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
```

```java

@Configuration
@EnableWebMvc
@ComponentScan("com.example.controller")
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
```

### Как увидеть все автоконфигурации?

`mvn spring-boot:run -Dspring-boot.run.arguments="--debug"`
Или добавь в application.properties: `debug=true`

### Как Spring Boot упрощает работу с настройками приложения?

В Spring Boot вы можете хранить все настройки в одном файле `application.properties` (классический формат) или
`application.yml` (более читаемый формат)

```properties
server.port=8081
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=secret
spring.jpa.hibernate.ddl-auto=update
```

или

   ```yml
   server:
     port: 8081

   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/mydb
       username: root
       password: secret
     jpa:
       hibernate:
         ddl-auto: update
   ```

1) Использование свойств в коде через @Value
   ```java
      @Component
   public class MyService {
   
       @Value("${server.port}")
       private int serverPort;
   
       public void printPort() {
           System.out.println("Server is running on port: " + serverPort);
       }
   }
   ```
2) Типобезопасная конфигурация через @ConfigurationProperties
   Если у вас есть группа связанных настроек , можно создать целый класс для их хранения:
   ```java
   @ConfigurationProperties(prefix = "app")
   public class AppProperties {
   
       private String name;
       private String version;
       private List<String> admins = new ArrayList<>();
   
       // Геттеры и сеттеры
   }
   ```
3) Профили: разные настройки для dev/test/prod
   Или указывать активный профиль в application.yml:

```yaml
spring:
  profiles:
    active: dev
```

### Как можно создать исполняемый JAR-файл с помощью Spring Boot?

- В pom указать ```<packaging>jar</packaging>```
- добавить плагин spring-boot-maven-plugin

```xml

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

- вызвать mvn clean package
- После этого в папке target/ будет создан исполняемый JAR-файл, например: my-app-0.0.1-SNAPSHOT.jar
- Запустить его ava -jar my-app-0.0.1-SNAPSHOT.jar

### Что такое Spring Boot Actuator и как он может быть полезен?

это мощный модуль в Spring Boot, который позволяет мониторить и управлять приложением в режиме реального времени. Он
предоставляет готовые эндпоинты (endpoints) для получения информации о состоянии приложения, его конфигурации, метриках
и многом другом.

##### Зачем нужен Spring Boot Actuator?

Actuator особенно полезен на стадии production , когда нужно:

- Проверять работоспособность сервиса (health check)
- Собирать метрики
- Смотреть текущие бины, свойства, логи
- Управлять приложением удалённо

##### Как подключить Spring Boot Actuator?

Добавь зависимость в pom.xml (для Maven):

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

| ENDPOINT              | ОПИСАНИЕ                                                                      |
|-----------------------|-------------------------------------------------------------------------------|
| /actuator/health      | Информация о здоровье приложения (работает ли БД, диск, внешние API и т.д.)   |
| /actuator/info        | Произвольная информация о приложении (версия, описание, git-информация и др.) |
| /actuator/metrics     | Статистика: использование памяти, количество HTTP-запросов, задержки и т.д.   |
| /actuator/env         | Все доступные профили и свойства окружения                                    |
| /actuator/beans       | Список всех Spring бинов в контексте                                          |
| /actuator/configprops | Конфигурационные свойства                                                     |
| /actuator/mappings    | Все зарегистрированные URL-маппинги                                           |
| /actuator/conditions  | Информация о том, какие автоконфигурации были применены, а какие — нет        |
| /actuator/logfile     | Чтение содержимого лог-файла (если указано)                                   |
| /actuator/heapdump    | Получение дампа кучи (для анализа memory leak'ов)                             |
| /actuator/threaddump  | Дамп текущих потоков                                                          |
| /actuator/refresh     | Перезагрузка конфигурации (при использовании Spring Cloud Config)             |
| /actuator/shutdown    | Остановка приложения (отключен по умолчанию)                                  |

По умолчанию не все эндпоинты доступны через HTTP.

Включи их в application.properties или application.yml.

Пример (application.yml):

```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*" # Включить все эндпоинты
  endpoint:
    shutdown:
      enabled: true # Включить shutdown
```

Если вы используете Spring Security , важно защитить эндпоинты Actuator от нежелательного доступа.

Пример настройки безопасности:

```java

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/actuator/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
```

### За счет чего в spring boot получается гибко настраивать контекст
@ConfigurationProperties, @ConditionalOnMissingBean

### В чём отличие между @AutoConfiguration и @Configuration?
@Configuration — базовая аннотация Spring
Это стандартная аннотация из Spring Framework , которая помечает класс как источник определения бинов.
Для нее необходимо чтобы
@AutoConfiguration — специальная аннотация из Spring Boot
Она говорит Spring Boot, что этот класс содержит автоматические настройки , которые должны быть применены только при выполнении определённых условий .
```java
@AutoConfiguration
public class MyAutoConfiguration {

    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }
}
```
Загружаются только если выполняются условия (например, есть определённый класс в classpath)
Не подвержены сканированию компонентов напрямую , а регистрируются через файл META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports

##### Как регистрируются автоконфигурации?
В Spring Boot используется механизм автонастройки, который работает так:

- В зависимостях (JAR'ах) находится файл: `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`
В этом файле перечислены все классы автоконфигураций, например:
```
com.example.autoconfig.MyAutoConfiguration
com.example.autoconfig.AnotherAutoConfiguration
```
Spring Boot автоматически подключает эти конфигурации при запуске приложения , если они подходят по условиям.
## Разбираемся

Spring действительно фреймворк, который реализует IoC (Inversion of Control — инверсия управления).
Он появился как альтернатива java ee, пофиксив сложность проекта но при этом сохранив совместимость.
Только со временем он так сильно развился, что на нем можно написать все приложение, все его компоненты.
У спринга все есть бин (класс который отдам спрингу под управление и внедрение) и их придется создать много.
Плюс под капотом используются разные внешние зависимости, у которых есть версии. И они могут быть не совместимы со
Spring и между собой.

- Мир изменился, раньше долго писали большие монолиты на JavaEE по waterfall, которые размещались на серверах
  приложений.
  И эти самые сервера уже содержали все необходимое и предлагали средства для конфигурирования.
- Теперь стараются быстро писать микросервисы на Spring по Scrum (или waterscrum©), которые сами себе сервера.

- И чтобы это получалось действительно быстро и удобно, нужен инструмент, который позволит всё это сконфигурировать и
  мониторить.
- Spring Boot — как раз такой инструмент.
  За счет специальных JAR-файлов, стартеров, которые содержат (могут) условные бины и конфигурации, где те определены (
  они
  называются автоконфигурациями).
- И да, можно всё это сделать без Spring Boot, но гораздо менее удобно + нужно будет знать устройство каждого такого
  JAR-файла.
- Кроме всего этого, Spring Boot предоставляет инструменты, которые позволяют более удобно работать со свойствами
  приложения (`@ConfigurationProperties`), средства для мониторинга (`Spring Boot Actuator`) и т.д.
- Не забыть включить @EnableAutoConfigurationProperties - сделает класс помеченный @ConfigurationProperties бином

#### Настройка
В отличие от Java/Spring приложений, где было много конфигурационных файлов разных форматов, Spring Boot сводит всю (
почти) настройку приложения в центральную точку: файл application.properties или application.yml.
Никаких дополнительных действий для этого делать не нужно.
Настройки из данных файлов можно внедрять, как через уже знакомый @Value, так и с помощью новой конструкции
@ConfigurationProperties. Последний вариант более удобен для настроек имеющих иерархию и при работе с Spring Cloud Bus
позволяет на лету менять настройки извне.
Настройки внедряются типизировано. Причём в отличии от чистого Spring, типов тут существенно больше (в т.ч. Map, List и
т.д.).

#### Исполняемые jar 
Spring Boot позволяет создавать исполняемые JAR-файлы, которые содержат все ресурсы и зависимости (в т.ч. встроенный сервер), необходимые для работы приложения.
Такой JAR-файл называется "fat JAR" или "uber JAR".
Его можно создать с помощью соответствующего плагина (spring-boot-maven-plugin).

Исполняемый jar - архив который можно запустить командой java -jar. Он имеет главный класс для запуска.
Если мы ничего не сделали во встроенным сервером то в таком джарнике по умолчанию будет запускаться tomcat 

#### Как работает запуск исполняемого JAR с помощью команды java -jar:
- JVM ищет точку входа (Main-Class) в файле META-INF/MANIFEST.MF.
- В Spring Boot эта точка входа указывает на org.springframework.boot.loader.JarLauncher.
- JarLauncher загружает классы из BOOT-INF/classes и библиотеки из BOOT-INF/lib.
- После загрузки классов и библиотек запускается ваш основной класс (класс с методом main, помеченный аннотацией @SpringBootApplication).
Пример манифеста
```
Manifest-Version: 1.0
Spring-Boot-Classpath-Index: BOOT-INF/classpath.idx
Archiver-Version: Plexus Archiver
Built-By: user
Spring-Boot-Layers-Index: BOOT-INF/layers.idx
Implementation-Version: 1.0.0
Start-Class: com.example.demo.DemoApplication
Spring-Boot-Mode: jar
Created-By: Apache Maven 3.8.6
Build-Jdk: 17.0.5
Main-Class: org.springframework.boot.loader.JarLauncher
```

#### Actuator
Spring Boot Actuator — это модуль Spring Boot, который предоставляет готовые endpoint'ы (конечные точки) для мониторинга и управления вашим приложением в production-среде.
Он позволяет собирать метрики, проверять состояние здоровья приложения, просматривать конфигурацию, логи и многое другое.
Actuator особенно полезен для DevOps-инженеров и разработчиков, так как упрощает наблюдение за состоянием приложения и диагностику проблем.


## Вопросы для самопроверки

- Что такое Spring Boot? В чём отличие от Spring? Зачем всё это надо?
- За счёт чего с помощью Spring Boot получается гибко настраивать контекст?
- Что такое стартеры и автоконфигурация?
- Как Spring Boot упрощает работу с настройками приложения?
- Как можно создать исполняемый JAR-файл с помощью Spring Boot?
- Что такое Spring Boot Actuator и как он может быть полезен?
- А мы не можем в чистом Spring взять и сделать JAR с бинами и конфигом и просто подключить его к приложению?
- Каким образом у Spring Boot получается догадываться какие бины нужны, а какие нет?
- А чистый Spring так может?
- Что такое @SpringBootApplication и какие аннотации она включает?
- За счёт чего вообще работают автоконфигурации, как они попадают в приложение?
- В чём отличие между @AutoConfiguration и @Configuration?
- А можно как-то помочь Spring Boot в загрузке автоконфигураций?

