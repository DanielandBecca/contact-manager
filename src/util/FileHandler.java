package util;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    private String directory;
    private String filename;

    public static void main(String[] args) {
        printMenu();
        grabContents();




    }
    public void createFile() throws IOException {
        Path files = Paths.get(filename);
        if (!Files.exists(files)) {
            Files.createFile(files);
        }
    }

    public void createDirectory() throws IOException {
        Path directories = Paths.get(directory);
        if (!Files.exists(directories)) {
            Files.createDirectory(directories);
        }
    }

    public static void grabContents() throws IOException {
        List<String> contents = new ArrayList<>();
        Path path = Paths.get("contacts", "contacts.txt");
        Files.write(path, contents, StandardOpenOption.APPEND);
    }

    public static void printMenu(){
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



