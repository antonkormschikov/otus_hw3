package db;

public interface IDBConnector {
    void close();
    void execute(String sqlRequest);


}
