package tools;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class PrintResultSet {

    public static void printRS(ResultSet rs) throws SQLException {

            ResultSetMetaData resultSetMetaData;
            resultSetMetaData=rs.getMetaData();
            int columnsCount=resultSetMetaData.getColumnCount();
            String headers="";
            for (int i=1; i<=columnsCount;i++){
            headers+=resultSetMetaData.getColumnName(i)+" ";
            }

            System.out.println(headers.trim());
            String rezRowString="";
            while (rs.next()) {
                for (int i=1;i<=columnsCount;i++){
                    rezRowString+="  "+rs.getString(i);
                }
                rezRowString=rezRowString.substring(2);
                System.out.println(rezRowString);
                rezRowString="";
            }

        }
}


