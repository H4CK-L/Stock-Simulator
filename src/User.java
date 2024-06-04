import java.io.*;
import java.util.*;

public class User {
    private String name;
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
