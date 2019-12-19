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
        Resume resume = new Resume("Игорь", "Питер");
        Resume resume1 = new Resume("Игорь1", "Питер");
        Resume resume2 = new Resume("Игорь2", "Питер");
        Resume resume3 = new Resume("Игорь3", "Питер");
        FileStorage fileStorage = new FileStorage();
        fileStorage.clear();
        System.out.println(fileStorage.size());
        fileStorage.save(resume);
        fileStorage.save(resume3);
        System.out.println(fileStorage.size());
        fileStorage.save(resume2);
        fileStorage.save(resume1);
        System.out.println(fileStorage.size());
        fileStorage.delete(resume1.getUuid());
        List<Resume> list = (List<Resume>) fileStorage.getAllSorted();

        System.out.println(list);
        System.out.println(fileStorage.size());


    }
}
