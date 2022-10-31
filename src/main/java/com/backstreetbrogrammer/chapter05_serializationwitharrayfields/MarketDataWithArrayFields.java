package com.backstreetbrogrammer.chapter05_serializationwitharrayfields;

import java.io.Serializable;
import java.util.Arrays;

public class MarketDataWithArrayFields implements Serializable {
    private static final long serialVersionUID = 3414094596727769694L;

    private String securityId;
    private long time;
    private double open;
    private double high;
    private double low;
    private double close;
    private double last;
    private boolean isLevelOne;
    private String[] mdProviders;


    public MarketDataWithArrayFields() {
    }

    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getLast() {
        return last;
    }

    public void setLast(double last) {
        this.last = last;
    }

    public boolean isLevelOne() {
        return isLevelOne;
    }

    public void setLevelOne(boolean levelOne) {
        isLevelOne = levelOne;
    }

    public String[] getMdProviders() {
        return mdProviders;
    }

    public void setMdProviders(String[] mdProviders) {
        this.mdProviders = mdProviders;
    }

    @Override
    public String toString() {
        return "MarketDataWithArrayFields{" +
                "securityId='" + securityId + '\'' +
                ", time=" + time +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", last=" + last +
                ", isLevelOne=" + isLevelOne +
                ", mdProviders=" + Arrays.toString(mdProviders) +
                '}';
    }
}
