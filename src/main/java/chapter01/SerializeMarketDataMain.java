package chapter01;

import java.io.*;
import java.util.List;

public class SerializeMarketDataMain {
    public static final String SERIALIZE_FILENAME = "testSerialize.ser";

    public static void main(final String[] args) {
//        testRunWithOneObject();
        testRunWithList();
    }

    private static void testRunWithList() {
        final List<MarketData> marketDataList = MarketDataFactory.getMarketDataObjects(3);
        serializeList(marketDataList);
        deserializeList();
    }

    private static void testRunWithOneObject() {
        final var marketData = MarketDataFactory.getOneMarketData();
        serializeOneObject(marketData);
        deserializeOneObject();
    }

    private static void serializeList(final List<MarketData> marketDataList) {
        try (final var oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(SERIALIZE_FILENAME)))) {
            for (final MarketData marketData :
                    marketDataList) {
                System.out.println("Before Serialization: ");
                System.out.println(marketData);
                oos.writeObject(marketData);
            }
            System.out.println("-------------------------");
            System.out.println("-------------------------");
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private static void deserializeList() {
        try (final var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(SERIALIZE_FILENAME)))) {
            while (true) {
                final var object = ois.readObject();
                if (object instanceof MarketData) {
                    final var marketData = (MarketData) object;
                    System.out.println("After Serialization: ");
                    System.out.println(marketData);
                }
            }
        } catch (final IOException | ClassNotFoundException e) {
            //e.printStackTrace();
        }
    }

    private static void serializeOneObject(final MarketData marketData) {
        try (final var oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(SERIALIZE_FILENAME)))) {
            System.out.println("Before Serialization: ");
            System.out.println(marketData);
            oos.writeObject(marketData);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private static void deserializeOneObject() {
        try (final var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(SERIALIZE_FILENAME)))) {
            final MarketData fromSerialize = (MarketData) ois.readObject();
            System.out.println("After Serialization: ");
            System.out.println(fromSerialize);
        } catch (final IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
