package tables;

import java.sql.ResultSet;

public interface ITable {

    void create();
    void delete ();
    void loadData();
    ResultSet selectQuery(String cols, String table1, String table2, String table3, String option);



}
