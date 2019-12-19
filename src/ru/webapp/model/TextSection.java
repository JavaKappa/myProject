package ru.webapp.model;

import java.io.Serializable;
import java.util.List;

/**
 * Капу пк
 * 05.12.2019
 */
public class TextSection extends Section implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<String> values;

    public TextSection(SectionType sectionType, List<String> values) {
        this.sectionType = sectionType;
        this.values = values;
    }
}
