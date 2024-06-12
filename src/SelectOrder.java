import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SelectOrder extends javax.swing.JFrame {
    private Client client;
    private List<Sector> sectors;
    private User user;
    private boolean isSellMode = false;
    private Map<String, List<String>> userStockMap;
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> selectSector;
    private javax.swing.JComboBox<String> selectStock;
    private javax.swing.JPanel allPanel;
    private javax.swing.JTextField contextField;
    private javax.swing.JToggleButton sectorButton;
    private javax.swing.JToggleButton sellButton;
    private javax.swing.JToggleButton buyButton;

    public SelectOrder(Client client, List<Sector> sectors) {
        this.client = client;
        this.sectors = sectors;
        initComponents();
        selectSector.setVisible(false);
        selectStock.setVisible(false);
        contextField.setEnabled(false);
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void initComponents() {
        allPanel = new javax.swing.JPanel();
        backButton = new javax.swing.JButton();
        sectorButton = new javax.swing.JToggleButton();
        selectSector = new javax.swing.JComboBox<>();
        selectStock = new javax.swing.JComboBox<>();
        contextField = new javax.swing.JTextField();
        sellButton = new javax.swing.JToggleButton();
        buyButton = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        allPanel.setBackground(new java.awt.Color(153, 204, 255));
        allPanel.setPreferredSize(new java.awt.Dimension(761, 524));

        backButton.setBackground(new java.awt.Color(153, 204, 255));
        backButton.setFont(new java.awt.Font("한컴 고딕", 1, 14));
        backButton.setText("뒤로가기");
        backButton.setToolTipText("");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        sectorButton.setFont(new java.awt.Font("한컴 고딕", 1, 14));
        sectorButton.setText("분야");
        sectorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sectorButtonActionPerformed(evt);
            }
        });

        selectSector.setModel(new javax.swing.DefaultComboBoxModel<>(
                sectors.stream().map(Sector::getName).toArray(String[]::new)
        ));
        selectSector.setSelectedIndex(-1);
        selectSector.setFocusable(false);
        selectSector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectSectorActionPerformed(evt);
            }
        });

        selectStock.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        selectStock.setSelectedIndex(-1);
        selectStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectStockActionPerformed(evt);
            }
        });

        contextField.setFont(new java.awt.Font("맑은 고딕", 0, 14));
        contextField.setForeground(new java.awt.Color(204, 204, 204));
        contextField.setText("수량. . .");
        contextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contextFieldActionPerformed(evt);
            }
        });
        contextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (contextField.getText().equals("수량. . .")) {
                    contextField.setText("");
                    contextField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (contextField.getText().isEmpty()) {
                    contextField.setText("수량. . .");
                    contextField.setForeground(new Color(153, 153, 153));
                }
            }
        });

        sellButton.setBackground(new java.awt.Color(255, 153, 102));
        sellButton.setFont(new java.awt.Font("맑은 고딕", 1, 14));
        sellButton.setText("시장가 매수");
        sellButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellButtonActionPerformed(evt);
            }
        });

        buyButton.setBackground(new java.awt.Color(255, 153, 102));
        buyButton.setFont(new java.awt.Font("맑은 고딕", 1, 14));
        buyButton.setText("시장가 매도");
        buyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(allPanel);
        allPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(sectorButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(selectSector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(buyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(sellButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(selectStock, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(135, 135, 135)
                                                .addComponent(contextField, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(369, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(sectorButton)
                                                        .addComponent(selectSector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(selectStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 247, Short.MAX_VALUE)
                                                .addComponent(sellButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(19, 19, 19)
                                                .addComponent(buyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(91, 91, 91))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(contextField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(123, 123, 123))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(allPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(allPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        sectorButton.setEnabled(false);
        pack();
    }

    private void sectorButtonActionPerformed(java.awt.event.ActionEvent evt) {
        selectSector.setVisible(sectorButton.isSelected());
        if (!sectorButton.isSelected()) {
            selectStock.setVisible(false);
        }
        selectStock.setSelectedIndex(-1);
    }

    private void selectSectorActionPerformed(java.awt.event.ActionEvent evt) {
        if (selectSector.getSelectedIndex() != -1) {
            if (isSellMode) {
                updateSellComboBox();
            } else {
                Sector selectedSector = sectors.get(selectSector.getSelectedIndex());
                List<Stock> stocks = selectedSector.getStocks();
                selectStock.removeAllItems();
                for (Stock stock : stocks) {
                    selectStock.addItem(stock.getName());
                }
                selectStock.setVisible(true);
            }
        }
    }

    private void selectStockActionPerformed(java.awt.event.ActionEvent evt) {
        checkComboBoxSelection();
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        client.setVisible(true);
    }

    private void contextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        String quantityText = contextField.getText();
        try {
            int quantity = Integer.parseInt(quantityText);
            if (sellButton.isSelected() && selectStock.getSelectedIndex() != -1) {
                buyStock(quantity);
            } else if (buyButton.isSelected() && selectStock.getSelectedIndex() != -1) {
                sellStock(quantity);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "잘못된 수량이 입력되었습니다.");
        }
    }

    private void sellButtonActionPerformed(java.awt.event.ActionEvent evt) {
        isSellMode = false;
        if(sellButton.isSelected()) {
            sectorButton.setEnabled(true);
        }
        else{
            sectorButton.setEnabled(false);
        }
        if(buyButton.isSelected()){
            buyButton.setSelected(false);
        }
        updateComboBoxes();
    }

    private void buyButtonActionPerformed(java.awt.event.ActionEvent evt) {
        isSellMode = true;
        if(buyButton.isSelected()) {
            sectorButton.setEnabled(true);
        }
        else{
            sectorButton.setEnabled(false);
        }
        if(sellButton.isSelected()){
            sellButton.setSelected(false);
        }
        updateComboBoxes();
    }

    private void checkComboBoxSelection() {
        contextField.setEnabled(selectStock.getSelectedIndex() != -1);
    }

    private void updateComboBoxes() {
        selectSector.removeAllItems();
        selectStock.removeAllItems();

        if (isSellMode) {
            userStockMap = user.getStockPortfolio().entrySet().stream()
                    .collect(Collectors.groupingBy(
                            entry -> getSectorByStock(entry.getKey()).getName(),
                            Collectors.mapping(entry -> entry.getKey().getName(), Collectors.toList())
                    ));
            userStockMap.keySet().forEach(sectorName -> selectSector.addItem(sectorName));
        } else {
            sectors.forEach(sector -> selectSector.addItem(sector.getName()));
        }

        selectSector.setSelectedIndex(-1);
        selectStock.setSelectedIndex(-1);

        selectSector.setVisible(false);
        selectStock.setVisible(false);
        sectorButton.setSelected(false);
    }

    private void updateSellComboBox() {
        String selectedSectorName = (String) selectSector.getSelectedItem();
        List<String> stockNames = userStockMap.get(selectedSectorName);
        selectStock.removeAllItems();
        for (String stockName : stockNames) {
            selectStock.addItem(stockName);
        }
        selectStock.setSelectedIndex(-1);
        selectStock.setVisible(true);
    }

    private Sector getSectorByStock(Stock stock) {
        return sectors.stream()
                .filter(sector -> sector.getStocks().contains(stock))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 주식의 분야를 찾을 수 없습니다.: " + stock.getName()));
    }

    private void buyStock(int quantity) {
        Sector selectedSector = sectors.get(selectSector.getSelectedIndex());
        Stock selectedStock = selectedSector.getStocks().get(selectStock.getSelectedIndex());
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
        String selectedSectorName = (String) selectSector.getSelectedItem();
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
}
