
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.webapp.model.Contact;
import ru.webapp.model.ContactType;
import ru.webapp.model.Resume;
import storage.ArrayStorage;


public class ArrayStorageTest {
    private Resume r1, r2, r3;

    private ArrayStorage storage = new ArrayStorage();
     {
        r1 = new Resume("Игорь", "Allworld");
        r1.addContact(new Contact(ContactType.PHONE_NUMBER, "+02"));
        r2 = new Resume("Ccfyz", "armeniya");
        r2.addContact(new Contact(ContactType.PHONE_NUMBER, "+04"));
        r2.addContact(new Contact(ContactType.EMAIL, "gmalru"));
        r2.addContact(new Contact(ContactType.PHONE_NUMBER, "+04"));
        r3 = new Resume("gogi", "gruziya");
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
        try {
            storage.save(r1);
            Assert.fail();
            storage.save(new Resume("22", ""));
            Assert.assertEquals(4, storage.size());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void update() {
//        Resume resume = new Resume(r2.getUuid(), "Александр", "3");
//        storage.update(resume);
//        Assert.assertEquals("Александр", storage.load(r2.getUuid()).getFullName());
        storage.update(r1);
        Assert.fail();
    }

    @Test
    public void load() {
        Resume r = storage.load(r1.getUuid());
        Assert.assertEquals(r1.getFullName(), r.getFullName());
        Assert.assertEquals(r1.getUuid(), r.getUuid());
        Assert.assertEquals(r1.getHomePage(), r.getHomePage());
    }

    @Test
    public void delete() {
         storage.delete(r1.getUuid());
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
    }
    @Test
    public void size() {
        Assert.assertEquals( 3, storage.size());
    }
}