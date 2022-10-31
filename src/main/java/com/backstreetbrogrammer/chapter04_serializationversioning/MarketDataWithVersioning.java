package com.backstreetbrogrammer.chapter04_serializationversioning;

import java.io.Serializable;
import java.util.Objects;

public class MarketDataWithVersioning implements Serializable {
    private static final long serialVersionUID = 2389906946734286709L;

    private String securityId;
    private long time;
    private double open;
    private double high;
    private double low;
    private double close;
    private double last;
    private boolean isLevelOne;

    public MarketDataWithVersioning() {
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
        return "MarketDataWithVersioning{" +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarketDataWithVersioning that = (MarketDataWithVersioning) o;
        return time == that.time && Double.compare(that.open, open) == 0 && Double.compare(that.high, high) == 0 && Double.compare(that.low, low) == 0 && Double.compare(that.close, close) == 0 && Double.compare(that.last, last) == 0 && isLevelOne == that.isLevelOne && Objects.equals(securityId, that.securityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(securityId, time, open, high, low, close, last, isLevelOne);
    }
}
