package dopMaterial.IO_File.sample7_Alishev;

import java.io.Serializable;


public class People implements Serializable {
    private final static long serialVersionUID = 1;

    int id;
    String name;

    public People(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " : " + name;
    }
}
