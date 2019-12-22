package ru.webapp.model;

import java.io.Serializable;

public class Section implements Serializable{
    private static final long serialVersionUID = 1L;
    protected SectionType sectionType;


    @Override
    public String toString() {
        return "Section{" +
                "sectionType=" + sectionType +
                '}';
    }
}
