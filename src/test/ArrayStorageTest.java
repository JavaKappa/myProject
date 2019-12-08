
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.webapp.model.Contact;
import ru.webapp.model.ContactType;
import ru.webapp.model.Resume;
import storage.ArrayStorage;
import storage.WebAppException;

import java.util.ArrayList;
import java.util.Comparator;


public class ArrayStorageTest {
    private Resume r1, r2, r3,r4, r5, r6, r7, r8, r9;

    private ArrayStorage storage = new ArrayStorage();

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
    public void save() {
            storage.save(new Resume("22", ""));
            Assert.assertEquals(4, storage.size());
    }

    @Test
    public void update() throws WebAppException {
        Resume resume = new Resume(r2.getUuid(), "Александр", "3");
        storage.update(resume);
        Assert.assertEquals("Александр", storage.load(r2.getUuid()).getFullName());
        try {
            storage.update(r1);
        } catch (Exception e) {

        }
    }

    @Test
    public void load() {
        Resume r = storage.load(r1.getUuid());
        Assert.assertEquals(r1.getFullName(), r.getFullName());
        Assert.assertEquals(r1.getUuid(), r.getUuid());
        Assert.assertEquals(r1.getHomePage(), r.getHomePage());
    }

    @Test
    public void delete() throws WebAppException {
        storage.delete(r1.getUuid());
        System.out.println(storage.size());
        Assert.assertEquals(2, storage.size());
        Assert.assertNull(storage.load(r1.getUuid()));
    }

    @Test
    public void clear() {

        Assert.assertEquals(3, storage.size());
        storage.clear();
        Assert.assertEquals(0, storage.size());
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