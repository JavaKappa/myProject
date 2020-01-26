import ru.webapp.WebAppConfig;

/**
 * Капу пк
 * 23.01.2020
 */
public class SqlStorageTest extends AbstractStorageTest {
    {
        storage = WebAppConfig.get().getStorage();
    }
}
