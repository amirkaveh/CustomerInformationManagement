use cim;

#drop table loan_types_grant_conditions;
#drop table grant_conditions;
#drop table loan_types;

create table loan_types (
loan_type_id int not null auto_increment,
loan_type_name varchar(30),
interest_rate double,
primary key(loan_type_id));

create table grant_conditions (
grant_condition_id int not null auto_increment,
loan_type_id int not null,
grant_name varchar(30),
min_contract_duration int,
max_contract_duration int,
min_contract_amount numeric,
max_contract_amount numeric,
primary key(grant_condition_id),
foreign key(loan_type_id) references loan_types(loan_type_id)
);

#create table loan_types_grant_conditions (
#loan_type_id int,
#grant_condition_id int,
#foreign key (loan_type_id) references loan_types(loan_type_id),
#foreign key (grant_condition_id) references grant_conditions(grant_condition_id),
#primary key (grant_condition_id));