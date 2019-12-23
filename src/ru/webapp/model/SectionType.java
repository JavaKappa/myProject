package ru.webapp.model;

import javax.xml.bind.annotation.XmlEnum;

public enum SectionType {
    OBJECTIVE("Позиция"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт"),
    EDUCATION("Образование");

    private String title;


    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
