package ru.webapp.model;

import java.io.Serializable;

/**
 * Капу пк
 * 04.12.2019
 */
public class Link implements Serializable {
    private final String name;
    private final String url;


    public Link() {
        this("", "");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Link other = (Link) obj;
        return (name.equals(other.name)) && url.equals(other.url);
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
