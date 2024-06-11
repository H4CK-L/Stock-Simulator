import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.time.*;
import org.jfree.data.xy.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Client extends JFrame {
    private Sector sector;
    private List<Sector> sectors;
    private User user;
    private StringBuilder stockContent;
    private LocalDateTime now;
    private DateTimeFormatter formatter;
    private String formattedDateTime;
    private int countNewsMin = 30;
    private int countNewsSec = 0;
    private int stockMin = 0;
    private int stockSec = 10;
    private volatile boolean running = true;
    private UIManager uiManager;
    private Assets assets;
    private Ranking ranking;
    private BotSystem bot;
    private JLabel newsLabel;
    private News newsManager;
    private TimeSeries series;
    private JFreeChart chart;

    public Client() {
        newsManager = new News();
        newsManager.loadNewsFromFile("PositiveNews.txt");
        newsManager.loadNewsFromFile("NegativeNews.txt");
    }

    private void connecting(Client client) {
        sectors = new ArrayList<>();
        sectors.add(new Sector("기술"));
        sectors.add(new Sector("예술"));
        sectors.add(new Sector("게임"));
        readStocksFromFile("stock.txt", sectors); // 주식 파일 불러오기
        uiManager = new UIManager(Client.this, sectors);
        sector = sectors.get(2);
        bot = new BotSystem();
        uiManager.getConnect().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창이 닫힐 때 자원을 해제하도록 설정
        uiManager.getConnect().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Connect 창이 닫힐 때 호출될 메소드
                File file = new File("database.txt");
                if (!file.exists()) {
                    uiManager.viewSetNickname();
                    user = new User("database.txt");
                    assets = new Assets(user, sectors);
                    bot.setUser(user);
                    ranking = new Ranking(user, bot);
                    uiManager.setOrderUser(user);
                    uiManager.setAssets(assets);
                    uiManager.setRanking(ranking);
                    uiManager.setShop(user);
                    initComponents(client);
                } else {
                    user = new User("database.txt");
                    assets = new Assets(user, sectors);
                    bot.setUser(user);
                    ranking = new Ranking(user, bot);
                    uiManager.setOrderUser(user);
                    uiManager.setAssets(assets);
                    uiManager.setRanking(ranking);
                    uiManager.setShop(user);
                    initComponents(client);
                }
            }
        });
    }

    public void readStocksFromFile(String filename, List<Sector> sectors) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    int price = Integer.parseInt(parts[1].trim());
                    String sectorName = parts[2].trim();

                    Stock stock = new Stock(name, price);
                    addStockToSector(sectorName, stock, sectors);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    private void addStockToSector(String sectorName, Stock stock, List<Sector> sectors) {
        for (Sector sector : sectors) {
            if (sector.getName().equalsIgnoreCase(sectorName)) {
                sector.addStock(stock);
                return;
            }
        }
        // If the sector does not exist, create it and add the stock
        sector = new Sector(sectorName);
        sector.addStock(stock);
        sectors.add(sector);
    }

    @SuppressWarnings("unchecked")
    private void initComponents(Client client) {
        now = LocalDateTime.now();
        formatter = DateTimeFormatter.ofPattern("hh : mm : ss a", Locale.US);
        formattedDateTime = now.format(formatter);
        jPanel1 = new JPanel();
        reloadStock = new JButton();
        dataDeleteButton = new JButton();
        jPanel2 = new JPanel();
        buyStock = new JButton();
        sellStock = new JButton();
        selectStock = new JToggleButton();
        stockComboBox = new JComboBox<String>();
        saveButton = new JButton();
        jPanel3 = new JPanel();
        stockPrice = new JLabel();
        shopButton = new JButton();
        partTimeButton = new JButton();
        assetsButton = new JButton();
        rankingButton = new JButton();
        corporateButton = new JButton();
        reloadNews = new JButton();
        jPanel4 = new JPanel();
        jPanel5 = new JPanel();
        jLabel1 = new JLabel();
        jPanel7 = new JPanel();
        jPanel6 = new JPanel();
        jLabel2 = new JLabel();
        jPanel8 = new JPanel();  // 신문 패널 추가

        // 신문 레이블 생성
        newsLabel = new JLabel();
        newsLabel.setVerticalAlignment(SwingConstants.TOP); // 수직 정렬
        newsLabel.setHorizontalAlignment(SwingConstants.LEFT); // 수평 정렬
        newsLabel.setFont(new Font("한컴 고딕", 0, 13)); // 폰트 설정
        newsLabel.setText("<html>신문 내용이 여기에 표시됩니다.</html>");
        jPanel8.add(newsLabel);

        // JFreeChart 초기화
        series = new TimeSeries("Stock Prices");
        XYDataset dataset = createDataset();
        chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));
        jPanel4.setLayout(new BorderLayout());
        jPanel4.add(chartPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("랭킹");

        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setPreferredSize(new Dimension(1000, 1000));

        reloadStock.setBackground(new Color(204, 255, 255));
        reloadStock.setFont(new Font("한컴 고딕", 0, 13)); // NOI18N
        reloadStock.setText("주가 갱신");
        reloadStock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                reloadStockActionPerformed(evt);
            }
        });

        dataDeleteButton.setBackground(new Color(255, 102, 102));
        dataDeleteButton.setFont(new Font("한컴 고딕", 0, 13)); // NOI18N
        dataDeleteButton.setText("데이터 삭제");
        dataDeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dataDeleteButtonActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new Color(216, 228, 230));

        buyStock.setFont(new Font("한컴 고딕", 0, 13)); // NOI18N
        buyStock.setText("주식 매수");
        buyStock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buyStockActionPerformed(evt);
            }
        });

        sellStock.setFont(new Font("한컴 고딕", 0, 13)); // NOI18N
        sellStock.setText("주식 매도");
        sellStock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                sellStockActionPerformed(evt);
            }
        });

        selectStock.setFont(new Font("한컴 고딕", 0, 13)); // NOI18N
        selectStock.setText("주식 분야");
        selectStock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                selectStockActionPerformed(evt);
            }
        });

        saveButton.setBackground(new Color(255, 255, 204));
        saveButton.setFont(new Font("한컴 고딕", 0, 13)); // NOI18N
        saveButton.setText("게임 저장");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(selectStock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buyStock)
                                        .addComponent(sellStock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap(29, Short.MAX_VALUE)
                                .addComponent(buyStock, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(sellStock, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(selectStock, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
        );

        jPanel3.setBackground(new Color(216, 228, 230));

        shopButton.setFont(new Font("한컴 고딕", 0, 13)); // NOI18N
        shopButton.setText("상점");
        shopButton.setPreferredSize(new Dimension(82, 23));
        shopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                shopButtonActionPerformed(evt);
            }
        });

        partTimeButton.setFont(new Font("한컴 고딕", 0, 13)); // NOI18N
        partTimeButton.setText("알바");
        partTimeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                partTimeButtonActionPerformed(evt);
            }
        });

        assetsButton.setFont(new Font("한컴 고딕", 0, 13)); // NOI18N
        assetsButton.setText("총 자산");
        assetsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                assetsButtonActionPerformed(evt);
            }
        });

        rankingButton.setFont(new Font("한컴 고딕", 0, 13)); // NOI18N
        rankingButton.setText("랭킹");
        rankingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                rankingButtonActionPerformed(evt);
            }
        });

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(partTimeButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(assetsButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rankingButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(shopButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(7, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(shopButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(partTimeButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(assetsButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rankingButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(29, Short.MAX_VALUE))
        );

        reloadNews.setBackground(new Color(204, 255, 204));
        reloadNews.setFont(new Font("한컴 고딕", 0, 13)); // NOI18N
        reloadNews.setText("신문 갱신");
        reloadNews.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                reloadNewsActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new Color(153, 153, 153));

        GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(chartPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(chartPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new Color(153, 153, 153));
        jLabel1.setFont(new Font("한컴 고딕", 1, 14)); // NOI18N
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        jLabel1.setText("주가 차트 | 현재 시각 : " + formattedDateTime + " | 주가 변동까지 남은 시간 : " + String.format("%02d분 %02d초", stockMin, stockSec));

        jPanel7.setBackground(new Color(51, 51, 51));

        GroupLayout jPanel7Layout = new GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(stockPrice, GroupLayout.PREFERRED_SIZE, 566, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(stockPrice, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                .addContainerGap())
        );

        changeStockContent();

        stockPrice.setVerticalAlignment(SwingConstants.TOP);
        stockPrice.setFont(new Font("한컴 고딕", 1, 14));
        GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(jLabel1))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(37, 37, 37)
                                                .addComponent(jPanel7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new Color(250, 250, 250));

        jLabel2.setFont(new Font("한컴 고딕", 1, 14)); // NOI18N
        TimerThread timerThread = new TimerThread(sectors, newsManager); // TimerThread 객체 생성
        timerThread.start(); // 타이머 시작
        jLabel2.setText(String.format("Daily News! | 신문 갱신까지 남은 시간 : %02d분 %02d초", countNewsMin, countNewsSec));

        jPanel8.setBackground(new Color(255, 153, 102));

        GroupLayout jPanel8Layout = new GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(newsLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(newsLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        GroupLayout jPanel6Layout = new GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(jPanel8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(9, Short.MAX_VALUE))
        );

        stockComboBox.setModel(new DefaultComboBoxModel<>(new String[]{sectors.get(0).getName(), sectors.get(1).getName(), sectors.get(2).getName()}));
        stockComboBox.setSelectedIndex(-1);
        stockComboBox.setVisible(false);
        stockComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int selectedIndex = stockComboBox.getSelectedIndex();
                switch (selectedIndex) {
                    case 0:
                        sector = sectors.get(selectedIndex);
                        changeStockContent();
                        break;
                    case 1:
                        sector = sectors.get(selectedIndex);
                        changeStockContent();
                        break;
                    case 2:
                        sector = sectors.get(selectedIndex);
                        changeStockContent();
                        break;
                }
                stockComboBoxActionPerformed(evt);
            }
        });

        corporateButton.setBackground(new Color(152, 112, 187));
        corporateButton.setFont(new Font("한컴 고딕", 0, 13)); // NOI18N
        corporateButton.setText("기업 정보");
        corporateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                corporateButtonActionPerformed(evt);
            }
        });
        corporateButton.setPreferredSize(reloadNews.getPreferredSize());

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(reloadStock, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(corporateButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel5, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(dataDeleteButton, GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jPanel2, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(reloadNews, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(stockComboBox, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)))
                                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jPanel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(22, 22, 22))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(reloadStock, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dataDeleteButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(117, 117, 117)
                                                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(stockComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(reloadNews, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(58, 58, 58))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(corporateButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(58, 58, 58))))))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 915, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 773, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        client.pack();
        client.setVisible(true);
        client.setLocationRelativeTo(null);

        updateNews();
    }

    private void reloadStockActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void dataDeleteButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void saveButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void sellStockActionPerformed(ActionEvent evt) {
        uiManager.viewOrder();
    }

    private void buyStockActionPerformed(ActionEvent evt) {
        uiManager.viewOrder();
    }

    private void shopButtonActionPerformed(ActionEvent evt) {
        uiManager.viewShop();
    }

    private void partTimeButtonActionPerformed(ActionEvent evt) {
        uiManager.viewPartTime();
    }

    private void assetsButtonActionPerformed(ActionEvent evt) {
        assets.setUser(user);
        uiManager.viewAssets();
    }

    private void reloadNewsActionPerformed(ActionEvent evt) {
        updateNews();
    }

    private void selectStockActionPerformed(ActionEvent evt) {
        stockComboBox.setVisible(selectStock.isSelected());
    }

    private void rankingButtonActionPerformed(ActionEvent evt) {
        ranking.setRanking(user, bot);
        uiManager.viewRanking();
    }

    private void corporateButtonActionPerformed(ActionEvent evt) {
        uiManager.viewCorporateInfo();
    }

    private void updateTimerLabel() {
        jLabel2.setText(String.format("Daily News! | 신문 갱신까지 남은 시간 : %02d분 %02d초", countNewsMin, countNewsSec));
    }

    private void updateStockLabel() {
        jLabel1.setText("주가 차트 | 현재 시각 : " + formattedDateTime + " | 주가 변동까지 남은 시간 : " + String.format("%02d분 %02d초", stockMin, stockSec));
    }

    private void stopTimerThread() {
        running = false;
    }

    private void stockComboBoxActionPerformed(ActionEvent evt) {
        stockComboBox.setVisible(false);
        stockComboBox.setSelectedIndex(-1);
        selectStock.setSelected(false);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Client client = new Client();
                client.connecting(client);
            }
        });
    }

    public void changeStockContent() {
        stockContent = new StringBuilder("<html>");
        stockContent.append("<span style='color:black;'>");
        stockContent.append("[");
        stockContent.append(sector.getName());
        stockContent.append("]");
        stockContent.append("<br>");
        stockContent.append("<br>");

        NumberFormat formatter = NumberFormat.getInstance();
        formatter.setGroupingUsed(true);

        for (Stock stock : sector.getStocks()) {
            if (stock.getDelisting()) {
                stockContent.append("<span style='color:orange;'>");
            } else {
                if (stock.getStat()) {
                    stockContent.append("<span style='color:green;'>");
                } else {
                    if (!stock.getNotChange()) {
                        stockContent.append("<span style='color:red;'>");
                    } else {
                        stockContent.append("<span style='color:gray;'>");
                    }
                }
            }
            stockContent.append(stock.getName())
                    .append(" : (")
                    .append(formatter.format(stock.getPrice()))
                    .append(" 원) ");

            if (stock.getDelisting()) {
                stockContent.append(">> [상장 폐지로 인한 가격 조정]");
                stock.setDelisting(false);
            } else {
                if (stock.getStat()) {
                    stockContent.append(stock.getChangedPrice());
                    stockContent.append("▲");
                } else {
                    if (!stock.getNotChange()) {
                        stockContent.append(stock.getChangedPrice());
                        stockContent.append("▼");
                    } else {
                        stockContent.append(stock.getChangedPrice());
                        stockContent.append("-");
                    }
                }
            }
            stockContent.append("</span><br/><br/>");
        }
        stockContent.append("</html>");

        stockPrice.setText(stockContent.toString());
    }

    private void updateNews() {
        List<String> currentNews = newsManager.getRandomNews(3);
        StringBuilder newsContent = new StringBuilder("<html>");
        for (String news : currentNews) {
            if (newsManager.getNegativeNews().contains(news)) {  // 부정적인 뉴스
                newsContent.append("<span style='color:red;'>").append(news).append("</span><br/><br/>");
            } else if (newsManager.getPositiveNews().contains(news)) {  // 긍정적인 뉴스
                newsContent.append("<span style='color:green;'>").append(news).append("</span><br/><br/>");
            }
        }
        newsContent.append("</html>");
        newsLabel.setText(newsContent.toString());

        // 주가 변동 설정
        for (Sector sector : sectors) {
            for (Stock stock : sector.getStocks()) {
                stock.removeNewsEffect();
            }
        }
        for (String news : currentNews) {
            if (newsManager.getPositiveNews().contains(news)) {
                applyNewsEffect(news, true);
            } else if (newsManager.getNegativeNews().contains(news)) {
                applyNewsEffect(news, false);
            }
        }
        for (Sector sector : sectors) {
            for (Stock stock : sector.getStocks()) {
                System.out.println("Stock: " + stock.getName() + ", Current Price: " + stock.getPrice() + ", Multiplier: " + stock.getNewsMultiplier());
            }
        }
    }

    private void applyNewsEffect(String news, boolean isPositive) {
        for (Sector sector : sectors) {
            for (Stock stock : sector.getStocks()) {
                if (news.contains(stock.getName())) {
                    if (isPositive) {
                        stock.applyPositiveNewsEffect();
                    } else {
                        stock.applyNegativeNewsEffect();
                    }
                }
            }
        }
    }

    private XYDataset createDataset() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {
        return ChartFactory.createTimeSeriesChart(
                "Stock Prices",
                "Time",
                "Price",
                dataset,
                true,
                true,
                false
        );
    }

    public class TimerThread extends Thread {
        private List<Sector> sectors;
        private News newsManager;

        public TimerThread(List<Sector> sectors, News newsManager) {
            this.sectors = sectors;
            this.newsManager = newsManager;
        }

        @Override
        public void run() {
            while (running) {
                now = LocalDateTime.now();
                formatter = DateTimeFormatter.ofPattern("hh : mm : ss a", Locale.US);
                formattedDateTime = now.format(formatter);
                try {
                    Thread.sleep(1000); // 1초 대기

                    if (stockSec == 0) {
                        if (stockMin == 0) {
                            stockMin = 3;
                            stockSec = 0;
                            for (Sector sc : sectors) {
                                sc.updateStockPrices(sc);
                            }
                            changeStockContent();
                            bot.setBotMoney();
                            // 차트 데이터 업데이트
                            series.addOrUpdate(new Second(Date.from(now.atZone(ZoneId.systemDefault()).toInstant())), sector.getStocks().get(0).getPrice());
                        } else {
                            stockMin--;
                            stockSec = 59;
                        }
                    } else {
                        stockSec--;
                    }
                    if (countNewsSec == 0) {
                        if (countNewsMin == 0) {
                            countNewsMin = 30;
                            countNewsSec = 0;
                            updateNews();
                        } else {
                            countNewsMin--;
                            countNewsSec = 59;
                        }
                    } else {
                        countNewsSec--;
                    }
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            updateStockLabel();
                            updateTimerLabel();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private JButton reloadStock;
    private JButton rankingButton;
    private JButton reloadNews;
    private JButton dataDeleteButton;
    private JButton buyStock;
    private JButton sellStock;
    private JToggleButton selectStock;
    private JComboBox<String> stockComboBox;
    private JButton saveButton;
    private JButton shopButton;
    private JButton partTimeButton;
    private JButton assetsButton;
    private JButton corporateButton;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel stockPrice;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JPanel jPanel8;
}