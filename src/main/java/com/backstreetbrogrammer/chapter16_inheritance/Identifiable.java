package com.backstreetbrogrammer.chapter16_inheritance;

public class Identifiable {

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
