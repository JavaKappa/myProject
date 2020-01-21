package ru.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Капу пк
 * 21.01.2020
 */
public interface SqlExecutor<T> {
    T execute(PreparedStatement ps) throws SQLException;
}
