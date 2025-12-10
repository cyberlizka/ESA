# Практическая работа №3. RESTful веб-сервис
## Общее задание
Разработать медицинское приложение с REST интерфейсом для управления врачами и их отчетами о сменах. Приложение должно предоставлять доступ к данным в базе данных H2 с поддержкой форматов JSON и XML.

### Задание 1
*Сравните JAX-RS и SpringREST. Выберите один из них для своего приложения. Приведите аргументы в пользу вашего выбора.*

Выбранный фреймворк: SpringREST

Аргументы за выбор SpringREST:

    - Полная интеграция с экосистемой Spring: SpringREST идеально работает со Spring Boot, Spring Data JPA, Spring Security и другими модулями Spring

    - Конфигурация через аннотации: Мощные аннотации @RestController, @GetMapping, @PostMapping делают код чистым и читаемым

    - Автоматическое согласование контента: Встроенная поддержка JSON и XML без дополнительной конфигурации

    - Готовые production-функции: Обработка ошибок, валидация и безопасность из коробки

    - Упрощенная разработка: Spring Boot уменьшает количество шаблонного кода

### Задание 2
*Выберите одно из ваших предыдущих приложений для дальнейшей разработки. Разработайте REST API для доступа к вашим данным.*

Выбрано медицинское приложение со следующими сущностями:

- Врачи (Doctor): ID, ФИО, Специальность

- Отчеты о сменах (ShiftReport): ID, Врач (связь), Дата отчета, Заметки

### Задание 3
*Реализуйте API. Оно должно поддерживать использование как XML, так и JSON (как для входных данных, так и для выходных).*

Endpoints для Врачей:

    - GET /api/doctors/json - Получить всех врачей в формате JSON

    - GET /api/doctors/xml - Получить всех врачей в формате XML с XSLT преобразованием

    - GET /api/doctors/{id}/json - Получить врача по ID в формате JSON

    - GET /api/doctors/{id}/xml - Получить врача по ID в формате XML с XSLT

    - POST /api/doctors/new - Создать нового врача (принимает JSON/XML)

    - PUT /api/doctors/{id} - Обновить врача (принимает JSON/XML)

    - DELETE /api/doctors/{id} - Удалить врача

Endpoints для Отчетов о сменах:

    - GET /api/shiftReports/json - Получить все отчеты в формате JSON

    - GET /api/shiftReports/xml - Получить все отчеты в формате XML с XSLT

    - GET /api/shiftReports/{id}/json - Получить отчет по ID в формате JSON

    - GET /api/shiftReports/{id}/xml - Получить отчет по ID в формате XML с XSLT

    - POST /api/shiftReports/new - Создать новый отчет (принимает JSON/XML)

    - PUT /api/shiftReports/{id} - Обновить отчет (принимает JSON/XML)

    - DELETE /api/shiftReports/{id} - Удалить отчет

### Задание 4
*Разработайте XSL-преобразование для ваших XML-объектов, чтобы браузер мог отображать их как HTML-страницы.*

Созданы XSLT файлы:

- doctors.xsl: Преобразует XML с врачами в HTML таблицу с навигацией

- shiftReports.xsl: Преобразует XML с отчетами в HTML таблицу с навигацией

### Задание 5
*Сделайте так, чтобы тег с данными об XSL-преобразовании добавлялся в начало XML-откликов REST.*

Во все XML ответы добавлена инструкция обработки XSLT:

`<?xml-stylesheet type="text/xsl" href="/xsl/doctors.xsl"?>`

### Задание 6
*Добейтесь того, чтобы всё это вместе заработало…*

## Тестирование
### Endpoints для врачей
 - Получить всех врачей (JSON)

<img width="835" height="425" alt="Image" src="https://github.com/user-attachments/assets/a7d9b703-adf3-44fe-bf0e-aba863d08e9a" />

- Получить всех врачей (XML)

<img width="957" height="368" alt="Image" src="https://github.com/user-attachments/assets/cc510cdc-531c-4d8d-9511-66f5e768867e" />

- Получить врача по ID (JSON)

<img width="495" height="317" alt="Image" src="https://github.com/user-attachments/assets/5621afad-43fa-4750-b746-575cac75b52b" />

- Получить врача по ID (XML)

<img width="964" height="392" alt="Image" src="https://github.com/user-attachments/assets/3b72a4b4-0909-4de9-beca-f1576f494041" />

- Создать нового врача (JSON)

<img width="593" height="338" alt="Image" src="https://github.com/user-attachments/assets/8f872d17-db9c-460e-9d8a-a47591929dd8" />

- Обновить врача (JSON)

<img width="503" height="378" alt="Image" src="https://github.com/user-attachments/assets/3db49541-a5fb-48f2-84e2-a4e4072a575a" />

-  Удалить врача

<img width="539" height="299" alt="Image" src="https://github.com/user-attachments/assets/da46a542-95c1-423a-9191-dbe430ff45f6" />


### Endpoints для отчетов
- Получить все отчеты (JSON)

<img width="533" height="604" alt="Image" src="https://github.com/user-attachments/assets/379b3c8e-7078-4be1-98ba-cecd1791d0c8" />

- Получить  все отчеты (XML)

<img width="964" height="303" alt="Image" src="https://github.com/user-attachments/assets/5be84258-8cb8-4732-8bad-3b682d714410" />

- Создать новый отчет (JSON)

<img width="593" height="551" alt="Image" src="https://github.com/user-attachments/assets/97747e18-9c05-4ec8-8cf1-582ed360927a" />
<img width="962" height="339" alt="Image" src="https://github.com/user-attachments/assets/07e1abdf-66ee-48a8-9e03-ee2207b7ef9c" />

-  Удалить отчет

<img width="392" height="238" alt="Image" src="https://github.com/user-attachments/assets/510292d0-798d-48fc-b7f0-d1a86cdeda40" />
