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

