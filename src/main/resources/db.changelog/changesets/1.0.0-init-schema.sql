create table replace_me (
    id   uuid default uuidv7(),
    name varchar(80) not null,
    constraint pk_replaceme_id primary key (id),
    constraint unq_replaceme_name unique (name)
);
