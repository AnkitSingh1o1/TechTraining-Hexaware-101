use mydb_insurance;

describe user;
describe client;
describe policy;
describe claim;
describe payment;

insert into user values
(1, 'ankit', '123', 'admin'),
(2, 'client', '123', 'client');

insert into policy(policy_id, policy_name, policy_cost) values 
(1, "Jeevan Bima", 8000);

select * from policy;

update policy
set policy_id = 1,
policy_name = "JeevanBima",
policy_cost = 8000,
isActive = "yes"
where policy_id = 1;

-- Error Code: 1054. Unknown column 'isActve' in 'field list'
describe policy;
alter table policy
add column isActive varchar(255)
default 'yes'; 

update policy
set isActive = "no"
where policy_id = 1;

insert into policy(policy_id, policy_name, policy_cost) values (2, "JeevanJ", 1000);
-- Error Code: 1136. Column count doesn't match value count at row 1

select * from client;
insert into client values
(1, 'Client1', 343242, 2, 1);

select * from payment;

insert into payment(payment_id, payment_date, payment_amount, client_id) values
(1, '2024-04-13', 8000, 1);

select * from claim;

select * from client;
select * from user;
insert into claim(claim_id, claim_number, claim_date_filed, claim_amount, claim_status, client_id, policy_id) values
(1, 1234, '2024-04-13', 8000, 'Pending', 1, 1);

select c.client_id from client c JOIN user u ON c.user_id=u.user_id 
where u.user_username='client' AND u.user_password=123;
-- Error Code: 1054. Unknown column 'client' in 'where clause'
