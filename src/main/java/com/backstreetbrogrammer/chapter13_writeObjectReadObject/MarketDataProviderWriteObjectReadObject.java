package com.backstreetbrogrammer.chapter13_writeObjectReadObject;

import java.util.Objects;

public class MarketDataProviderWriteObjectReadObject {
    private String name;

    public MarketDataProviderWriteObjectReadObject(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MarketDataProviderWriteObjectReadObject{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final MarketDataProviderWriteObjectReadObject that = (MarketDataProviderWriteObjectReadObject) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
