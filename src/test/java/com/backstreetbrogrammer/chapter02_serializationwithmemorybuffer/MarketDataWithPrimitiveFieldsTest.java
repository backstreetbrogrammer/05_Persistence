package com.backstreetbrogrammer.chapter02_serializationwithmemorybuffer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class MarketDataWithPrimitiveFieldsTest {

    @Test
    @DisplayName("Test basic serialization and deserialization for Java POJO using memory buffer")
    void testSerializeAndDeserializeUsingMemoryBuffer() throws IOException, ClassNotFoundException {
        final var marketData = new MarketDataWithPrimitiveFields();
        marketData.setSecurityId("AAPL");
        marketData.setTime(10000L);
        marketData.setOpen(160.3D);
        marketData.setHigh(165.7D);
        marketData.setLow(157.2D);
        marketData.setClose(163.1D);
        marketData.setLast(161.9D);
        marketData.setLevelOne(true);

        // serialize and write
        final var bout = new ByteArrayOutputStream();
        try (final var oos = new ObjectOutputStream(
                new BufferedOutputStream(bout))) {
            System.out.println("Before Serialization: ");
            System.out.println(marketData);
            oos.writeObject(marketData);
        }

        try (final var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new ByteArrayInputStream(bout.toByteArray())))) {
            final var fromSerialize = (MarketDataWithPrimitiveFields) ois.readObject();
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
