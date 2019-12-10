package ru.webapp.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

/**
 * Капу пк
 * 04.12.2019
 */
public class Resume {
    public String getUuid() {
        return uuid;
    }

    private String uuid;
    private String fullName;
    private String location;
    private String homePage;
    private Collection<Section> sections = new ArrayList<>();
    private Collection<Contact> contacts = new ArrayList<>();

    public Resume(String fullName, String location) {
        this(UUID.randomUUID().toString(), fullName, location);
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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



    public void addSection(Section section) {
        sections.add(section);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, location, homePage, sections, contacts);
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
        return Objects.equals(this.uuid, other.uuid)
                && Objects.equals(this.fullName, other.fullName)
                && Objects.equals(this.location, other.location)
                && Objects.equals(this.homePage, other.homePage)
                && Objects.equals(this.sections, other.sections)
                && Objects.equals(this.contacts, other.contacts);
    }


}
