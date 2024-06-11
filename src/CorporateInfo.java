import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CorporateInfo extends javax.swing.JFrame {
    private Client client;
    private List<Sector> sectors;
    private ArrayList<String> techName = new ArrayList<>();
    private ArrayList<String> techEx = new ArrayList<>();
    private ArrayList<String> artName = new ArrayList<>();
    private ArrayList<String> artEx = new ArrayList<>();
    private ArrayList<String> gameName = new ArrayList<>();
    private ArrayList<String> gameEx = new ArrayList<>();
    private ArrayList<String> selectedCompanies;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> selectSector;
    private javax.swing.JComboBox<String> selectStock;
    private javax.swing.JLabel titleText;
    private javax.swing.JLabel infoText;
    private javax.swing.JPanel allPanel;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JPanel infoPanel;

    public CorporateInfo(Client client, List<Sector> sectors) {
        this.client = client;
        this.sectors = sectors;
        readFile("Tech.txt", techName, techEx);
        readFile("Art.txt", artName, artEx);
        readFile("Game.txt", gameName, gameEx);
        initComponents();
    }

    private void readFile(String file, ArrayList name, ArrayList ex){
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    name.add(parts[0].trim());
                    ex.add(parts[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {

        allPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        titlePanel = new javax.swing.JPanel();
        titleText = new javax.swing.JLabel();
        selectSector = new javax.swing.JComboBox<>();
        selectStock = new javax.swing.JComboBox<>();
        infoPanel = new javax.swing.JPanel();
        infoText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        allPanel.setBackground(new java.awt.Color(244, 221, 244));
        allPanel.setPreferredSize(new java.awt.Dimension(761, 524));

        jButton1.setBackground(new java.awt.Color(255, 171, 255));
        jButton1.setFont(new java.awt.Font("한컴 고딕", 1, 14)); // NOI18N
        jButton1.setText("뒤로가기");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        titleText.setFont(new java.awt.Font("한컴 고딕", 1, 13)); // NOI18N
        titleText.setText("기업 정보");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(titleText)
                                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleText, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        selectSector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { sectors.get(0).getName(), sectors.get(1).getName(), sectors.get(2).getName()}));
        selectSector.setSelectedIndex(-1);
        selectSector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        selectStock.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        selectStock.setSelectedIndex(-1);
        selectStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        infoPanel.setBackground(new java.awt.Color(255, 255, 255));

        infoPanel.setBackground(new java.awt.Color(255, 255, 255));

        infoText.setText("분야를 선택하고 기업을 선택하면 설명이 나타납니다.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(infoText, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(infoText, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(allPanel);
        allPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(247, 247, 247)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(selectStock, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(selectSector, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(204, 204, 204))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(146, Short.MAX_VALUE)
                                .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(136, 136, 136))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(selectSector, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addComponent(selectStock, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(69, Short.MAX_VALUE))
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
        int selectedIndex = selectSector.getSelectedIndex();
        selectedCompanies = new ArrayList<>();
        switch (selectedIndex) {
            case 0: // 기술 분야
                selectedCompanies = techName;
                break;
            case 1: // 예술 분야
                selectedCompanies = artName;
                break;
            case 2: // 게임 분야
                selectedCompanies = gameName;
                break;
            default:
                break;
        }

        // 기업 목록을 jComboBox2에 설정
        selectStock.setModel(new javax.swing.DefaultComboBoxModel<>(selectedCompanies.toArray(new String[0])));
        selectStock.setSelectedIndex(-1);
    }

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {
        // jComboBox2에서 기업 선택 시, 해당 기업의 설명을 jLabel2에 출력
        String selectedCompany = (String) selectStock.getSelectedItem();
        ArrayList<String> selectedCompanyEx = null;
        // 선택된 기업의 설명 찾기
        if (techName.contains(selectedCompany)) {
            selectedCompanyEx = techEx;
        } else if (artName.contains(selectedCompany)) {
            selectedCompanyEx = artEx;
        } else if (gameName.contains(selectedCompany)) {
            selectedCompanyEx = gameEx;
        }
        // 선택된 기업의 설명을 jLabel2에 출력
        if (selectedCompanyEx != null) {
            int index = selectedCompanies.indexOf(selectedCompany);
            String description = selectedCompanyEx.get(index);
            infoText.setText(description);
        } else {
            infoText.setText("");
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        client.setVisible(true);
    }
}
