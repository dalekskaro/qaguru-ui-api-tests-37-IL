# Демопроект по автоматизации тестирования сайта DemoQa
<p align="center">
<a href="https://www.cyberpunk.net/ru/ru/"><img width="30%" title="GitHub" src="media/logo/Toolsqa.jpg">
</p>

## :page_with_curl: Содержание:
- [Стек технологий](#dvd-стек-технологий)
- [Реализованные проверки](#mag-реализованные-проверки)
- [Запуск автотестов](#crystal_ball-запуск-автотестов)
- [Сборка Jenkins](#oncoming_automobile-сборка-jenkins--037-attanosolas-cyberpunk2077)
- [Отчет Allure](#bar_chart-отчет-allure)
- [TMS ТестОпс](#blue_book-tms-тестопс)
- [Задача в Jira](#information_source-задача-в-jira-homework-1550)
- [Уведомление в Telegram](#bell-уведомление-в-telegram)
- [Видео примера запуска тестов в Selenoid](#movie_camera-видео-примера-запуска-тестов-в-selenoid)
---

---
## :dvd: Стек технологий
<p align="center">
<a href="https://github.com/"><img width="6%" title="GitHub" src="media/logo/GitHub.svg">
<a href="https://www.jetbrains.com/idea/"><img width="6%" title="IntelliJ IDEA" src="media/logo/Intelij_IDEA.svg">
<a href="https://www.java.com/"><img width="6%" title="Java" src="media/logo/Java.svg">
<a href="https://gradle.org/"><img width="6%" title="Gradle" src="media/logo/Gradle.svg">
<a href="https://junit.org/junit5/"><img width="6%" title="JUnit5" src="media/logo/JUnit5.svg">
<a href="https://selenide.org/"><img width="6%" title="Selenide" src="media/logo/Selenide.svg">
<a href="https://rest-assured.io/"><img width="6%" title="Selenide" src="media/logo/Rest-assured.png">
<a href="https://projectlombok.org/"><img width="6%" title="Selenide" src="media/logo/Lombok.png">
<a href="https://www.jenkins.io/"><<img width="6%" title="Jenkins" src="media/logo/Jenkins.svg">
<a href="https://aerokube.com/selenoid/"><img width="6%" title="Selenoid" src="media/logo/Selenoid.svg">
<a href="https://allurereport.org/"><img width="6%" title="Allure Report" src="media/logo/Allure_Report.svg">
<a href="https://web.telegram.org/"><img width="6%" title="Telegram" src="media/logo/Telegram.svg">
<a href="https://qameta.io"><img width="6%" title="TestOps" src="media/logo/TespOps.ico">
<a href="https://www.atlassian.com/software/jira"><img width="6%" title="jira" src="media/logo/Jira.svg">
</p>

---
## :mag: Реализованные проверки
- 

---
## :crystal_ball: Запуск автотестов

```bash
./gradlew clean homework-16 -Disbn="9781593275846" -DbookTitle="Eloquent JavaScript, Second Edition" -DuserName={USER_NAME} -Dpassword={PASSWORD}
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