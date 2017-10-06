package contacts;

import util.FileHandler;
import java.io.IOException;
import java.util.List;
import util.Input;

public class Contacts {
    static Input input = new Input();
    static FileHandler fh = new FileHandler("contacts", "contacts.txt");

    public static void main(String[] args) {
        try {
            fh.createDirectory();
            fh.createFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        do {
            printMenu();
            userOption();
        } while (input.yesNo("View main menu? (y/n)"));


    }

    public static void printMenu() {

        System.out.println("1. View Contacts");
        System.out.println("2. Add new contact");
        System.out.println("3. Search a contact by name");
        System.out.println("4. Delete an existing contact");
        System.out.println("5. Exit");
        System.out.println("Enter an option(1, 2, 3, 4, 5): ");
    }

    public static void userOption() {

        int userInput = input.getInt();

        switch (userInput) {
            case 1:
//                were iterating through our list(list is pulled from fh.retrievingContacts) and printing each individual list object
                showContacts();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                // WE NEED TO SPLIT THE CONTACT INTO A STRING ARRAY
                searchContact();
                break;
            case 4:
                deleteContact();
                break;
            case 5:
                System.out.println("Thank you, come again!");
                System.exit(0);
                break;
        }

    }

    public static void showContacts() {
        String leftAlignFormat = "| %-8s | %-16s|%n";
        System.out.format("+----------------------------+%n");
        System.out.format(leftAlignFormat, "Name", "Phone Number");
        System.out.format("+----------------------------+%n");
        for (String string : fh.retrievingContacts()) {
            String[] contact = string.split(",");
            System.out.format(leftAlignFormat, contact[0], formatContacts(contact[1]));
        }
        System.out.format("+----------------------------+%n");
    }


    public static void addNewContact() {
        do {
            System.out.println("Ok, please enter the name of your contact: ");
            String contactName = input.getString();
            checkContacts(contactName);
            System.out.println("Please enter the contact's number: ");
            String contactNumber = input.getString();
            int phoneNumber = contactNumber.trim().length();
            if (phoneNumber == 10 || phoneNumber == 7) {
                String contact = (contactName + ", " + contactNumber);
                fh.addContact(contact);
                fh.writingContacts();
                System.out.println("Contact has been added!");
            } else {
                System.out.println("Please enter a 7 or 10 digit phone number, k thaaanks :)");
                String newNumber = input.getString().trim();
                contactNumber = newNumber;
                fh.addContact(contactName + ", " + contactNumber);
                fh.writingContacts();
            }
        }
        while (input.yesNo("Do you want to add another contact? (y/n)"));
    }

    public static void searchContact() {
        do {
            System.out.println("Ok, what is the name of the contact you would like to see? ");
            String whichOne = input.getString();
            //FLAG VARIABLE, PURPOSE IS TO SHOW IF SOMETHING HAPPENED OR NOT
            boolean found = false;
            for (String string : fh.retrievingContacts()) {
                String[] contactParts = string.split(",");
                if (contactParts[0].equalsIgnoreCase(whichOne)) {
                    System.out.println(contactParts[0] + " | " + contactParts[1]);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("That name is not in your contacts!");
            }
        } while (input.yesNo("Do you want to search for another contact? (y/n)"));
    }

    public static void deleteContact() {
        do {
            showContacts();
            System.out.println("Please enter the name of the contact you would like to delete?");
            String delete = input.getString();
            List<String> contactFile = fh.retrievingContacts();
            for (int i = 0; i < contactFile.size(); i++) {
                String[] contactParts = contactFile.get(i).split(",");
                if (contactParts[0].equalsIgnoreCase(delete)) {
                    fh.remove(i);
                    fh.writingContacts();
                    System.out.println("Your contact list has been updated");

                } else {
                    System.out.println("whoopsie, contact not found");
                }
            }
        } while (input.yesNo("Would you like to delete another contact?"));
    }

    public static String formatContacts(String number) {
        int size = number.trim().length();
        if (size == 10) {
            String first = number.trim().substring(0, 3);
            String second = number.trim().substring(3, 6);
            String third = number.trim().substring(6, 10);
            String full = ("(" + first + ") " + second + "-" + third);
            return full;
        } else if (size == 7) {
            String first = number.trim().substring(0, 3);
            String second = number.trim().substring(3, 7);
            String full = (first + "-" + second);
            return full;
        } else {
            return "nope!";
        }
    }

    public static void checkContacts(String name){
        List<String> contacts = fh.retrievingContacts();
        for (int i = 0; i < contacts.size(); i++) {
            String[] contact = contacts.get(i).split(",");
            if(contact[0].equalsIgnoreCase(name)){
                System.out.println(("contact " + name + " already exists. "));
                if(input.yesNo("Would you like to overwrite? (y/n)" )){
                    fh.remove(i);
                }
            }
    }
}}


