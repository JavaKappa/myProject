package ru.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
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
