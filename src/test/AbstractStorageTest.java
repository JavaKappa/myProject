
import org.junit.Before;
import org.junit.Test;
import ru.webapp.model.ContactType;
import ru.webapp.model.Resume;
import storage.IStorage;
import storage.WebAppException;

import java.util.*;

import static org.junit.Assert.assertEquals;

abstract public class AbstractStorageTest {
    private Resume R1, R2, R3;

    protected IStorage storage;


    @Before
    public void before() {
        R1 = new Resume("Полное Имя1", "location1");
        R1.addContact(ContactType.MAIL, "mail1@ya.ru");
        R1.addContact(ContactType.PHONE, "11111");
        R2 = new Resume("Полное Имя2", "Location1");
        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "22222");
        R3 = new Resume("Полное Имя3", "");

        storage.clear();
        storage.save(R3);
        storage.save(R1);
        storage.save(R2);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void uppdate(){
        R2.setFullName("Updated N2");
        storage.update(R2);
        assertEquals(R2, storage.load(R2.getUuid()));
    }

    @Test
    public void load() {
        Resume load = storage.load(R1.getUuid());
        assertEquals(R1, load);
        assertEquals(R2, storage.load(R2.getUuid()));
        assertEquals(R3, storage.load(R3.getUuid()));
    }

    @Test(expected = WebAppException.class)
    public void deleteNotFound() {
        storage.load("dummy");
    }

    @Test
    public void delete() {
        storage.delete(R1.getUuid());
        assertEquals(2, storage.size());
    }

    @Test
    public void getAllSorted() {
//        Resume[] src = new Resume[]{R1, R2, R3};
//        Arrays.sort(src);
//        assertArrayEquals(src, storage.getAllSorted().toArray());
        List<Resume> list = Arrays.asList(R1, R2, R3);
        list.sort((o1, o2) -> 0);
        assertEquals(list, new ArrayList<>(storage.getAllSorted()));
    }

    @Test
    public void size(){
        assertEquals(10, storage.size());
    }

    @Test(expected = WebAppException.class)
    public void deleteMissed()  {
        storage.delete("dummy");
    }

    @Test(expected = WebAppException.class)
    public void savePresented() {
        storage.save(R1);
    }

    @Test(expected = WebAppException.class)
    public void updateMissed()  {
        Resume resume = new Resume("dummy", "fullName_U1", "location_U1");
        storage.update(resume);
    }
}

