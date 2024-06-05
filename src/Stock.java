import java.util.*;
import javax.swing.*;

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

    public boolean getNotChange(){
        return notChange;
    }

    public boolean getStat(){
        return stat;
    }

    public boolean getDelisting(){
        return delisting;
    }

}