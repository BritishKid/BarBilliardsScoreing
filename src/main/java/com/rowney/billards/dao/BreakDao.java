package com.rowney.billards.dao;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class BreakDao extends CommonDao{

    private Statement statement;

    public void addToCurrentBreak(int score) throws SQLException {
        statement = createConnection();

        String sql = String.format("UPDATE BREAK SET BREAKSCORE = BREAKSCORE + %s WHERE CURRENT = true", score);

        statement.executeUpdate(sql);
    }

    public int getCurrentBreak() throws SQLException {
        statement = createConnection();

        String sql = "SELECT * FROM BREAK WHERE CURRENT = true";

        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.next();
        return resultSet.getInt("breakscore");
    }

    public void voidCurrentBreak() throws SQLException {
        statement = createConnection();

        String sql = "UPDATE BREAK SET BREAKSCORE = 0, CURRENT = false WHERE CURRENT = true";

        statement.executeUpdate(sql);

    }

    public void closeBreak() throws SQLException {
        statement = createConnection();

        String sql = "UPDATE BREAK SET CURRENT = false WHERE CURRENT = true";

        statement.executeUpdate(sql);
    }

    public void startBreak() throws SQLException {
        statement = createConnection();

        String sql = "INSERT INTO BREAK VALUES (0, true)";

        statement.executeUpdate(sql);
    }
}
