import java.io.*;
import java.util.*;

public class User {
    private String name;
    private int money;
    private int partTimeCount;
    private Map<Stock, Integer> stockPortfolio;
    private Map<Stock, Double> stockPrices;
    private Map<String, Integer> itemInventory;

    public User(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            this.name = reader.readLine();
            this.money = Integer.parseInt(reader.readLine());
            this.partTimeCount = Integer.parseInt(reader.readLine());
            this.stockPortfolio = new HashMap<>();
            this.stockPrices = new HashMap<>();
            this.itemInventory = new HashMap<>();
        } catch (IOException e) {
            System.out.println("Cannot Read data");
        }
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setPartTimeCount(int partTimeCount) {
        this.partTimeCount = partTimeCount;
    }

    public String getName() {
        return this.name;
    }

    public int getMoney() {
        return this.money;
    }

    public int getPartTimeCount() {
        return this.partTimeCount;
    }

    public void addStock(Stock stock, int quantity, int price) {
        int currentQuantity = stockPortfolio.getOrDefault(stock, 0);
        double currentPrice = stockPrices.getOrDefault(stock, 0.0);
        double newPrice = ((currentQuantity * currentPrice) + (quantity * price)) / (currentQuantity + quantity);

        stockPortfolio.put(stock, currentQuantity + quantity);
        stockPrices.put(stock, newPrice);
    }

    public void removeStock(Stock stock, int quantity) {
        int currentQuantity = stockPortfolio.getOrDefault(stock, 0);
        if (currentQuantity >= quantity) {
            stockPortfolio.put(stock, currentQuantity - quantity);
            if (stockPortfolio.get(stock) == 0) {
                stockPortfolio.remove(stock);
                stockPrices.remove(stock);
            }
        }
    }

    public int getStockQuantity(Stock stock) {
        return stockPortfolio.getOrDefault(stock, 0);
    }

    public Map<Stock, Integer> getStockPortfolio() {
        return stockPortfolio;
    }

    public double getStockPrice(Stock stock) {
        return stockPrices.getOrDefault(stock, 0.0);
    }

    public void addItem(String itemName) {
        itemInventory.put(itemName, itemInventory.getOrDefault(itemName, 0) + 1);
    }

    public Map<String, Integer> getItemInventory() {
        return itemInventory;
    }
}
