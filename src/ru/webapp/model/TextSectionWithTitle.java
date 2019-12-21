package ru.webapp.model;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Капу пк
 * 05.12.2019
 */
public class TextSectionWithTitle extends Section implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    protected String[] comments;


    public TextSectionWithTitle(SectionType sectionType, String title, String... comments) {
        super(sectionType);
        this.title = title;
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final TextSectionWithTitle other = (TextSectionWithTitle) obj;
        if (this.sectionType != other.sectionType) {
            return false;
        }
        if (!this.title.equals(other.title)) {
            return false;
        }
        if (comments.length != other.comments.length) {
            return false;
        }
        for (int i = 0; i < comments.length; i++) {
            if (!comments[i].equals(other.comments[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "TextSectionWithTitle{" +
                "title='" + title + '\'' +
                ", comments=" + Arrays.toString(comments) +
                ", sectionType=" + sectionType +
                '}';
    }
}
