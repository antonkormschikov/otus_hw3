package tables;


import java.util.HashMap;
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
