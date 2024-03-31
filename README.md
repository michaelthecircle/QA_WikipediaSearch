## QA_WikipediaSearch
Автоматическое тестирование поиска Wikipedia с применением паттерна PageObject

Требования:
1) Саджесты начинаются на поисковой запрос
2) Переход из саджеста в точности на поисковую страницу
3) Переход на первую подсказку саджеста ведет туда же, куда поиск с нажатием кнопки поиска 
4) Поиск содержащих страниц

Java 17 
система сборки gradle 
логирование logback
testNG 7.7
Selenium 2.41
необходимо иметь приложение Google Chrome (WebDriverManager сам найдет необдходимый драйвер)

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




