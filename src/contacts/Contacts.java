package contacts;

import util.FileHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import util.Input;

public class Contacts {

    static FileHandler fh = new FileHandler("contacts", "contacts.txt");

    public static void main(String[] args) {
        try {
            fh.createDirectory();
            fh.createFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printMenu();
        userOption();

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
        Input input = new Input();
        int userInput = input.getInt();
//        scanner.nextLine();
//        System.out.println();

        switch (userInput) {
            case 1:
//                were iterating through our list(list is pulled from fh.retrievingContacts) and printing each individual list object
                try {
                    for (String string : fh.retrievingContacts()) {
                        System.out.println(string);
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                break;
            case 2:
                System.out.println("Ok, please enter the name of your contact: ");
                String contactName = input.getString();
                System.out.println("Please enter the contact's number: ");
                String contactNumber = input.getString();

                String contact = (contactName + ", " + contactNumber);
                List<String> addContact = Arrays.asList(contact);
                try {
                    fh.writingContacts(addContact);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Contact has been added!");
                break;
            case 3:
                // WE NEED TO SPLIT THE CONTACT INTO A STRING ARRAY

                System.out.println("Search a contact by name");
                System.out.println("Ok, what is the name of the contact you would like to see? ");
                String whichOne = input.getString();
                try {
                    for (String string : fh.retrievingContacts()) {
                        String[] contactParts = string.split(",");
                        if (contactParts[0].equalsIgnoreCase(whichOne)) {
                            System.out.println(contactParts[0] + " | " + contactParts[1]);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                System.out.println("Delete an existing contact");
                break;
            case 5:
                System.out.println("Exit");
                break;
        }

    }


}
