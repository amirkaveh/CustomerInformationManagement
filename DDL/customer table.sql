use CIM;
create table customers(
customer_id int not null auto_increment,
customer_type enum ('natural-person','legal-person') not null,
primary key(customer_id),
check (customer_type != 0)
);

ALTER TABLE customers AUTO_INCREMENT=10000;

DELIMITER $$
CREATE TRIGGER `test_type_before_insert` before INSERT ON `customers`
FOR EACH ROW
BEGIN
    IF new.customer_type = 0 THEN
		#delete from customers where customer_type=0;
        SIGNAL SQLSTATE '12345'
		SET MESSAGE_TEXT = 'check constraint on customer_type failed';
    END IF;
END$$
DELIMITER ;
