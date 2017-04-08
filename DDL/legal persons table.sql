use CIM;
create table legal_persons(
customer_id int not null,
company_name varchar(30),
registration_date date,
economical_id bigint(12) unique,
primary key(customer_id),
foreign key(customer_id) references customers(customer_id)
);