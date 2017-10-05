package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private String directory;
    private String filename;
    private List<String> contents;
    private List<String> newList;

   public FileHandler (String directory, String filename) {
       this.directory = directory;
       this.filename = filename;
       this.contents = retrievingContacts(); //new ArrayList<>();
   }

    public void createFile() throws IOException {
        Path files = Paths.get(this.directory, this.filename);
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

    public List<String> retrievingContacts() {
        Path path = Paths.get(this.directory, this.filename);
        try {
            newList = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.contents = newList;
        return newList;
    }

    public void writingContacts() {
        Path path = Paths.get(this.directory, this.filename);
        try {
            Files.write(path, contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remove(int index) {
        contents.remove(index);
    }

    public void addContact(String contact) {
        contents.add(contact);
    }
}



