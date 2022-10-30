package com.backstreetbrogrammer.chapter02_serializationwithmemorybuffer;

import java.io.Serializable;

public class MarketDataWithPrimitiveFields implements Serializable {
    private String securityId;
    private long time;
    private double open;
    private double high;
    private double low;
    private double close;
    private double last;
    private boolean isLevelOne;

    public MarketDataWithPrimitiveFields() {
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

    @Override
    public String toString() {
        return "MarketDataWithPrimitiveFields{" +
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
}
