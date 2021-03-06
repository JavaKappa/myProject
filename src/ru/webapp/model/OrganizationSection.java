package ru.webapp.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

/**
 * Капу пк
 * 05.12.2019
 */
public class OrganizationSection  extends TextSection implements Serializable {

    private Collection<Organization> organizations;


    public OrganizationSection(SectionType sectionType, Organization... organizations) {
        this.sectionType = sectionType;
        this.organizations = Arrays.asList(organizations);
    }

    public OrganizationSection() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final OrganizationSection other = (OrganizationSection) obj;
        if (this.sectionType != other.sectionType) {
            return false;
        }
        return Arrays.equals(this.organizations.toArray(), other.organizations.toArray());
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "organizations=" + organizations +
                ", sectionType=" + sectionType +
                '}';
    }

    public Collection<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Collection<Organization> organizations) {
        this.organizations = organizations;
    }
}
