import storage.FileStorage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

import static java.nio.file.Files.isDirectory;

public class Main {
    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader("D:/123/text.txt"));
        System.out.println(isDirectory(Paths.get(FileStorage.path)));

    }
}
