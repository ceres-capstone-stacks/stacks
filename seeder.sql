insert into expenses (amount, date, description, is_regular, type, user_id) values ('9.91', '2019-08-30', 'Opn/oth rep tcspd vlv-ts', false, 1, 1);
insert into expenses (amount, date, description, is_regular, type, user_id) values ('9.29', '2019-03-14', 'Delay opening enterostom', true, 2, 1);


insert into goals (amount, amount_saved, date, description, user_id) values ('10.00', '4.55', '2021-01-01','this is a savings plan',1);
insert into goals (amount, amount_saved, date, description, user_id) values ('250.00', '25.55', '2020-11-12','this is for my birthday',1);

TRUNCATE goals;
TRUNCATE expenses;


SELECT SUM(expenses.amount) FROM expenses WHERE type = '5';