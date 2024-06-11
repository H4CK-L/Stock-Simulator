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
        addPriceToHistory(price); // 가격 변경 시 기록 추가
    }

    private void addPriceToHistory(int price) {
        if (priceHistory.size() == 10) {
            priceHistory.removeFirst(); // 기록이 10개인 경우 첫 번째 기록 삭제
        }
        priceHistory.add(new PriceRecord(price, LocalDateTime.now())); // 새로운 가격 기록 추가
    }

    public List<PriceRecord> getPriceHistory() {
        return Collections.unmodifiableList(priceHistory); // 가격 기록 반환
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
        // 주식에 긍정적인 뉴스 효과를 적용하는 로직 구현
        setNewsMultiplier(1.2);  // 예시: 주가 변동률을 1.2배로 증가
    }

    public void applyNegativeNewsEffect() {
        // 주식에 부정적인 뉴스 효과를 적용하는 로직 구현
        setNewsMultiplier(0.8);  // 예시: 주가 변동률을 0.8배로 감소
    }

    public void removeNewsEffect() {
        // 주식에 적용된 뉴스 효과를 제거하는 로직 구현
        setNewsMultiplier(1.0);  // 예시: 주가 변동률을 기본 값으로 재설정
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