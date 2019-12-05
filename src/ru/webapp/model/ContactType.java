package ru.webapp.model;

public enum ContactType {
    PHONE_NUMBER("Номер телефона"),
    EMAIL ("Электронная почта"),
    SKYPE("Skype"),
    HOME_ADDRESS("Домашний адрес");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
