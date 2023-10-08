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
           inner join "Groups" g on g.id =s.group_id
           inner join curators c on c.id =g.curator_id*/
           System.out.println("Выводим свисок всех студентов");

           ResultSet resultSet = studentsTable.scriptGen("students.id, students.fio, students.sex, groups.name as group_name, curators.fio as curator_fio ","students","curators","groups","where students.group_id=groups.id and groups.curator_id=curators.id");
           while (resultSet.next()) {
               System.out.println(String.format("%d  %s  %s  %s %s" , resultSet.getInt(1),
                                                           resultSet.getString(2),
                                                           resultSet.getString(3),
                                                           resultSet.getString(4),
                                                           resultSet.getString(5)));
           }
            resultSet.close();
           System.out.println("-------------------------------------");
           /*Вывести на экран количество студентов*/

           resultSet=studentsTable.scriptGen("count(*)","students",null,null,null);
           while (resultSet.next()) {
               System.out.println(String.format("Всего студентов - %d", resultSet.getInt(1)));
           }

           System.out.println("-------------------------------------");
           /*Вывести студенток*/
           System.out.println("Выводим только студенток");

          resultSet = studentsTable.scriptGen("students.id, students.fio, students.sex, groups.name as group_name, curators.fio as curator_fio ","students","curators","groups","where students.group_id=groups.id and groups.curator_id=curators.id and students.sex='female'");
           while (resultSet.next()) {
               System.out.println(String.format("%d  %s  %s  %s %s" , resultSet.getInt(1),
                       resultSet.getString(2),
                       resultSet.getString(3),
                       resultSet.getString(4),
                       resultSet.getString(5)));
           }
           System.out.println("-------------------------------------");

           /*Обновить данные по группе сменив куратора*/
           System.out.println("Обновляем данные по группе, сменив в группе 3;PythonTestGroup куратора с Куратор3 на Куратор4");

           groupsTable.updateTable("curator_id=4","curator_id=3");
           System.out.println("-------------------------------------");
           /*Вывести список групп с их кураторами*/
           System.out.println("Выводим список групп с их кураторами");
           resultSet = groupsTable.scriptGen("groups.id,groups.name as group_name, curators.fio as curator_fio ","groups","curators",null,"where groups.curator_id=curators.id");
           while (resultSet.next()) {
               System.out.println(String.format("%d  %s  %s" , resultSet.getInt(1),
                       resultSet.getString(2),
                       resultSet.getString(3)
                                              ));
           }
           System.out.println("-------------------------------------");

           /*Используя вложенные запросы вывести на экран студентов из определенной группы(поиск по имени группы)*/
           System.out.println("Поиск студентов по названию группы. Введите название группы");
           String st="";
           Scanner scanner = new Scanner(System.in);
           st=scanner.nextLine().toUpperCase();
           System.out.println(st);

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
