package chapter01;

public class MarketDataProvider {
    private String name;

    public MarketDataProvider(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MarketDataProvider{" +
                "name='" + name + '\'' +
                '}';
    }
}
