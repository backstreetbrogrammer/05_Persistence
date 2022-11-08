package com.backstreetbrogrammer.chapter17_externalizable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class MarketDataExternalizableTest {

    private File serFile;

    @BeforeEach
    void setUp() throws IOException {
        final var serPath = Path.of("src", "test", "resources", "MarketDataExternalizableTest.ser");
        serFile = serPath.toFile();
        if (!serFile.exists()) {
            final var success = serFile.createNewFile();
            assertTrue(success);
        }
    }

    @Test
    @DisplayName("Test basic serialization for Java POJO with Externalizable")
    void testSerialize() throws IOException {
        final var marketData = new MarketDataExternalizable();
        marketData.setSecurityId("AAPL");
        marketData.setTime(10000L);
        marketData.setOpen(160.3D);
        marketData.setHigh(165.7D);
        marketData.setLow(157.2D);
        marketData.setClose(163.1D);
        marketData.setLast(161.9D);
        marketData.setLevelOne(true);

        marketData.setMdProvider("REUTERS");

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
    @DisplayName("Test basic deserialization for Java POJO with Externalizable")
    void testDeserialize() throws IOException, ClassNotFoundException {
        try (final var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(serFile)))) {
            final var fromSerialize = (MarketDataExternalizable) ois.readObject();
            System.out.println("After Serialization: ");
            System.out.println(fromSerialize);

            // assertions
            assertNotNull(fromSerialize);
            assertEquals("AAPL", fromSerialize.getSecurityId());
            assertEquals(10000L, fromSerialize.getTime());
            assertEquals(0D, fromSerialize.getOpen());
            assertEquals(0D, fromSerialize.getHigh());
            assertEquals(0D, fromSerialize.getLow());
            assertEquals(0D, fromSerialize.getClose());
            assertEquals(161.9D, fromSerialize.getLast());
            assertTrue(fromSerialize.isLevelOne());
            assertEquals("REUTERS", fromSerialize.getMdProvider());
        }
    }
}
