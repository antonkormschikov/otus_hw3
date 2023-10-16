import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import settings.Settings;
import dataObj.Student;
import db.DBConnector;
import tables.AbsTable;
import tables.StudentsTable;
import tables.AbsTable;
import tables.CuratorsTable;
import tables.GroupsTable;
import tables.StudentsTable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static tools.printResultSet.printRS;

public class Main {

    public static void main(String[] args){

        boolean deleteTables = false;//////если нужно удалить таблицы
        /*Создать таблицу Student - Колонки id, fio, sex, id_group*/
        /*Создать таблицу Groups - Колонки id, name, id_curator*/
        /*Создать таблицу Curator - Колонки id, fio*/
        ///Создание таблиц
        AbsTable studentsTable = new StudentsTable();
        AbsTable curatorsTable = new CuratorsTable();
        AbsTable groupsTable = new GroupsTable();

       try {
           /*Создаем таблицы*/
        System.out.println("Создаем таблицы");
        studentsTable.create();
        curatorsTable.create();
        groupsTable.create();
            /*Загружаем данные*/
        System.out.println("Загружаем данные");
        studentsTable.loadData();
        curatorsTable.loadData();
        groupsTable.loadData();
           /*Вывести на экран информацию о всех студентах включая название группы и имя куратора
           select s.id, s.fio, s.sex, g."name" as group_name ,c.fio as curator_name
           from students s
           inner join Groups g on g.id =s.group_id
           inner join curators c on c.id =g.curator_id*/
           System.out.println("Выводим свисок всех студентов");

           ResultSet resultSet = studentsTable.selectQuery("students.id, students.fio, students.sex, groups.name as group_name, curators.fio as curator_fio ","students","curators","groups","where students.group_id=groups.id and groups.curator_id=curators.id");

           if (!resultSet.isBeforeFirst()){
               System.out.println("Запрос вернул пустой ответ");
           } else {
               printRS(resultSet);
           }
           System.out.println("-------------------------------------");


           /*Вывести на экран количество студентов*/
           resultSet=null;
           resultSet=studentsTable.selectQuery("count(*)","students",null,null,null);
           while (!resultSet.isBeforeFirst()) {
               System.out.println(String.format("Всего студентов - %d", resultSet.getInt(1)));
           }
           System.out.println("-------------------------------------");

           /*Вывести студенток*/
           resultSet=null;
           resultSet=studentsTable.selectQuery("id,fio,sex","students",null,null,"where sex='female'");
           System.out.println("Выводим только студенток");
           if (!resultSet.isBeforeFirst()){
               System.out.println("Запрос вернул пустой ответ");
           } else {
               printRS(resultSet);
           }
           System.out.println("-------------------------------------");

           /*Обновить данные по группе сменив куратора*/
           System.out.println("Обновляем данные по группе, сменив в группе 3;PythonTestGroup куратора с Куратор3 на Куратор4");

           groupsTable.updateTable("curator_id=4","id=3");
           System.out.println("-------------------------------------");
           /*Вывести список групп с их кураторами*/
           System.out.println("Выводим список групп с их кураторами");
           resultSet=null;
           resultSet=groupsTable.selectQuery("groups.id,groups.name, curators.fio as curators_fio","groups","curators",null,"where groups.curator_id=curators.id");
           if (!resultSet.isBeforeFirst()){
               System.out.println("Запрос вернул пустой ответ");
           } else {
               printRS(resultSet);
           }
           System.out.println("-------------------------------------");

           /*Используя вложенные запросы вывести на экран студентов из определенной группы(поиск по имени группы)*/
           System.out.println("Поиск студентов по названию группы. Введите название группы");
           resultSet=null;
           String st="";
           Scanner scanner = new Scanner(System.in);
           st=scanner.nextLine().toUpperCase();

           resultSet=groupsTable.selectQuery("students.id,students.fio,students.sex","students",null,null,"where students.group_id in (select id from groups where upper(name) like '%"+st+"%')");

           if (!resultSet.isBeforeFirst()){
               System.out.println("Запрос вернул пустой ответ");
           } else {
               printRS(resultSet);
           }
           System.out.println("-------------------------------------");
            System.out.println("Конец программы");
           } catch (SQLException e) {
           throw new RuntimeException(e);
       } finally {
        if (deleteTables){
            studentsTable.delete();
            curatorsTable.delete();
            groupsTable.delete();
        }

           DBConnector.close();
       }

    }
}
