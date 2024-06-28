# QA_WikipediaSearch
##Автоматическое тестирование поиска Wikipedia с применением паттерна PageObject

1) Java 17 
2) система сборки gradle 
3) логирование logback
4) testNG 7.7
5) Selenium 2.41
6) необходимо иметь приложение Google Chrome (WebDriverManager сам найдет необдходимый драйвер)

  Предлагаемые тесты:
* Саджесты начинаются на поисковой запрос
* Переход из саджеста в точности на поисковую страницу
* Переход на первую подсказку саджеста ведет туда же, куда поиск с нажатием кнопки поиска 
* Поиск содержащих страниц

Tutorial:
```sh
git clone https://github.com/michaelthecircle/QA_WikipediaSearch.git
cd QA_WikipediaSearch
git checkout develop
```
Необязательно
- убедимся командой ls что в директории есть файл build.gradle
- пропишем brew install gradle(для mac) чтобы установить gradle

Запуск тестов в параллельном режиме
```sh
gradle test
```
Запуск тестов последовательно
```sh
gradle test -DrunMode=serial
```
Запуск тестов по отдельности
```sh
gradle test --tests org.wikitest.tests.TestSearchForWord
gradle test --tests org.wikitest.tests.TestSuitableNames
gradle test --tests org.wikitest.tests.TestSuitablePage
gradle test --tests org.wikitest.tests.TestSuitableSuggestion
```
Логирование производится в директорию logs
```
cd logs
```
Настройка логгирования задается в файле logback-test.xml
```
src/test/resources/logback-test.xml
```
Параметры testng в файле testng.xml
```
src/test/resources/testng.xml
```




