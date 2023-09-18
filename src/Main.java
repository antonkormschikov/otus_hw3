import db.DBConnector;
/*import tables.AbsTable;
import tables.CuratorsTable;
import tables.GroupsTable;
import tables.StudentsTable;*/

public class Main {

    public static void main(String[] args){
    /*    AbsTable studentsTable = new StudentsTable();
        AbsTable curatorTable = new CuratorsTable();
        AbsTable groupsTable = new GroupsTable();*/

      /*  try {
            studentsTable.create();
            curatorTable.create();
            groupsTable.create();

        }finally {

        }*/
        DBConnector dbConnector = new DBConnector();
        dbConnector.execute("create table if not exists Students(\n" +
                "id bigint,\n" +
                "surname varchar,\n" +
                "\"name\" varchar,\n" +
                "patronymic varchar,\n" +
                "sex varchar,\n" +
                "group_id bigint\n" +
                ")\n");
    }
}
