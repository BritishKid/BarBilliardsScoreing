package com.rowney.billards.service;

import com.rowney.billards.dao.ScoreboardDao;
import com.rowney.billards.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ScoreboardService {

    @Autowired
    private ScoreboardDao scoreboardDao;

    @Autowired
    private PlayerService playerService;

    public List<Score> getScoreboard() throws SQLException {
        List<Score> scoreboards = scoreboardDao.getScores();

        return scoreboards;
    }

    //used at end of a break
    public void updateScore(int score, int playerId) throws SQLException {

        scoreboardDao.updateScore(score, playerId);
    }

    public void updateScore(int score) throws SQLException {

        scoreboardDao.updateActiveScore(score);
    }

    public void addToScore(int score) throws SQLException {

        scoreboardDao.addToScore(score);
    }

    public void resetScore() throws SQLException {
        scoreboardDao.resetScores();
    }

    public List<Score> resetScoreSamePlayers() throws SQLException {
        int numberOfPlayers = scoreboardDao.getScores().size();

        for (int i = 1; i <= numberOfPlayers; i++) {
            updateScore(0, i);
        }

        return  scoreboardDao.getScores();
    }


    public List<Score> updateTurn(List<Score> scores) throws SQLException {
        boolean isUpdated = false;

        for (int i = 0; i < scores.size()-1; i++) {
            Score score = scores.get(i);
                if (score.isActivePlayer() && !isUpdated) {
                    score.setActivePlayer(false);
                    scores.get(i+1).setActivePlayer(true);

                    scoreboardDao.updateActivePlayer(true, scores.get(i+1).getPlayerId());
                    scoreboardDao.updateActivePlayer(false, scores.get(i).getPlayerId());

                    isUpdated = true;
            }
        }

        if (!isUpdated) {
            if(scores.get(scores.size()-1).isActivePlayer()) {
                scoreboardDao.updateActivePlayer(false, scores.get(scores.size()-1).getPlayerId());
            }
            scores.get(0).setActivePlayer(true);
            scoreboardDao.updateActivePlayer(true, scores.get(0).getPlayerId());
        }

        return scores;
    }

    public String getActivePlayer() throws SQLException {
        return scoreboardDao.getActivePlayer();
    }
}
