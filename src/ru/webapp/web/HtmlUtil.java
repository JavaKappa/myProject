package ru.webapp.web;

import ru.webapp.model.ContactType;
import ru.webapp.model.Resume;

public class HtmlUtil {
    public static String getContact(Resume resume, ContactType type) {
        String contact = resume.getContact(type);
        return contact == null ? "nbsp" : type.toHtml(contact);
    }
}
