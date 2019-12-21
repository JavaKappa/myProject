package ru.webapp.model;

import java.io.Serializable;

public abstract class Section implements Serializable{
    private static final long serialVersionUID = 1L;
    protected SectionType sectionType;


    public Section(SectionType sectionType) {
        this.sectionType = sectionType;
    }
}
