package ru.webapp.model;

import java.util.Collection;
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
    private Collection<Section> sections;
    private Collection<Contact> contacts;

    public Resume(String fullName, String location) {
        this(UUID.randomUUID().toString(), fullName, location);
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

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public void addContacts(Contact contact) {
        contacts.add(contact);
    }
}
