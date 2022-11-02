package chapter01;

import java.io.Serializable;

public class Identifiable implements Serializable {
    private static final long serialVersionUID = 43L;
    
    private String id;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Identifiable{" +
                "id='" + id + '\'' +
                '}';
    }
}
