package tables;

import dataObj.Curator;
import dataObj.Student;
import db.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

public class CuratorsTable extends AbsTable {

    private static final String NAME = "Curators";

    private static final Map<String,String> COLUMNS = new HashMap(){{
        put("id", "int");
        put("fio", "text");

    }};

    public CuratorsTable(){
        super(NAME, COLUMNS);

    }
    @Override
    public List<Curator> list(List<String> columns){
        List<Curator> result = new ArrayList<>();

        ResultSet sqlResult = dbConnector.executeQuery(String.format("select %s from %s",this.convertCollumnsTable(columns),NAME));
        try{
            while (sqlResult.next()){
                result.add(new Curator(
                        sqlResult.getInt(1),
                        sqlResult.getString(2)

                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return result;

}
}