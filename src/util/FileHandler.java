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

   public FileHandler (String directory, String filename) {
       this.directory = directory;
       this.filename = filename;
       this.contents = new ArrayList<>();
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

    public List<String> retrievingContacts() throws IOException {
        Path path = Paths.get(this.directory, this.filename);
        this.contents = Files.readAllLines(path);
        return contents;
    }

    public void writingContacts() throws IOException {
        Path path = Paths.get(this.directory, this.filename);
        Files.write(path, this.contents, StandardOpenOption.APPEND);
    }

}



