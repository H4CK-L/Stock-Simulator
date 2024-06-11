import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Map;
import java.util.List;
import java.util.Random;

public class Assets extends javax.swing.JFrame {
    private User user;
    private StringBuilder assetsContent;
    private List<Sector> sectors;
    private JDialog itemDialog;
    private javax.swing.JLabel assetsInfo;
    private javax.swing.JPanel assetsPanel;
    private javax.swing.JButton showItemsButton;

    public Assets(User user, List<Sector> sectors) {
        this.user = user;
        this.sectors = sectors;
        initComponents();
    }

    public void setUser(User user) {
        this.user = user;
        updateUserInfo();
    }

    public User getUser() {
        return user;
    }

    private void initComponents() {
        setTitle("총 자산");

        assetsPanel = new javax.swing.JPanel();
        assetsPanel.setBackground(new java.awt.Color(51, 51, 51));
        assetsInfo = new javax.swing.JLabel();
        showItemsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 300));

        setAssets();

        assetsInfo.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        showItemsButton.setText("보유 아이템 보기");
        showItemsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showItemsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(assetsPanel);
        assetsPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(assetsInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(showItemsButton)
                                .addGap(350, 350, 350))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(assetsInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(showItemsButton)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(assetsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(assetsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    public void setAssets() {
        assetsContent = new StringBuilder("<html>");
        assetsContent.append("<span style='color:black; font-size:13px;'>")
                .append("[")
                .append("</span>")
                .append("<span style='color:white; font-size:13px;'>")
                .append(user.getName())
                .append("</span>")
                .append("<span style='color:black; font-size:13px;'>")
                .append("]님의 총 자산<br>")
                .append("</span>");
        assetsContent.append("<span style='color:black; font-size:10px;'>보유한 돈 : </span>")
                .append("<span style='color:lightblue; font-size:10px;'>")
                .append(user.getMoney())
                .append("원</span> | ");
        assetsContent.append("<span style='color:black; font-size:10px;'>알바 가능 횟수 : </span>")
                .append("<span style='color:yellow; font-size:10px;'>")
                .append(user.getPartTimeCount())
                .append("회</span><br><br>");

        // 유저가 보유한 주식 정보를 추가
        assetsContent.append("<span style='color:lightgray; font-size:12px;'>보유 주식:</span><br>");
        NumberFormat formatter = NumberFormat.getInstance();
        formatter.setGroupingUsed(true);

        for (Map.Entry<Stock, Integer> entry : user.getStockPortfolio().entrySet()) {
            Stock stock = entry.getKey();
            int quantity = entry.getValue();
            double avgPrice = user.getStockPrice(stock); // 평균 구매 가격
            double currentPrice = stock.getPrice(); // 현재 가격
            double totalPurchasePrice = avgPrice * quantity; // 총 구매 가격
            double totalCurrentPrice = currentPrice * quantity; // 현재 총 가격

            double priceChange = currentPrice - avgPrice;
            String priceChangeStr = String.format("%,.2f", Math.abs(priceChange));
            String priceChangeSymbol;
            String priceChangeColor;

            if (priceChange > 0) {
                priceChangeSymbol = "▲";
                priceChangeColor = "green";
            } else if (priceChange < 0) {
                priceChangeSymbol = "▼";
                priceChangeColor = "red";
            } else {
                priceChangeSymbol = "-";
                priceChangeColor = "gray";
            }

            assetsContent.append("<span style='color:lightblue; font-size:12px;'>")
                    .append(stock.getName())
                    .append(": </span><span style='color:white; font-size:10px;'>총 구매 가격 ")
                    .append(formatter.format(totalPurchasePrice))
                    .append(" 원</span><span style='color:white; font-size:12px;'>, </span><span style='color:white; font-size:10px;'>현재 총 가격 ")
                    .append(formatter.format(totalCurrentPrice))
                    .append(" 원</span><span style='color:white; font-size:12px;'>, </span><span style='color:")
                    .append(priceChangeColor)
                    .append("; font-size:10px;'>가격 변동 ")
                    .append(priceChangeStr)
                    .append(priceChangeSymbol)
                    .append("</span><span style='color:white; font-size:12px;'>, </span><span style='color:white; font-size:10px;'>수량 ")
                    .append(quantity)
                    .append("주</span><br>");
        }

        assetsContent.append("</html>");

        assetsInfo.setText(assetsContent.toString());
    }

    public void updateUserInfo() {
        setAssets();
    }

    private void showItemsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        displayItemInventory();
    }

    private void displayItemInventory() {
        if (itemDialog != null) {
            itemDialog.dispose();
        }

        JPanel panel = new JPanel(new GridLayout(0, 1));
        Map<String, Integer> itemInventory = user.getItemInventory();

        if (itemInventory.isEmpty()) {
            JOptionPane.showMessageDialog(this, "보유 중인 아이템이 없습니다.", "보유 아이템", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (Map.Entry<String, Integer> entry : itemInventory.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();

            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel itemLabel = new JLabel(itemName + ": 수량 " + quantity);
            JButton useButton = new JButton("사용");

            useButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    useItem(itemName);
                }
            });

            itemPanel.add(itemLabel);
            itemPanel.add(useButton);
            panel.add(itemPanel);
        }

        itemDialog = new JDialog(this, "보유 아이템", true);
        itemDialog.getContentPane().add(new JScrollPane(panel));
        itemDialog.pack();
        itemDialog.setLocationRelativeTo(this);
        itemDialog.setVisible(true);
    }

    private void useItem(String itemName) {
        Random random = new Random();
        switch (itemName) {
            case "랜덤 주식 5주":
                addRandomStocks(5);
                break;
            case "랜덤 금액":
                int randomMoney = random.nextInt(9901) + 100; // 100 ~ 10000
                user.setMoney(user.getMoney() + randomMoney);
                JOptionPane.showMessageDialog(this, "랜덤 금액: " + randomMoney + "원이 추가되었습니다.");
                break;
            case "알바 추가":
                user.setPartTimeCount(user.getPartTimeCount() + 1);
                JOptionPane.showMessageDialog(this, "알바 횟수가 1회 증가하였습니다.");
                break;
            case "랜덤 주식 10주":
                addRandomStocks(10);
                break;
            case "고액 랜덤 금액":
                int highRandomMoney = random.nextInt(19001) + 1000; // 1000 ~ 20000
                user.setMoney(user.getMoney() + highRandomMoney);
                JOptionPane.showMessageDialog(this, "고액 랜덤 금액: " + highRandomMoney + "원이 추가되었습니다.");
                break;
            case "랜덤 주식 20주":
                addRandomStocks(20);
                break;
        }

        // 아이템 수량 감소 및 0이 되면 제거
        int newQuantity = user.getItemInventory().get(itemName) - 1;
        if (newQuantity > 0) {
            user.getItemInventory().put(itemName, newQuantity);
        } else {
            user.getItemInventory().remove(itemName);
        }
        updateUserInfo();
        displayItemInventory(); // 업데이트된 아이템 목록을 다시 표시
    }

    private void addRandomStocks(int quantity) {
        Sector sector = getRandomSector();
        if (sector != null && !sector.getStocks().isEmpty()) {
            Random random = new Random();
            Stock randomStock = sector.getStocks().get(random.nextInt(sector.getStocks().size()));
            user.addStock(randomStock, quantity, randomStock.getPrice());
            JOptionPane.showMessageDialog(this, randomStock.getName() + " 주식 " + quantity + "주가 추가되었습니다.");
        } else {
            JOptionPane.showMessageDialog(this, "랜덤 주식 추가에 실패하였습니다. 유효한 분야가 없습니다.");
        }
    }

    private Sector getRandomSector() {
        if (sectors.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return sectors.get(random.nextInt(sectors.size()));
    }
}
