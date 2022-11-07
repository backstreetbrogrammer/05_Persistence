package com.backstreetbrogrammer.chapter14_usingobjectstreamfield;

import java.io.*;

public class MarketDataWhitelisting implements Serializable {

    private static final long serialVersionUID = 5146363952418217459L;

    private String securityId;
    private long time;
    private double open;
    private double high;
    private double low;
    private double close;
    private double last;
    private boolean isLevelOne;

    private MarketDataProviderWhitelisting marketDataProvider;

    public MarketDataWhitelisting() {
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

    public MarketDataProviderWhitelisting getMarketDataProvider() {
        return marketDataProvider;
    }

    public void setMarketDataProvider(final MarketDataProviderWhitelisting marketDataProvider) {
        this.marketDataProvider = marketDataProvider;
    }

    @Override
    public String toString() {
        return "MarketDataWhitelisting{" +
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

    private static final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("securityId", String.class),
            new ObjectStreamField("time", Long.TYPE),
            new ObjectStreamField("last", Double.TYPE)
    };

    private void writeObject(final ObjectOutputStream os) throws IOException {
        try {
            final ObjectOutputStream.PutField fields = os.putFields();
            fields.put("securityId", securityId);
            fields.put("time", time);
            fields.put("last", last);
            os.writeFields();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private void readObject(final ObjectInputStream is) throws IOException, ClassNotFoundException {
        try {
            final ObjectInputStream.GetField fields = is.readFields();
            securityId = (String) fields.get("securityId", null);
            time = fields.get("time", 0L);
            last = fields.get("last", 0D);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
