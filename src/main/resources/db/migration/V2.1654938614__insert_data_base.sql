INSERT INTO public.faculties(name)
VALUES ('ФИТ');

INSERT INTO public.groups(faculty_id, name)
VALUES (1, 'ИВТ-1-18');

INSERT INTO public.users(first_name, last_name, patronymic, mobile_phone_number, user_name, email, password, faculty_id,
                         group_id, role, is_enabled)
VALUES ('admin', 'admin', 'admin', '999 999 999', 'admin', 'admin',
        '$2a$12$kUqIupH5HrFKIAw2KKZsKuc7bsiLgmkctxgfMeqqdHkjM/1SVQGKK', '1', '1', 'ADMIN', true);

INSERT INTO public.verify_users(user_id, token, is_active)
VALUES (1, '7a4902d7-2e4b-4469-911d-92831ded74d8', true);

INSERT INTO public.week_days(week_day, name)
VALUES ('1', 'Понедельник'),
       ('2', 'Вторник'),
       ('3', 'Среда'),
       ('4', 'Четверг'),
       ('5', 'Пятница'),
       ('6', 'Суббота');

INSERT INTO public.times(time, number_lesson)
VALUES ('8:00 - 9:20', 1),
       ('9:30 - 10:50', 2),
       ('11:00 - 12:20', 3),
       ('13:00 - 14:20', 4),
       ('14:30 - 15:50', 5),
       ('16:00 - 17:20', 6),
       ('17:30 - 18:50', 7),
       ('19:00 - 20:20', 8);