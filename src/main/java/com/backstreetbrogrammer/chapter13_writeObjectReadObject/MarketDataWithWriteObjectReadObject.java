package com.backstreetbrogrammer.chapter13_writeObjectReadObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MarketDataWithWriteObjectReadObject implements Serializable {

    private static final long serialVersionUID = -81051919619200610L;

    private String securityId;
    private long time;
    private double open;
    private double high;
    private double low;
    private double close;
    private double last;
    private boolean isLevelOne;

    private transient MarketDataProviderWriteObjectReadObject marketDataProvider;

    public MarketDataWithWriteObjectReadObject() {
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

    public MarketDataProviderWriteObjectReadObject getMarketDataProvider() {
        return marketDataProvider;
    }

    public void setMarketDataProvider(MarketDataProviderWriteObjectReadObject marketDataProvider) {
        this.marketDataProvider = marketDataProvider;
    }

    @Override
    public String toString() {
        return "MarketDataWithWriteObjectReadObject{" +
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

    private void writeObject(final ObjectOutputStream os) throws IOException {
        try {
            os.defaultWriteObject();
            os.writeUTF(marketDataProvider.getName());
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private void readObject(final ObjectInputStream is) throws IOException, ClassNotFoundException {
        try {
            is.defaultReadObject();
            marketDataProvider = new MarketDataProviderWriteObjectReadObject(is.readUTF());
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
