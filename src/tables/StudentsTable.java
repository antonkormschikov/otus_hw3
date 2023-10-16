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





}
