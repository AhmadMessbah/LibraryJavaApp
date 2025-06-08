create table persons
(
    id          number primary key,
    name        nvarchar2(30)   not null,
    family      nvarchar2(30)   not null,
    national_id char(10) unique not null,
    birth_date  date
);
create sequence person_seq start with 1 increment by 1;

--

create table books
(
    id     number primary key,
    title  nvarchar2(30) not null,
    author nvarchar2(30) not null,
    isbn   char(16)      not null
);
create sequence book_seq start with 1 increment by 1;

--

create table loans
(
    id          number primary key,
    person_id references persons,
    book_id references books,
    loan_date   date default sysdate,
    return_date date default null
);
create sequence loan_seq start with 1 increment by 1;

--

create view loan_report as
select l.id          as loan_id,
       l.person_id,
       p.name        as person_name,
       p.family      as person_family,
       p.national_id as person_national_id,
       p.birth_date  as person_birth_date,
       l.book_id,
       b.title       as book_title,
       b.author      as book_author,
       b.isbn        as book_isbn,
       l.loan_date,
       l.return_date
from loans l
         join persons p
              on l.person_id = p.id
         join books b
              on l.book_id = b.id;

---

select *
from loan_report
where return_date is null
  and person_name = 'ali';

---

select l.id          as loan_id,
       l.person_id,
       p.name        as person_name,
       p.family      as person_family,
       p.national_id as person_natioanl_id,
       p.birth_date  as person_birth_date,
       l.book_id,
       b.title       as book_title,
       b.author      as book_author,
       b.isbn        as book_isbn,
       l.loan_date,
       l.return_date
from loans l
         right join persons p
              on l.person_id = p.id
         right join books b
              on l.book_id = b.id;