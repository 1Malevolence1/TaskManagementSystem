
create table if not exists public.account_role(
    role_id serial primary key,
    role_name varchar(30) not null unique
);


create table if not exists public.account(
        account_id serial primary key,
        account_emial varchar(100) not null,
        account_password text,
        role_id int,

        foreign key(role_id) references public.account_role(role_id)
);

create table if not exists public.task(
     task_id serial primary key,
     title varchar(255) not null,
     description text not null,
     status varchar(50) not null check (status in ('WAITING', 'IN_PROGRESS', 'COMPLETED')),
     priority varchar(50) not null check (priority in ('HIGH', 'MEDIUM', 'LOW')),
     author_id integer,
     assignee_id integer,

     foreign key(author_id) references public.account(account_id),
     foreign key(assignee_id) references public.account(account_id)
);


create table if not exists public.comment(
    comment_id serial primary key,
    comment_text text not null,
    task_id bigint not null,
    account_id bigint not null,

    foreign key(task_id) references public.task(task_id),
    foreign key(account_id) references public.account(account_id)
);
