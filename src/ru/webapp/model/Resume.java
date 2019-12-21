package ru.webapp.model;

import java.io.Serializable;
import java.util.*;
import java.util.function.BiConsumer;

/**
 * Капу пк
 * 04.12.2019
 */
public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUID = 1L;

    public static final Resume EMPTY;
    static {
        EMPTY = new Resume();
    }

    public Resume() {

    }

    public String getUuid() {
        return uuid;
    }

    private String uuid;
    private String fullName;
    private String location;
    private String homePage;
    private Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);
    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);


    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Section getSection(SectionType type) {
        return sections.get(type);
    }

    public Resume(String fullName, String location) {
        this(UUID.randomUUID().toString(), fullName, location);
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public void addObjective(String... values) {
        sections.put(SectionType.OBJECTIVE, new TextSection(SectionType.OBJECTIVE, values));
    }

    public void addTextSectionWithTitle(SectionType type, String title, String... values) {
        sections.put(type, new TextSectionWithTitle(type, title, values));
    }
    public Resume(String uuid, String fullName, String location) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.location = location;
    }


    public String getFullName() {
        return fullName;
    }

    public String getHomePage() {
        return homePage;
    }



    public void addSection(SectionType type, Section section) {
        sections.put(type, section);
    }

    public void addContact(ContactType contactType, String value) {
        contacts.put(contactType, value);
    }
    public String getContact(ContactType contactType) {
        return contacts.get(contactType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {

            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Resume other = (Resume) obj;
        if (!this.sections.equals(other.sections)) {
            return false;
        }
        if (!this.contacts.equals(other.contacts)) {
            return false;
        }


        return Objects.equals(hashCode(), obj.hashCode())
                && Objects.equals(this.uuid, other.uuid) &&  Objects.equals(getFullName(), other.getFullName());
    }


    @Override
    public int compareTo(Resume o) {
        int cmp = this.getFullName().compareTo(o.getFullName());
        if (cmp != 0){
            return cmp;
        }
        else{
            return Objects.compare(this.getUuid(), o.getUuid(), Comparator.naturalOrder());
        }
    }


    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }
}
