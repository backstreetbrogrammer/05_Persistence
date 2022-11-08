package com.backstreetbrogrammer.chapter15_securedFields;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class MarketDataSecuredTest {
    private File serFile;
    private final String password = "back$treetBrogr@mmer";

    @BeforeEach
    void setUp() throws IOException {
        final var serPath = Path.of("src", "test", "resources", "MarketDataSecuredTest.ser");
        serFile = serPath.toFile();
        if (!serFile.exists()) {
            final var success = serFile.createNewFile();
            assertTrue(success);
        }
    }

    @Test
    @DisplayName("Test basic serialization for Java POJO with secured fields")
    void testSerialize() throws IOException {
        final var marketData = new MarketDataSecured();
        marketData.setSecurityId("AAPL");
        marketData.setTime(10000L);
        marketData.setOpen(160.3D);
        marketData.setHigh(165.7D);
        marketData.setLow(157.2D);
        marketData.setClose(163.1D);
        marketData.setLast(161.9D);
        marketData.setLevelOne(true);

        marketData.setLoginPassword(password);

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
    @DisplayName("Test basic deserialization for Java POJO with secured fields")
    void testDeserialize() throws IOException, ClassNotFoundException {
        try (final var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(serFile)))) {
            final var fromSerialize = (MarketDataSecured) ois.readObject();
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
            assertFalse(fromSerialize.isLevelOne());

            assertEquals(password, fromSerialize.getLoginPassword());
        }
    }
}
