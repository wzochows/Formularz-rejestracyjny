import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
    Borowy
    Żochowska
    Jęczmieniowski
 */

public class CustomTextField extends JTextField {
    String inputText;

    CustomTextField(String text){
        super(text);
        this.inputText = text;

        setAlignmentX(0.5f);
        setMaximumSize(new Dimension(400,20));

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(getText().equals(text)){
                    setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(getText().isEmpty()){
                    setText(text);
                }
                switch (text){
                    case "Imię":
                        if (getText().matches("[A-ZŁŚŻa-ząćęłńóśźż]+")){
                            setText(getText().substring(0,1).toUpperCase() + getText().substring(1).toLowerCase());
                            setForeground(Color.BLACK);
                        }
                        else{
                            setForeground(Color.RED);
                        }
                        break;
                    case "Nazwisko":
                        if (getText().matches("[A-ZŁŚŻŹĆÓ\\-a-ząćęłńóśźż]+")){
                            doubleSurname();
                            setForeground(Color.BLACK);
                        }
                        else{
                            setForeground(Color.RED);
                        }
                        break;
                    case "PESEL":
                        if (getText().matches("\\d+") && getText().length()==11 && checkPESEL(getText())){
                            setForeground(Color.BLACK);
                        }
                        else {
                            setForeground(Color.RED);
                        }
                        break;
                    case "E-mail":
                        if(getText().matches(".+@(o2\\.pl|pw\\.edu\\.pl|wp\\.pl|yahoo\\.com|gmail\\.com)")){
                            setForeground(Color.BLACK);
                        }
                        else {
                            setForeground(Color.RED);
                        }
                        break;
                    case "Nr telefonu":
                        if (getText().matches("\\d+") && getText().length()==9){
                            setForeground(Color.BLACK);
                            phoneNumberCheck();
                        }
                        else {
                            setForeground(Color.RED);
                        }
                        break;
                    case "Data urodzenia":
                        if (isValidDate(getText())) {
                            setForeground(Color.BLACK);
                        } else {
                            setForeground(Color.RED);
                        }
                        break;
                }
            }
        });
    }

    private boolean isValidDate(String inputDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            Date parsedDate = sdf.parse(inputDate);

            if (parsedDate != null) {
                int year = parsedDate.getYear() + 1900;
                int currentYear = java.time.Year.now().getValue();
                return year >= 1900 && year <= currentYear-1;

            } else {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
    }

    boolean checkPESEL(String PESEL){
        int[] PESELnumbers = {1,3,7,9,1,3,7,9,1,3};
        int sum = 0;

        for(int i = 0; i < PESEL.length()-1; i++){
            sum += (PESELnumbers[i] * Character.getNumericValue(PESEL.charAt(i)));
        }

        if (Character.getNumericValue(PESEL.charAt(10))==(10-(sum % 10))){
            return true;
        }
        return false;
    }

    boolean hasCorrectInput(){
        if(getForeground()==Color.RED || getText().equals(inputText)){
            setForeground(Color.RED);
            return false;
        }
        setForeground(Color.BLACK);
        return true;
    }

    boolean isBirthMatchingPESEL(CustomTextField PESELComponent){
        String firstDigitOfYear = getText().substring(0,1);
        String PESEL = PESELComponent.getText();

        switch (firstDigitOfYear){
            case "1":
                if (getText().substring(2,4).equals(PESEL.substring(0,2)) &&
                        getText().substring(5,7).equals(PESEL.substring(2,4)) &&
                        getText().substring(8).equals(PESEL.substring(4,6))) {
                    return true;
                }
                break;
            case "2":
                int monthDigits = Integer.parseInt(getText().substring(5,7)) + 20;

                if (getText().substring(2,4).equals(PESEL.substring(0,2)) &&
                        String.valueOf(monthDigits).equals(PESEL.substring(2,4)) &&
                        getText().substring(8).equals(PESEL.substring(4,6))) {
                    return true;
                }
        }
        setForeground(Color.RED);
        PESELComponent.setForeground(Color.RED);
        JOptionPane.showMessageDialog(null, "Data urodzenia nie zgadza się z numerem PESEL!", "Rejestracja odrzucona", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    void doubleSurname(){
        String[] splittedString = getText().split("-");

        if (splittedString.length == 2){
            setText(Character.toUpperCase(splittedString[0].charAt(0)) + splittedString[0].substring(1) + "-" + Character.toUpperCase(splittedString[1].charAt(0)) + splittedString[1].substring(1));
        }
        else {
            setText(getText().substring(0,1).toUpperCase() + getText().substring(1).toLowerCase());
        }
    }

    void phoneNumberCheck(){
        if (getText().substring(0,1).equals("0")){
            setForeground(Color.RED);
            JOptionPane.showMessageDialog(null, "Podany numer telefonu nie istnieje!", "Rejestracja odrzucona", JOptionPane.ERROR_MESSAGE);
        }
    }
}
