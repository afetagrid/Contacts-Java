package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PersonContact extends Contact{
    private String name;
    private String surname;
    private LocalDate birthDate;
    private char gender;

    public PersonContact(String name, String surname, String phoneNumber, LocalDate dob, char gender) {
        super(phoneNumber);
        this.name = name;
        this.surname = surname;
        this.birthDate = dob;
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
        this.lastEditDate = LocalDateTime.now();
    }
    public void setSurname(String surname) {
        this.surname = surname;
        this.lastEditDate = LocalDateTime.now();
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        this.lastEditDate = LocalDateTime.now();
    }
    public void setGender(char gender) {
        this.gender = gender;
        this.lastEditDate = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public char getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Name: " + name + '\n' +
                "Surname: " + surname + '\n' +
                "Birth date: " + (birthDate == null ? "[no data]" : birthDate)  + '\n' +
                "Gender: " + (gender == ' ' ? "[no data]" : gender) + '\n' +
                "Number: " + phoneNumber + '\n' +
                "Time created: " + creationDate + '\n' +
                "Time last edit: " + lastEditDate;
    }
}
