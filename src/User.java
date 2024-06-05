import java.io.*;
import java.util.*;

public class User {
    private String name; // 닉네임 불러오기 성공
    private int money;
    private int partTimeCount;
    public User(String file){
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            this.name = reader.readLine();
            this.money = Integer.parseInt(reader.readLine());
            this.partTimeCount = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            System.out.println("Cannot Read data");
        }
    }

    public void setMoney(int money){
        this.money = money;
    }

    public void setPartTimeCount(int partTimeCount){
        this.partTimeCount = partTimeCount;
    }

    public String getName(){
        return this.name;
    }


    public int getMoney() {
        return this.money;
    }

    public int getPartTimeCount() {
        return this.partTimeCount;
    }
}
