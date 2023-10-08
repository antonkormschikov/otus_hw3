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


    @Override
    public List<Group> list(List<String> columns){
        List<Group> result = new ArrayList<>();

        ResultSet sqlResult = dbConnector.executeQuery(String.format("select %s from %s",this.convertCollumnsTable(columns),NAME));
    try{
        while (sqlResult.next()){
            result.add(new Group(
                    sqlResult.getInt(1),
                    sqlResult.getString(2),
                    sqlResult.getInt(3)
            ));
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }


        return result;

    }

}
