package com.rowney.billards.dao;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class PlayerDao extends CommonDao {

    private Statement statement;

    public void addNewPlayer(String playerName) throws SQLException {
        statement = createConnection();

        String sql = String.format("INSERT INTO SCORES (playername, score, ISACTIVE) VALUES ('%s', 0, false)", playerName);

        statement.executeUpdate(sql);
    }

    public void clearPlayers() {

    }

    public List<String> getAllPlayers() {
        return null;
    }
}
