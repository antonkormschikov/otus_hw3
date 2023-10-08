package tables;

import java.sql.ResultSet;

public interface ITable {

    void create();
    void delete ();
    void loadData();
    ResultSet scriptGen(String cols, String a, String b, String c, String option);



}
