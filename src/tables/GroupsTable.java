package tables;

import java.util.HashMap;
import java.util.Map;

public class GroupsTable extends AbsTable{
    private static final String NAME = "Groups";

    public  GroupsTable(){
        super(NAME,COLUMNS);
    }

    private static final Map<String,String> COLUMNS = new HashMap(){{
        put("id", "int");
        put("name", "text");
        put("curator_id","int");
    }};




}
