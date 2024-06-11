import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class SetNickname extends javax.swing.JFrame {
    private javax.swing.JLabel infoText;
    private javax.swing.JLabel titleText;
    private javax.swing.JPanel allPanel;
    private javax.swing.JTextField nameField;

    public SetNickname() {
        initComponents();
    }

    private void initComponents() {

        allPanel = new javax.swing.JPanel();
        infoText = new javax.swing.JLabel();
        titleText = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        allPanel.setBackground(new java.awt.Color(255, 255, 255));
        allPanel.setPreferredSize(new java.awt.Dimension(761, 524));

        infoText.setFont(new java.awt.Font("한컴 고딕", 0, 18)); // NOI18N
        infoText.setText("닉네임을 입력하여 등록해주세요.");

        titleText.setFont(new java.awt.Font("한컴 고딕", 0, 24)); // NOI18N
        titleText.setText("현재 닉네임이 등록되어 있지 않습니다.");

        nameField.setForeground(new java.awt.Color(153, 153, 153));
        nameField.setText("입력. . .");
        nameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (nameField.getText().equals("입력. . .")) {
                    nameField.setText("");
                    nameField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (nameField.getText().isEmpty()) {
                    nameField.setText("입력. . .");
                    nameField.setForeground(new Color(153, 153, 153));
                }
            }
        });
        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(allPanel);
        allPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(266, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(infoText)
                                                .addGap(242, 242, 242))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(295, 295, 295))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addContainerGap(199, Short.MAX_VALUE)
                                        .addComponent(titleText)
                                        .addGap(179, 179, 179)))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(190, 190, 190)
                                .addComponent(infoText)
                                .addGap(143, 143, 143)
                                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(144, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(146, 146, 146)
                                        .addComponent(titleText)
                                        .addContainerGap(346, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(allPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(allPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        allPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                allPanel.requestFocusInWindow(); // JPanel로 포커스 이동
            }
        });

        nameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nameField.requestFocusInWindow(); // JPanel로 포커스 이동
            }
        });
        pack();
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
        String input = nameField.getText();
        if (!input.matches("[A-Za-z0-9]{1,10}")) {
            JOptionPane.showMessageDialog(this, "닉네임 조건이 올바르지 않습니다!!\n조건 : 영어 대소문자, 숫자로 조합된 10글자","", JOptionPane.ERROR_MESSAGE);
        }
        else {
            String filePath = System.getProperty("user.dir") + "/User.txt";
            try {
                // 파일에 닉네임 저장
                FileWriter writer = new FileWriter(filePath);
                writer.write(input);
                writer.close();
                JOptionPane.showMessageDialog(this, "닉네임이 저장되었습니다.", "", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "닉네임을 저장하는 도중 오류가 발생했습니다.", "", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
