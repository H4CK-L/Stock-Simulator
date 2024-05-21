import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Client {
    private Connect connect;
    public Client(){
        connecting();
    }

    private void connecting(){
        connect = new Connect();
        connect.setVisible(true);
        connect.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창이 닫힐 때 자원을 해제하도록 설정
        connect.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Connect 창이 닫힐 때 호출될 메소드
                showSetNickname();
            }
        });
    }

    private void showSetNickname(){
        SetNickname setNickname = new SetNickname();
        setNickname.setVisible(true);
    }
}
