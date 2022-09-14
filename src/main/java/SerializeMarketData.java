import java.io.*;

public class SerializeMarketData {
    public static final String SERIALIZE_FILENAME = "testSerialize.ser";

    public static void main(final String[] args) {
        final MarketData marketData = new MarketData();
        marketData.setSecurityId("AAPL");
        marketData.setTime(System.currentTimeMillis());
        marketData.setOpen(160.3D);
        marketData.setHigh(165.7D);
        marketData.setLow(157.2D);
        marketData.setClose(163.1D);
        marketData.setLast(161.9D);

        serializeObject(marketData);
        deserializeObject();
    }

    private static void deserializeObject() {
        try (final var fis = new FileInputStream(SERIALIZE_FILENAME);
             final var ois = new ObjectInputStream(fis)) {
            final MarketData fromSerialize = (MarketData) ois.readObject();
            System.out.println("After Serialization: ");
            System.out.println(fromSerialize);
        } catch (final IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void serializeObject(final MarketData marketData) {
        try (final var fos = new FileOutputStream(SERIALIZE_FILENAME);
             final var oos = new ObjectOutputStream(fos)) {
            System.out.println("Before Serialization: ");
            System.out.println(marketData);
            oos.writeObject(marketData);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

}
