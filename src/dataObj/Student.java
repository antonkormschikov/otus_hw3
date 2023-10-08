package dataObj;

public class Student {
    private int id=-1;
    private String fio="";
    private String sex="";
    private int group_id=-1;


    public Student(int id, String fio, String sex, int group_id){
        this.id=id;
        this.fio=fio;
        this.sex=sex;
        this.group_id=group_id;

    }

    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public String getSex() {
        return sex;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }
}
