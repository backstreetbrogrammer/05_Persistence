package com.backstreetbrogrammer.chapter22_serializationproxypattern;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class MarketDataWithProxyPattern implements Serializable {

    private static final long serialVersionUID = 6665048656197332881L;

    private String securityId;
    private long time;
    private double open;
    private double high;
    private double low;
    private double close;
    private double last;
    private boolean isLevelOne;

    public MarketDataWithProxyPattern() {
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

    @Override
    public String toString() {
        return "MarketDataWithProxyPattern{" +
                "securityId='" + securityId + '\'' +
                ", time=" + time +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", last=" + last +
                ", isLevelOne=" + isLevelOne +
                '}';
    }

    private Object writeReplace() {
        System.out.println("Inside enclosing class writeReplace()");
        return new MarketDataProxy(this);
    }

    private void readObject(final ObjectInputStream ois) throws InvalidObjectException {
        System.out.println("Inside enclosing class readObject()");
        throw new InvalidObjectException("Proxy required");
    }

    private static class MarketDataProxy implements Serializable {
        private static final long serialVersionUID = -7464320176996003654L;

        private final String securityId;
        private final long time;
        private final double open;
        private final double high;
        private final double low;
        private final double close;
        private final double last;
        private final boolean isLevelOne;

        public MarketDataProxy(final MarketDataWithProxyPattern enclosing) {
            this.securityId = enclosing.securityId;
            this.time = enclosing.time;
            this.open = enclosing.open;
            this.high = enclosing.high;
            this.low = enclosing.low;
            this.close = enclosing.close;
            this.last = enclosing.last;
            this.isLevelOne = enclosing.isLevelOne;
        }

        private Object readResolve() {
            System.out.println("Inside proxy readResolve()");
            final var marketData = new MarketDataWithProxyPattern();
            marketData.securityId = securityId;
            marketData.time = time;
            marketData.open = open;
            marketData.high = high;
            marketData.low = low;
            marketData.close = close;
            marketData.last = last;
            marketData.isLevelOne = isLevelOne;

            return marketData;
        }
    }
}
