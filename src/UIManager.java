import javax.swing.*;
import java.util.List;

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
    private Ranking ranking;
    private List<Sector> sectors;

    public UIManager(Client client, List<Sector> sectors) {
        this.client = client;
        this.sectors = sectors;
        connect = new Connect();
        SwingUtilities.invokeLater(() -> {
            connect.setLocationRelativeTo(null);
            connect.setVisible(true);
        });
        shop = new Shop(client);
        shop.setVisible(false);
        selectOrder = new SelectOrder(client, sectors);
        selectOrder.setVisible(false);
        corporateInfo = new CorporateInfo(client, sectors);
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

    public void setShop(User user){
        shop.setUser(user);
    }
    public void viewOrder(){
        client.setVisible(false);
        SwingUtilities.invokeLater(() -> {
            selectOrder.setLocationRelativeTo(null);
            selectOrder.setVisible(true);
        });
    }

    public void setOrderUser(User user){
        this.user = user; // Set the user field in UIManager
        selectOrder.setUser(user);
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

    public void setRanking(Ranking ranking){
        this.ranking = ranking;
    }

    public void viewRanking(){
        SwingUtilities.invokeLater(() -> {
            ranking.setLocationRelativeTo(null);
            ranking.setVisible(true);
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
