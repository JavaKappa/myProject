package ru.webapp.storage;

import ru.webapp.WebAppException;
import ru.webapp.model.ContactType;
import ru.webapp.model.Resume;
import ru.webapp.sql.Sql;
import ru.webapp.sql.SqlExecutor;
import ru.webapp.sql.SqlTransaction;
import ru.webapp.util.Util;

import java.sql.*;
import java.util.*;

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
        sql.execute(conn -> {
            try (PreparedStatement statementResume = conn.prepareStatement("INSERT INTO resume (uuid, full_name, location, home_page) VALUES (?,?,?,?)")) {
                statementResume.setString(1, resume.getUuid());
                statementResume.setString(2, resume.getFullName());
                statementResume.setString(3, resume.getLocation());
                statementResume.setString(4, resume.getHomePage());
                statementResume.executeUpdate();
                SqlStorage.this.insertContact(conn, resume);
                return null;
            }
        });
    }

    @Override
    public Resume load(final String uuid) {
        return sql.execute("SELECT *\n" +
                        "       FROM resume r\n" +
                        "       LEFT JOIN contact c ON c.resume_uuid=r.uuid \n" +
                        "       WHERE r.uuid=?",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) throw new WebAppException("Resume does not exist");
                    Resume r = new Resume(uuid, rs.getString("full_name"), rs.getString("location"), rs.getString("home_page"));
                    addContact(rs, r);
                    while (rs.next()) {
                        addContact(rs, r);
                    }
                    return r;
                });
    }

    private void addContact(ResultSet rs, Resume r) throws SQLException {
        String type = rs.getString("type");
        if (!Util.isEmpty(type)) {
            r.addContact(ContactType.valueOf(type), rs.getString("value"));
        }
    }

    @Override
    public void update(Resume resume) throws WebAppException {
        sql.execute(conn -> {
            try (PreparedStatement statementResume = conn.prepareStatement("UPDATE resume SET full_name=?, location=?,home_page=? WHERE uuid=?")) {
                statementResume.setString(1, resume.getFullName());
                statementResume.setString(2, resume.getLocation());
                statementResume.setString(3, resume.getHomePage());
                statementResume.setString(4, resume.getUuid());
                if (statementResume.executeUpdate() == 0) throw new WebAppException("Resume  does not exist");
            }
            deleteContacts(conn, resume);
            SqlStorage.this.insertContact(conn, resume);
            return null;
        });
    }

    @Override
    public void delete(String uuid) throws WebAppException {
        sql.execute("DELETE FROM resume WHERE uuid = ?", (SqlExecutor<Void>) ps -> {
            ps.setString(1, uuid);
            int countOChanges = ps.executeUpdate();
            if (countOChanges == 0) throw new WebAppException("Resume " + uuid + " does not exists");
            return null;
        });
    }

    @Override
    public void clear() {
        sql.execute("DELETE FROM resume");
    }

    @Override
    public Collection<Resume> getAllSorted() {
        return sql.execute("SELECT * FROM resume r LEFT JOIN contact c ON r.uuid=c.resume_uuid ORDER BY full_name, uuid", new SqlExecutor<Collection<Resume>>() {
            @Override
            public Collection<Resume> execute(PreparedStatement ps) throws SQLException {
                Map<String, Resume> map = new LinkedHashMap<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    Resume r = map.get(uuid);
                    if (r == null) {
                        r = new Resume(uuid, rs.getString("full_name"), rs.getString("location"), rs.getString("home_page"));
                        map.put(uuid, r);
                    }
                    addContact(rs, r);
                }
                return map.values();
            }
        });
    }

    @Override
    public int size() {
        return sql.execute("SELECT count(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        });
    }

    private void insertContact(Connection conn, Resume resume) throws SQLException {
        String uuid = resume.getUuid();
        try (PreparedStatement statementContact = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                statementContact.setString(1, resume.getUuid());
                statementContact.setString(2, entry.getKey().name());
                statementContact.setString(3, entry.getValue());
                statementContact.addBatch();
            }
            statementContact.executeBatch();
        }
    }

    private void deleteContacts(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid = ?")) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }

    @Override
    public boolean isSectionSupported() {
        return false;
    }
}
