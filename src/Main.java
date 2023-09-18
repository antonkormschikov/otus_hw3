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
        dbConnector.execute("drop table if exists Students;");
    }
}
