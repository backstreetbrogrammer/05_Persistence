package com.backstreetbrogrammer.chapter11_objectgraphs;

import java.io.Serializable;

public class MarketDataWithReferenceObjects implements Serializable {
    private static final long serialVersionUID = 1243007358393336670L;

    private String securityId;
    private long time;
    private double open;
    private double high;
    private double low;
    private double close;
    private double last;
    private boolean isLevelOne;

    private MarketDataProvider marketDataProvider;

    public MarketDataWithReferenceObjects() {
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

    public MarketDataProvider getMarketDataProvider() {
        return marketDataProvider;
    }

    public void setMarketDataProvider(final MarketDataProvider marketDataProvider) {
        this.marketDataProvider = marketDataProvider;
    }

    @Override
    public String toString() {
        return "MarketDataWithReferenceObjects{" +
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
