import org.junit.Test;
import ru.webapp.model.ContactType;
import ru.webapp.model.Resume;
import ru.webapp.model.SectionType;
import ru.webapp.storage.SerializeFileStorage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SerializeFileTest extends AbstractStorageTest {
    {
        storage = new SerializeFileStorage("C:\\Users\\qwark\\IdeaProjects\\myProject\\file_storage");
    }


    @Test
    public void testSectionEquals() {
        R1.addObjective("3");
        storage.update(R1);

        Resume rr = storage.load(R1.getUuid());
        rr.addTextSectionWithTitle(SectionType.EXPERIENCE, "ach1","ach2");
        assertNotEquals(R1, rr);
    }
    @Test
    public void testSectionEqualsB() {
        R1.addObjective("3");
        storage.update(R1);
        Resume rr = storage.load(R1.getUuid());
        rr.addObjective("4");
        assertNotEquals(R1, rr);
    }
    @Test
    public void testContacts() {
        R1.addContact(ContactType.SKYPE, "skype");
        storage.update(R1);
        Resume rr = storage.load(R1.getUuid());
        assertEquals(R1, rr);
    }
    @Test
    public void testContactsA() {
        R1.addContact(ContactType.SKYPE, "skype");
        storage.update(R1);
        Resume rr = storage.load(R1.getUuid());
        rr.addContact(ContactType.SKYPE, "neSkype");
        assertNotEquals(R1, rr);
    }
    @Test
    public void testContactsB() {
        R1.addContact(ContactType.SKYPE, "skype");
        storage.update(R1);
        Resume rr = storage.load(R1.getUuid());
        rr.addContact(ContactType.PHONE, "nePhone");
        assertNotEquals(R1, rr);
    }
}
