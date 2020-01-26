package ru.webapp.sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Капу пк
 * 26.01.2020
 */
public interface SqlTransaction<T> {
    T execute(Connection conn)throws SQLException;
}
