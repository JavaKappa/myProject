package ru.webapp;

import ru.webapp.storage.IStorage;
import ru.webapp.storage.SerializeFileStorage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.LogManager;

/**
 * Капу пк
 * 09.01.2020
 */
public class WebAppConfig {
    public static final WebAppConfig INSTANCE = new WebAppConfig();

    private IStorage storage;
    private Properties properties;

    public static WebAppConfig get() {
        return INSTANCE;
    }

    public IStorage getStorage() {
        return storage;
    }

    private WebAppConfig() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("logging.properties");
             InputStream webAppProp = getClass().getClassLoader().getResourceAsStream("webapp.properties")) {
            LogManager.getLogManager().readConfiguration(is);
            properties = new Properties();
            if (webAppProp == null) {
                throw new WebAppException("webapp.properties doest not exist");
            }
            properties.load(webAppProp);
            storage = new SerializeFileStorage(properties.getProperty("storage.dir"));
            properties.getProperty("db.url");
            properties.getProperty("db.user");
            properties.getProperty("db.password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
