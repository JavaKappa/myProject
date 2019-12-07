package ru.webapp.model;


import java.util.List;

/**
 * Капу пк
 * 05.12.2019
 */
public class TextSectionWithTitle extends Section {
    private String title;
    private String comment;

    public TextSectionWithTitle(SectionType sectionType, String title, String comment) {
        this.sectionType = sectionType;
        this.title = title;
        this.comment = comment;
    }
}
