import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BotSystem {
    private User user;
    private ArrayList<String> botName = new ArrayList<>();
    private ArrayList<Integer> botMoney = new ArrayList<>();

    public BotSystem(){
        loadBotData("BotData.txt");
    }

    public void setUser(User user){
        this.user = user;
    }
    public void loadBotData(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    int money = Integer.parseInt(parts[1].trim());
                    botName.add(name);
                    botMoney.add(money);
                } else {
                    System.err.println("잘못된 형식의 데이터입니다: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getBotNames() {
        return botName;
    }

    public ArrayList<Integer> getBotMoney() {
        return botMoney;
    }

    public void setBotMoney() {
        Random random = new Random();
        int value;
        for (int i = 0; i < botName.size(); i++) {
            boolean increase = random.nextBoolean();
            value = random.nextInt(botMoney.get(i) + 1);
            if(increase){
                botMoney.set(i, botMoney.get(i) + value);
            }
            else{
                botMoney.set(i, botMoney.get(i) - value);
            }

            if(botMoney.get(i) <= 0){
                botMoney.set(i, user.getMoney() - value);
            }
        }
    }
}
