package ru.webapp.model;

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
}
