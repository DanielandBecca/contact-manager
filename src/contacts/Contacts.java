package contacts;

import util.FileHandler;

import java.io.IOException;
import java.util.Scanner;

public class Contacts {

    public static void main(String[] args) {
        FileHandler fh = new FileHandler("contacts", "contacts.txt");
        try {
            fh.createDirectory();
            fh.createFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printMenu();
    }

    public static void printMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. View Contacts");
        System.out.println("2. Add new contact");
        System.out.println("3. Search a contact by name");
        System.out.println("4. Delete an existing contact");
        System.out.println("5. Exit");
        System.out.println("Enter an option(1, 2, 3, 4, 5): ");
        String userInput = scanner.nextLine();
        System.out.println(userInput);
    }
}
