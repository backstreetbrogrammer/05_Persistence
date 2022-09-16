package chapter01;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class MarketDataFactory {

    private static final String[] securityIds = {"AAPL", "IBM", "GOOGL", "AMZN"};
    private static final MarketDataProvider[] dataProvider = {new MarketDataProvider("BBG"),
            new MarketDataProvider("REUTERS"),
            new MarketDataProvider("CHIX"),
            new MarketDataProvider("INET")};

    private static final AtomicInteger securityId = new AtomicInteger(1);

    private MarketDataFactory() {
    }

    public static List<MarketData> getMarketDataObjects(final int n) {
        final List<MarketData> marketDataObjects = Lists.newArrayList();

        if (n > 0) {
            for (int i = 0; i < n; i++) {
                final var marketData = new MarketData();

                marketData.setId(String.valueOf(securityId.getAndAdd(1)));
                marketData.setSecurityId(securityIds[ThreadLocalRandom.current().nextInt(securityIds.length)]);

                final double open = 20 + 100 * ThreadLocalRandom.current().nextDouble();
                marketData.setOpen(open);
                marketData.setHigh(open * 1.1D);
                marketData.setLow(open * 0.97D);
                marketData.setClose(open * 1.05D);
                marketData.setLast(open * 1.06D);
                marketData.setTime(System.currentTimeMillis());

                marketData.setMarketDataProvider(dataProvider[ThreadLocalRandom.current().nextInt(dataProvider.length)]);
                marketDataObjects.add(marketData);
            }
        }
        return marketDataObjects;
    }

    public static MarketData getOneMarketData() {
        final var marketData = new MarketData();
        marketData.setSecurityId("AAPL");
        marketData.setTime(System.currentTimeMillis());
        marketData.setOpen(160.3D);
        marketData.setHigh(165.7D);
        marketData.setLow(157.2D);
        marketData.setClose(163.1D);
        marketData.setLast(161.9D);

        final var marketDataProvider = new MarketDataProvider("Bloomberg");
        marketData.setMarketDataProvider(marketDataProvider);
        marketData.setId("0001");

        return marketData;
    }
}
