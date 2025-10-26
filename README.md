# Задание

- Разработать автотест на удаление товара из списка https://demoqa.com/profile
- Добавить степы, модели и спецификации
- Реализовать авторизацию с @WithLogin
- В качестве ответа приложить ссылку на репозиторий и Allure-отчет в Jenkins

# Запуск тестов:
```bash
./gradlew clean homework-16 -Disbn="9781593275846" -DbookTitle="Eloquent JavaScript, Second Edition"
```

Книги для теста:
| isbn          | bookTitle                                 |
|---------------|-------------------------------------------|
| 9781449325862 | Git Pocket Guide                          |
| 9781449331818 | Learning JavaScript Design Patterns       |
| 9781449337711 | Designing Evolvable Web APIs with ASP.NET |
| 9781449365035 | Speaking JavaScript                       |
| 9781491904244 | You Don't Know JS                         |
| 9781491950296 | Programming JavaScript Applications       |
| 9781593275846 | Eloquent JavaScript, Second Edition       |
| 9781593277574 | Understanding ECMAScript 6                |