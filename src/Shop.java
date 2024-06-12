import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Shop extends javax.swing.JFrame {
    private Client client;
    private int countMin = 30;
    private int countSec = 0;
    private volatile boolean running = true;
    private User user;
    private ArrayList<String[]> items = new ArrayList<>();
    private javax.swing.JButton backButton;
    private javax.swing.JButton buy1;
    private javax.swing.JButton buy2;
    private javax.swing.JButton buy3;
    private javax.swing.JLabel titleText;
    private javax.swing.JLabel refreshTimer;
    private javax.swing.JLabel item1Name;
    private javax.swing.JLabel item2Name;
    private javax.swing.JLabel item3Name;
    private javax.swing.JLabel item1Ex;
    private javax.swing.JLabel item2Ex;
    private javax.swing.JLabel item3Ex;
    private javax.swing.JPanel allPanel;
    private javax.swing.JPanel item2Panel;
    private javax.swing.JPanel item1Panel;
    private javax.swing.JPanel item3Panel;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JPanel item1NamePanel;
    private javax.swing.JPanel item2NamePanel;
    private javax.swing.JPanel item3NamePanel;
    private javax.swing.JPanel item1ExPanel;
    private javax.swing.JPanel item2ExPanel;
    private javax.swing.JPanel item3ExPanel;

    public void setUser(User user) {
        this.user = user;
    }

    private class TimerThread extends Thread {
        @Override
        public void run() {
            while (running) {
                try {
                    Thread.sleep(1000); // 1초 대기
                    if (countSec == 0) {
                        if (countMin == 0) {
                            countMin = 30;
                            countSec = 0;
                            refreshItems();
                        } else {
                            countMin--;
                            countSec = 59;
                        }
                    } else {
                        countSec--;
                    }
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            updateTimerLabel();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Shop(Client client) {
        this.client = client;
        loadItems();
        initComponents();
        refreshItems(); // 처음 실행 시 아이템 갱신
    }

    private void initComponents() {
        allPanel = new javax.swing.JPanel();
        backButton = new javax.swing.JButton();
        titlePanel = new javax.swing.JPanel();
        titleText = new javax.swing.JLabel();
        item1NamePanel = new javax.swing.JPanel();
        item1Name = new javax.swing.JLabel();
        item2NamePanel = new javax.swing.JPanel();
        item2Name = new javax.swing.JLabel();
        item3NamePanel = new javax.swing.JPanel();
        item3Name = new javax.swing.JLabel();
        item1ExPanel = new javax.swing.JPanel();
        item1Ex = new javax.swing.JLabel();
        buy1 = new javax.swing.JButton();
        item2ExPanel = new javax.swing.JPanel();
        item2Ex = new javax.swing.JLabel();
        buy2 = new javax.swing.JButton();
        item3ExPanel = new javax.swing.JPanel();
        item3Ex = new javax.swing.JLabel();
        buy3 = new javax.swing.JButton();
        refreshTimer = new javax.swing.JLabel();
        item3Panel = new javax.swing.JPanel();
        item2Panel = new javax.swing.JPanel();
        item1Panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        allPanel.setBackground(new java.awt.Color(255, 194, 103));
        allPanel.setPreferredSize(new java.awt.Dimension(761, 524));

        backButton.setBackground(new java.awt.Color(255, 194, 103));
        backButton.setFont(new java.awt.Font("한컴 고딕", 1, 14)); // NOI18N
        backButton.setText("뒤로가기");
        backButton.setToolTipText("");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        titleText.setFont(new java.awt.Font("한컴 고딕", 1, 13)); // NOI18N
        titleText.setText("상점");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(titleText)
                                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(titleText)
                                .addContainerGap(16, Short.MAX_VALUE))
        );

        item1NamePanel.setPreferredSize(new java.awt.Dimension(181, 0));

        item1Name.setText("");
        item1Name.setToolTipText("");
        item1Name.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(item1NamePanel);
        item1NamePanel.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(item1Name, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(item1Name, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                                .addContainerGap())
        );

        item2Name.setText("");
        item2Name.setToolTipText("");
        item2Name.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(item2NamePanel);
        item2NamePanel.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(item2Name, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(item2Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        item3NamePanel.setPreferredSize(new java.awt.Dimension(181, 0));

        item3Name.setText("");
        item3Name.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(item3NamePanel);
        item3NamePanel.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(item3Name, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(item3Name, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                                .addContainerGap())
        );

        item1Ex.setText("");
        item1Ex.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        buy1.setText("구매");
        buy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buy1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(item1ExPanel);
        item1ExPanel.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(item1Ex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(buy1)
                                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(item1Ex, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addComponent(buy1)
                                .addContainerGap())
        );

        item2ExPanel.setPreferredSize(new java.awt.Dimension(216, 113));

        item2Ex.setText("");
        item2Ex.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        buy2.setText("구매");
        buy2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buy2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(item2ExPanel);
        item2ExPanel.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(item2Ex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addContainerGap(70, Short.MAX_VALUE)
                                .addComponent(buy2)
                                .addGap(70, 70, 70))
        );
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(item2Ex, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buy2)
                                .addContainerGap())
        );

        item3ExPanel.setPreferredSize(new java.awt.Dimension(216, 113));

        item3Ex.setText("");
        item3Ex.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        buy3.setText("구매");
        buy3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buy3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(item3ExPanel);
        item3ExPanel.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(item3Ex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addContainerGap(70, Short.MAX_VALUE)
                                .addComponent(buy3)
                                .addGap(70, 70, 70))
        );
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(item3Ex, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buy3)
                                .addContainerGap())
        );

        refreshTimer.setFont(new java.awt.Font("한컴 고딕", 1, 13)); // NOI18N
        TimerThread timerThread = new TimerThread();
        timerThread.start();
        refreshTimer.setText(String.format("상점 갱신까지 남은 시간 : %02d분 %02d초", countMin, countSec));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(item3Panel);
        item3Panel.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(item2Panel);
        item2Panel.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(item1Panel);
        item1Panel.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(allPanel);
        allPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(244, 244, 244)
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(item1ExPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(item2ExPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(item3ExPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(32, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(item1NamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(item2NamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(item3NamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(refreshTimer)
                                .addGap(290, 290, 290))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(item1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                                .addComponent(item2Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(142, 142, 142)
                                .addComponent(item3Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(refreshTimer)
                                .addGap(55, 55, 55)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(item3Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(item2Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(item1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(item1NamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(item2NamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(item3NamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(item1ExPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(item2ExPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(item3ExPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(83, Short.MAX_VALUE))
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

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        client.setVisible(true);
    }

    private void updateTimerLabel() {
        refreshTimer.setText(String.format("상점 갱신까지 남은 시간 : %02d분 %02d초", countMin, countSec));
    }

    private void stopTimerThread() {
        running = false;
    }

    private void loadItems() {
        try (BufferedReader br = new BufferedReader(new FileReader("Shop.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] itemDetails = line.split(", ");
                items.add(itemDetails);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void refreshItems() {
        Collections.shuffle(items);
        String[] item1 = items.get(0);
        String[] item2 = items.get(1);
        String[] item3 = items.get(2);

        this.item1Name.setText(item1[0]);
        item2Name.setText(item2[0]);
        item3Name.setText(item3[0]);

        item1Ex.setText("<html>" + item1[1] + "<br>가격: " + item1[2] + "</html>");
        item2Ex.setText("<html>" + item2[1] + "<br>가격: " + item2[2] + "</html>");
        item3Ex.setText("<html>" + item3[1] + "<br>가격: " + item3[2] + "</html>");

        buy1.setEnabled(true);
        buy2.setEnabled(true);
        buy3.setEnabled(true);
    }

    private void buy1ActionPerformed(java.awt.event.ActionEvent evt) {
        purchaseItem(0, buy1);
    }

    private void buy2ActionPerformed(java.awt.event.ActionEvent evt) {
        purchaseItem(1, buy2);
    }

    private void buy3ActionPerformed(java.awt.event.ActionEvent evt) {
        purchaseItem(2, buy3);
    }

    private void purchaseItem(int itemIndex, JButton button) {
        String[] selectedItem = items.get(itemIndex);
        int itemPrice = Integer.parseInt(selectedItem[2].replaceAll("[^0-9]", ""));
        String itemName = selectedItem[0];
        String itemDescription = selectedItem[1];

        int response = JOptionPane.showConfirmDialog(this,
                String.format("%s\n%s\n가격: %d\n구매하시겠습니까?", itemName, itemDescription, itemPrice),
                "아이템 구매",
                JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            if (user.getMoney() >= itemPrice) {
                user.setMoney(user.getMoney() - itemPrice);
                user.addItem(itemName);
                JOptionPane.showMessageDialog(this, "아이템을 성공적으로 구매하였습니다.");
                button.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "돈이 부족하여 아이템을 구매할 수 없습니다.");
            }
        }
    }
}
