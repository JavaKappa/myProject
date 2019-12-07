package ru.webapp.model;

import java.util.List;

/**
 * Капу пк
 * 05.12.2019
 */
public class TextSection extends Section {
    private List<String> values;

    public TextSection(SectionType sectionType, List<String> values) {
        this.sectionType = sectionType;
        this.values = values;
    }
}
