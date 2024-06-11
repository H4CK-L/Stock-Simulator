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

    public Sector updateStockPrices(Sector sector) {
        Random random = new Random();
        int change = 0;
        for (Stock stock : sector.getStocks()) {
            if(stock.getPrice() <= 10){
                change = random.nextInt(51);
            } else {
                change = random.nextInt((stock.getPrice() / 10) + 1);
            }
            boolean increase = random.nextBoolean();

            change = (int)(change * stock.getNewsMultiplier());

            if(change == 0){
                stock.setStat(false);
                stock.setNotChange(true);
            } else {
                stock.setPrevPrice(stock.getPrice());
                if (increase) {
                    stock.setPrice(stock.getPrice() + change);
                    stock.setStat(true);
                } else {
                    stock.setPrice(stock.getPrice() - change);
                    stock.setStat(false);
                }
                stock.setNotChange(false);
            }

            if(stock.getPrice() <= 0){
                resetPrice(stock);
                continue;
            }

            stock.setChangedPrice();
        }

        return sector;
    }

    public Stock resetPrice(Stock stock) {
        stock.setPrice(1000);
        stock.setStat(false);
        stock.setNotChange(true);
        stock.setDelisting(true);
        return stock;
    }
}
