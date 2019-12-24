package TPE.Timbiriche.View;

import javax.swing.*;
import java.awt.*;

public class Launcher extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HomeView hv = new HomeView();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


}