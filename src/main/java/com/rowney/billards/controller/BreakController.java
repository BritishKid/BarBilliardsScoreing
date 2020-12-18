package com.rowney.billards.controller;

import com.rowney.billards.model.Score;
import com.rowney.billards.service.BreakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class BreakController {

    @Autowired
    private BreakService breakService;

    @RequestMapping("/break/start")
    public String startNewBreak(Model model) throws SQLException {
        breakService.startBreak();
        model.addAttribute("activePlayerName", breakService.getCurrentPlayer());
        model.addAttribute("score", 0);


        return "break";
    }

    ///keep track of current break to remove or add, can also track highest break of the round
    @RequestMapping("/break/addScore={score}")
    public String addToBreak(@PathVariable("score") int score, Model model) throws SQLException {

        int currentBreak = breakService.addCurrentBreak(score);
        model.addAttribute("activePlayerName", breakService.getCurrentPlayer());

        model.addAttribute("score", currentBreak);
        return "break";
    }

    //hit white
    @RequestMapping("/break/void")
    public String voidBreak(Model model) throws SQLException {

        List<Score> scores = breakService.voidCurrentBreak();

        model.addAttribute("scoreboard", scores);
        return "scoreboard";
    }

    //hit black
    @RequestMapping("/break/clear")
    public String clearScoreBreak(Model model) throws SQLException {
        List<Score> scores = breakService.clearScoreBreak();

        model.addAttribute("scoreboard", scores);
        return "scoreboard";
    }

    ///the nextplayer score will show the scoreboard and who is actively playing with options. Need to use ui to find them details of active player boolean
    @RequestMapping("/break/close")
    public String closeBreak(Model model) throws SQLException {

        List<Score> scores = breakService.updateScoreWithBreak();

        model.addAttribute("scoreboard", scores);

        return "scoreboard";
    }
}
