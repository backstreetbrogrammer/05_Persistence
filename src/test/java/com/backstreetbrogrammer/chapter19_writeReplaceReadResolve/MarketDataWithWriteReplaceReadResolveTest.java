package com.backstreetbrogrammer.chapter19_writeReplaceReadResolve;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class MarketDataWithWriteReplaceReadResolveTest {
    private File serFile;

    @BeforeEach
    void setUp() throws IOException {
        final var serPath = Path.of("src", "test", "resources",
                "MarketDataWithWriteReplaceReadResolveTest.ser");
        serFile = serPath.toFile();
        if (!serFile.exists()) {
            final var success = serFile.createNewFile();
            assertTrue(success);
        }
    }

    @Test
    @DisplayName("Test basic serialization for Java POJO with write replace and read resolve")
    void testSerialize() throws IOException {
        final var marketData = new MarketDataWithWriteReplaceReadResolve();
        marketData.setSecurityId("AAPL");
        marketData.setTime(10000L);
        marketData.setOpen(160.3D);
        marketData.setHigh(165.7D);
        marketData.setLow(157.2D);
        marketData.setClose(163.1D);
        marketData.setLast(161.9D);
        marketData.setLevelOne(true);

        // serialize and write
        try (final var oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(serFile)))) {
            System.out.println("Before Serialization: ");
            System.out.println(marketData);
            oos.writeObject(marketData);
        }
    }

    @Test
    @DisplayName("Test basic deserialization for Java POJO with write replace and read resolve")
    void testDeserialize() throws IOException, ClassNotFoundException {
        try (final var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(serFile)))) {
            final var fromSerialize = (MarketDataWithWriteReplaceReadResolve) ois.readObject();
            System.out.println("After Serialization: ");
            System.out.println(fromSerialize);

            // assertions
            assertNotNull(fromSerialize);
            assertEquals("AMZN", fromSerialize.getSecurityId());
            assertEquals(30000L, fromSerialize.getTime());
            assertEquals(360.3D, fromSerialize.getOpen());
            assertEquals(365.7D, fromSerialize.getHigh());
            assertEquals(357.2D, fromSerialize.getLow());
            assertEquals(363.1D, fromSerialize.getClose());
            assertEquals(361.9D, fromSerialize.getLast());
            assertTrue(fromSerialize.isLevelOne());
        }
    }
}
