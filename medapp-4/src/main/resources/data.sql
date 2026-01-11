-- Вставка тестовых докторов
INSERT INTO doctor (name, specialty) VALUES ('Иванов Иван', 'Терапевт');
INSERT INTO doctor (name, specialty) VALUES ('Петров Петр', 'Хирург');

-- Вставка тестовых отчетов о сменах
INSERT INTO shift_report (doctor_id, report_date, notes) VALUES (1, CURRENT_DATE, 'Ночное дежурство: 3 вызова');
INSERT INTO shift_report (doctor_id, report_date, notes) VALUES (2, CURRENT_DATE, 'Дневное дежурство: всё спокойно');

INSERT INTO notifications (email, entity_name, change_type)
VALUES
('dashanek007@gmail.com', '*', '*'),
('dashanek007@gmail.com', 'Doctor', 'DELETE'),
('dashanek007@gmail.com', 'ShiftReport', 'INSERT');