
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
    }

    @Test
    public void update() {
    }

    @Test
    public void load() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void clear() {
    }

    @Test
    public void getAllSorted() {
    }
    @Test
    public void size() {
        Assert.assertEquals( 3, storage.size());
    }
}