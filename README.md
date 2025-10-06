
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
<img width="1907" height="598" alt="image" src="https://github.com/user-attachments/assets/3353cc1b-f15a-4bf6-9bbe-807032069158" />

### Результат

<img width="1920" height="1080" alt="Снимок экрана (110)" src="https://github.com/user-attachments/assets/fd3e45fb-3b2f-4cca-9ae7-c751f02cf92d" />

<img width="1920" height="1080" alt="Снимок экрана (106)" src="https://github.com/user-attachments/assets/5ea566d6-dd9b-43e2-a6a0-5d3ce75edb03" />

<img width="1920" height="1080" alt="Снимок экрана (107)" src="https://github.com/user-attachments/assets/35651a26-746f-4578-9135-71364c8918f3" />

<img width="1920" height="1080" alt="Снимок экрана (108)" src="https://github.com/user-attachments/assets/09588714-6e15-485e-b212-70fa569ff2b4" />

<img width="1920" height="1080" alt="Снимок экрана (109)" src="https://github.com/user-attachments/assets/0e2c3e0b-9098-49dd-8bb9-fe91c63a9c15" />

<img width="1920" height="1080" alt="Снимок экрана (111)" src="https://github.com/user-attachments/assets/3fd0b42e-ca71-4940-b187-1f2f25106ac8" />

<img width="1920" height="1080" alt="Снимок экрана (112)" src="https://github.com/user-attachments/assets/4938ce85-cc48-4dd0-9e50-8c5220de8321" />

<img width="1920" height="1080" alt="Снимок экрана (113)" src="https://github.com/user-attachments/assets/a42d49ef-758e-438e-a4e4-22261fe41f8a" />





