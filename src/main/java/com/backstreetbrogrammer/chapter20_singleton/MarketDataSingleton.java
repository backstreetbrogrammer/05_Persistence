package com.backstreetbrogrammer.chapter20_singleton;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class MarketDataSingleton implements Serializable {

    private static final long serialVersionUID = -5605897089597388186L;

    private String securityId;
    private long time;
    private double open;
    private double high;
    private double low;
    private double close;
    private double last;
    private boolean isLevelOne;

    private MarketDataProviderSingleton marketDataProvider;

    private static final MarketDataSingleton INSTANCE = new MarketDataSingleton();

    private MarketDataSingleton() {
    }

    public static MarketDataSingleton getInstance() {
        return INSTANCE;
    }

    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(final String securityId) {
        this.securityId = securityId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(final long time) {
        this.time = time;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(final double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(final double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(final double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(final double close) {
        this.close = close;
    }

    public double getLast() {
        return last;
    }

    public void setLast(final double last) {
        this.last = last;
    }

    public boolean isLevelOne() {
        return isLevelOne;
    }

    public void setLevelOne(final boolean levelOne) {
        isLevelOne = levelOne;
    }

    public MarketDataProviderSingleton getMarketDataProvider() {
        return marketDataProvider;
    }

    public void setMarketDataProvider(final MarketDataProviderSingleton marketDataProvider) {
        this.marketDataProvider = marketDataProvider;
    }

    @Override
    public String toString() {
        return "MarketDataSingleton{" +
                "securityId='" + securityId + '\'' +
                ", time=" + time +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", last=" + last +
                ", isLevelOne=" + isLevelOne +
                ", marketDataProvider=" + marketDataProvider +
                '}';
    }

    private void readObject(final ObjectInputStream is) throws IOException, ClassNotFoundException {
        System.out.println("In readObject()");
        is.defaultReadObject();
    }

    private Object readResolve() throws ObjectStreamException {
        System.out.println("In readResolve()");
        System.out.printf("Deserialized object's hashcode before INSTANCE: %d%n", this.hashCode());
        return INSTANCE;
    }

}
