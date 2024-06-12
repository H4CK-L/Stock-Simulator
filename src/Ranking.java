import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Ranking extends javax.swing.JFrame {
    private User user;
    private BotSystem bot;
    private HashMap<String, Integer> map = new HashMap<>();
    private ArrayList<String> botName = new ArrayList<>();
    private ArrayList<Integer> botMoney = new ArrayList<>();
    private List<Map.Entry<String, Integer>> sortList;
    private LinkedHashMap<String, Integer> sortedMap;
    private LocalDateTime now;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh : mm : ss a", Locale.US);
    private String formattedDateTime;
    private int rank = 1;
    private javax.swing.JLabel curTime;
    private javax.swing.JLabel nameText;
    private javax.swing.JLabel moneyText;
    private javax.swing.JLabel titleText;
    private javax.swing.JPanel timePanel;
    private javax.swing.JPanel rankPanel;

    public Ranking(User user, BotSystem bot) {
        this.user = user;
        this.bot = bot;
        initComponents();
    }

    public void setRanking(User user, BotSystem bot) {
        this.user = user;
        this.bot = bot;
        this.botName = bot.getBotNames();
        this.botMoney = bot.getBotMoney();

        int userTotalAssets = calculateTotalAssets(user);
        map.put(user.getName(), userTotalAssets);

        for (int i = 0; i < botName.size(); i++) {
            map.put(botName.get(i), botMoney.get(i));
        }

        sortList = new ArrayList<>(map.entrySet());

        Collections.sort(sortList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : sortList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        now = LocalDateTime.now();
        formattedDateTime = now.format(formatter);
        curTime.setText("<html>" + formattedDateTime + " 기준 순위" + "</html>");

        StringBuilder names = new StringBuilder();
        StringBuilder values = new StringBuilder();
        StringBuilder top = new StringBuilder("명예의 전당");

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            names.append(rank).append(". ").append(entry.getKey()).append("<br>");
            values.append("총 자산 : ").append(entry.getValue()).append("<br>");
            rank++;
        }

        titleText.setText("<html><div style='text-align: center;'>" + top.toString() + "</div></html>");
        nameText.setText("<html>" + names.toString() + "</html>");
        moneyText.setText("<html>" + values.toString() + "</html>");
    }

    private int calculateTotalAssets(User user) {
        int totalAssets = user.getMoney();
        for (Map.Entry<Stock, Integer> entry : user.getStockPortfolio().entrySet()) {
            Stock stock = entry.getKey();
            int quantity = entry.getValue();
            totalAssets += quantity * user.getStockPrice(stock);
        }
        return totalAssets;
    }

    private void initComponents() {
        timePanel = new javax.swing.JPanel();
        curTime = new javax.swing.JLabel();
        rankPanel = new javax.swing.JPanel();
        nameText = new javax.swing.JLabel();
        moneyText = new javax.swing.JLabel();
        titleText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CCSS : Capital Conquest : Stock Saga : Ranking");

        setPreferredSize(new java.awt.Dimension(600, 400));

        timePanel.setBackground(new java.awt.Color(255, 255, 255));
        timePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        now = LocalDateTime.now();
        formattedDateTime = now.format(formatter);
        setRanking(user, bot);
        curTime.setText(formattedDateTime + " 기준 순위");
        curTime.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        curTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        curTime.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));

        rankPanel.setBackground(new java.awt.Color(204, 255, 204));
        rankPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        nameText.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        nameText.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));

        moneyText.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        moneyText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        moneyText.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));

        titleText.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        titleText.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        titleText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(rankPanel);
        rankPanel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(moneyText, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(titleText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleText, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(nameText, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                        .addComponent(moneyText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(timePanel);
        timePanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(rankPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(curTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(curTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rankPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(timePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(timePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setSize(600, 400);
        setLocationRelativeTo(null);
    }
}
