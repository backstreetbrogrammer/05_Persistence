package com.backstreetbrogrammer.chapter06_serializationwitharrayobjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MarketDataWithArrayObjectsTest {

    private File serFile;

    @BeforeEach
    void setUp() throws IOException {
        final var serPath = Path.of("src", "test", "resources", "MarketDataWithArrayObjectsTest.ser");
        serFile = serPath.toFile();
        if (!serFile.exists()) {
            final var success = serFile.createNewFile();
            assertTrue(success);
        }
    }

    @Test
    @DisplayName("Test basic serialization for Java POJOs as array")
    void testSerialize() throws IOException {
        final var marketData1 = new MarketDataWithArrayObjects();
        marketData1.setSecurityId("AAPL");
        marketData1.setTime(10000L);
        marketData1.setOpen(160.3D);
        marketData1.setHigh(165.7D);
        marketData1.setLow(157.2D);
        marketData1.setClose(163.1D);
        marketData1.setLast(161.9D);
        marketData1.setLevelOne(true);

        final var marketData2 = new MarketDataWithArrayObjects();
        marketData2.setSecurityId("META");
        marketData2.setTime(20000L);
        marketData2.setOpen(260.3D);
        marketData2.setHigh(265.7D);
        marketData2.setLow(257.2D);
        marketData2.setClose(263.1D);
        marketData2.setLast(261.9D);
        marketData2.setLevelOne(true);

        final var marketDataObjects = new MarketDataWithArrayObjects[]{marketData1, marketData2};

        // serialize and write
        try (final var oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(serFile)))) {
            System.out.println("Before Serialization: ");
            Arrays.stream(marketDataObjects)
                    .forEach(System.out::println);
            oos.writeObject(marketDataObjects);
        }
    }

    @Test
    @DisplayName("Test basic deserialization for Java POJOs as array")
    void testDeserialize() throws IOException, ClassNotFoundException {
        try (final var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(serFile)))) {
            final var fromSerialize = ois.readObject();
            assertNotNull(fromSerialize);
            assertTrue(fromSerialize.getClass().isArray());

            final var marketDataObjects = (MarketDataWithArrayObjects[]) fromSerialize;
            assertEquals(2, marketDataObjects.length);

            final var marketData1 = marketDataObjects[0];
            assertNotNull(marketData1);
            assertEquals("AAPL", marketData1.getSecurityId());
            assertEquals(10000L, marketData1.getTime());
            assertEquals(160.3D, marketData1.getOpen());
            assertEquals(165.7D, marketData1.getHigh());
            assertEquals(157.2D, marketData1.getLow());
            assertEquals(163.1D, marketData1.getClose());
            assertEquals(161.9D, marketData1.getLast());
            assertTrue(marketData1.isLevelOne());

            final var marketData2 = marketDataObjects[1];
            assertNotNull(marketData2);
            assertEquals("META", marketData2.getSecurityId());
            assertEquals(20000L, marketData2.getTime());
            assertEquals(260.3D, marketData2.getOpen());
            assertEquals(265.7D, marketData2.getHigh());
            assertEquals(257.2D, marketData2.getLow());
            assertEquals(263.1D, marketData2.getClose());
            assertEquals(261.9D, marketData2.getLast());
            assertTrue(marketData2.isLevelOne());
        }
    }
}
