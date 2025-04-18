# Калькулятор отпускных
![Java](https://img.shields.io/badge/Java-11-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.18-green)
## 🏗️ Структура проекта
```
src/
├── main/
│ ├── java/
│ │ └── com/example/WeekendKalculator/
│ │ ├── config/ # Конфигурация праздников
│ │ ├── controller/ 
│ │ ├── exceptions/ # Кастомные исключения
│ │ ├── model/ # Модели данных
│ │ ├── services/ # логика
│ │ └── WeekendKalculatorApplication.java
└── test/ # Тесты
```
## 📡 API 
```
GET /api/calculate
```
----------------------------
Параметры запроса:

| Параметр |	Тип	| Обязательный	| Описание |
|----------|------|----------------|---------|
|averageSalary	| String	|Да|	Средняя зарплата|
|vacationDays	|String|	Да|	Количество дней отпуска|
|startDate|	String	|Нет	|Дата начала отпуска (YYYY-MM-DD)|

## Пример запроса

```
localhost:8080/api/calculate?averageSalary=1000000&vacationDays=14&startDate=2024-04-06
```
### Пример успешного ответа: {"payment":341296.93}
----------------------------
## 🔧 Логика расчета
<ol>
  <li>Расчет дневного заработка:</li>
averageSalary/29.3 , где 29.3 - среднее количество дней в месяце
  <li>Определение рабочих дней: </li>
  <ul>
    <li>Исключаются выходные (суббота, воскресенье)</li>
<li>Исключаются официальные праздники РФ</li>
    <li>Если дата начала не указана, считаются все дни как рабочиe</li>
  </ul>
  <li>Формула расчета:</li>
  payment = Math.round(dailySalary * workingDays * 100) / 100.0;
</ol>

## 🚧 Ограничения

<ol>
  <li>Работает только с целыми числами для зарплаты и дней отпуска</li>
  <li>Учитывает только официальные праздники РФ</li>
</ol>

# Telegram: @xepie
