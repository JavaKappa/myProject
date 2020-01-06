import ru.webapp.model.Resume;
import ru.webapp.storage.ArrayStorage;
import ru.webapp.storage.IStorage;

import java.util.Collection;


public class Main {
    public static void main(String[] args) {
        IStorage storage = new ArrayStorage();
        storage.save(new Resume("Ivan", "moscow"));
        storage.save(new Resume("Igor", "simf"));
        storage.save(new Resume("Leva", "Gruzovik"));
        storage.save(new Resume());
        Collection<Resume> collection = storage.getAllSorted();
        System.out.println(collection);


    }
}
