
# Лабораторные работы по курсу Архитектура корпоративных систем
## Выполнили: студенты группы 6133-010402D Ерзикова Е.А. и Некрасова Д.В.
### [Лабораторная №1 ]()
### Описание лабораторной №1:
#### IDE: Eclipse IDE for Enterprise Java and Web Developers
#### JDK: jdk-17.0.16+8
#### GlassFish: Eclipse GlassFish 7.0.24 
<img width="1113" height="622" alt="Снимок экрана 2025-10-06 144255" src="https://github.com/user-attachments/assets/e05cdd2b-5dac-460c-9402-a9b07d20d24d" />
<img width="1280" height="469" alt="image" src="https://github.com/user-attachments/assets/06152b64-e332-4afd-b7be-b0eea27d29d6" />

#### СУБД: PostgreSQL
SQL:
```
CREATE TABLE doctor (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  specialty VARCHAR(100)
);

CREATE TABLE shift_report (
  id SERIAL PRIMARY KEY,
  doctor_id INT NOT NULL REFERENCES doctor(id) ON DELETE CASCADE,
  report_date DATE,
  notes TEXT
);

INSERT INTO doctor (name, specialty) VALUES 
  ('Иванов Иван', 'Терапевт'),
  ('Петров Петр', 'Хирург');

INSERT INTO shift_report (doctor_id, report_date, notes) VALUES
  (1, CURRENT_DATE, 'Ночное дежурство: 3 вызова'),
  (2, CURRENT_DATE, 'Дневное дежурство: всё спокойно');
```
