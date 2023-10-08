package dataObj;

public class Curator {
    private int id=-1;
    private String fio="";

    public Curator(int id,String fio){
        this.id=id;
        this.fio=fio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
