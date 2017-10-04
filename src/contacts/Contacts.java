package contacts;

import util.FileHandler;

import java.io.IOException;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        int userInput = scanner.nextInt();

        switch (userInput) {
            case 1:
//                were iterating through our list(list is pulled from fh.retrievingContacts) and printing each individual list object
                try {
                    for (String string:fh.retrievingContacts())
                    {
                        System.out.println(string);
                    }
                } catch (IOException ioe){
                    ioe.printStackTrace();
                }
                break;
            case 2:
                System.out.println("Add new contact");
                break;
            case 3:
                System.out.println("Search a contact by name");
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
