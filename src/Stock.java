import java.time.LocalDateTime;
import java.util.*;

public class Stock {
    private String name;
    private int price;
    private int prevPrice;
    private int changedPrice;
    private boolean stat = false;
    private boolean notChange = true;
    private boolean delisting = false;
    private LinkedList<PriceRecord> priceHistory = new LinkedList<>();

    public Stock(String name, int price) {
        this.name = name;
        this.price = price;
        addPriceToHistory(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public int getPrevPrice() {
        return prevPrice;
    }

    public int getChangedPrice() {
        return changedPrice;
    }

    public void setChangedPrice() {
        this.changedPrice = price - prevPrice;
    }

    public void setPrevPrice(int prevPrice) {
        this.prevPrice = prevPrice;
    }

    public void setPrice(int price) {
        this.price = price;
        addPriceToHistory(price);
    }

    private void addPriceToHistory(int price) {
        if (priceHistory.size() == 10) {
            priceHistory.removeFirst();
        }
        priceHistory.add(new PriceRecord(price, LocalDateTime.now()));
    }

    public List<PriceRecord> getPriceHistory() {
        return Collections.unmodifiableList(priceHistory);
    }

    public void setStat(boolean stat) {
        this.stat = stat;
    }

    public void setNotChange(boolean notChange) {
        this.notChange = notChange;
    }

    public void setDelisting(boolean delisting) {
        this.delisting = delisting;
    }

    public boolean getNotChange() {
        return notChange;
    }

    public boolean getStat() {
        return stat;
    }

    public boolean getDelisting() {
        return delisting;
    }

    public void applyPositiveNewsEffect() {
        setNewsMultiplier(1.2);
    }

    public void applyNegativeNewsEffect() {
        setNewsMultiplier(0.8);
    }

    public void removeNewsEffect() {
        setNewsMultiplier(1.0);
    }

    private double newsMultiplier = 1.0;

    public double getNewsMultiplier() {
        return newsMultiplier;
    }

    public void setNewsMultiplier(double newsMultiplier) {
        this.newsMultiplier = newsMultiplier;
    }

    public static class PriceRecord {
        private final int price;
        private final LocalDateTime time;

        public PriceRecord(int price, LocalDateTime time) {
            this.price = price;
            this.time = time;
        }

        public int getPrice() {
            return price;
        }

        public LocalDateTime getTime() {
            return time;
        }
    }
}