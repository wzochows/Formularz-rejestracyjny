import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/*
    Borowy
    Żochowska
    Jęczmieniowski
 */

public class CustomFrame extends JFrame {
    CardLayout cards;
    StartingPanel mainPage;
    FormPanel formPage;

    CustomFrame() throws IOException {
        setSize(800,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("FORMULARZ");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setIconImage(new ImageIcon("resources\\logo.png").getImage());
        cards = new CardLayout();
        setLayout(cards);

        mainPage = new StartingPanel();
        formPage = new FormPanel();

        add(mainPage, "mainPage");
        add(formPage, "formSheet");
    }


}
