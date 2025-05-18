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

В большинстве случаев в Spring Boot аннотация `@ComponentScan` не требуется , потому что она уже неявно применяется
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

### а как в чистом спринге происходит поиск бинов?

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