import java.io.*;
import java.util.*;

public class User {
    private String name; // 닉네임 불러오기 성공
    private int Money;
    private int partTimeCount;
    public User(String name){
        try(BufferedReader reader = new BufferedReader(new FileReader(name))){
            this.name = reader.readLine();
        } catch (IOException e){
            System.out.println("Cannot Read data");
        }
    }

    public String getName(){
        return this.name;
    }
}
