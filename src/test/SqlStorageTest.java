import ru.webapp.storage.SqlStorage;

/**
 * Капу пк
 * 23.01.2020
 */
public class SqlStorageTest extends AbstractTest {
    {
        storage = new SqlStorage("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
    }
}
