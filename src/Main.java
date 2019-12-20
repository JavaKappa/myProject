import ru.webapp.model.ContactType;
import ru.webapp.model.Resume;
import ru.webapp.model.SectionType;
import storage.FileStorage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Files.isDirectory;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File(FileStorage.pathToFiles + "/123.txt");
        System.out.println(file.getAbsolutePath());
        file.createNewFile();

    }
}
