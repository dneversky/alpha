## Запуск с помощью Gradle
- скачайте и распакуйте репозиторий на компьютер
- откройте командную консоль в распакованном каталоге
- выполните команду `gradle bootRun`
## Запуск с помощью Docker
- скачайте образ командой `docker pull dneversky/alpha:latest`
- запустите контейнер командой `docker run -dp 8080:8080 --rm --name alpha dneversky/alpha:latest`
- чтобы завершить выполнение контейнера введите команду `docker stop alpha`

# API Endpoints
Конечные точки позволят работать с приложением.
## GET `http://localhost:8080/v1/exchange?code=RUB`

Получить GIF в зависимости от курса доллара к рублю. Если курс по отношению к USD за сегодня стал выше вчерашнего, то ответом будет случайный GIF-файл отсюда https://giphy.com/search/rich, если ниже - отсюда https://giphy.com/search/broke

Параметры

|     name      |   required    |     type      |  description                                        |
| ------------- | ------------- | ------------- | --------------------------------------------------- |
|     code      |      yes      |     string    |  код валюты по отношению с которой сравнивается USD |