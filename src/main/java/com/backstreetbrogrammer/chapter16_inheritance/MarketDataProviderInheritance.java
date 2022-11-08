package com.backstreetbrogrammer.chapter16_inheritance;

import java.io.Serializable;
import java.util.Objects;

public class MarketDataProviderInheritance implements Serializable {

    private static final long serialVersionUID = -804656019789705752L;

    private String name;

    public MarketDataProviderInheritance(final String name) {
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
        return "MarketDataProviderInheritance{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final MarketDataProviderInheritance that = (MarketDataProviderInheritance) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
