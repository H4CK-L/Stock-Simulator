import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class PartTime extends javax.swing.JFrame {
    private Client client;
    private int countMin = 30;
    private int countSec = 0;
    private volatile boolean running = true;
    private User user;
    private ArrayList<String> ex = new ArrayList<>();
    private ArrayList<String> pokemon = new ArrayList<>();
    private ArrayList<String> pokemonAnswer = new ArrayList<>();
    private ArrayList<String> word = new ArrayList<>();
    private ArrayList<String> wordAnswer = new ArrayList<>();
    private ArrayList<String> arth = new ArrayList<>();
    private ArrayList<Integer> arthAnswer = new ArrayList<>();
    private Timer timer;
    private int timeLimitSeconds = 30;
    private int random;
    private StringBuilder partMessage;
    private javax.swing.JButton backButton;
    private javax.swing.JButton startButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel titleText;
    private javax.swing.JLabel haveCount;
    private javax.swing.JLabel countTimer;
    private javax.swing.JLabel exText;
    private javax.swing.JLabel answerTimer;
    private javax.swing.JPanel allPanel;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JPanel textPanel;
    private javax.swing.JTextField answerText;

    public PartTime(Client client) {
        this.client = client;
        loadFile("PartTimeExplain.txt", ex);
        loadFile("Pokemon.txt", pokemon);
        loadFile("PokemonAnswer.txt", pokemonAnswer);
        loadFile("Word.txt", word);
        loadFile("WordAnswer.txt", wordAnswer);
        loadFile("Arthmetic.txt", arth);
        loadArthFile("ArthmeticAnswer.txt", arthAnswer);
        initComponents();
    }

    private void loadFile(String filename, ArrayList<String> list) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadArthFile(String filename, ArrayList<Integer> list) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int value;
            while ((line = br.readLine()) != null) {
                value = Integer.parseInt(line.trim());
                list.add(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUser(User user) {
        this.user = user;
        haveCount.setText("알바 가능 횟수 : " + user.getPartTimeCount());
        updateStartButton();
    }

    private void updateStartButton() {
        if (user.getPartTimeCount() == 0) {
            startButton.setBackground(Color.GRAY);
            startButton.setEnabled(false);
            jComboBox1.setEnabled(false);
            SwingUtilities.invokeLater(() -> exText.setText("<html><span style='color:red;'>보유한 알바 가능 횟수가 없습니다!!<br>충전이 된 후 시도하세요!!<br/></span></html>"));
        } else {
            startButton.setBackground(Color.RED);
            jComboBox1.setEnabled(true);
            startButton.setEnabled(true);
            exText.setText("");
        }
    }

    public class TimerThread extends Thread {
        @Override
        public void run() {
            while (running) {
                try {
                    Thread.sleep(1000); // 1초 대기
                    if (countSec == 0) {
                        if (countMin == 0) {
                            countMin = 30;
                            countSec = 0;
                            if (user.getPartTimeCount() < 3) {
                                user.setPartTimeCount(user.getPartTimeCount() + 1);
                                System.out.println(user.getPartTimeCount());
                                haveCount.setText("알바 가능 횟수 : " + user.getPartTimeCount());
                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        updateStartButton(); // 버튼 상태 갱신
                                    }
                                });
                            }
                            else{
                                continue;
                            }
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

    @SuppressWarnings("unchecked")
    private void initComponents() {

        allPanel = new javax.swing.JPanel();
        backButton = new javax.swing.JButton();
        titlePanel = new javax.swing.JPanel();
        titleText = new javax.swing.JLabel();
        haveCount = new javax.swing.JLabel();
        countTimer = new javax.swing.JLabel();
        exText = new javax.swing.JLabel();
        answerTimer = new javax.swing.JLabel();
        answerText = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        textPanel = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        allPanel.setBackground(new java.awt.Color(140, 255, 187));
        allPanel.setFont(new java.awt.Font("한컴 고딕", 1, 14)); // NOI18N
        allPanel.setPreferredSize(new java.awt.Dimension(761, 524));

        backButton.setBackground(new java.awt.Color(140, 255, 187));
        backButton.setFont(new java.awt.Font("한컴 고딕", 1, 14)); // NOI18N
        backButton.setText("뒤로가기");
        backButton.setToolTipText("");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        titleText.setFont(new java.awt.Font("한컴 고딕", 1, 13)); // NOI18N
        titleText.setText("알바존");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(27, Short.MAX_VALUE)
                                .addComponent(titleText)
                                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(titleText)
                                .addContainerGap(16, Short.MAX_VALUE))
        );

        haveCount.setFont(new java.awt.Font("한컴 고딕", 1, 14)); // NOI18N

        countTimer.setFont(new java.awt.Font("한컴 고딕", 1, 14)); // NOI18N
        TimerThread timerThread = new TimerThread(); // TimerThread 객체 생성
        timerThread.start(); // 타이머 시작
        countTimer.setText(String.format("알바 횟수 추가까지 남은 시간 : %02d분 %02d초", countMin, countSec));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "포켓몬 도감 맞추기", "단어 맞추기", "산술 연산"}));
        jComboBox1.setSelectedIndex(-1);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int selectedIndex = jComboBox1.getSelectedIndex();
                partTimeEx(selectedIndex);
            }
        });


        exText.setText("");
        exText.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        answerText.setText("");
        answerText.setVisible(false);
        answerText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int selectedIndex = jComboBox1.getSelectedIndex();
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    switch (selectedIndex) {
                        case 0:
                            checkAnswer();
                            break;
                        case 1:
                            checkAnswer();
                            break;
                        case 2:
                            if (containsAlphabets(answerText.getText())) {
                                JOptionPane.showMessageDialog(null, "텍스트에 문자가 포함되어 있습니다. 숫자로만 입력해주세요.");
                                answerText.setText("");
                                answerText.requestFocus();
                            }
                            else{
                                checkArthAnswer();
                            }
                            break;
                    }
                }
            }
        });

        answerTimer.setText("");
        answerTimer.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(textPanel);
        textPanel.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(answerTimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(exText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(answerText, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(exText, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(answerTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(answerText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        startButton.setBackground(new java.awt.Color(255, 102, 102));
        startButton.setFont(new java.awt.Font("한컴 고딕", 1, 13)); // NOI18N
        startButton.setText("시작하기");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(allPanel);
        allPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(331, 331, 331)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(haveCount, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(174, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(countTimer)
                                                .addGap(272, 272, 272))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                                .addComponent(startButton)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(textPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addGap(173, 173, 173))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(haveCount)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(countTimer)
                                                .addGap(59, 59, 59)
                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                                                .addComponent(textPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(63, Short.MAX_VALUE))
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

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedIndex = jComboBox1.getSelectedIndex();
        switch (selectedIndex) {
            case 0:
                checkAnswer();
            case 1:
                checkAnswer();
            case 2:

                checkArthAnswer();
        }
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        client.setVisible(true);
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedIndex = jComboBox1.getSelectedIndex();

        switch (selectedIndex) {
            case 0:
                startButton.setEnabled(false);
                jComboBox1.setEnabled(false);
                poketmonStart();
                break;
            case 1:
                startButton.setEnabled(false);
                jComboBox1.setEnabled(false);
                wordStart();
                break;
            case 2:
                startButton.setEnabled(false);
                jComboBox1.setEnabled(false);
                arthStart();
                break;
            default:
                JOptionPane.showMessageDialog(this, "유효한 문제 유형을 선택하세요.");
        }
    }

    private void updateTimerLabel() {
        countTimer.setText(String.format("알바 횟수 추가 까지 남은 시간 : %02d분 %02d초", countMin, countSec));
    }

    private void stopTimerThread() {
        running = false;
    }

    private void partTimeEx(int index){
        switch(index){
            case 0:
                exText.setText(ex.get(index));
                break;
            case 1:
                exText.setText(ex.get(index));
                break;
            case 2:
                exText.setText(ex.get(index));
                break;
        }
    }

    private void poketmonStart(){
        answerText.setVisible(true);
        startTimer();
        random = (int) (Math.random() * pokemon.size());
        exText.setText(pokemon.get(random));
    }

    private void wordStart(){
        answerText.setVisible(true);
        startTimer();
        random = (int) (Math.random() * word.size());
        exText.setText(word.get(random));
    }

    private void arthStart(){
        answerText.setVisible(true);
        startTimer();
        random = (int) (Math.random() * arth.size());
        exText.setText(arth.get(random));
    }

    private void checkAnswer() {
        int selectedIndex = jComboBox1.getSelectedIndex();
        partMessage = new StringBuilder();
        String userAnswer = answerText.getText().trim();
        boolean isCorrect = false;

        switch (selectedIndex) {
            case 0:
                isCorrect = pokemonAnswer.get(random).contains(userAnswer);
                break;
            case 1:
                isCorrect = wordAnswer.get(random).contains(userAnswer);
                break;
        }

        if (isCorrect) {
            partMessage.append("<html><span style='color:#00CC66;'>알바를 성공적으로 마쳤습니다!!<br><br>")
                    .append("보상으로 ")
                    .append("2000원을 획득하였습니다!!<br/><br/></span></html>");
        } else {
            partMessage.append("<html><span style='color:#FF6666;'>알바를 실패하여 사장님에게 쫒겨났습니다!!<br><br>더 연습하세요!!<br/><br/></span></html>");
        }

        timer.stop(); // 타이머 정지
        SwingUtilities.invokeLater(() -> exText.setText(partMessage.toString()));
        user.setPartTimeCount(user.getPartTimeCount() - 1);
        if (isCorrect) {
            user.setMoney(user.getMoney() + 2000);
        }

        setUser(user);
        jComboBox1.setSelectedIndex(-1);
        answerTimer.setVisible(false);
        updateStartButton();
        System.out.println(user.getPartTimeCount());
        answerText.setText("");
        answerText.setVisible(false);
    }

    public boolean containsAlphabets(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                return true; // 문자가 포함되어 있으면 true 반환
            }
        }
        return false; // 문자가 포함되어 있지 않으면 false 반환
    }

    private void checkArthAnswer() {
        int userAnswer = Integer.parseInt(answerText.getText().trim());
        partMessage = new StringBuilder();
        boolean isCorrect = false;

        if (arthAnswer.get(random) == userAnswer) {
            isCorrect = true;
        }

        if (isCorrect) {
            partMessage.append("<html><span style='color:#00CC66;'>알바를 성공적으로 마쳤습니다!!<br><br>")
                    .append("보상으로 ")
                    .append("2000원을 획득하였습니다!!<br/><br/></span></html>");
        } else {
            partMessage.append("<html><span style='color:#FF6666;'>알바를 실패하여 사장님에게 쫒겨났습니다!!<br><br>더 연습하세요!!<br/><br/></span></html>");
        }

        timer.stop(); // 타이머 정지
        SwingUtilities.invokeLater(() -> exText.setText(partMessage.toString()));
        user.setPartTimeCount(user.getPartTimeCount() - 1);
        if (isCorrect) {
            user.setMoney(user.getMoney() + 2000);
        }

        setUser(user);
        jComboBox1.setSelectedIndex(-1);
        answerTimer.setVisible(false);
        updateStartButton();
        System.out.println(user.getPartTimeCount());
        answerText.setText("");
        answerText.setVisible(false);
    }


    private void startTimer() {
        timeLimitSeconds = 30; // 초기화
        updateTimer();
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLimitSeconds--;
                updateTimer();
                if (timeLimitSeconds == 0) {
                    timer.stop();
                    SwingUtilities.invokeLater(() -> exText.setText("<html><span style='color:#FF6666;'>시간 초과로 알바를 실패하였습니다!<br><br>더 연습하세요!!<br/><br/></span></html>"));
                    user.setPartTimeCount(user.getPartTimeCount() - 1);
                    setUser(user);
                    updateStartButton();
                    answerText.setVisible(false);
                    jComboBox1.setEnabled(true);
                    startButton.setEnabled(true);
                    answerTimer.setVisible(false);
                }
            }
        });
        timer.start();
        answerTimer.setVisible(true);
    }


    private void updateTimer() {
        int minutes = timeLimitSeconds / 60;
        int seconds = timeLimitSeconds % 60;
        answerTimer.setText(String.format("제한 시간 : %02d분 %02d초", minutes, seconds));
    }
}
