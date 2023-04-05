package contacts;

import java.time.LocalDateTime;

public abstract class Contact {
    protected String phoneNumber;
    protected LocalDateTime creationDate;
    protected LocalDateTime lastEditDate;

    public Contact(String phoneNumber) {
        this.phoneNumber = checkPhoneNumber(phoneNumber) ? phoneNumber : "[no number]";
        this.creationDate = LocalDateTime.now();
        this.lastEditDate = LocalDateTime.now();
    }

    private boolean checkPhoneNumber(String phoneNumber) {
        String regex = "[+]?([(][0-9a-zA-Z]+[)]|[0-9a-zA-Z]+[ -][(][0-9a-zA-Z]{2,}[)]|[0-9a-zA-Z]+)([ -][0-9a-zA-Z]{2,})*";
        return phoneNumber.matches(regex);
    }

    public boolean hasNumber() {
        return phoneNumber == null || phoneNumber.equals("[no number]");
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = checkPhoneNumber(phoneNumber) ? phoneNumber : "[no number]";
        this.lastEditDate = LocalDateTime.now();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
