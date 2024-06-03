public class UIManager {
    private Shop shop;
    private Client client;
    private SelectOrder selectOrder;
    private CorporateInfo corporateInfo;
    private PartTime partTime;

    public UIManager(Client client) {
        this.client = client;
        shop = new Shop(client);
        shop.setVisible(false);
        selectOrder = new SelectOrder(client);
        selectOrder.setVisible(false);
        corporateInfo = new CorporateInfo(client);
        corporateInfo.setVisible(false);
        partTime = new PartTime(client);
        partTime.setVisible(false);
    }

    public void viewShop(){
        client.setVisible(false);
        shop.setVisible(true);
    }
    public void viewOrder(){
        client.setVisible(false);
        selectOrder.setVisible(true);
    }
    public void viewCorporateInfo(){
        client.setVisible(false);
        corporateInfo.setVisible(true);
    }
    public void viewPartTime(){
        client.setVisible(false);
        partTime.setVisible(true);
    }
}