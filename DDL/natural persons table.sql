use CIM;
create table natural_persons(
customer_id int not null,
person_name varchar(30),
person_family varchar(50),
father_name varchar(30),
birth_date date,
national_id bigint(10),
primary key(customer_id),
foreign key(customer_id) references customers(customer_id)
);