/*Создать таблицу Student
Колонки id, fio, sex, id_group*/
create table if not exists Students(
id bigint,
surname varchar,
"name" varchar,
patronymic varchar,
sex varchar,
group_id bigint
)

insert into students (id,surname,"name",patronymic,sex,group_id) values (1,'Иванов','Иван','Иванович','male',1);
insert into students (id,surname,"name",patronymic,sex,group_id) values (1,'Петрова','Анна','Петровна','female',2);
/*drop table if exists students;*/

//////////////////////////////
/*Создать таблицу Groups
Колонки id, name, id_curator*/

create table if not exists "Groups"(
id bigint,
"name" varchar,
curator_id bigint
)

insert into "Groups" (id,"name",curator_id) values(1,'LoadTest',1)

insert into "Groups" (id,"name",curator_id) values(2,'JavaTest',2)

/*drop table if exists "Groups";*/

//////////////////////
/*Создать таблицу Curator
Колонки id, fio*/

create table if not exists curators (
id bigint,
surname varchar,
"name" varchar,
patronymic varchar
)

insert into curators (id,surname,"name",patronymic) values (1,'Сидоров','Степан','Михайлович')

insert into curators (id,surname,"name",patronymic) values (2,'Сидорова','Светлана','Григорьевна')

/*Заполнить таблицы данными(15 студентов, 3 группы, 4 куратора)*/

/*Вывести на экран информацию о всех студентах включая название группы и имя куратора*/

select s.id, s.surname, s."name", s.patronymic, s.sex, g."name" as group_name , 
c.surname||' '||substring(c."name",1,1)||'.'||substring(c.patronymic,1,1)||'.' as curator_name
from students s 
inner join "Groups" g on g.id =s.group_id 
inner join curators c on c.id =g.curator_id 



/*Вывести на экран количество студентов*/

select count(*) from students

/*Вывести студенток*/
select id, surname, "name", patronymic from students where sex ='female'

/*Обновить данные по группе сменив куратора*/

update "Groups" set curator_id =2 where id=1

/*Вывести список групп с их кураторами*/

select g.id,g."name", 
c.surname||' '||substring(c."name",1,1)||'.'||substring(c.patronymic,1,1)||'.' as curator_name
from "Groups" g 
inner join curators c on c.id=g.curator_id

/*Используя вложенные запросы вывести на экран студентов из определенной группы(поиск по имени группы)*/

select s.id, s.surname, s."name", s.patronymic, s.sex
from students s 
where group_id =(select id from "Groups" where upper("name") like upper('%j%'))


