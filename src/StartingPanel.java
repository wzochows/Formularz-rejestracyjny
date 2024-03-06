import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
    Borowy
    Żochowska
    Jęczmieniowski
 */

public class StartingPanel extends JPanel implements ActionListener {

    BoxLayout boxLay;
    CustomButton registerButton;
    JPanel upperPanel;
    JPanel lowerPanel;
    JPanel rightPanel;
    JPanel leftPanel;
    JPanel centralPanel;
    JTextArea welcomeText;
    JTextArea rodoText;
    JTextArea informationText;

    public StartingPanel() {
        Color lighter = new Color(248, 237, 228);
        setLayout(new BorderLayout());

        upperPanel  = new JPanel();
        lowerPanel = new JPanel();
        rightPanel = new JPanel();
        leftPanel = new JPanel();
        centralPanel = new JPanel();

        lowerPanel.setBackground(lighter);
        rightPanel.setBackground(lighter);
        leftPanel.setBackground(lighter);
        centralPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        centralPanel.setBackground(lighter);

        upperPanel.setPreferredSize(new Dimension(800,100));
        upperPanel.setBackground(Color.WHITE);
        upperPanel.add(Box.createVerticalStrut(100));
        lowerPanel.setPreferredSize(new Dimension(800,200));
        lowerPanel.add(Box.createVerticalStrut(100));
        rightPanel.setPreferredSize(new Dimension(65,700));
        leftPanel.setPreferredSize(new Dimension(65,700));
        centralPanel.setPreferredSize(new Dimension(670,500));

        boxLay = new BoxLayout(centralPanel,BoxLayout.Y_AXIS);
        centralPanel.setLayout(boxLay);
        centralPanel.add(Box.createVerticalStrut(30));
        centralPanel.setAlignmentX(CENTER_ALIGNMENT);

        add(leftPanel, BorderLayout.EAST);
        add(upperPanel, BorderLayout.NORTH);
        add(centralPanel, BorderLayout.CENTER);
        add(lowerPanel, BorderLayout.SOUTH);
        add(rightPanel, BorderLayout.WEST);

        welcomeText = new JTextArea();
        welcomeText.setText("Witaj!");
        welcomeText.setFont(new Font("Bauhaus 93",Font.BOLD,72));

        welcomeText.setEditable(false);
        welcomeText.setFocusable(false);
        welcomeText.setWrapStyleWord(true);
        welcomeText.setOpaque(false);

        informationText = new JTextArea();
        informationText.setAlignmentX(Component.CENTER_ALIGNMENT);

        informationText.setBackground(lighter);
        informationText.setText("Jeśli chcesz wypełnić formularz, wciśnij przycisk ZAREJESTRUJ SIĘ");
        informationText.setAlignmentX(CENTER_ALIGNMENT);
        informationText.setLineWrap(true);
        informationText.setWrapStyleWord(true);
        informationText.setEditable(false);
        informationText.setFocusable(false);
        informationText.setFont(new Font("Calibri",Font.BOLD,27));
        informationText.setOpaque(true);

        rodoText = new JTextArea();
        rodoText.setBackground(lighter);
        rodoText.setText("Wcisnięcie przycisku ZAREJESTRUJ SIĘ jest równoznaczne z wyrażeniem zgody na przetwarzanie moich danych osobowych dla potrzeb niezbędnych do realizacji procesu rekrutacji (zgodnie z ustawą z dnia 10 maja 2018 roku o ochronie danych osobowych (Dz. Ustaw z 2018, poz. 1000) oraz zgodnie z Rozporządzeniem Parlamentu Europejskiego i Rady (UE) 2016/679 z dnia 27 kwietnia 2016 r.");
        rodoText.setLineWrap(true);
        rodoText.setWrapStyleWord(true);
        rodoText.setEditable(false);
        rodoText.setFocusable(false);
        rodoText.setFont(new Font("Calibri",Font.BOLD,14));
        rodoText.setOpaque(true);

        upperPanel.add(welcomeText);
        centralPanel.add(informationText);
        centralPanel.add(rodoText);

        registerButton = new CustomButton("ZAREJESTRUJ SIĘ");
        registerButton.setFocusPainted(false);
        registerButton.addActionListener(this);
        registerButton.setMargin(new Insets(50,50,50,50));
        registerButton.setFont(new Font("SansSerif",Font.BOLD,30));
        lowerPanel.add(registerButton);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Container container = SwingUtilities.getAncestorOfClass(Container.class, this);
        if (container != null && container.getLayout() instanceof CardLayout) {
            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "formSheet");

        }
    }
}