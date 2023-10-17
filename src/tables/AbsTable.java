package tables;

import db.DBConnector;
import db.IDBConnector;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.util.*;


public abstract class AbsTable implements ITable{
    protected IDBConnector dbConnector = null;
    private String tableName = "";
    private Map<String,String> columns=null;

    public AbsTable(String tableName,Map<String,String> columns) {
        dbConnector = new DBConnector();
        this.tableName = tableName;
        this.columns=columns;
    }

    public void create() {
        List<String> columnsStr = new ArrayList<>();

        for (Map.Entry<String,String> entry: columns.entrySet()){
            if (entry.getKey().equals("id")){
                columnsStr.add(String.format("%s serial primary key",entry.getKey()));
            } else {
                columnsStr.add(String.format("%s %s",entry.getKey(), entry.getValue()));
            }
        }
        dbConnector.execute(String.format("create table if not exists %s (%s);", this.tableName, String.join(", ",columnsStr)));
    }

 //загрузка данных
    public void loadData(){
        //чистим таблицу перед вставкой
        dbConnector.execute(String.format("truncate %s",this.tableName));
        //читаем данные их файла
        try {
            Scanner scanner = new Scanner(new File(System.getProperty("user.dir")+"/src/sources/"+this.tableName+".txt"));
            List<String> strings = new ArrayList<>();
            while (scanner.hasNextLine()){
                strings.add(scanner.nextLine());
            }
            String firstRow =strings.get(0).replace(";",",");
//            System.out.println(String.format("Названия столбцов - %s",firstRow));
            for (int i=1;i<strings.size();i++){
                String val_str=strings.get(i).replace(";",",");
               // System.out.println(val_str);
                String val[]=val_str.split(",");
                val_str="";
                //определяю тип данных
                for (String s:val){
                    if (s.matches("[-+]?\\d+")){
                        val_str=val_str+","+s;
                    } else val_str=val_str+",'"+s+"'";
                    }
                val_str=val_str.substring(1);
           //     System.out.println(String.format("Строка значений %s",val_str));
                String sqlRequest=String.format("insert into %s (%s) values (%s);",this.tableName,firstRow,val_str);
            //    System.out.println(sqlRequest);
                dbConnector.execute(sqlRequest);
            }
        }catch (FileNotFoundException ex) {ex.printStackTrace();}
    }

    @Override
    public void delete(){
        dbConnector.execute(String.format("drop table if exists %s;",this.tableName));
    }

    //cols - набор полей,  таблицы, options - условия и джойны
    public ResultSet selectQuery(String cols, String table1, String table2, String table3, String option){
        String sqlQuery = String.format("select %s from %s",cols,table1);
        //if (b!=null) sqlQuery+= ", "+a;
        if (table2!=null) sqlQuery+= ", "+table2;
        if (table3!=null) sqlQuery+= ", "+table3;
        if (option!=null) sqlQuery+= " "+option;

        return dbConnector.executeQuery(sqlQuery);
    }

    public void updateTable (String updData,String options){
        String sqlQuery=String.format("update %s set %s where %s ;", this.tableName,updData, options);
        dbConnector.execute(sqlQuery);
    }


}
