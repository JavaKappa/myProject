package ru.webapp.model;

import java.io.Serializable;

public abstract class Section implements Serializable{
    protected SectionType sectionType;
    protected String[] comments;

    public Section(String... comments) {
        this.comments = comments;
    }
}
