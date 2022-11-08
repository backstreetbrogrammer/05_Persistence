package com.backstreetbrogrammer.chapter18_objectinputvalidation;

import java.io.*;

public class MarketDataValidateObject implements Serializable, ObjectInputValidation {

    private static final long serialVersionUID = -8365445356039131264L;

    private String securityId;
    private long time;
    private double open;
    private double high;
    private double low;
    private double close;
    private double last;
    private boolean isLevelOne;

    public MarketDataValidateObject() {
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
        return "MarketDataValidateObject{" +
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
        try {
            os.defaultWriteObject();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private void readObject(final ObjectInputStream is) throws IOException, ClassNotFoundException {
        try {
            is.registerValidation(this, 0);
            is.defaultReadObject();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void validateObject() throws InvalidObjectException {
        if (time < 0L) {
            throw new InvalidObjectException(String.format("time %d can never be negative", time));
        }
    }
}
