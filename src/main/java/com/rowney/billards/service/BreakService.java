package com.rowney.billards.service;

import com.rowney.billards.dao.BreakDao;
import com.rowney.billards.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BreakService {

    @Autowired
    private BreakDao breakDao;

    @Autowired
    private ScoreboardService scoreboardService;

    public int addCurrentBreak(int score) throws SQLException {

        breakDao.addToCurrentBreak(score);
        return getCurrentBreak();
    }

    public List<Score> voidCurrentBreak() throws SQLException {
        breakDao.voidCurrentBreak();
        return updateTurn();
    }

    public List<Score> clearScoreBreak() throws SQLException {
        breakDao.voidCurrentBreak();
        scoreboardService.updateScore(0);
        return updateTurn();
    }

    public List<Score> updateScoreWithBreak() throws SQLException {
        int currentBreak = breakDao.getCurrentBreak();
        breakDao.closeBreak();
        scoreboardService.addToScore(currentBreak);
        return updateTurn();

    }

    public int getCurrentBreak() throws SQLException {
        return breakDao.getCurrentBreak();
    }


    private List<Score> updateTurn() throws SQLException {
        List<Score> scoreboard = scoreboardService.getScoreboard();
        return scoreboardService.updateTurn(scoreboard);
    }

    public String getCurrentPlayer() throws SQLException {

        return scoreboardService.getActivePlayer();
    }

    public void startBreak() throws SQLException {

        breakDao.startBreak();
    }
}
