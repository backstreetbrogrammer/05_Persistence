package com.backstreetbrogrammer.chapter12_transient;

import java.io.Serializable;

public class MarketDataWithTransient implements Serializable {
    private static final long serialVersionUID = 5872089273677350872L;

    private String securityId;
    private long time;
    private double open;
    private double high;
    private double low;
    private double close;

    private transient double last;
    private transient boolean isLevelOne;
    private transient MarketDataProviderTransient marketDataProvider;

    public MarketDataWithTransient() {
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

    public MarketDataProviderTransient getMarketDataProvider() {
        return marketDataProvider;
    }

    public void setMarketDataProvider(final MarketDataProviderTransient marketDataProvider) {
        this.marketDataProvider = marketDataProvider;
    }

    @Override
    public String toString() {
        return "MarketDataWithTransient{" +
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
}
