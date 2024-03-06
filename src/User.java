/*
    Borowy
    Żochowska
    Jęczmieniowski
 */

public class User {

    private final String name;
    private final String surname;
    private final String PESEL;
    private final String gender;
    private final String birthDate;
    private final String email;
    private final int phoneNumber;

    User(String name, String surname, String PESEL, String birthDate, String email, int phoneNumber, String gender){
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPESEL() {
        return PESEL;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
}
