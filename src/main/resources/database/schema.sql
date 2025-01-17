create table if not exists public.task (
     task_id serial primary key,
     title varchar(255) not null,
     description text not null,
     status varchar(50) not null check (status in ('WAITING', 'IN_PROGRESS', 'COMPLETED')),
     priority varchar(50) not null check (priority in ('HIGH', 'MEDIUM', 'LOW'))
);



