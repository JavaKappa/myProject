package ru.webapp.model;

import util.LocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Objects;

/**
 * Капу пк
 * 05.12.2019
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private String companyName;
    private Link organizationWebSite;
    private Period[] periods = new Period[0];

    public Organization(String companyName, Link organizationWebSite, Period... periods) {
        this.companyName = companyName;
        this.organizationWebSite = organizationWebSite;
        this.periods = periods;

    }

    public Organization() {
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final Organization other = (Organization) obj;

        if (this.companyName != null && !this.companyName.equals(other.companyName)) {
            return false;
        }
        if (this.organizationWebSite != null && !this.organizationWebSite.equals(other.organizationWebSite)) {
            return false;
        }
        return Arrays.equals(periods, other.periods);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "companyName='" + companyName + '\'' +
                ", organizationWebSite=" + organizationWebSite +
                ", periods=" + Arrays.toString(periods) +
                '}';
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Link getOrganizationWebSite() {
        return organizationWebSite;
    }

    public void setOrganizationWebSite(Link organizationWebSite) {
        this.organizationWebSite = organizationWebSite;
    }

    public Period[] getPeriods() {
        return periods;
    }

    public void setPeriods(Period[] periods) {
        this.periods = periods;
    }

    /**
     * Капу пк
     * 05.12.2019
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Period implements Serializable {
        public static final LocalDate NOW = LocalDate.of(3000, 1, 1);
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;
        private String position;
        private String content;

        public Period() {
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            final Period other = (Period) obj;
            if (!Objects.equals(this.startDate, other.startDate)) return false;
            if (!Objects.equals(this.endDate, other.endDate)) return false;
            if (!Objects.equals(this.content, other.content)) return false;
            return Objects.equals(this.position, other.position);

        }

        public Period(int yearStart, Month monthStart, int yearEnd, Month monthEnd, String position, String content) {
            startDate = LocalDate.of(yearStart, monthStart, 1);
            endDate = LocalDate.of(yearEnd, monthEnd, 1);
            this.position = position;
            this.content = content;
        }

        public Period(LocalDate startDate, LocalDate endDate, String position, String content) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.position = position;
            this.content = content;
        }

        @Override
        public String toString() {
            return "Period{" +
                    "startDate=" + startDate +
                    ", endDate=" + endDate +
                    ", position='" + position + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}
