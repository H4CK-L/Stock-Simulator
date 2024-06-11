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

        map.put(user.getName(), user.getMoney() /* total로 바꿔야 함*/);
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

        int rank = 1;

        now = LocalDateTime.now();
        formattedDateTime = now.format(formatter);
        jLabel1.setText("<html>" + formattedDateTime + " 기준 순위" + "</html>");

        StringBuilder names = new StringBuilder();
        StringBuilder values = new StringBuilder();
        StringBuilder top = new StringBuilder("명예의 전당");

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            names.append(rank).append(". ").append(entry.getKey()).append("<br>");
            values.append("총 자산 : ").append(entry.getValue()).append("<br>");
            rank++;
        }

        jLabel4.setText("<html><div style='text-align: center;'>" + top.toString() + "</div></html>");
        jLabel2.setText("<html>" + names.toString() + "</html>");
        jLabel3.setText("<html>" + values.toString() + "</html>");
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 400));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        now = LocalDateTime.now();
        formattedDateTime = now.format(formatter);
        setRanking(user, bot);
        jLabel1.setText(formattedDateTime + " 기준 순위");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel2.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));

        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));

        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel4.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setSize(600, 400);  // Set the initial size of the frame
        setLocationRelativeTo(null);  // Center the frame on the screen
    }

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
}
