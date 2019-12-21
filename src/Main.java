import storage.SerializeFileStorage;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File(SerializeFileStorage.pathToFiles + "/123.txt");
        System.out.println(file.getAbsolutePath());
        file.createNewFile();

    }
}
