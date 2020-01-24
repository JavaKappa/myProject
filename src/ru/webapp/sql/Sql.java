package ru.webapp.sql;
import ru.webapp.WebAppException;
import org.postgresql.*;
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
//            System.out.println(e);
            throw new WebAppException("sql fail" + sql, e);
        }
    }
}
