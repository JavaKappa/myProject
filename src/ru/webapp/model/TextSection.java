package ru.webapp.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Капу пк
 * 05.12.2019
 */

public class TextSection extends Section implements Serializable {
    private static final long serialVersionUID = 1L;
    protected SectionType sectionType;


    public TextSection(SectionType sectionType, String value) {
        this.sectionType = sectionType;
        this.value = value;
    }

    public TextSection(String value){
        sectionType = SectionType.OBJECTIVE;
        this.value = value;
    }
    public TextSection() {
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final TextSection other = (TextSection) obj;
        if (this.sectionType != other.sectionType) {
            return false;
        }
        return value.equals(other.value);
    }


    @Override
    public String toString() {
        return "TextSection{" +
                "value='" + value + '\'' +
                ", sectionType=" + sectionType +
                '}';
    }
}
