# JsonProperty

При работе с дто на поля которые сериализуються и десериализуються в json необходимо проставлять аннотацию @JsonProperty
с именем поля и параметром обязательности.
Это делается для того чтобы при рефакторинге случайно не изменить ожидаемое имя и не словать интеграцию

```java
class Request {
    @JsonProperty(value = "time_to_window", required = true)
    private String timeToWindow;

    @JsonProperty(value = "local_date")
    private String localDate;

    @JsonProperty(value = "countOfAmount", requred = true)
    private int countOfAmount;
}

```

Для свойства localDate не обязательно устанавливать requred = false, так как это дефолтное значение у данного параметра.
localDate отличается от свойства который придет в json, оно будет мапится с именем local_date.
А countOfAmount совпадает со свойством countOfAmount в json. Но лучше показывать явно какое поле мы ожидаем

# @DirtiesContext
Если имееться несколько интеграционных тестов для кафки. Необходимо для каждого чистить контекст спринга (тест контейнер) чтобы друг другу не мешали
```java
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

@ContextConfiguration(initializers = {ContainersBaseTest.PropertyInitializer.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class ContainersBaseTest {

    private static final String DATABASE_NAME = "dispatcher-planning-test";

    @Container
    public static PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:14.6-alpine"))
                    .withReuse(true)
                    .withDatabaseName(DATABASE_NAME);

    @Container
    static KafkaContainer kafkaContainer =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.5.0"))
                    .withEnv("KAFKA_HEAP_OPTS", "-Xmx256M -Xms256M")
                    .withReuse(true);

    static class PropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            postgresContainer.start();
            kafkaContainer.start();
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgresContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgresContainer.getUsername(),
                    "spring.datasource.password=" + postgresContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("kafka.service1.producer-properties.\"[bootstrap.servers]\"", kafkaContainer::getBootstrapServers);
        registry.add("kafka.service1.consumer-properties.\"[bootstrap.servers]\"", kafkaContainer::getBootstrapServers);
        registry.add("kafka.service2.producer-properties.\"[bootstrap.servers]\"", kafkaContainer::getBootstrapServers);
        registry.add("kafka.service2.consumer-properties.\"[bootstrap.servers]\"", kafkaContainer::getBootstrapServers);
    }
```



### @Valid
@Valid относится к валидации, не относится непосредственно к спринговому контроллеру. 
лучше оставлять его в интерфейсе (на который навешены аннотации сваггера) А от этого интерфейса уже реализуется контроллер.

### @NotNull
Если есть эта аннотация, не обязательно ставить ```@JsonProperty(value="name", required=true)```
Так же можно в параметрах указать какое сообщение вывести, чтобы ошибка была более понятной
```@NotNull(message="test city not null")```

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Город и его направление обработки")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityDto {

    @Schema(description = "Код города")
    @JsonProperty(value = "city_sapCode", required = true)
    @NotNull
    private String citySapCode;

    @Schema(description = "Направление автопланирования для города")
    @JsonProperty(value = "direction", required = true)
    @ValueOfEnum(enumClass = Direction.class,
                 message = "Неправильное направление. Допустимые значения [A,B,V]")
    @NotNull
    private String direction;
}

```

### Enum валидатор
<https://www.baeldung.com/javax-validations-enums>