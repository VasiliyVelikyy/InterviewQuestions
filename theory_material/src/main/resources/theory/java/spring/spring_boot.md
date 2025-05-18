### Вопросы

### Что такое Spring Boot?

Spring Boot — мощный инструмент, который позволяет разработчикам быстро создавать приложения на основе Spring
фреймворка прикладывая для этого минимум усилий.
Т.к. в данном фреймворке реализован принцип инверсии зависимостей, с его помощью можно легко настраивать и запускать
приложения, используя встроенные компоненты, что делает Spring Boot популярным выбором среди разработчиков.

### Так, а Spring что такое?

Spring — это фреймворк, реализующий принцип инверсии зависимостей и предоставляющий набор инструментов и библиотек,
которые упрощают и ускоряют процесс разработки, позволяя сосредоточиться на бизнес-логике приложения.

### В чем отличия?

Главными понятиями Spring Boot является стартеры и автоконфигурации

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

#### Почему Spring Boot удобнее?

Spring Boot позволяет делать так называемые автонастраиваемые стартеры , которые:

Самостоятельно регистрируют свои бины при подключении JAR-файла.
Не требуют ручного @Import или `@ComponentScan`.
Могут считывать настройки из `application.properties`.
Это достигается за счёт:

Файла `META-INF/spring.factories`
Аннотации `@EnableAutoConfiguration`
Использования условных аннотаций (`@ConditionalOnClass, @ConditionalOnMissingBean` и др.)

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
DispatcherServlet — это главный контроллер в Spring MVC , который принимает все HTTP-запросы и направляет их к нужным контроллерам, обработчикам и представлениям.
1. через web.xml (традиционный способ) как показано выше `org.springframework.web.servlet.DispatcherServlet` И отдельный файл: dispatcher-servlet.xml
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

      
