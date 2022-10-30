package com.backstreetbrogrammer.chapter03_serializationwithfile;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class MarketDataWithPrimitiveFieldsFileTest {

    private File serFile;
    private final long time = 10000L;

    @BeforeEach
    void setUp() throws IOException {
        final Path serPath = Path.of("src", "test", "resources", "MarketDataWithPrimitiveFieldsFileTest.ser");
        serFile = serPath.toFile();
        if (!serFile.exists()) {
            final boolean success = serFile.createNewFile();
            assertTrue(success);
        }
    }

    @Test
    @DisplayName("Test basic serialization for Java POJO with primitives")
    void testSerialize() throws IOException {
        final var marketData = new MarketDataWithPrimitiveFieldsFile();
        marketData.setSecurityId("AAPL");
        marketData.setTime(time);
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
    @DisplayName("Test basic deserialization for Java POJO with primitives")
    void testDeserialize() throws IOException, ClassNotFoundException {
        try (final var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(serFile)))) {
            final MarketDataWithPrimitiveFieldsFile fromSerialize = (MarketDataWithPrimitiveFieldsFile) ois.readObject();
            System.out.println("After Serialization: ");
            System.out.println(fromSerialize);

            // assertions
            assertNotNull(fromSerialize);
            assertEquals("AAPL", fromSerialize.getSecurityId());
            assertEquals(time, fromSerialize.getTime());
            assertEquals(160.3D, fromSerialize.getOpen());
            assertEquals(165.7D, fromSerialize.getHigh());
            assertEquals(157.2D, fromSerialize.getLow());
            assertEquals(163.1D, fromSerialize.getClose());
            assertEquals(161.9D, fromSerialize.getLast());
            assertTrue(fromSerialize.isLevelOne());
        }
    }

    @AfterEach
    void tearDown() {
//        if (serFile != null) {
//            final boolean delete = serFile.delete();
//            assertTrue(delete);
//        }
    }
}
