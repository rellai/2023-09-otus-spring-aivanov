# Домашнее задание 8
## Описание задания:
### Цель:  
максимально просто писать слой репозиториев с применением современных подходов
### Результат: 
приложение со слоем репозиториев на Spring Data JPA
Домашнее задание выполняется переписыванием предыдущего на JPA.
## Требования:
1. Использовать Spring Data MongoDB репозитории, а если не хватает функциональности, то и *Operations
2. Тесты можно реализовать с помощью Flapdoodle Embedded MongoDB 
3. Hibernate, равно, как и JPA, и spring-boot-starter-data-jpa не должно остаться в зависимостях, если ДЗ выполняется на основе предыдущего.
4. Как хранить книги, авторов, жанры и комментарии решать Вам. Но перенесённая с реляционной базы структура не всегда будет подходить для MongoDB.