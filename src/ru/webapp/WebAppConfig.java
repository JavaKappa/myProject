package ru.webapp;

import ru.webapp.storage.IStorage;
import ru.webapp.storage.SerializeFileStorage;

import java.io.IOException;
import java.io.InputStream;

/**
 * Капу пк
 * 09.01.2020
 */
public class WebAppConfig {
    public static final WebAppConfig INSTANCE = new WebAppConfig();

    private IStorage storage;

    public static WebAppConfig get() {
        return INSTANCE;
    }

    public IStorage getStorage() {
        return storage;
    }

    private WebAppConfig() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("logging.properties")) {
            storage = new SerializeFileStorage("C:\\Users\\qwark\\IdeaProjects\\myProject\\file_storage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
