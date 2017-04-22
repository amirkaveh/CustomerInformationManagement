use cim;
create table grant_conditions (
id int not null auto_increment,
grant_name varchar(30),
min_contract_duration date,
max_contract_duration date,
min_contract_amount numeric,
max_contract_amount numeric,
primary key(id))