package com.backstreetbrogrammer.chapter19_writeReplaceReadResolve;

import java.io.*;

public class MarketDataWithWriteReplaceReadResolve implements Serializable {

    private static final long serialVersionUID = 3258961892310923186L;

    private String securityId;
    private long time;
    private double open;
    private double high;
    private double low;
    private double close;
    private double last;
    private boolean isLevelOne;

    public MarketDataWithWriteReplaceReadResolve() {
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
        return "MarketDataWithWriteReplaceReadResolve{" +
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

    private void writeObject(final ObjectOutputStream os) throws IOException {
        System.out.println("In writeObject()");
        os.defaultWriteObject();
    }

    private void readObject(final ObjectInputStream is) throws IOException, ClassNotFoundException {
        System.out.println("In readObject()");
        is.defaultReadObject();
    }

    private Object writeReplace() throws ObjectStreamException {
        System.out.println("In writeReplace()");

        final var marketData = new MarketDataWithWriteReplaceReadResolve();
        marketData.setSecurityId("META");
        marketData.setTime(20000L);
        marketData.setOpen(260.3D);
        marketData.setHigh(265.7D);
        marketData.setLow(257.2D);
        marketData.setClose(263.1D);
        marketData.setLast(261.9D);
        marketData.setLevelOne(true);

        return marketData;
    }


    private Object readResolve() throws ObjectStreamException {
        System.out.println("In readResolve()");

        final var marketData = new MarketDataWithWriteReplaceReadResolve();
        marketData.setSecurityId("AMZN");
        marketData.setTime(30000L);
        marketData.setOpen(360.3D);
        marketData.setHigh(365.7D);
        marketData.setLow(357.2D);
        marketData.setClose(363.1D);
        marketData.setLast(361.9D);
        marketData.setLevelOne(true);

        return marketData;
    }
}
