package com.backstreetbrogrammer.chapter17_externalizable;

import java.io.*;

public class MarketDataExternalizable implements Externalizable {
    private static final long serialVersionUID = -7860178587676652067L;

    private String securityId;
    private long time;
    private double open;
    private double high;
    private double low;
    private double close;
    private boolean isLevelOne;

    private transient double last;
    private static String mdProvider;

    public MarketDataExternalizable() {
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

    public boolean isLevelOne() {
        return isLevelOne;
    }

    public void setLevelOne(final boolean levelOne) {
        isLevelOne = levelOne;
    }

    public double getLast() {
        return last;
    }

    public void setLast(final double last) {
        this.last = last;
    }

    public static String getMdProvider() {
        return mdProvider;
    }

    public static void setMdProvider(final String mdProvider) {
        MarketDataExternalizable.mdProvider = mdProvider;
    }

    @Override
    public String toString() {
        return "MarketDataExternalizable{" +
                "securityId='" + securityId + '\'' +
                ", time=" + time +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", isLevelOne=" + isLevelOne +
                ", last=" + last +
                ", mdProvider=" + mdProvider +
                '}';
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeUTF(securityId);
        out.writeLong(time);
        out.writeBoolean(isLevelOne);

        out.writeDouble(last);
        out.writeUTF(mdProvider);
    }

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        securityId = in.readUTF();
        time = in.readLong();
        isLevelOne = in.readBoolean();

        last = in.readDouble();
        mdProvider = in.readUTF();
    }

    private void writeObject(final ObjectOutputStream os) throws IOException {
        System.out.println("writeObject() will never be called");
    }

    private void readObject(final ObjectInputStream is) throws IOException, ClassNotFoundException {
        System.out.println("readObject() will never be called");
    }
}
