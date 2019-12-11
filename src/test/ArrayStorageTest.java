
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.webapp.model.Contact;
import ru.webapp.model.ContactType;
import ru.webapp.model.Resume;
import storage.AbstractStorage;
import storage.ArrayStorage;
import storage.CollectionStorage;
import storage.WebAppException;

import java.util.ArrayList;
import java.util.Comparator;


public class ArrayStorageTest {
    private Resume r1, r2, r3,r4, r5, r6, r7, r8, r9;

//    private ArrayStorage storage = new ArrayStorage();
    private AbstractStorage storage = new CollectionStorage();

    {
        r1 = new Resume("Игорь", "Allworld");
        r1.addContact(new Contact(ContactType.PHONE_NUMBER, "+02"));
        r2 = new Resume("Ccfyz", "armeniya");
        r2.addContact(new Contact(ContactType.PHONE_NUMBER, "+04"));
        r2.addContact(new Contact(ContactType.EMAIL, "gmalru"));
        r2.addContact(new Contact(ContactType.PHONE_NUMBER, "+04"));
        r3 = new Resume("gogi", "gruziya");
        r4 = new Resume("gogi", "gruziya");
        r5 = new Resume("gogi", "gruziya");
        r6 = new Resume("gogi", "gruziya");
        r7 = new Resume("gogi", "gruziya");
        r8 = new Resume("gogi", "gruziya");
        r9 = new Resume("gogi", "gruziya");
        r3.addContact(new Contact(ContactType.PHONE_NUMBER, "+00"));
        r3.addContact(new Contact(ContactType.SKYPE, "+skype00"));
    }

    @Before
    public void before() throws Exception {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);

    }

    @Test
    public void load() {
       Assert.assertEquals(r1, storage.load(r1.getUuid()));
       Assert.assertEquals(r2, storage.load(r2.getUuid()));
       Assert.assertEquals(r3, storage.load(r3.getUuid()));
    }

    @Test
    public void delete() throws WebAppException {
        storage.delete(r1.getUuid());
        System.out.println(storage.size());
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void clear() {

        Assert.assertEquals(3, storage.size());
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        r2.setFullName("Updated");
        storage.update(r2);
        Assert.assertEquals(r2, storage.load(r2.getUuid()));
    }

    @Test(expected = WebAppException.class)
    public void testLoadNotExistFile() {
        storage.load("uuid");

    }
    @Test(expected = WebAppException.class)
    public void testUdpateNotExistFile() {
        storage.update(new Resume("1113", "3"));
    }

    @Test(expected = WebAppException.class)
    public void testDeleteNotExistFile() {
        storage.delete("1113");
    }
    @Test(expected = WebAppException.class)
    public void testSaveAlreadyInUse() {
        storage.save(r1);
    }
    @Test
    public void getAllSorted() {
        ArrayList<Resume> arrayList = new ArrayList<>();
        arrayList.add(r1);
        arrayList.add(r2);
        arrayList.add(r3);
        arrayList.sort(Comparator.comparing(Resume::getFullName));
        Assert.assertEquals(arrayList, storage.getAllSorted());
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
        storage.save(r4);
        storage.save(r5);
        storage.save(r6);
        storage.save(r7);
        Assert.assertEquals(7, storage.size());
        storage.delete(r5.getUuid());
        storage.delete(r1.getUuid());
        Assert.assertEquals(5, storage.size());
    }
}