create or replace function avg_users_age
return NUMBER
as
    age NUMBER(7, 0);
begin
select avg(extract(year from SYSDATE) - extract(year from BIRTHDATE)) into age from USERS;
return age;
end;

--------------------------------------------------------------------------------

create or replace function count_transactions_with_status(sta NUMBER, login VARCHAR2)
return NUMBER
as
counter NUMBER;
begin
select count(TRANSACTION_ID) into counter from TRANSACTIONS
                                                   join OFFERS using(OFFER_ID)
where STATUS=sta and SELLER like login;

return counter;
end;

--------------------------------------------------------------------------------

create or replace function count_users
return NUMBER
as
    counter NUMBER;
begin
select count(LOGIN) into counter from USERS;
return counter;
end;

--------------------------------------------------------------------------------

create or replace function total_earned(login Varchar2)
return number
as
    total number;
begin
select sum(PRICE) into total from TRANSACTIONS
                                      join OFFERS using(OFFER_ID)
where SELLER like login and STATUS=1;
return total;
end;