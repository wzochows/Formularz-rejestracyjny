import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
    Borowy
    Żochowska
    Jęczmieniowski
 */

public class FormPanel extends JPanel implements ActionListener {

    BoxLayout boxLay;
    CustomButton submitButton;
    JPanel centralPanel;
    JPanel leftPanel;
    JPanel rightPanel;
    JPanel bottomPanel;
    JPanel textPanel;
    JLabel textLabel;
    JComboBox genderList;
    CustomTextField name;
    CustomTextField surname;
    CustomTextField PESEL;
    CustomTextField birthDate;
    CustomTextField email;
    CustomTextField phoneNumber;
    String[] genders = {"Mężczyzna", "Kobieta", "Wolę nie podawać"};
    JSONFileWriter jsonFileWriter = new JSONFileWriter();
    CustomButton cancelButton;
    Color darker = new Color(241, 202, 169);

    FormPanel() {
        setLayout(new BorderLayout());
        Color lighter = new Color(248, 237, 228);

        centralPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();

        bottomPanel.setBackground(darker);
        centralPanel.setBackground(lighter);
        leftPanel.setBackground(Color.WHITE);
        rightPanel.setBackground(Color.WHITE);

        centralPanel.setPreferredSize(new Dimension(600,600));
        leftPanel.setPreferredSize(new Dimension(100,600));
        rightPanel.setPreferredSize(new Dimension(100,600));
        bottomPanel.setPreferredSize(new Dimension(800,60));
        bottomPanel.add(Box.createVerticalStrut(50));

        add(centralPanel, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        boxLay = new BoxLayout(centralPanel, BoxLayout.Y_AXIS);
        centralPanel.setLayout(boxLay);

        centralPanel.add(Box.createVerticalStrut(20));
        name = createText("Imię",centralPanel);
        surname = createText("Nazwisko",centralPanel);
        PESEL = createText("PESEL",centralPanel);

        textPanel = new JPanel();
        textLabel = new JLabel("Płeć");
        textPanel.setAlignmentX(0.5f);
        textPanel.setMaximumSize(new Dimension(400,20));
        centralPanel.add(Box.createVerticalStrut(5));
        textPanel.setBackground(darker);
        textPanel.setOpaque(true);
        centralPanel.add(textPanel);
        textPanel.add(textLabel);

        genderList = new JComboBox(genders);
        genderList.setMaximumSize(new Dimension(400,20));
        centralPanel.add(genderList);

        centralPanel.add(Box.createVerticalStrut(20));
        birthDate = createText("Data urodzenia",centralPanel);
        email = createText("E-mail",centralPanel);
        phoneNumber = createText("Nr telefonu",centralPanel);

        submitButton = new CustomButton("ZATWIERDŹ");
        submitButton.setAlignmentX(0.5f);
        submitButton.setFocusPainted(false);
        bottomPanel.add(submitButton);
        bottomPanel.add(Box.createHorizontalStrut(40));
        submitButton.addActionListener(this);

        cancelButton = new CustomButton("ANULUJ");
        cancelButton.setFocusPainted(false);
        bottomPanel.add(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showOptionDialog(null,
                        "Anulowanie rejestracji wiąże się z utratą wprowadzonych danych. Czy na pewno chcesz przerwać?",
                        "Anulowanie rejestracji",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null, new String[]{"TAK", "NIE"}, null);

                if (choice == JOptionPane.YES_OPTION) {
                    Container container = SwingUtilities.getAncestorOfClass(Container.class, FormPanel.this);
                    if (container != null && container.getLayout() instanceof CardLayout) {
                        CardLayout cardLayout = (CardLayout) container.getLayout();
                        cardLayout.show(container, "mainPage");
                    }
                    clearForm();
                }
            }
        });
    }

    private CustomTextField createText(String text, JPanel jPanel){
        CustomTextField textField = new CustomTextField(text);
        JPanel textPanel = new JPanel();
        JLabel textLabel = new JLabel(text);
        if(text.equals("Data urodzenia")){
            textLabel.setText(text+" (rrrr-mm-dd)");
        }
        textPanel.setAlignmentX(0.5f);
        textPanel.setMaximumSize(new Dimension(400,20));
        jPanel.add(Box.createVerticalStrut(5));
        textPanel.setBackground(darker);
        textPanel.setOpaque(true);

        jPanel.add(textPanel);
        textPanel.add(textLabel);
        jPanel.add(textField);
        jPanel.add(Box.createVerticalStrut(20));
        return textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Container container = SwingUtilities.getAncestorOfClass(Container.class, this);

        if (birthDate.isBirthMatchingPESEL(PESEL) &&
                container != null && container.getLayout() instanceof CardLayout &&
                name.hasCorrectInput() && surname.hasCorrectInput() && PESEL.hasCorrectInput() &&
                birthDate.hasCorrectInput() && email.hasCorrectInput() && phoneNumber.hasCorrectInput()){
            JOptionPane.showMessageDialog(null, "Pomyślnie zarejestrowano!", "Rejestracja zatwierdzona", JOptionPane.INFORMATION_MESSAGE);
            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "mainPage");
            User user = new User(name.getText(), surname.getText(), PESEL.getText(), birthDate.getText(), email.getText(), Integer.parseInt(phoneNumber.getText()), genderList.getSelectedItem().toString());
            jsonFileWriter.appendToJson(user);
            clearForm();
        }

        else {
            JOptionPane.showMessageDialog(null, "Istnieją pola, które zostały błędnie uzupełnione!", "Rejestracja odrzucona", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm(){
        name.setText("Imię");
        name.setForeground(Color.BLACK);
        surname.setText("Nazwisko");
        surname.setForeground(Color.BLACK);
        PESEL.setText("PESEL");
        PESEL.setForeground(Color.BLACK);
        birthDate.setText("Data urodzenia");
        birthDate.setForeground(Color.BLACK);
        email.setText("E-mail");
        email.setForeground(Color.BLACK);
        phoneNumber.setText("Nr telefonu");
        phoneNumber.setForeground(Color.BLACK);
    }
}
