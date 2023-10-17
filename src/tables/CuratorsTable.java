package tables;

import java.util.HashMap;
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

}