package tables;

import db.DBConnector;

import java.util.List;
import java.util.Map;

public abstract class AbsTable {
    private DBConnector dbConnector = null;
    private String tableName = "";

    public AbsTable(String tableName) {
        dbConnector = new DBConnector();
        this.tableName = tableName;

    }

    public void create(Map<String,String> columns) {
        dbConnector.execute(String.format("create table if not exist %s ();", this.tableName));

    }

    public void delete (){
        dbConnector.execute(String.format("delete table if exist %s",this.tableName));
    }
public abstract List list();


}
