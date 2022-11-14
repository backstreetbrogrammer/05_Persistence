package com.backstreetbrogrammer.chapter20_singleton;

import java.io.Serializable;
import java.util.Objects;

public class MarketDataProviderSingleton implements Serializable {

    private static final long serialVersionUID = 6939481248418136304L;

    private String name;

    public MarketDataProviderSingleton(final String name) {
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
        final MarketDataProviderSingleton that = (MarketDataProviderSingleton) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
