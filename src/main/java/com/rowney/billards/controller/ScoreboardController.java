package com.rowney.billards.controller;

import com.rowney.billards.model.Score;
import com.rowney.billards.service.ScoreboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ScoreboardController {

    @Autowired
    private ScoreboardService scoreboardService;

    //get the current scoreboard
    @RequestMapping("/scoreboard")
    public String getScoreboard(Model model) throws SQLException {

        List<Score> scoreboards = scoreboardService.getScoreboard();
        model.addAttribute("scoreboard", scoreboards);

        return "scoreboard";
    }

    //clear db and reset game state
    @RequestMapping("/reset/blank")
    public String resetScoreboard() throws SQLException {
        scoreboardService.resetScore();
        return "newplayer";
    }

    //clear scores to play again with same players
    @RequestMapping("/reset/rematch")
    public String resetScoreboardSamePlayers(Model model) throws SQLException {
        List<Score> scores = scoreboardService.resetScoreSamePlayers();

        model.addAttribute("scoreboard", scores);
        return "scoreboard";
    }

    //initialises game state by taking players into the scoreboard
    //todo create playercontroller and have players added 1 at a time
    @RequestMapping("/start")
    public String startGame(Model model) throws SQLException {

        List<Score> scoreboard = scoreboardService.getScoreboard();
        scoreboard = scoreboardService.updateTurn(scoreboard);

        model.addAttribute("scoreboard", scoreboard);
        return "scoreboard";
    }
}
