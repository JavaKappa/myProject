package ru.webapp.model;

/**
 * Капу пк
 * 04.12.2019
 */
public class Link {
    private final String name;
    private final String url;

    public Link() {
        this("", null);
    }

    @Override
    public String toString() {
        return "name = " + name + ", url = " + url;
    }

    public Link(String name, String url) {
        this.name = name;
        this.url = url;
    }


    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
