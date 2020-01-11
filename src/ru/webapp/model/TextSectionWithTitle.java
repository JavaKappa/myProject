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
    private List<String> values;



    public TextSectionWithTitle(SectionType sectionType, String... comments) {
        this.sectionType = sectionType;
        StringBuilder sb = new StringBuilder();
        Arrays.stream(comments).forEach(sb::append);
        value = sb.toString();
    }

    public TextSectionWithTitle(SectionType sectionType) {
        sectionType = SectionType.ACHIEVEMENT;
        this.sectionType = sectionType;
    }

    public TextSectionWithTitle() {
    }

    public TextSectionWithTitle(List<String> values) {
        this.values = values;
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

        return value.equals(other.value);
    }


    @Override
    public String toString() {
        return "TextSectionWithTitle{" +
                ", sectionType=" + sectionType +
                ", value='" + value + '\'' +
                '}';
    }

    public List<String> getValues() {
        return values;
    }
}
