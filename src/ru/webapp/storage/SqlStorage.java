package ru.webapp.storage;

import ru.webapp.WebAppException;
import ru.webapp.model.ContactType;
import ru.webapp.model.Resume;
import ru.webapp.sql.Sql;
import ru.webapp.sql.SqlExecutor;
import ru.webapp.sql.SqlTransaction;

import java.nio.file.WatchEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

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
        sql.execute(new SqlTransaction<Object>() {
            @Override
            public Object execute(Connection conn) throws SQLException {
                try (PreparedStatement statementResume = conn.prepareStatement("INSERT INTO resume (uuid, full_name, location, home_page) VALUES (?,?,?,?)")) {
                    statementResume.setString(1, resume.getUuid());
                    statementResume.setString(2, resume.getFullName());
                    statementResume.setString(3, resume.getLocation());
                    statementResume.setString(4, resume.getHomePage());
                    statementResume.executeUpdate();
                }
                try (PreparedStatement statementContact = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
                    for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                        statementContact.setString(1, resume.getUuid());
                        statementContact.setString(2, entry.getKey().getTitle());
                        statementContact.setString(3, entry.getValue());
                        statementContact.executeUpdate();
                    }
                }
                conn.commit();
                return null;
            }
        });
//        sql.execute("INSERT INTO resume (uuid, full_name, location, home_page) VALUES (?,?,?,?)", new SqlExecutor<Void>() {
//            @Override
//            public Void execute(PreparedStatement ps) throws SQLException {
//                ps.setString(1, resume.getUuid());
//                ps.setString(2, resume.getFullName());
//                ps.setString(3, resume.getLocation());
//                ps.setString(4, resume.getHomePage());
//                ps.execute();
//                return null;
//            }
//        });
    }

    @Override
    public void update(Resume resume) throws WebAppException {
        sql.execute("UPDATE resume SET full_name=?, location=?,home_page=? WHERE uuid=?", new SqlExecutor<Object>() {
            @Override
            public Object execute(PreparedStatement ps) throws SQLException {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getLocation());
                ps.setString(3, resume.getHomePage());
                ps.setString(4, resume.getUuid());
                int countOfChanges = ps.executeUpdate();
                if (countOfChanges == 0) throw new WebAppException("Resume does not exist");
                return null;
            }
        });
    }

    @Override
    public Resume load(final String uuid) {
        return sql.execute("SELECT * FROM resume r WHERE r.uuid = ?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            //QUESTION ABOUT THIS
            if (!rs.next()) throw new WebAppException("Resume " + uuid + " does not exists");

            String fullname = rs.getString("full_name");
            String location = rs.getString("location");
            String homePage = rs.getString("home_page");
            return new Resume(uuid, fullname, location, homePage);
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
        return sql.execute("SELECT * FROM resume ORDER BY full_name, uuid", ps -> {
            ResultSet rs = ps.executeQuery();
            Collection<Resume> resumes = new ArrayList<>();
            while (rs.next()) {
                String uuid = rs.getString("uuid");
                String fullname = rs.getString("full_name");
                String location = rs.getString("location");
                String homePage = rs.getString("home_page");
                resumes.add(new Resume(uuid, fullname, location, homePage));
            }

            return resumes;
        });
    }

    @Override
    public int size() {
        return sql.execute("SELECT count(*) FROM resume", new SqlExecutor<Integer>() {
            @Override
            public Integer execute(PreparedStatement ps) throws SQLException {
                ResultSet rs = ps.executeQuery();
                rs.next();
                return rs.getInt(1);
            }
        });
    }

    @Override
    public boolean isSectionSupported() {
        return false;
    }
}
