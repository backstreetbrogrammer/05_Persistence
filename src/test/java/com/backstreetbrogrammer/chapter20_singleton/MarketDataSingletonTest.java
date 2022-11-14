package com.backstreetbrogrammer.chapter20_singleton;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class MarketDataSingletonTest {
    private File serFile;

    @BeforeEach
    void setUp() throws IOException {
        final var serPath = Path.of("src", "test", "resources",
                "MarketDataSingletonTest.ser");
        serFile = serPath.toFile();
        if (!serFile.exists()) {
            final var success = serFile.createNewFile();
            assertTrue(success);
        }
    }

    @Test
    @DisplayName("Test basic serialization and deserialization for Java POJO with singleton pattern")
    void testSerializeAndDeserialize() throws IOException, ClassNotFoundException {
        final var marketData = MarketDataSingleton.getInstance();
        marketData.setSecurityId("AAPL");
        marketData.setTime(10000L);
        marketData.setOpen(160.3D);
        marketData.setHigh(165.7D);
        marketData.setLow(157.2D);
        marketData.setClose(163.1D);
        marketData.setLast(161.9D);
        marketData.setLevelOne(true);

        marketData.setMarketDataProvider(new MarketDataProviderSingleton("INET"));

        final int objectHashCode = marketData.hashCode();

        // serialize and write
        try (final var oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(serFile)))) {
            System.out.println("Before Serialization: ");
            System.out.println(marketData);
            System.out.println(objectHashCode);
            oos.writeObject(marketData);
        }

        // deserialize and read the same INSTANCE
        testDeserialize(objectHashCode);
    }

    void testDeserialize(final int objectHashCode) throws IOException, ClassNotFoundException {
        try (final var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(serFile)))) {
            final var fromSerialize = (MarketDataSingleton) ois.readObject();
            System.out.println("After Serialization: ");
            System.out.println(fromSerialize);

            // assertions
            assertNotNull(fromSerialize);
            System.out.println(fromSerialize.hashCode());
            assertEquals(objectHashCode, fromSerialize.hashCode());

            assertEquals("AAPL", fromSerialize.getSecurityId());
            assertEquals(10000L, fromSerialize.getTime());
            assertEquals(160.3D, fromSerialize.getOpen());
            assertEquals(165.7D, fromSerialize.getHigh());
            assertEquals(157.2D, fromSerialize.getLow());
            assertEquals(163.1D, fromSerialize.getClose());
            assertEquals(161.9D, fromSerialize.getLast());
            assertTrue(fromSerialize.isLevelOne());

            final var marketDataProvider = fromSerialize.getMarketDataProvider();
            assertNotNull(marketDataProvider);
            assertEquals("INET", marketDataProvider.getName());
        }
    }
}
