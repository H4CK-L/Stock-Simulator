import java.util.*;

public class Sector {
    private String name;
    private List<Stock> stocks;

    public Sector(String name) {
        this.name = name;
        this.stocks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void addStock(Stock stock) {
        this.stocks.add(stock);
    }

    @Override
    public String toString() {
        return "Sector{" +
                "name='" + name + '\'' +
                ", stocks=" + stocks +
                '}';
    }
}