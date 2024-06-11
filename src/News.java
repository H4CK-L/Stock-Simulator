import java.io.*;
import java.util.*;

public class News {
    private List<String> positiveNews;
    private List<String> negativeNews;
    private List<String> cooperationNews;

    public News() {
        positiveNews = new ArrayList<>();
        negativeNews = new ArrayList<>();
        cooperationNews = new ArrayList<>();
    }

    public void loadNewsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (filename.contains("PositiveNews")) {  // 파일 이름에 따라 분류
                    positiveNews.add(line);
                } else if (filename.contains("NegativeNews")) {
                    negativeNews.add(line);
                } else {
                    cooperationNews.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getPositiveNews() {
        return positiveNews;
    }

    public List<String> getNegativeNews() {
        return negativeNews;
    }

    public List<String> getCooperationNews() {
        return cooperationNews;
    }

    public List<String> getRandomNews(int count) {
        List<String> allNews = new ArrayList<>();
        allNews.addAll(positiveNews);
        allNews.addAll(negativeNews);
        allNews.addAll(cooperationNews);

        Collections.shuffle(allNews);
        return allNews.subList(0, Math.min(count, allNews.size()));
    }
}
