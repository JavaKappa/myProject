package ru.webapp.storage;

import ru.webapp.WebAppException;
import ru.webapp.model.Resume;
import ru.webapp.sql.Sql;
import ru.webapp.sql.SqlExecutor;

import java.sql.*;
import java.util.Collection;

/**
 * Капу пк
 * 18.01.2020
 */
public class SqlStorage implements IStorage {
    private Sql sql;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sql = new Sql(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void save(Resume resume) {

    }

    @Override
    public void update(Resume resume) throws WebAppException {

    }

    @Override
    public Resume load(final String uuid) {
        sql.execute("SELECT * FROM resume r WHERE r.uuid = ?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) throw new WebAppException("Resume " + uuid + " does not exists");

            String fullname = rs.getString("full_name");
            String location = rs.getString("location");
            String homePage = rs.getString("home_page");
            Resume resume = new Resume(uuid, fullname, location, homePage);
            return resume;
        });

        return null;
    }

    @Override
    public void delete(String uuid) throws WebAppException {

    }

    @Override
    public void clear() {
        sql.execute("DELETE FROM resume");
    }

    @Override
    public Collection<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isSectionSupported() {
        return false;
    }
}
