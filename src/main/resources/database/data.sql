INSERT INTO public.account_role (role_id, role_name) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN')
ON CONFLICT (role_id) DO NOTHING;

INSERT INTO public.account (account_id, account_email, account_password, role_id) VALUES
(1, 'user1', crypt('user1', gen_salt('bf')), 1),
(2, 'user2', crypt('user2', gen_salt('bf')), 1),
(3, 'admin1', crypt('admin1', gen_salt('bf')), 2),
(4, 'admin2', crypt('admin2', gen_salt('bf')), 2)
ON CONFLICT (account_id) DO NOTHING;

INSERT INTO public.task (task_id, title, description, status, priority, author_id, assignee_id) VALUES
(1, 'Task 1', 'Description for Task 1', 'WAITING', 'HIGH', 1, 3),
(2, 'Task 2', 'Description for Task 2', 'IN_PROGRESS', 'MEDIUM', 2, 4),
(3, 'Task 3', 'Description for Task 3', 'COMPLETED', 'LOW', 1, 4),
(4, 'Task 4', 'Description for Task 4', 'WAITING', 'HIGH', 2, 3),
(5, 'Task 5', 'Description for Task 5', 'IN_PROGRESS', 'MEDIUM', 1, 4),
(6, 'Task 6', 'Description for Task 6', 'COMPLETED', 'LOW', 2, 3)
ON CONFLICT (task_id) DO NOTHING;

INSERT INTO public.comment (comment_id, comment_text, task_id, account_id) VALUES
(1, 'Comment 1 for Task 1', 1, 1),
(2, 'Comment 2 for Task 1', 1, 3),
(3, 'Comment 1 for Task 2', 2, 2),
(4, 'Comment 2 for Task 2', 2, 4),
(5, 'Comment 1 for Task 3', 3, 1),
(6, 'Comment 2 for Task 3', 3, 4),
(7, 'Comment 1 for Task 4', 4, 2),
(8, 'Comment 2 for Task 4', 4, 3),
(9, 'Comment 1 for Task 5', 5, 1),
(10, 'Comment 2 for Task 5', 5, 4),
(11, 'Comment 1 for Task 6', 6, 2),
(12, 'Comment 2 for Task 6', 6, 3)
ON CONFLICT (comment_id) DO NOTHING;

SELECT setval('account_role_role_id_seq', (SELECT MAX(role_id) FROM public.account_role));
SELECT setval('account_account_id_seq', (SELECT MAX(account_id) FROM public.account));
SELECT setval('task_task_id_seq', (SELECT MAX(task_id) FROM public.task));
SELECT setval('comment_comment_id_seq', (SELECT MAX(comment_id) FROM public.comment));