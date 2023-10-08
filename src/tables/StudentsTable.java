package tables;

import dataObj.Curator;
import dataObj.Group;
import dataObj.Student;
import db.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentsTable extends AbsTable {
   private static final String NAME = "Students";

    public StudentsTable() {
        super(NAME, COLUMNS);
    }

    private static final Map<String,String> COLUMNS = new HashMap(){{
        put("id", "int");
        put("fio", "text");
        put("sex","text");
        put("group_id","int");
    }};


    @Override
    public List<Student> list(List<String> columns){
        List<Student> result = new ArrayList<>();

        ResultSet sqlResult = dbConnector.executeQuery(String.format("select %s from %s",this.convertCollumnsTable(columns),NAME));
        try{
            while (sqlResult.next()){
                result.add(new Student(
                        sqlResult.getInt(1),
                        sqlResult.getString(2),
                        sqlResult.getString(3),
                        sqlResult.getInt(4)
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return result;

    }


}
