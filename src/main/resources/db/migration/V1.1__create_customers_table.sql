create table if not exists customers (
    id serial not null,
    full_name varchar(50) not null,
    gender char(1) not null,
    book_id bigint,
    constraint pk_customers primary key(id),
    constraint fk_customers_cities foreign key (book_id) references books(id)
);