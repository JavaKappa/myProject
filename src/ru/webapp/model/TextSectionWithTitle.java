package ru.webapp.model;


import java.io.Serializable;
import java.util.List;

/**
 * Капу пк
 * 05.12.2019
 */
public class TextSectionWithTitle extends Section implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String comment;

    public TextSectionWithTitle(SectionType sectionType, String title, String comment) {
        this.sectionType = sectionType;
        this.title = title;
        this.comment = comment;
    }
}
