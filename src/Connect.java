import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Connect extends javax.swing.JFrame {
    private javax.swing.JLabel gameName;
    private javax.swing.JLabel gameNameKr;
    private javax.swing.JLabel pressKeyText;
    private javax.swing.JPanel allPanel;

    public Connect() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        allPanel = new javax.swing.JPanel();
        gameName = new javax.swing.JLabel();
        gameNameKr = new javax.swing.JLabel();
        pressKeyText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        allPanel.setBackground(new java.awt.Color(255, 255, 255));

        gameName.setFont(new java.awt.Font("한컴 고딕", 1, 18)); // NOI18N
        gameName.setText("Capitcal Conquest : Stock Saga");

        gameNameKr.setFont(new java.awt.Font("한컴 고딕", 0, 14)); // NOI18N
        gameNameKr.setText("자본 정복 : 주식 서사");

        pressKeyText.setFont(new java.awt.Font("한컴 고딕", 0, 12)); // NOI18N
        pressKeyText.setText("게임을 시작하려면 아무런 키를 입력하세요. . .");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(allPanel);
        allPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(264, 264, 264)
                                                .addComponent(pressKeyText))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(318, 318, 318)
                                                .addComponent(gameNameKr))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(246, 246, 246)
                                                .addComponent(gameName)))
                                .addContainerGap(256, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(152, 152, 152)
                                .addComponent(gameName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gameNameKr)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                                .addComponent(pressKeyText)
                                .addGap(103, 103, 103))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(allPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(allPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                setVisible(false);
                dispose();
            }
        });
        pack();
    }
}
