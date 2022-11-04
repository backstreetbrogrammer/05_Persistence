package com.backstreetbrogrammer.chapter12_transient;

import java.util.Objects;

public class MarketDataProviderTransient {
    private String name;

    public MarketDataProviderTransient(final String name) {
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
        return "MarketDataProviderTransient{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarketDataProviderTransient that = (MarketDataProviderTransient) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
