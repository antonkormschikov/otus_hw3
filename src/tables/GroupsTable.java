package tables;

import dataObj.Curator;
import dataObj.Group;
import db.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
