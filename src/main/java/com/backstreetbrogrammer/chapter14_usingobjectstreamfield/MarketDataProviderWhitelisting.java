package com.backstreetbrogrammer.chapter14_usingobjectstreamfield;

import java.io.Serializable;
import java.util.Objects;

public class MarketDataProviderWhitelisting implements Serializable {

    private static final long serialVersionUID = 1335168121172928353L;

    private String name;

    public MarketDataProviderWhitelisting(final String name) {
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
        return "MarketDataProviderWhitelisting{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final MarketDataProviderWhitelisting that = (MarketDataProviderWhitelisting) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
