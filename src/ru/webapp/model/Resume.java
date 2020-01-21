package ru.webapp.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.*;


/**
 * Капу пк
 * 04.12.2019
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUID = 1L;
    private String uuid;
    private String fullName;
    private String location;
    private String homePage;
    private Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);
    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);

    public Resume(String uuid, String fullName, String location, String homePage) {
        Objects.requireNonNull(fullName, "uuid is null");
        Objects.requireNonNull(fullName, "fullname is null");
        Objects.requireNonNull(fullName, "location is null");
        Objects.requireNonNull(fullName, "homePage is null");
        this.uuid = uuid;
        this.fullName = fullName;
        this.location = location;
        this.homePage = homePage;
    }


    public Resume() {
        uuid = UUID.randomUUID().toString();
        fullName = "without name";
        location = "without location";
        homePage = "without homePage";
    }

    public Resume(String fullName, String location) {
        this(UUID.randomUUID().toString(), fullName, location, "");
    }

    public Resume(String fullName, String location, String homePage) {
        this(UUID.randomUUID().toString(), fullName, location, homePage);
    }

    public String getUuid() {
        return uuid;
    }
    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Section getSection(SectionType type) {
        return sections.get(type);
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

    public void addObjective(String value) {
        sections.put(SectionType.OBJECTIVE, new TextSection(SectionType.OBJECTIVE, value));
    }

    public void addTextSectionWithTitle(SectionType type, String title, String... values) {
        sections.put(type, new TextSectionWithTitle(type, title, values.toString()));
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

    public void addOrganizationSection(SectionType type, Organization... organizations) {
        addSection(type, new OrganizationSection(type, organizations));
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
        if (this.sections != null && other.sections != null) {
            if (!this.sections.equals(other.sections)) {
                return false;
            }
        }
        if (this.contacts != null && other.contacts != null) {
            if (!this.contacts.equals(other.contacts)) {
                return false;
            }
        }


        return Objects.equals(hashCode(), obj.hashCode())
                && Objects.equals(this.uuid, other.uuid) &&
                Objects.equals(getFullName(), other.getFullName());
    }


    @Override
    public int compareTo(Resume o) {
        Objects.requireNonNull(o);
        if (this.getFullName() == null) {
            throw new IllegalStateException();
        } else if (o.getFullName() == null) {
            throw new IllegalStateException();
        }
        int cmp = this.getFullName().compareTo(o.getFullName());
        if (cmp == 0) {
            return Objects.compare(this.getUuid(), o.getUuid(), Comparator.naturalOrder());
        } else {
            return cmp;
        }
    }


    public Map<SectionType, Section> getSections() {
        return sections;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", location='" + location + '\'' +
                ", homePage='" + homePage + '\'' +
                ", sections=" + sections +
                ", contacts=" + contacts +
                '}';
    }

    public String getLocation() {
        return location;
    }

    public void removeContact(ContactType type) {
        contacts.remove(type);
    }
}
