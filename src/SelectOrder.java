import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SelectOrder extends javax.swing.JFrame {
    private Client client;
    private List<Sector> sectors;
    private User user;
    private boolean isSellMode = false;
    private Map<String, List<String>> userStockMap;

    public SelectOrder(Client client, List<Sector> sectors) {
        this.client = client;
        this.sectors = sectors;
        initComponents();
        jComboBox1.setVisible(false);
        jComboBox2.setVisible(false);
        jTextField1.setEnabled(false);
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(761, 524));

        jButton1.setBackground(new java.awt.Color(153, 204, 255));
        jButton1.setFont(new java.awt.Font("한컴 고딕", 1, 14));
        jButton1.setText("뒤로가기");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jToggleButton1.setFont(new java.awt.Font("한컴 고딕", 1, 14));
        jToggleButton1.setText("분야");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(
                sectors.stream().map(Sector::getName).toArray(String[]::new)
        ));
        jComboBox1.setSelectedIndex(-1);
        jComboBox1.setFocusable(false);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jComboBox2.setSelectedIndex(-1);
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("맑은 고딕", 0, 14));
        jTextField1.setForeground(new java.awt.Color(204, 204, 204));
        jTextField1.setText("수량. . .");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (jTextField1.getText().equals("수량. . .")) {
                    jTextField1.setText("");
                    jTextField1.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (jTextField1.getText().isEmpty()) {
                    jTextField1.setText("수량. . .");
                    jTextField1.setForeground(new Color(153, 153, 153));
                }
            }
        });

        jToggleButton2.setBackground(new java.awt.Color(255, 153, 102));
        jToggleButton2.setFont(new java.awt.Font("맑은 고딕", 1, 14));
        jToggleButton2.setText("시장가 매수");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jToggleButton4.setBackground(new java.awt.Color(255, 153, 102));
        jToggleButton4.setFont(new java.awt.Font("맑은 고딕", 1, 14));
        jToggleButton4.setText("시장가 매도");
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jToggleButton1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(135, 135, 135)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(369, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jToggleButton1)
                                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 247, Short.MAX_VALUE)
                                                .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(19, 19, 19)
                                                .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(91, 91, 91))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(123, 123, 123))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        jToggleButton1.setEnabled(false);
        pack();
    }

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        jComboBox1.setVisible(jToggleButton1.isSelected());
        if (!jToggleButton1.isSelected()) {
            jComboBox2.setVisible(false);
        }
        jComboBox2.setSelectedIndex(-1);
    }

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (jComboBox1.getSelectedIndex() != -1) {
            if (isSellMode) {
                updateSellComboBox();
            } else {
                Sector selectedSector = sectors.get(jComboBox1.getSelectedIndex());
                List<Stock> stocks = selectedSector.getStocks();
                jComboBox2.removeAllItems();
                for (Stock stock : stocks) {
                    jComboBox2.addItem(stock.getName());
                }
                jComboBox2.setVisible(true);
            }
        }
    }

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {
        checkComboBoxSelection();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        client.setVisible(true);
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
        String quantityText = jTextField1.getText();
        try {
            int quantity = Integer.parseInt(quantityText);
            if (jToggleButton2.isSelected() && jComboBox2.getSelectedIndex() != -1) {
                buyStock(quantity);
            } else if (jToggleButton4.isSelected() && jComboBox2.getSelectedIndex() != -1) {
                sellStock(quantity);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid quantity input.");
        }
    }

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        isSellMode = false;
        if(jToggleButton2.isSelected()) {
            jToggleButton1.setEnabled(true);
        }
        else{
            jToggleButton1.setEnabled(false);
        }
        if(jToggleButton4.isSelected()){
            jToggleButton4.setSelected(false);
        }
        updateComboBoxes();
    }

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        isSellMode = true;
        if(jToggleButton4.isSelected()) {
            jToggleButton1.setEnabled(true);
        }
        else{
            jToggleButton1.setEnabled(false);
        }
        if(jToggleButton2.isSelected()){
            jToggleButton2.setSelected(false);
        }
        updateComboBoxes();
    }

    private void checkComboBoxSelection() {
        jTextField1.setEnabled(jComboBox2.getSelectedIndex() != -1);
    }

    private void updateComboBoxes() {
        jComboBox1.removeAllItems();
        jComboBox2.removeAllItems();

        if (isSellMode) { // 매도 모드
            userStockMap = user.getStockPortfolio().entrySet().stream()
                    .collect(Collectors.groupingBy(
                            entry -> getSectorByStock(entry.getKey()).getName(),
                            Collectors.mapping(entry -> entry.getKey().getName(), Collectors.toList())
                    ));
            userStockMap.keySet().forEach(sectorName -> jComboBox1.addItem(sectorName));
        } else { // 매수 모드
            sectors.forEach(sector -> jComboBox1.addItem(sector.getName()));
        }

        jComboBox1.setSelectedIndex(-1);
        jComboBox2.setSelectedIndex(-1);

        jComboBox1.setVisible(false);
        jComboBox2.setVisible(false);
        jToggleButton1.setSelected(false);
    }

    private void updateSellComboBox() {
        String selectedSectorName = (String) jComboBox1.getSelectedItem();
        List<String> stockNames = userStockMap.get(selectedSectorName);
        jComboBox2.removeAllItems();
        for (String stockName : stockNames) {
            jComboBox2.addItem(stockName);
        }
        jComboBox2.setSelectedIndex(-1);
        jComboBox2.setVisible(true);
    }

    private Sector getSectorByStock(Stock stock) {
        return sectors.stream()
                .filter(sector -> sector.getStocks().contains(stock))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Sector not found for stock: " + stock.getName()));
    }

    private void buyStock(int quantity) {
        Sector selectedSector = sectors.get(jComboBox1.getSelectedIndex());
        Stock selectedStock = selectedSector.getStocks().get(jComboBox2.getSelectedIndex());
        int totalPrice = selectedStock.getPrice() * quantity;

        if (user.getMoney() >= totalPrice) {
            user.setMoney(user.getMoney() - totalPrice);
            user.addStock(selectedStock, quantity, selectedStock.getPrice());
            JOptionPane.showMessageDialog(this, String.format("%s %d 주\n남은 돈: %d\n총 구매액: %d",
                    selectedStock.getName(), quantity, user.getMoney(), totalPrice));
            printUserPortfolio();
        } else {
            JOptionPane.showMessageDialog(this, "돈이 부족합니다.");
        }
    }

    private void sellStock(int quantity) {
        String selectedSectorName = (String) jComboBox1.getSelectedItem();
        List<String> stockNames = userStockMap.get(selectedSectorName);
        Stock selectedStock = sectors.stream()
                .filter(sector -> sector.getName().equals(selectedSectorName))
                .flatMap(sector -> sector.getStocks().stream())
                .filter(stock -> stockNames.contains(stock.getName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid stock selected"));

        int userStockQuantity = user.getStockQuantity(selectedStock);
        if (userStockQuantity >= quantity) {
            int totalPrice = selectedStock.getPrice() * quantity;
            user.setMoney(user.getMoney() + totalPrice);
            user.removeStock(selectedStock, quantity);
            JOptionPane.showMessageDialog(this, String.format("%s %d 주\n남은 돈: %d\n총 판매액: %d",
                    selectedStock.getName(), quantity, user.getMoney(), totalPrice));
            printUserPortfolio();
        } else {
            JOptionPane.showMessageDialog(this, "보유한 주식 수량이 부족합니다.");
        }
    }

    private void printUserPortfolio() {
        System.out.println("현재 보유한 주식 목록:");
        for (Map.Entry<Stock, Integer> entry : user.getStockPortfolio().entrySet()) {
            System.out.println(entry.getKey().getName() + ": " + entry.getValue() + " 주");
        }
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton4;
}
