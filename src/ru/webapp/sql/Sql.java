package ru.webapp.sql;
import ru.webapp.WebAppException;

import java.sql.*;

public class Sql {
    private final ConnectionFactory connectionFactory;

    public Sql(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new WebAppException("Driver not found", e);
        }
    }

    public void execute(String sql) {
        execute(sql, (SqlExecutor<Void>) ps -> {
            ps.execute();
            return null;
        });
    }

    public <T> T execute(String sql, SqlExecutor<T> executor) {

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return executor.execute(ps);
        } catch (SQLException e) {
            throw new WebAppException("SQL failed: " + sql, e);
        }
    }

    public <T> T execute(SqlTransaction<T> transaction) {
        try (Connection conn = connectionFactory.getConnection()){
            try {
                conn.setAutoCommit(false);
                T res = transaction.execute(conn);
                conn.commit();
                return res;
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new WebAppException("Transaction failed", e);
        }
    }
}
