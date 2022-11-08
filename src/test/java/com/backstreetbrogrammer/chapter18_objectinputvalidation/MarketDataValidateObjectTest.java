package com.backstreetbrogrammer.chapter18_objectinputvalidation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class MarketDataValidateObjectTest {
    private File serFile;
    private File serFile1;
    private final MarketDataValidateObject marketData = new MarketDataValidateObject();

    @BeforeEach
    void setUp() throws IOException {
        final var serPath = Path.of("src", "test", "resources", "MarketDataValidateObjectTest.ser");
        serFile = serPath.toFile();
        if (!serFile.exists()) {
            final var success = serFile.createNewFile();
            assertTrue(success);
        }

        final var serPath1 = Path.of("src", "test", "resources", "MarketDataValidateObjectTest1.ser");
        serFile1 = serPath1.toFile();
        if (!serFile1.exists()) {
            final var success = serFile1.createNewFile();
            assertTrue(success);
        }

        marketData.setSecurityId("AAPL");
        marketData.setOpen(160.3D);
        marketData.setHigh(165.7D);
        marketData.setLow(157.2D);
        marketData.setClose(163.1D);
        marketData.setLast(161.9D);
        marketData.setLevelOne(true);
    }

    @Test
    @DisplayName("Test basic serialization for Java POJO with invalid object state")
    void testSerializeWithInvalidObjectState() throws IOException {
        marketData.setTime(-10000L);

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
    @DisplayName("Test basic deserialization for Java POJO with invalid object state")
    void testDeserializeWithInvalidObjectState() throws IOException {
        try (final var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(serFile)))) {

            final Throwable exception = assertThrows(InvalidObjectException.class, ois::readObject);
            assertEquals(exception.getMessage(), "time -10000 can never be negative");
        }
    }

    @Test
    @DisplayName("Test basic serialization for Java POJO with valid object state")
    void testSerializeWithValidObjectState() throws IOException {
        marketData.setTime(10000L);

        // serialize and write
        try (final var oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(serFile1)))) {
            System.out.println("Before Serialization: ");
            System.out.println(marketData);
            oos.writeObject(marketData);
        }
    }

    @Test
    @DisplayName("Test basic deserialization for Java POJO with valid object state")
    void testDeserializeWithValidObjectState() throws IOException, ClassNotFoundException {
        try (final var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(serFile1)))) {
            final var fromSerialize = (MarketDataValidateObject) ois.readObject();
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
        }
    }

}
