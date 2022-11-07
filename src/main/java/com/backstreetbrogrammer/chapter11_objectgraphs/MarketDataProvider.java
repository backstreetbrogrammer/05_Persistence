package com.backstreetbrogrammer.chapter11_objectgraphs;

import java.io.Serializable;
import java.util.Objects;

public class MarketDataProvider implements Serializable {
    private static final long serialVersionUID = -7633339434701733029L;

    private String name;

    public MarketDataProvider(final String name) {
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
        return "MarketDataProvider{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final MarketDataProvider that = (MarketDataProvider) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
