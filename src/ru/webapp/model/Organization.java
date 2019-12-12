package ru.webapp.model;

import java.util.Collection;
import java.util.Date;

/**
 * Капу пк
 * 05.12.2019
 */
public class Organization {
    private String companyName;
    private Link organizationWebSite;
    private Collection<Period> periods;

    /**
     * Капу пк
     * 05.12.2019
     */
    public static class Period {
        private Date startDate;
        private Date endDate;
        private String position;
        private String content;
    }
}
