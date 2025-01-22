-- Вставляем роли с явным указанием ID
INSERT INTO public.account_role (role_id, role_name) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN')
ON CONFLICT (role_id) DO NOTHING; -- Игнорируем конфликты по role_id

-- Вставляем пользователей с зашифрованными паролями
-- Пароль для всех пользователей: "123"
INSERT INTO public.account (account_id, account_email, account_password, role_id) VALUES
(1, 'user1', crypt('123', gen_salt('bf')), 1), -- ROLE_USER
(2, 'user2', crypt('123', gen_salt('bf')), 1), -- ROLE_USER
(3, 'admin1', crypt('123', gen_salt('bf')), 2), -- ROLE_ADMIN
(4, 'admin2', crypt('123', gen_salt('bf')), 2) -- ROLE_ADMIN
ON CONFLICT (account_id) DO NOTHING; -- Игнорируем конфликты по account_id

-- Вставляем задачи
INSERT INTO public.task (task_id, title, description, status, priority, author_id, assignee_id) VALUES
(1, 'Task 1', 'Description for Task 1', 'WAITING', 'HIGH', 1, 3), -- Автор: user1, Исполнитель: admin1
(2, 'Task 2', 'Description for Task 2', 'IN_PROGRESS', 'MEDIUM', 2, 4) -- Автор: user2, Исполнитель: admin2
ON CONFLICT (task_id) DO NOTHING; -- Игнорируем конфликты по task_id

-- Вставляем комментарии
INSERT INTO public.comment (comment_id, comment_text, task_id, account_id) VALUES
(1, 'Comment 1 for Task 1', 1, 1), -- Комментарий от user1 для Task 1
(2, 'Comment 2 for Task 1', 1, 3), -- Комментарий от admin1 для Task 1
(3, 'Comment 1 for Task 2', 2, 2), -- Комментарий от user2 для Task 2
(4, 'Comment 2 for Task 2', 2, 4) -- Комментарий от admin2 для Task 2
ON CONFLICT (comment_id) DO NOTHING; -- Игнорируем конфликты по comment_id

-- Сбрасываем последовательности (sequences) для корректной генерации новых ID
SELECT setval('account_role_role_id_seq', (SELECT MAX(role_id) FROM public.account_role));
SELECT setval('account_account_id_seq', (SELECT MAX(account_id) FROM public.account));
SELECT setval('task_task_id_seq', (SELECT MAX(task_id) FROM public.task));
SELECT setval('comment_comment_id_seq', (SELECT MAX(comment_id) FROM public.comment));