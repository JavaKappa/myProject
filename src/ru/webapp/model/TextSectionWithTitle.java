package ru.webapp.model;


import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Капу пк
 * 05.12.2019
 */
public class TextSectionWithTitle extends TextSection implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;



    public TextSectionWithTitle(SectionType sectionType, String title, String... comments) {
        this.sectionType = sectionType;
        this.title = title;
        StringBuilder sb = new StringBuilder();
        Arrays.stream(comments).forEach(sb::append);
        value = sb.toString();
    }

    public TextSectionWithTitle(SectionType sectionType) {
        this.sectionType = sectionType;
    }

    public TextSectionWithTitle() {
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
        return value.equals(other.value);
    }


    @Override
    public String toString() {
        return "TextSectionWithTitle{" +
                "title='" + title + '\'' +
                ", sectionType=" + sectionType +
                ", value='" + value + '\'' +
                '}';
    }
}
