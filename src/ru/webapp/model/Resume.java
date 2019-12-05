package ru.webapp.model;

import java.util.Collection;

/**
 * Капу пк
 * 04.12.2019
 */
public class Resume {
    private String fullName;
    private String location;
    private String homePage;
    private Collection<Section> sections;
    private Collection<Contact> contacts;

    public String getFullName() {
        return fullName;
    }

    public String getHomePage() {
        return homePage;
    }
}
