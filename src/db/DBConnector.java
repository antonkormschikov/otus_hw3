package db;

import settings.Settings;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DBConnector implements IDBConnector{

    private static Connection connection = null;
    private static Statement statement = null;
    private Map<String,String> settings;

    public DBConnector(){
        this.settings = new Settings().getDbSettings();
    }

    private void open(){

        try{
            if (connection == null){
                connection = DriverManager.getConnection(settings.get("db_url")+"/"
                        +settings.get("db_name"),settings.get("username"),settings.get("password"));
            }
            if (statement == null) {
                statement = connection.createStatement();
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }

    }
    public void execute(String sqlRequest){
        this.open();
        try {
            statement.execute(sqlRequest);
        } catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            this.close();
        }

    }

    public void close(){

    if (statement != null) {
        try {
        statement.close();
    }catch (SQLException ex){ex.printStackTrace();}

    }
    if (connection!=null){
        try{
        connection.close();
    }catch (SQLException ex) {ex.printStackTrace();}
    }
    }

    public List executeQuery(){
        return null;
    }



}
