package chapter01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MarketData extends Identifiable implements Serializable {
    private static final long serialVersionUID = 47L;

    private String securityId;
    private long time;
    private double open;
    private double high;
    private double low;
    private double close;
    private double last;

    private transient MarketDataProvider marketDataProvider;

    public MarketData() {
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

    public MarketDataProvider getMarketDataProvider() {
        return marketDataProvider;
    }

    public void setMarketDataProvider(final MarketDataProvider marketDataProvider) {
        this.marketDataProvider = marketDataProvider;
    }

    @Override
    public String toString() {
        return "MarketData{" +
                "securityId='" + securityId + '\'' +
                ", time=" + time +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", last=" + last +
                ", marketDataProvider=" + marketDataProvider +
                ", id=" + getId() +
                '}';
    }

    private void writeObject(final ObjectOutputStream oos) {
        try {
            oos.defaultWriteObject();
            oos.writeUTF(marketDataProvider.getName());
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private void readObject(final ObjectInputStream ois) {
        try {
            ois.defaultReadObject();
            marketDataProvider = new MarketDataProvider(ois.readUTF());
        } catch (final IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
