package com.backstreetbrogrammer.chapter01_introduction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MarketDataWithNoFieldsTest {
    private File serFile;

    @BeforeEach
    void setUp() throws IOException {
        final Path serPath = Path.of("src", "test", "resources", "MarketDataWithNoFieldsTest.ser");
        serFile = serPath.toFile();
        if (!serFile.exists()) {
            final boolean success = serFile.createNewFile();
            assertTrue(success);
        }
    }

    @Test
    @DisplayName("Test basic serialization for Java POJO")
    void testSerialize() throws IOException {
        final var marketData = new MarketDataWithNoFields();
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
    @DisplayName("Test basic deserialization for Java POJO")
    void testDeserialize() throws IOException, ClassNotFoundException {
        try (final var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(serFile)))) {
            final MarketDataWithNoFields fromSerialize = (MarketDataWithNoFields) ois.readObject();
            System.out.println("After Serialization: ");
            System.out.println(fromSerialize);

            // assertions
            assertNotNull(fromSerialize);
        }
    }

}
