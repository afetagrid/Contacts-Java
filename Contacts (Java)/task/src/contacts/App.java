package contacts;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Contact> contacts = new ArrayList<>();


    private void countContacts() {
        System.out.println("The Phone Book has " + contacts.size() + " records.");
    }
    private void addContact() {
        System.out.print("Enter the type (person, organization): > ");
        String type = scanner.nextLine();
        if (type.equals("person")) {
            System.out.print("Enter the name: > ");
            String name = scanner.nextLine();
            System.out.print("Enter the surname: > ");
            String surname = scanner.nextLine();
            System.out.print("Enter the birth date: > ");
            LocalDate bod = null;
            try {
                bod = LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Bad birth date!");
            }
            System.out.print("Enter the gender (M, F): > ");
            char gender = ' ';
            String genderInput = scanner.nextLine();
            if (genderInput.equals("M")) {
                gender = 'M';
            } else if (genderInput.equals("F")) {
                gender = 'F';
            } else {
                System.out.println("Bad gender!");
            }
            System.out.print("Enter the number: > ");
            String phoneNumber = scanner.nextLine();
            contacts.add(new PersonContact(name, surname, phoneNumber, bod, gender));
            if (contacts.get(contacts.size() - 1).hasNumber()) {
                System.out.println("Wrong number format!");
            }
            System.out.println("The record added");
        } else if (type.equals("organization")) {
            System.out.print("Enter the organization name: > ");
            String organizationName = scanner.nextLine();
            System.out.print("Enter the address: > ");
            String address = scanner.nextLine();
            System.out.print("Enter the number: > ");
            String phoneNumber = scanner.nextLine();
            contacts.add(new OrganizationContact(organizationName, address, phoneNumber));
            if (contacts.get(contacts.size() - 1).hasNumber()) {
                System.out.println("Wrong number format!");
            }
            System.out.println("The record added");
        }
    }
    private void listContact() {
        while (true) {
            if (contacts.size() == 0) {
                System.out.println("No records to list!");
                return;
            }
            for (int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i) instanceof PersonContact personContact) {
                    System.out.println((i + 1) + ". " + personContact.getName() + " " + personContact.getSurname());
                } else if (contacts.get(i) instanceof OrganizationContact) {
                    System.out.println((i + 1) + ". " + ((OrganizationContact) contacts.get(i)).getOrganizationName());
                }
            }
            System.out.print("\n[list] Enter action ([number], back): > ");
            String action = scanner.nextLine();
            if (action.equals("back")) {
                break;
            } else if (action.matches("[0-9]+")) {
                int index = Integer.parseInt(action) - 1;
                if (index >= 0 && index < contacts.size()) {
                    infoContact(index);
                    break;
                }
            }
        }
    }
    private void searchContact() {
        while (true) {
            if (contacts.size() == 0) {
                System.out.println("No records to list!");
                return;
            }
            System.out.print("Enter search query: > ");
            String query = scanner.nextLine();
            List<Integer> foundContactsIndexes = new ArrayList<>();
            for (int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i) instanceof PersonContact personContact) {
                    String fields = personContact.getName().toLowerCase() + ' ' +
                            personContact.getSurname().toLowerCase() + ' ' +
                            personContact.getBirthDate() + ' ' +
                            personContact.getGender() + ' ' +
                            personContact.getPhoneNumber();
                    if (fields.contains(query)) {
                        foundContactsIndexes.add(i);
                    }
                } else if (contacts.get(i) instanceof OrganizationContact organizationContact) {
                    String fields = organizationContact.getOrganizationName().toLowerCase() + ' ' +
                            organizationContact.getAddress().toLowerCase() + ' ' +
                            organizationContact.getPhoneNumber();
                    if (fields.contains(query)) {
                        foundContactsIndexes.add(i);
                    }
                }
            }
            System.out.println("Found " + foundContactsIndexes.size() + " results");
            for (int i = 0; i < foundContactsIndexes.size(); i++) {
                if (contacts.get(foundContactsIndexes.get(i)) instanceof PersonContact personContact) {
                    System.out.println((i + 1) + ". " + personContact.getName() + " " + personContact.getSurname());
                } else if (contacts.get(foundContactsIndexes.get(i)) instanceof OrganizationContact organizationContact) {
                    System.out.println((i + 1) + ". " + organizationContact.getOrganizationName());
                }
            }
            System.out.print("\n[search] Enter action ([number], back, again): > ");
            String action = scanner.nextLine();
            if (action.equals("back")) {
                break;
            } else if (action.equals("again")) {
                continue;
            } else if (action.matches("[0-9]+")) {
                int index = foundContactsIndexes.get(Integer.parseInt(action) - 1);
                if (index >= 0 && index < contacts.size()) {
                    infoContact(index);
                    break;
                }
            }
        }
    }
    private void removeContact(int index) {
        contacts.remove(index);
        System.out.println("The record was deleted");
    }
    private void editContact(int index) {
        if (contacts.get(index) instanceof PersonContact) {
            System.out.print("Select a field (name, surname, birth, gender, number): > ");
            String field = scanner.nextLine();
            if (field.equals("name")) {
                System.out.print("Enter name: > ");
                String name = scanner.nextLine();
                ((PersonContact) contacts.get(index)).setName(name);
                System.out.println("The record updated!");
            } else if (field.equals("surname")) {
                System.out.print("Enter surname: > ");
                String surname = scanner.nextLine();
                ((PersonContact) contacts.get(index)).setSurname(surname);
                System.out.println("The record updated!");
            } else if (field.equals("birth")) {
                System.out.print("Enter the birth date: > ");
                LocalDate bod = null;
                try {
                    bod = LocalDate.parse(scanner.nextLine());
                } catch (DateTimeParseException e) {
                    System.out.println("Bad birth date!");
                }
                ((PersonContact) contacts.get(index)).setBirthDate(bod);
                System.out.println("The record updated!");
            } else if (field.equals("gender")) {
                System.out.print("Enter the gender (M, F): > ");
                char gender = ' ';
                String genderInput = scanner.nextLine();
                if (genderInput.equals("M")) {
                    gender = 'M';
                } else if (genderInput.equals("F")) {
                    gender = 'F';
                } else {
                    System.out.println("Bad gender!");
                }
                ((PersonContact) contacts.get(index)).setGender(gender);
                System.out.println("The record updated!");
            } else if (field.equals("number")) {
                System.out.print("Enter number: > ");
                String phoneNumber = scanner.nextLine();
                contacts.get(index).setPhoneNumber(phoneNumber);
                System.out.println("The record updated!");
            }
        } else if (contacts.get(index) instanceof OrganizationContact) {
            System.out.print("Select a field (name, address, number): > ");
            String field = scanner.nextLine();
            if (field.equals("name")) {
                System.out.print("Enter organization name: > ");
                String name = scanner.nextLine();
                ((OrganizationContact) contacts.get(index)).setOrganizationName(name);
                System.out.println("The record updated!");
            } else if (field.equals("address")) {
                System.out.print("Enter address: > ");
                String address = scanner.nextLine();
                ((OrganizationContact) contacts.get(index)).setAddress(address);
                System.out.println("The record updated!");
            } else if (field.equals("number")) {
                System.out.print("Enter number: > ");
                String phoneNumber = scanner.nextLine();
                contacts.get(index).setPhoneNumber(phoneNumber);
                System.out.println("The record updated!");
            }
        }

    }
    private void infoContact(int index) {
        loop: while (true) {
            System.out.println(contacts.get(index).toString());
            System.out.print("\n[record] Enter action (edit, delete, menu): > ");
            String action = scanner.nextLine();
            switch (action) {
                case "edit":
                    editContact(index);
                    break;
                case "delete":
                    removeContact(index);
                    break loop;
                case "menu":
                    break loop;
                default:
                    System.out.println("Undefined command");
            }
        }
    }

    public void menu() {
        loop: while (true) {
            System.out.print("\n[menu] Enter action (add, list, search, count, exit): > ");
            String command = scanner.nextLine();
            switch (command) {
                case "add":
                    addContact();
                    break;
                case "count":
                    countContacts();
                    break;
                case "list":
                    listContact();
                    break;
                case "search":
                    searchContact();
                    break;
                case "exit":
                    break loop;
                default:
                    System.out.println("Undefined command");
            }
        }
    }
}
