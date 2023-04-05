package contacts;

import java.time.LocalDateTime;

public class OrganizationContact extends Contact{
    private String organizationName;
    private String address;

    public OrganizationContact(String organizationName, String address, String phoneNumber) {
        super(phoneNumber);
        this.organizationName = organizationName;
        this.address = address;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
        this.lastEditDate = LocalDateTime.now();
    }
    public void setAddress(String address) {
        this.address = address;
        this.lastEditDate = LocalDateTime.now();
    }

    public String getOrganizationName() {
        return organizationName;
    }
    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Organization name: " + organizationName + '\n' +
                "Address: " + address + '\n' +
                "Number: " + phoneNumber + '\n' +
                "Time created: " + creationDate + '\n' +
                "Time last edit: " + lastEditDate;
    }
}
