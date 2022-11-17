package com.backstreetbrogrammer.chapter21_serializationfilters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class MarketDataWithFiltersTest {

    private File serFile;

    @BeforeEach
    void setUp() throws IOException {
        final var serPath = Path.of("src", "test", "resources",
                "MarketDataWithFiltersTest.ser");
        serFile = serPath.toFile();
        if (!serFile.exists()) {
            final var success = serFile.createNewFile();
            assertTrue(success);
        }
    }

    @Test
    @DisplayName("Test basic serialization for Java POJO with filters")
    void testSerialize() throws IOException {
        final var marketData = new MarketDataWithFilters();
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
    @DisplayName("Test basic deserialization for Java POJO based on serial class - ALLOWED")
    void testDeserializeBasedOnSerialClassAllowed() throws IOException, ClassNotFoundException {
        try (final var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(serFile)))) {
            ois.setObjectInputFilter(c ->
            {
                System.out.println("c.serialClass()=" + c.serialClass());
                System.out.println("c.arrayLength()=" + c.arrayLength());
                System.out.println("c.depth()=" + c.depth());
                System.out.println("c.references()=" + c.references());
                System.out.println("c.streamBytes()=" + c.streamBytes());

                return (c.serialClass() == MarketDataWithFilters.class) ?
                        ObjectInputFilter.Status.ALLOWED : ObjectInputFilter.Status.UNDECIDED;
            });

            final var fromSerialize = (MarketDataWithFilters) ois.readObject();
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


    @Test
    @DisplayName("Test basic deserialization for Java POJO based on serial class - REJECTED")
    void testDeserializeBasedOnSerialClassRejected() throws IOException, ClassNotFoundException {
        try (final var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(serFile)))) {
            ois.setObjectInputFilter(c ->
                    (c.serialClass() == MarketDataWithFilters.class) ?
                            ObjectInputFilter.Status.REJECTED : ObjectInputFilter.Status.UNDECIDED);

            final Throwable exception = assertThrows(InvalidClassException.class, ois::readObject);
            assertEquals(exception.getMessage(), "filter status: REJECTED");
        }
    }

    @Test
    @DisplayName("Test basic deserialization for Java POJO based on stream bytes size - REJECTED")
    void testDeserializeBasedOnStreamBytesRejected() throws IOException {
        try (final var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(serFile)))) {
            ois.setObjectInputFilter(c ->
                    (c.streamBytes() > 100L) ?
                            ObjectInputFilter.Status.REJECTED : ObjectInputFilter.Status.UNDECIDED);

            final Throwable exception = assertThrows(InvalidClassException.class, ois::readObject);
            assertEquals(exception.getMessage(), "filter status: REJECTED");
        }
    }


    @Test
    @DisplayName("Test basic deserialization for Java POJO based on pattern - REJECTED")
    void testDeserializeBasedOnPatternRejected() throws IOException {
        try (final var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(serFile)))) {
            final var filter = ObjectInputFilter.Config
                    .createFilter(
                            "!com.backstreetbrogrammer.chapter21_serializationfilters.MarketDataWithFilters;");
            ois.setObjectInputFilter(filter);

            final Throwable exception = assertThrows(InvalidClassException.class, ois::readObject);
            assertEquals(exception.getMessage(), "filter status: REJECTED");
        }
    }
}
