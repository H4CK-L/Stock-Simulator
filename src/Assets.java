import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.text.*;

public class Assets extends javax.swing.JFrame {
    private User user;
    private StringBuilder assetsContent;

    public Assets(User user) {
        this.user = user;
        initComponents();
    }

    public void setUser(User user) {
        this.user = user;
        updateUserInfo();
    }

    public User getUser(){
        return user;
    }
    private void initComponents() {
        setTitle("총 자산");

        jPanel1 = new javax.swing.JPanel();
        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        assetsInfo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 300));

        setAssets();

        assetsInfo.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(assetsInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(assetsInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                .addContainerGap())
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
    }

    public void setAssets() {
        assetsContent = new StringBuilder("<html>");
        assetsContent.append("<span style='color:black; font-size:13px;'>")
                .append("[")
                .append("</span")
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
                .append("회</span>");
        assetsContent.append("<br/><br/>");
        assetsContent.append("</html>");

        assetsInfo.setText(assetsContent.toString());
    }

    public void updateUserInfo(){
        setAssets();
    }

    private javax.swing.JLabel assetsInfo;
    private javax.swing.JPanel jPanel1;
}
