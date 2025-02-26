# Path

Нужно избегать camelCase в пути на едпоинты.

- Не нужно ставить в конце пути слэш ```/v1/city/```
- PUT /v1/city - понятно что обновляем сущность. (отличие put от patch от post)
- GET /v1/city - понятно что вытягиваем какое то значение городов
- GET /v1/city/{id} - извлечение 1города по какому то айдишнику

```java

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/city")
public class CityController implements CityApi {
    private final CityService cityService;

    @Override
    @PutMapping()
    public void changeCity(@RequestBody CityDto request) {
        cityService.changeCityDirection(request);
    }

    @Override
    @GetMapping()
    public List<CityDto> getAllCities() {
        return cityService.getAllCities();
    }
}
```

# Отличие PUT POST PATCH