package ru.webapp.model;

import java.io.Serializable;

/**
 * Капу пк
 * 05.12.2019
 */
public class TextSection extends Section implements Serializable {
    private static final long serialVersionUID = 1L;

    public TextSection(SectionType sectionType, String... comments) {
        super(comments);
        this.sectionType = sectionType;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Section other = (Section) obj;
        if (this.sectionType != other.sectionType) {
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
}
