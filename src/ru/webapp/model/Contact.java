package ru.webapp.model;

public class Contact {
    private final ContactType contactType;
    private final String value;


    public Contact(ContactType contactType, String value) {
        this.contactType = contactType;
        this.value = value;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public String getValue() {
        return value;
    }
}
