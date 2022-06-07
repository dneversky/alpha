## Запуск с помощью Gradle
- скачайте и распакуйте репозиторий на компьютер
- откройте командную консоль в распакованном каталоге
- выполните команду gradle bootRun
## Запуск с помощью Docker
- скачайте образ командой docker pull dneversky/alpha:latest
- запустите контейнер командой docker run -dp 8080:8080 --rm --name alpha dneversky/alpha:latest
- чтобы завершить выполнение контейнера введите команду docker stop alpha
