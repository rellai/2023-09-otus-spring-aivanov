# Домашнее задание 5
Это домашнее задание выполняется НЕ на основе предыдущего.
## Описание задания:
Цель: использовать возможности Spring JDBC и spring-boot-starter-jdbc для подключения к реляционным базам данных
Результат: приложение с хранением данных в реляционной БД, которое в дальнейшем будем развивать
## Требования:
1. Создать приложение, каталог книг в библиотеке
2. Использовать Spring JDBC и реляционную базу (можно H2)
3. Предусмотреть сущности авторов, книг и жанров. Каждая должна храниться в своей таблице
4. Предполагаются отношения многие-к-одному (у книги один автор, но у автора может быть несколько книг, то же касается книг и жанров)
5. Интерфейс выполняется на Spring Shell (CRUD книги плюс, как минимум, операции вывода всех авторов и жанров)
6. Создание и инициализация схемы БД должно происходить через schema.sql + data.sql или через систему миграций (Liquibase/Flyway)
7. С помощью @JdbcTest сделать интеграционные тесты всех методов дао книг (со встроенной БД)

## Дополнительные требования к выполнению работы:
1. Проблема N+1 должна быть решена
2. Использовать NamedParametersJdbcTemplate
3. Не делать абстрактных или обобщенных Dao
4. Не делать абстрактных или обобщенных сущностей
5. Не делать двунаправленных связей (в книге автор, в авторе книги)

## Опциональное усложнение
Отношения многие-ко-многим для одной сущности