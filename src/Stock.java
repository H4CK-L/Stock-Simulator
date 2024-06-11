import java.util.*;

public class Stock {
    private String name;
    private int price;
    private int prevPrice;
    private int changedPrice;
    private boolean stat = false;
    private boolean notChange = true;
    private boolean delisting = false;

    public Stock(String name, int price) {
        this.name = name;
        this.price = price;
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


}
