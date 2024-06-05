import javax.swing.*;

public class UIManager {
    private Shop shop;
    private Client client;
    private SelectOrder selectOrder;
    private CorporateInfo corporateInfo;
    private PartTime partTime;
    private Connect connect;
    private Assets assets;
    private SetNickname setNickname;
    private User user;

    public UIManager(Client client) {
        this.client = client;
        connect = new Connect();
        SwingUtilities.invokeLater(() -> {
            connect.setLocationRelativeTo(null);
            connect.setVisible(true);
        });
        shop = new Shop(client);
        shop.setVisible(false);
        selectOrder = new SelectOrder(client);
        selectOrder.setVisible(false);
        corporateInfo = new CorporateInfo(client);
        corporateInfo.setVisible(false);
        partTime = new PartTime(client);
        partTime.setVisible(false);
    }

    public Connect getConnect(){
        return connect;
    }
    public void viewShop(){
        client.setVisible(false);
        SwingUtilities.invokeLater(() -> {
            shop.setLocationRelativeTo(null);
            shop.setVisible(true);
        });
    }
    public void viewOrder(){
        client.setVisible(false);
        SwingUtilities.invokeLater(() -> {
            selectOrder.setLocationRelativeTo(null);
            selectOrder.setVisible(true);
        });
    }
    public void viewCorporateInfo(){
        client.setVisible(false);
        SwingUtilities.invokeLater(() -> {
            corporateInfo.setLocationRelativeTo(null);
            corporateInfo.setVisible(true);
        });
    }
    public void viewPartTime(){
        client.setVisible(false);
        partTime.setUser(assets.getUser());
        SwingUtilities.invokeLater(() -> {
            partTime.setLocationRelativeTo(null);
            partTime.setVisible(true);
        });
    }

    public void setAssets(Assets assets){
        this.assets = assets;
    }

    public void viewAssets(){
        SwingUtilities.invokeLater(() -> {
            assets.setLocationRelativeTo(null);
            assets.setVisible(true);
        });
    }

    public void viewSetNickname(){
        setNickname = new SetNickname();
        SwingUtilities.invokeLater(() -> {
            setNickname.setLocationRelativeTo(null);
            setNickname.setVisible(true);
        });
    }
}