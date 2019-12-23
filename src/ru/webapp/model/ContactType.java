package ru.webapp.model;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum ContactType {
    PHONE("Номер телефона"),
    MAIL("Электронная почта"),
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
