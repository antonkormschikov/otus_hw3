package dataObj;

public class Group {
    private int id=-1;
    private String name="";
    private int curator_id=-1;


    public Group(int id, String name, int curator_id) {
        this.id = id;
        this.name = name;
        this.curator_id = curator_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurator_id() {
        return curator_id;
    }

    public void setCurator_id(int curator_id) {
        this.curator_id = curator_id;
    }
}
