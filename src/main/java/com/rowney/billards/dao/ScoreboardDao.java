package com.rowney.billards.dao;

import com.rowney.billards.model.Score;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScoreboardDao extends CommonDao {

    private Statement statement;

    public List<Score> getScores() throws SQLException {
        statement = createConnection();
        String sql = "SELECT * FROM SCORES";

        ResultSet resultSet = statement.executeQuery(sql);

        List<Score> scoreboards = new ArrayList<>();

        while (resultSet.next()){
            Score scoreboard = new Score();
            scoreboard.setPlayerId(resultSet.getInt("playerId"));
            scoreboard.setPlayerName(resultSet.getString("playerName"));
            scoreboard.setScore(resultSet.getInt("score"));
            scoreboard.setActivePlayer(resultSet.getBoolean("isActive"));
            scoreboards.add(scoreboard);
        }

        return scoreboards;
    }

    public void resetScores() throws SQLException {
        statement = createConnection();
        String sql = "TRUNCATE TABLE SCORES";

        statement.executeUpdate(sql);
    }

    public void updateScore(int score, int playerId) throws SQLException {
        statement = createConnection();
        String sql = String.format("UPDATE SCORES SET SCORE = %s WHERE PLAYERID = %s", score, playerId);

        statement.executeUpdate(sql);
    }

    public void updateActiveScore(int score) throws SQLException {
        statement = createConnection();
        String sql = String.format("UPDATE SCORES SET SCORE = %s WHERE ISACTIVE = true", score);

        statement.executeUpdate(sql);
    }

    public void updateActivePlayer(boolean isActive, int playerId) throws SQLException {
        statement = createConnection();
        String sql = String.format("UPDATE SCORES SET ISACTIVE = '%s' WHERE PLAYERID = %s", isActive, playerId);

        statement.executeUpdate(sql);
    }

    public void addToScore(int score) throws SQLException {
        statement = createConnection();

        String sql = String.format("UPDATE SCORES SET SCORE = SCORE + %s WHERE ISACTIVE = true", score);

        statement.executeUpdate(sql);
    }

    public String getActivePlayer() throws SQLException {
        statement = createConnection();

        String sql = "SELECT PLAYERNAME FROM SCORES WHERE ISACTIVE = true";


        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.next();

        return resultSet.getString("playername");
    }
}
