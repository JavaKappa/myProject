package ru.webapp.sql;

import ru.webapp.WebAppException;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql {
    private final ConnectionFactory connectionFactory;

    public Sql(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String sql) {
        try (Connection conn = connectionFactory.getConnection()) {
            conn.prepareStatement(sql);
        } catch (SQLException e) {
            throw new WebAppException("SQL failed", e);
        }
    }
}
