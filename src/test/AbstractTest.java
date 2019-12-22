
import org.junit.Before;
import org.junit.Test;
import ru.webapp.model.*;
import storage.IStorage;
import storage.WebAppException;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

abstract public class AbstractTest {
    protected Resume R1, R2, R3;

    protected IStorage storage;


    @Before
    public void before() {
        R1 = new Resume("Полное Имя1", "location1");
        R1.addContact(ContactType.MAIL, "mail1@ya.ru");
        R1.addContact(ContactType.PHONE, "11111");
        R1.addSection(SectionType.ACHIEVEMENT, new TextSection(SectionType.ACHIEVEMENT,"javarush", "basejava", "topjava"));
        R1.addContact(ContactType.PHONE, "11111");
        R2 = new Resume("Полное Имя2", "Location1");
        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "22222");
        R3 = new Resume("Полное Имя3", "");
        R3.addSection(SectionType.OBJECTIVE, new TextSection(SectionType.OBJECTIVE, "ggg", "zzz"));
        R3.addSection(SectionType.EXPERIENCE, new TextSectionWithTitle(SectionType.EXPERIENCE, "ggg", "zzz", "gghh"));
        R1.addSection(SectionType.QUALIFICATIONS, new TextSection(SectionType.QUALIFICATIONS,"Java", "SQL"));
        R1.addOrganizationSection(SectionType.EXPERIENCE,
                new Organization("Organization11", null,
                        new Organization.Period(LocalDate.of(2005, Month.JANUARY, 1), Organization.Period.NOW, "position1", "content1"),
                        new Organization.Period(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2")));
        R1.addOrganizationSection(SectionType.EDUCATION,
                new Organization("Institute", null,
                        new Organization.Period(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                        new Organization.Period(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                new Organization("Organization12", new Link("Organizations1", "http://Organization12.ru")));

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
    public void update(){
        R2.setFullName("Updated N2");
        storage.update(R2);
        assertEquals(R2, storage.load(R2.getUuid()));
    }

    @Test
    public void load() {
        Resume load = storage.load(R1.getUuid());
        System.out.println(R1.getContacts());
        System.out.println(load.getContacts());
        System.out.println(R1.getSections());
        System.out.println(load.getSections());
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
        list.sort(Comparator.naturalOrder());
        assertEquals(list, new ArrayList<>(storage.getAllSorted()));
    }

    @Test
    public void size(){
        assertEquals(3, storage.size());
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

