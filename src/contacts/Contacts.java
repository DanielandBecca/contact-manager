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
                showContacts();
                break;
            case 2:
                System.out.println("Ok, please enter the name of your contact: ");
                String contactName = input.getString();
                System.out.println("Please enter the contact's number: ");
                String contactNumber = input.getString();

                String contact = (contactName + ", " + contactNumber);
                fh.addContact(contact);
                //List<String> addContact = Arrays.asList(contact);
                try {
                    fh.writingContacts();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Contact has been added!");
                break;
            case 3:
                // WE NEED TO SPLIT THE CONTACT INTO A STRING ARRAY

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
                break;
            case 4:
                showContacts();
                System.out.println("Which one of your contacts would you like to delete?");
                String delete = input.getString();
                try {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 5:
                System.out.println("Exit");
                break;
        }

    }

    public static void showContacts () {
        for (String string : fh.retrievingContacts()) {
            System.out.println(string);
        }
    }


}
