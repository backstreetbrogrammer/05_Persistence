package com.backstreetbrogrammer.chapter10_serializationwithstaticfields;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class MarketDataWithStaticFieldsTest {

    private File serFile;

    @BeforeEach
    void setUp() throws IOException {
        final var serPath = Path.of("src", "test", "resources", "MarketDataWithStaticFieldsTest.ser");
        serFile = serPath.toFile();
        if (!serFile.exists()) {
            final var success = serFile.createNewFile();
            assertTrue(success);
        }
    }

    @Test
    @DisplayName("Test basic serialization and deserialization for Java POJO with static fields")
    void testSerializeAndDeserialize() throws IOException, ClassNotFoundException {
        final var marketData = new MarketDataWithStaticFields();
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

        marketData.setMdProvider("BBG");

        // deserialize and read
        try (final var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(serFile)))) {
            final var fromSerialize = (MarketDataWithStaticFields) ois.readObject();
            System.out.println("After Serialization: ");
            System.out.println(fromSerialize);

            // assertions
            assertNotNull(fromSerialize);
            assertEquals("AAPL", fromSerialize.getSecurityId());
            assertEquals(10000L, fromSerialize.getTime());
            assertEquals(160.3D, fromSerialize.getOpen());
            assertEquals(165.7D, fromSerialize.getHigh());
            assertEquals(157.2D, fromSerialize.getLow());
            assertEquals(163.1D, fromSerialize.getClose());
            assertEquals(161.9D, fromSerialize.getLast());
            assertTrue(fromSerialize.isLevelOne());

            assertEquals("BBG", fromSerialize.getMdProvider());

            marketData.setMdProvider("INET");
            assertEquals("INET", fromSerialize.getMdProvider());
        }
    }
}
